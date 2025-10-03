package net.rd.doctracking.service;

import net.rd.doctracking.service.jpa.repository.DocumentRepository;
import net.rd.doctracking.service.jpa.repository.PersonRepository;
import net.rd.doctracking.service.jpa.repository.TeamRepository;
import net.rd.doctracking.service.rest.DocumentApiController;
import net.rd.doctracking.service.rest.TeamApiController;
import net.rd.doctracking.service.rest.PersonApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SpringInitializationTest {

    private final TeamApiController teamApiController;
    private final PersonApiController personApiController;
    private final DocumentApiController documentApiController;
    private final TeamRepository teamRepository;
    private final PersonRepository personRepository;
    private final DocumentRepository documentRepository;

    @Autowired
    public SpringInitializationTest(
            final TeamApiController teamApiController,
            final PersonApiController personApiController,
            final DocumentApiController documentApiController,
            final TeamRepository teamRepository,
            final PersonRepository personRepository,
            final DocumentRepository documentRepository) {
        this.teamApiController = teamApiController;
        this.personApiController = personApiController;
        this.documentApiController = documentApiController;
        this.teamRepository = teamRepository;
        this.personRepository = personRepository;
        this.documentRepository = documentRepository;
    }

    @Test
    public void testContextLoading() throws Exception {
        assertNotNull(teamApiController);
        assertNotNull(personApiController);
        assertNotNull(documentApiController);
        assertNotNull(teamRepository);
        assertNotNull(personRepository);
        assertNotNull(documentRepository);
    }
}
