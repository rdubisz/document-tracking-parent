package net.rd.doctracking.service.rest;

import net.rd.doctracking.service.CommonUtils;
import net.rd.doctracking.service.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Map;

import static net.rd.doctracking.service.rest.RestSecurityConfig.WEB_SECRET;
import static net.rd.doctracking.service.rest.RestSecurityConfig.WEB_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiTest {

	private final TestRestTemplate restTemplate;
	private final int port;

	private final TeamModel teamModel = new TeamModel(
			null, "A-Team", CommonUtils.TS_2);
	private final PersonModel personModel = new PersonModel(
			null, "xyz@bubble.com", "First", "Name", 1L, CommonUtils.TS_2);
	private final DocumentModel documentModel = new DocumentModel(
			999L, "File-name", "Some words", CommonUtils.TS_1, "user1@dot.com", 321L);


	@Autowired
	public RestApiTest(
			final TestRestTemplate restTemplate,
			@Value(value="${local.server.port}") final int port) {
		this.restTemplate = restTemplate.withBasicAuth(WEB_USER, WEB_SECRET);
		this.port = port;
	}

	@Test
	public void testContextLoading() throws Exception {
		assertNotNull(restTemplate);
		assertTrue(port > 0);
	}

	@Test
	public void testAbout() throws Exception {
		assertThat(restTemplate.getForObject(url(), String.class))
				.contains("Example service");
	}

	@Test
	public void testGetAllTeams() throws Exception {
		final String returned = restTemplate.getForObject(url() + "/api/v1/team", String.class);
		assertThat(returned).contains("A-team");
		assertThat(returned).contains("B-team");
	}

	@Test
	public void testCreateAndDeleteTeam() throws Exception {
		final TeamModel returned = restTemplate.postForObject(
				url() + "/api/v1/team", teamModel, TeamModel.class);
		assertEquals("A-Team", returned.getName());
		restTemplate.delete(url() + "/api/v1/team/" + returned.getId());
	}

	@Test
	public void testGetAllPersons() throws Exception {
		final String returned = restTemplate.getForObject(url() + "/api/v1/person", String.class);
		assertThat(returned).contains("John");
		assertThat(returned).contains("Jane");
		assertThat(returned).contains("Doe");
		assertThat(returned).contains("bill@gates.windows");
	}

	@Test
	public void testCreateAndDeletePerson() throws Exception {
		final PersonModel returned = restTemplate.postForObject(
				url() + "/api/v1/person", personModel, PersonModel.class);
		assertEquals(personModel.getEmail(), returned.getEmail());
		assertEquals(personModel.getFirstName(), returned.getFirstName());
		assertEquals(personModel.getLastName(), returned.getLastName());
		restTemplate.delete(url() + "/api/v1/person/" + returned.getId());
	}

	@Test
	public void testCreateInvalidPerson() throws Exception {
		assertThrows(Exception.class, () -> {
			personModel.setEmail(null);
			restTemplate.postForObject(
					url() + "/api/v1/person", personModel, PersonModel.class);
		});
	}

	@Test
	public void testGetAllDocuments() throws Exception {
		final String returned = restTemplate.getForObject(url() + "/api/v1/document", String.class);
		assertThat(returned).contains("John");
		assertThat(returned).contains("Jane");
		assertThat(returned).contains("Doe");
		assertThat(returned).contains("bill@gates.windows");
	}

	@Test
	public void testCreateAndDeleteDocument() throws Exception {
		final DocumentModel returned = restTemplate.postForObject(
				url() + "/api/v1/document", documentModel, DocumentModel.class);
		assertEquals(documentModel.getName(), returned.getName());
		assertEquals(documentModel.getContent(), returned.getContent());
		assertEquals(documentModel.getFileLength(), returned.getFileLength());
		assertEquals(documentModel.getCreatedByEmail(), returned.getCreatedByEmail());
		restTemplate.delete(url() + "/api/v1/document" + returned.getId());
	}

	@Test
	public void testCreateInvalidDocument() throws Exception {
		assertThrows(Exception.class, () -> {
			documentModel.setContent(null);
			restTemplate.postForObject(
					url() + "/api/v1/document", documentModel, DocumentModel.class);
		});
	}

	/**
	 * <code><pre>
	 * select count(*) from document d where d.created_at between '2023-06-01T00:00:00' and '2023-07-01T00:00:00'
	 *   and exists (select * from person p where d.created_by_id = p.id and p.created_at < '2023-06-01T00:00:05');
	 * </pre></code>
	 */
	@Test
	public void testInactivePersonsQuery() throws Exception {
		final InactivePersonsQueryModel result = restTemplate.getForObject(
				url() + "/api/v1/person/inactive?startTime={startTime}&endTime={endTime}", InactivePersonsQueryModel.class,
				Map.of(
						"startTime", "2023-06-01T00:00:00",
						"endTime", "2023-07-01T00:00:00"));

		assertEquals(2, result.number());
		assertEquals(CommonUtils.TS_2, result.startTime());
	}


	/**
	 * <code><pre>
	 * A very nice sentence.
	 * I'm @#$%^&! now
	 * The blue apple;
	 * Me, I and myself?
	 *  Sentence {number} 5
	 * And Mambo No.5
	 * Apple is healthy /usually\
	 * </pre></code>
	 */
	@Test
	public void testWordFrequencyQuery() throws Exception {
		final DocumentStatsModel result = restTemplate.getForObject(
				url() + "/api/v1/document/1/stats/words-frequency", DocumentStatsModel.class);

		assertEquals(1L, result.documentId());
		assertEquals(2L, result.stats().get("apple"));
		assertEquals(2L, result.stats().get("sentence"));
		assertEquals(1L, result.stats().get("blue"));
		assertEquals(1L, result.stats().get("usually"));
		assertEquals(1L, result.stats().get("number"));
		assertNull(result.stats().get("i"));
		assertNull(result.stats().get("the"));
		assertNull(result.stats().get("The"));
		assertNull(result.stats().get("a"));
	}

	protected String url() {
		return "http://localhost:" + port + "/";
	}
}
