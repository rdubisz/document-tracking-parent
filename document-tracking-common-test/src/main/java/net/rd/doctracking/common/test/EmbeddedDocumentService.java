package net.rd.doctracking.common.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class EmbeddedDocumentService {

    public static final String DOCKER_IMAGE = "document-tracking-parent-backend-service:latest";

    private static final Logger log = LoggerFactory.getLogger(EmbeddedDocumentService.class);

    private GenericContainer<?> container;
    private final MariaDBContainer<?> mariaDbContainer;
    private final Network network;

    public EmbeddedDocumentService(
            final Network network,
            final MariaDBContainer<?> mariaDbContainer) {
        this.network = network;
        this.mariaDbContainer = mariaDbContainer;
    }

    public void start() {
        createContainer();
    }

    protected void createContainer() {
        try {
            container = new GenericContainer(DockerImageName.parse(DOCKER_IMAGE))
                    .withNetwork(network)
                    .dependsOn(mariaDbContainer)
                    .withExposedPorts(8080)
                    //.waitingFor(Wait.forHttp("/about").forStatusCode(200));
                    .withNetworkAliases("document-tracking-service");
            container.start();
            log.info("document-tracking-service started");
        } catch (Exception e) {
            log.error("Could not start document-tracking-service", e);
        }
    }
    public void stop() {
        if (container != null && container.isRunning())
            container.stop();
    }

    public String getContainerIp() {
        return container != null ? container.getHost() : null;
    }

    public GenericContainer<?> getContainer() {
        return container;
    }

}
