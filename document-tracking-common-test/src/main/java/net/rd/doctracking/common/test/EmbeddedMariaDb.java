package net.rd.doctracking.common.test;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.Network;

import javax.sql.DataSource;

public class EmbeddedMariaDb {

    public static final String DOCKER_IMAGE = "mariadb:12.0.2";
    public static final String DEFAULT_USER = "dbuser";
    public static final String DEFAULT_PASSWORD = "dbsecret";
    public static final String DEFAULT_DBNAME = "doctracking";

    private static final Logger log = LoggerFactory.getLogger(EmbeddedMariaDb.class);

    private final String username;
    private final String password;
    private final String dbName;

    protected DataSource dataSource;
    protected GenericObjectPool<PoolableConnection> connectionPool;

    protected DSLContext ctx;

    private MariaDBContainer<?> container;
    private final Network network;

    public EmbeddedMariaDb() {
        this(DEFAULT_USER, DEFAULT_PASSWORD, DEFAULT_DBNAME, null);
    }

    public EmbeddedMariaDb(
            final String username,
            final String password,
            final String dbName,
            final Network network) {
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.network = network;
    }

    public static GenericObjectPool<PoolableConnection> createObjectPool(
            final String url,
            final String username,
            final String password) {
        var connectionFactory = new DriverManagerConnectionFactory(url, username, password);

        var poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        poolableConnectionFactory.setValidationQuery("SELECT 1");

        final GenericObjectPoolConfig<PoolableConnection> config = new GenericObjectPoolConfig<>();
        config.setTestOnBorrow(true);
        config.setMaxTotal(10);

        var objectPool = new GenericObjectPool<>(poolableConnectionFactory, config);
        poolableConnectionFactory.setPool(objectPool);
        return objectPool;
    }

    public void start() {
        createEmbeddedMariaDb();
        schemaVersioning();
        jooqDslContext();
    }

    protected void createEmbeddedMariaDb() {
        try {
            container = new MariaDBContainer<>(DOCKER_IMAGE)
                    .withUsername(username)
                    .withPassword(password)
                    .withDatabaseName(dbName)
                    .withNetwork(network)
                    .withExposedPorts(3306)
                    .withNetworkAliases("db")
                    .withEnv("MARIADB_ROOT_PASSWORD", password)
                    .withEnv("MARIADB_USER", username)
                    .withEnv("MARIADB_PASSWORD", password)
            ;
            container.start();
            log.info("MariaDB started: {} as {}", container.getJdbcUrl(), username);

            connectionPool = createObjectPool(container.getJdbcUrl(), container.getUsername(), container.getPassword());
            dataSource = new PoolingDataSource<>(connectionPool);
            log.debug("MariaDB pooling data source created");
        } catch (Exception e) {
            log.error("Could not start MariaDB", e);
        }
    }

    protected void schemaVersioning() {
        var fw = Flyway.configure()
                .dataSource(dataSource)
                .createSchemas(true)
                .load();

        var migrateResult = fw.migrate();
        log.info("Flyway success: {}, migrated: {}", migrateResult.success, migrateResult.migrationsExecuted);
    }

    protected void jooqDslContext() {
        ctx = new DefaultDSLContext(dataSource, SQLDialect.MARIADB);
    }

    public void stop() {
        if (container != null && container.isRunning())
            container.stop();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDbName() {
        return dbName;
    }

    public String getJdbcUrl() {
        return container != null ? container.getJdbcUrl() : null;
    }

    public String getContainerIp() {
        return container != null ? container.getHost() : null;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public GenericObjectPool<PoolableConnection> getConnectionPool() {
        return connectionPool;
    }

    public DSLContext getCtx() {
        return ctx;
    }

    public MariaDBContainer<?> getContainer() {
        return container;
    }
}
