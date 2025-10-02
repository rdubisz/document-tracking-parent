package net.rd.doctracking.service.rest;

import net.rd.doctracking.service.CommonUtils;
import net.rd.doctracking.service.jpa.DatabaseConfiguration;
import net.rd.doctracking.service.model.TeamModel;
import net.rd.doctracking.service.model.PersonModel;
import net.rd.doctracking.service.model.PersonQueryParamModel;
import net.rd.doctracking.service.model.FileStatsQueryResultModel;
import net.rd.doctracking.service.transformer.ModelEntityTransformerTest;
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
	public void testCreateAndDeletePerson() throws Exception {
		final PersonModel returned = restTemplate.postForObject(
				url() + "/api/v1/person", personModel, PersonModel.class);
		assertEquals(personModel.getEmail(), returned.getEmail());
		assertEquals(personModel.getFirstName(), returned.getFirstName());
		assertEquals(personModel.getLastName(), returned.getLastName());
		restTemplate.delete(url() + "/api/v1/person" + returned.getId());
	}

	@Test
	public void testCreateInvalidPerson() throws Exception {
		assertThrows(Exception.class, () -> {
			personModel.setEmail(null);
			restTemplate.postForObject(
					url() + "/api/v1/person", personModel, PersonModel.class);
		});
	}

//	@Test
//	public void testPersonQuery() throws Exception {
//		final PersonQueryParamModel param = new PersonQueryParamModel();
//		param.setEmail("abc@def.net");
//		param.setFirstName("First");
//		param.setLastName("Last");
//
//		final Map<String, ?> paramsMap = param.toMap();
//		final FileStatsQueryResultModel result = restTemplate.getForObject(
//				url() + "/api/v1/person/query?email={email}&firstname={firstname}&teamId={teamId}",
//				FileStatsQueryResultModel.class, paramsMap);
//		assertEquals(60420L, result.getResultValue().longValue());
//	}

	protected String url() {
		return "http://localhost:" + port + "/";
	}
}
