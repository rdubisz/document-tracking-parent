package net.rd.doctracking.service.rest;

import net.rd.doctracking.service.model.TaskOperationModel;
import net.rd.doctracking.service.model.TaskOperationQueryParamModel;
import net.rd.doctracking.service.model.TaskOperationQueryResultModel;
import net.rd.doctracking.service.service.TaskOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Task operations REST controller.
 */
@RestController
public class TaskOperationApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final TaskOperationService taskOperationService;

    public TaskOperationApiController(final TaskOperationService taskOperationService) {
        this.taskOperationService = taskOperationService;
    }

    /**
     * Not very useful without paging implemented
     */
    @GetMapping("/api/v1/task/operation")
    public Iterable<TaskOperationModel> getAllTaskOperations() {
        return taskOperationService.getAllTaskOperations();
    }

    @GetMapping("/api/v1/task/{id}/operation")
    public Iterable<TaskOperationModel> getTaskOperations(@PathVariable final Long id) {
        return taskOperationService.getTaskOperations(id);
    }

    @PostMapping("/api/v1/task/operation")
    public TaskOperationModel createTaskOperation(@RequestBody final TaskOperationModel taskOperationModel) {
        return taskOperationService.createTaskOperation(taskOperationModel);
    }

    /**
     * Only value can be updated
     */
    @PutMapping("/api/v1/taskReading/{id}")
    public TaskOperationModel updateOrCreateTaskOperation(
            @RequestBody final TaskOperationModel taskOperationModel,
            @PathVariable final Long id) {
        return taskOperationService.updateOrCreateTaskOperation(taskOperationModel, id);
    }

    @GetMapping("/api/v1/task/operation/{id}")
    public TaskOperationModel getOneTaskDefinition(@PathVariable final Long id) {
        return taskOperationService.getOneTaskOperation(id);
    }

    @DeleteMapping("/api/v1/task/operation/{id}")
    public void deleteTaskOperation(@PathVariable final Long id) {
        taskOperationService.deleteTaskOperation(id);
    }

    @GetMapping("/api/v1/task/operation/query")
    public TaskOperationQueryResultModel query(
            @RequestParam final LocalDateTime startTime,
            @RequestParam final LocalDateTime endTime,
            @RequestParam(required = false) final Long taskDefinitionId) {

        final TaskOperationQueryParamModel param = new TaskOperationQueryParamModel(startTime, endTime, taskDefinitionId);
        return taskOperationService.taskOperationsQuery(param);
    }
}
