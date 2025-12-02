package net.rd.doctracking.service;

import net.rd.doctracking.common.test.EmbeddedMariaDb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmbeddedMariaDbTest {

    private final EmbeddedMariaDb db = new EmbeddedMariaDb();

    @Before
    public void setUp() throws Exception {
        db.start();
    }

    @After
    public void tearDown() throws Exception {
        db.stop();
    }

    @Test
    public void testConnection() throws SQLException {
        assertEquals(true, db
                .getDataSource()
                .getConnection()
                .createStatement()
                .execute("SELECT 1;"));
    }

}
