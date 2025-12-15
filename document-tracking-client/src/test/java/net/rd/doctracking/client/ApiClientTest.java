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

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApiClientTest {

    private static String url;
    private static EmbeddedMariaDb db;
    private static EmbeddedDocumentService documentService;
    private static ApiClient client;

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
        client = new ApiClient(url());
    }

    @AfterAll
    public static void tearDown() throws Exception {
        db.stop();
        documentService.stop();
    }

    @Test
    public void testContextLoading() throws Exception {
        assertNotNull(url);
        assertNotNull(client);
    }

    @Test
    public void testAbout() throws Exception {

    }

    @Test
    public void testGetAllTeams() throws Exception {
        Call<List<TeamModel>> call = client.apiIf.listAllTeams();
        Response<List<TeamModel>> callResponse = call.execute();
        assertNotNull(callResponse);
        List<TeamModel> list = callResponse.body();
        assertNotNull(list);
        assertEquals(3, list.size());
    }

    @Test
    public void testGetOneTeams() throws Exception {
        Call<TeamModel> call = client.apiIf.getOneTeam(1L);
        Response<TeamModel> callResponse = call.execute();
        assertNotNull(callResponse);
        TeamModel model = callResponse.body();
        assertNotNull(model);
        assertEquals("A-team", model.getName());
    }

    @Test
    public void testCreateAndDeleteTeam() throws Exception {
        List<TeamModel> listBeforeCreate = client.apiIf.listAllTeams().execute().body();
        assertEquals(3, listBeforeCreate.size());
        //Add
        TeamModel created = client.apiIf.createTeam(new TeamModel("To be deleted")).execute().body();
        assertNotNull(created);
        Call<List<TeamModel>> call = client.apiIf.listAllTeams();
        List<TeamModel> listAfterCreate = call.execute().body();
        assertEquals(4, listAfterCreate.size());
        // Delete
        client.apiIf.deleteTeam(created.getId()).execute();
        List<TeamModel> list = client.apiIf.listAllTeams().execute().body();
        assertEquals(3, list.size());
    }

    @Test
    public void testCreateOrUpdateTeam() throws Exception {
        String nameBefore = client.apiIf.getOneTeam(2L).execute().body().getName();
        assertEquals("B-team", nameBefore);
        // Update
        TeamModel updatedTeam = client.apiIf.updateOrCreateTeam(new TeamModel("Updated"), 2L).execute().body();
        assertNotNull(updatedTeam);
        assertEquals("Updated", updatedTeam.getName());
        // Verify
        String nameAfter = client.apiIf.getOneTeam(2L).execute().body().getName();
        assertEquals("Updated", nameAfter);
        // Reverse
        client.apiIf.updateOrCreateTeam(new TeamModel("B-team"), 2L).execute().body();
    }

    @Test
    public void testGetAllPersons() throws Exception {
        Call<List<PersonModel>> call = client.apiIf.listAllPersons();
        Response<List<PersonModel>> callResponse = call.execute();
        assertNotNull(callResponse);
        List<PersonModel> list = callResponse.body();
        assertNotNull(list);
        assertEquals(4, list.size());
    }

    @Test
    public void testCreateAndDeletePerson() throws Exception {

    }

    @Test
    public void testCreateInvalidPerson() throws Exception {

    }

    @Test
    public void testGetAllDocuments() throws Exception {
        Call<List<DocumentModel>> call = client.apiIf.listAllDocuments();
        Response<List<DocumentModel>> callResponse = call.execute();
        assertNotNull(callResponse);
        List<DocumentModel> list = callResponse.body();
        assertNotNull(list);
        assertEquals(7, list.size());
    }

    @Test
    public void testCreateAndDeleteDocument() throws Exception {

    }

    @Test
    public void testCreateInvalidDocument() throws Exception {

    }


    @Test
    public void testInactivePersonsQuery() throws Exception {
        Call<InactivePersonsQueryModel> call = client.apiIf.listInactivePersons(
                LocalDateTime.parse("2023-06-01T00:00:00"),
                LocalDateTime.parse("2023-07-01T00:00:00"));
        Response<InactivePersonsQueryModel> callResponse = call.execute();
        assertNotNull(callResponse);
        InactivePersonsQueryModel model = callResponse.body();
        assertNotNull(model);
        assertEquals(2, model.number());
        assertEquals(CommonUtils.TS_2, model.startTime());
    }


    @Test
    public void testWordFrequencyQuery() throws Exception {
        final Call<DocumentWordsFrequencyModel> call =  client.apiIf.documentWordsFrequency(1);
        Response<DocumentWordsFrequencyModel> callResponse = call.execute();
        assertNotNull(callResponse);
        DocumentWordsFrequencyModel model = callResponse.body();
        assertNotNull(model);

        assertEquals(1L, model.documentId());
        assertEquals(2L, model.stats().get("apple"));
        assertEquals(2L, model.stats().get("sentence"));
        assertEquals(1L, model.stats().get("blue"));
        assertEquals(1L, model.stats().get("usually"));
        assertEquals(1L, model.stats().get("number"));
        assertNull(model.stats().get("i"));
        assertNull(model.stats().get("the"));
        assertNull(model.stats().get("The"));
        assertNull(model.stats().get("a"));
    }


    @Test
    public void testLongestWordSynonymsQuery() throws Exception {

    }

    protected static String url() {
        return "http://" + url;
    }
}
