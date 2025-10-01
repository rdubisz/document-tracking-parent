package net.rd.doctracking.service.service;

import net.rd.doctracking.service.exception.TaskDefinitionEntityNotFoundException;
import net.rd.doctracking.service.exception.TaskOperationEntityNotFoundException;
import net.rd.doctracking.service.exception.TaskOperationInvalidException;
import net.rd.doctracking.service.exception.TaskOperationQueryParamInvalidException;
import net.rd.doctracking.service.transformer.ModelEntityTransformer;
import net.rd.doctracking.service.validation.InputModelValidator;
import net.rd.doctracking.service.jpa.entity.TaskOperationEntity;
import net.rd.doctracking.service.jpa.repository.ReportingQueryRepository;
import net.rd.doctracking.service.jpa.repository.TaskOperationRepository;
import net.rd.doctracking.service.jpa.repository.TaskDefinitionRepository;
import net.rd.doctracking.service.model.TaskOperationModel;
import net.rd.doctracking.service.model.TaskOperationQueryParamModel;
import net.rd.doctracking.service.model.TaskOperationQueryResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskOperationService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private TaskDefinitionRepository taskDefinitionRepository;
    private TaskOperationRepository taskOperationRepository;
    private ReportingQueryRepository reportingQueryRepository;

    public TaskOperationService(
            final TaskDefinitionRepository taskDefinitionRepository,
            final TaskOperationRepository taskOperationRepository,
            final ReportingQueryRepository reportingQueryRepository) {
        this.taskDefinitionRepository = taskDefinitionRepository;
        this.taskOperationRepository = taskOperationRepository;
        this.reportingQueryRepository = reportingQueryRepository;
    }

    public Iterable<TaskOperationModel> getAllTaskOperations() {
        final Iterable<TaskOperationEntity> entitiesList = taskOperationRepository.findAll();
        final List<TaskOperationModel> modelsList = new ArrayList<>();
        for (final TaskOperationEntity taskOperationEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(taskOperationEntity));
        }
        return modelsList;
    }

    public Iterable<TaskOperationModel> getTaskOperations(final Long id) {
        log.info("Getting operations for task-definition {}", id);

        final List<TaskOperationEntity> entitiesList = taskOperationRepository.findByTaskDefinitionId(id);
        final List<TaskOperationModel> modelsList = new ArrayList<>();
        for (final TaskOperationEntity taskOperationEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(taskOperationEntity));
        }
        return modelsList;
    }

    @Transactional
    public TaskOperationModel createTaskOperation(final TaskOperationModel taskOperationModel) {
        log.info("Creating task-operation {}", taskOperationModel);

        if (!InputModelValidator.valid(taskOperationModel))
            throw new TaskOperationInvalidException(taskOperationModel);

        if(!taskDefinitionRepository.existsById(taskOperationModel.getTaskDefinitionId()))
            throw new TaskDefinitionEntityNotFoundException(taskOperationModel.getTaskDefinitionId());

        if (taskOperationModel.getStartTime() == null)
            taskOperationModel.setStartTime(LocalDateTime.now(ZoneId.of("UTC")));
        final TaskOperationEntity taskOperationEntity = ModelEntityTransformer.modelToEntity(taskOperationModel);
        final TaskOperationEntity saved = taskOperationRepository.save(taskOperationEntity);

        return ModelEntityTransformer.entityToModel(saved);
    }


    public TaskOperationModel updateOrCreateTaskOperation(
            final TaskOperationModel taskOperation,
            final Long id) {
        log.info("Updating task-operation {} with id {}", taskOperation, id);

        return taskOperationRepository.findById(id).map(r -> {
            r.setDuration(taskOperation.getDuration());
            final TaskOperationEntity saved = taskOperationRepository.save(r);
            return ModelEntityTransformer.entityToModel(saved);
        }).orElseGet(() -> {
            final TaskOperationEntity taskOperationEntity = ModelEntityTransformer.modelToEntity(taskOperation);
            taskOperationEntity.setId(id);
            final TaskOperationEntity saved = taskOperationRepository.save(taskOperationEntity);
            return ModelEntityTransformer.entityToModel(saved);
        });
    }

    public TaskOperationModel getOneTaskOperation(final Long id) {
        log.info("Getting operation for task-definition {}", id);

        if (!taskDefinitionRepository.existsById(id))
            throw new TaskDefinitionEntityNotFoundException(id);

        final TaskOperationEntity taskOperationEntity = taskOperationRepository.findById(id)
                .orElseThrow(() -> new TaskOperationEntityNotFoundException(id));
        return ModelEntityTransformer.entityToModel(taskOperationEntity);
    }

    public void deleteTaskOperation(Long id) {
        log.info("Deleting task-operation {}", id);

        if (!taskOperationRepository.existsById(id))
            throw new TaskOperationEntityNotFoundException(id);

        taskOperationRepository.deleteById(id);
    }

    public TaskOperationQueryResultModel taskOperationsQuery(final TaskOperationQueryParamModel param) {
        log.info("Querying {}", param);

        if (!InputModelValidator.valid(param))
            throw new TaskOperationQueryParamInvalidException(param);

        final Double result = reportingQueryRepository.taskOperationQuery(
                param.getStartTime(), param.getEndTime(), param.getTaskDefinitionId());

        final TaskOperationQueryResultModel resultModel = new TaskOperationQueryResultModel(param);
        resultModel.setResultValue(result);

        return resultModel;
    }
}
