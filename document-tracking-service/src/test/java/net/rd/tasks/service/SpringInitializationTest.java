package net.rd.doctracking.service;

import net.rd.doctracking.service.jpa.repository.TaskOperationRepository;
import net.rd.doctracking.service.jpa.repository.TaskDefinitionRepository;
import net.rd.doctracking.service.rest.TaskDefinitionApiController;
import net.rd.doctracking.service.rest.TaskOperationApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SpringInitializationTest {

    private final TaskDefinitionApiController taskDefinitionApiController;
    private final TaskOperationApiController taskOperationApiController;
    private final TaskDefinitionRepository taskDefinitionRepository;
    private final TaskOperationRepository taskOperationRepository;

    @Autowired
    public SpringInitializationTest(
            final TaskDefinitionApiController taskDefinitionApiController,
            final TaskOperationApiController taskOperationApiController,
            final TaskDefinitionRepository taskDefinitionRepository,
            final TaskOperationRepository taskOperationRepository) {
        this.taskDefinitionApiController = taskDefinitionApiController;
        this.taskOperationApiController = taskOperationApiController;
        this.taskDefinitionRepository = taskDefinitionRepository;
        this.taskOperationRepository = taskOperationRepository;
    }

    @Test
    public void testContextLoading() throws Exception {
        assertNotNull(taskDefinitionApiController);
        assertNotNull(taskOperationApiController);
        assertNotNull(taskDefinitionRepository);
        assertNotNull(taskOperationRepository);
    }
}
