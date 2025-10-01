package net.rd.doctracking.service.rest;

import net.rd.doctracking.service.service.TaskDefinitionService;
import net.rd.doctracking.service.jpa.entity.TaskDefinitionEntity;
import net.rd.doctracking.service.model.TaskDefinitionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskDefinitionApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TaskDefinitionService taskDefinitionService;

    public TaskDefinitionApiController(final TaskDefinitionService taskDefinitionService) {
        this.taskDefinitionService = taskDefinitionService;
    }

    @GetMapping("/api/v1/task")
    public Iterable<TaskDefinitionModel> getAllTaskDefinitions() {
        return taskDefinitionService.getAllTaskDefinitions();
    }

    @PostMapping("/api/v1/task")
    public TaskDefinitionEntity createTaskDefinition(@RequestBody final TaskDefinitionModel taskDefinitionModel) {
        return taskDefinitionService.createTaskDefinition(taskDefinitionModel);
    }

    @GetMapping("/api/v1/task/{id}")
    public TaskDefinitionModel getOneTaskDefinition(@PathVariable final Long id) {
        return taskDefinitionService.getOneTaskDefinition(id);
    }

    @PutMapping("/api/v1/task/{id}")
    public TaskDefinitionModel updateOrCreateTaskDefinition(
            @RequestBody final TaskDefinitionModel taskDefinitionModel,
            @PathVariable final Long id) {
        return taskDefinitionService.updateOrCreateTaskDefinition(taskDefinitionModel, id);
    }

    @DeleteMapping("/api/v1/task/{id}")
    public void deleteTaskDefinition(@PathVariable Long id) {
        taskDefinitionService.deleteTaskDefinition(id);
    }
}
