package net.rd.doctracking.client;

import net.rd.doctracking.CommonUtils;
import net.rd.doctracking.common.test.EmbeddedDocumentService;
import net.rd.doctracking.common.test.EmbeddedMariaDb;
import net.rd.doctracking.model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.Network;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApiClientTest {

    private static String url;
    private static EmbeddedMariaDb db;
    private static EmbeddedDocumentService documentService;
    private static ApiClient apiClient;

    private final TeamModel teamModel = new TeamModel(
            null, "A-Team", CommonUtils.TS_2);
    private final PersonModel personModel = new PersonModel(
            null, "xyz@bubble.com", "First", "Name", 1L, CommonUtils.TS_2);
    private final DocumentModel documentModel = new DocumentModel(
            999L, "File-name", "Some words", CommonUtils.TS_1, "user1@dot.com", 321L);


    @BeforeAll
    public static void setUp() throws Exception {
        final Network network = Network.newNetwork();
        db = new EmbeddedMariaDb(EmbeddedMariaDb.DEFAULT_USER, EmbeddedMariaDb.DEFAULT_PASSWORD, EmbeddedMariaDb.DEFAULT_DBNAME, network);
        db.start();
        documentService = new EmbeddedDocumentService(network, db.getContainer());
        documentService.start();
        url = documentService.getContainerIp() + ":" + documentService.getContainer().getFirstMappedPort();
        apiClient = new ApiClient(url());
    }

    @AfterAll
    public static void tearDown() throws Exception {
        db.stop();
        documentService.stop();
    }

    @Test
    public void testContextLoading() throws Exception {
        assertNotNull(url);
        assertNotNull(apiClient);
    }

    @Test
    public void testAbout() throws Exception {

    }

    @Test
    public void testGetAllTeams() throws Exception {
        Call<List<TeamModel>> teamModelCall = apiClient.apiInterface.listAllTeams();
        Response<List<TeamModel>> callResponse = teamModelCall.execute();
        String string = callResponse.toString();
        assertNotNull(callResponse);
    }

    @Test
    public void testCreateAndDeleteTeam() throws Exception {

    }

    @Test
    public void testGetAllPersons() throws Exception {

    }

    @Test
    public void testCreateAndDeletePerson() throws Exception {

    }

    @Test
    public void testCreateInvalidPerson() throws Exception {

    }

    @Test
    public void testGetAllDocuments() throws Exception {

    }

    @Test
    public void testCreateAndDeleteDocument() throws Exception {

    }

    @Test
    public void testCreateInvalidDocument() throws Exception {

    }


    @Test
    public void testInactivePersonsQuery() throws Exception {

    }


    @Test
    public void testWordFrequencyQuery() throws Exception {

    }


    @Test
    public void testLongestWordSynonymsQuery() throws Exception {

    }

    protected static String url() {
        return "http://" + url;
    }
}
