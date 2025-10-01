package net.rd.doctracking.service.service;

import net.rd.doctracking.service.exception.TaskDefinitionEntityNotFoundException;
import net.rd.doctracking.service.exception.TaskDefinitionInvalidException;
import net.rd.doctracking.service.transformer.ModelEntityTransformer;
import net.rd.doctracking.service.validation.InputModelValidator;
import net.rd.doctracking.service.jpa.entity.TaskDefinitionEntity;
import net.rd.doctracking.service.jpa.repository.TaskDefinitionRepository;
import net.rd.doctracking.service.model.TaskDefinitionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskDefinitionService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TaskDefinitionRepository taskDefinitionRepository;

    public TaskDefinitionService(final TaskDefinitionRepository taskDefinitionRepository) {
        this.taskDefinitionRepository = taskDefinitionRepository;
    }

    public Iterable<TaskDefinitionModel> getAllTaskDefinitions() {
        log.info("Get all task-definitions");

        final Iterable<TaskDefinitionEntity> entitiesList = taskDefinitionRepository.findAll();
        final List<TaskDefinitionModel> modelsList = new ArrayList<>();
        for (final TaskDefinitionEntity taskDefinitionEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(taskDefinitionEntity));
        }

        return modelsList;
    }

    public TaskDefinitionEntity createTaskDefinition(final TaskDefinitionModel taskDefinitionModel) {
        log.info("Creating task-definition {}", taskDefinitionModel);

        if(!InputModelValidator.valid(taskDefinitionModel))
            throw new TaskDefinitionInvalidException(taskDefinitionModel);

        final TaskDefinitionEntity taskDefinitionEntity = ModelEntityTransformer.modelToEntity(taskDefinitionModel);

        return taskDefinitionRepository.save(taskDefinitionEntity);
    }

    public TaskDefinitionModel getOneTaskDefinition(final Long id) {
        final TaskDefinitionEntity taskDefinitionEntity = taskDefinitionRepository
                .findById(id)
                .orElseThrow(() -> new TaskDefinitionEntityNotFoundException(id));

        return ModelEntityTransformer.entityToModel(taskDefinitionEntity);
    }

    public TaskDefinitionModel updateOrCreateTaskDefinition(
            final TaskDefinitionModel taskDefinition,
            final Long id) {
        log.info("Updating task definition {}", id);

        return taskDefinitionRepository.findById(id).map(s -> {
            final TaskDefinitionEntity taskDefinitionEntity = ModelEntityTransformer.modelToEntity(taskDefinition);
            final TaskDefinitionEntity saved = taskDefinitionRepository.save(taskDefinitionEntity);
            return ModelEntityTransformer.entityToModel(saved);
        }).orElseGet(() -> {
            taskDefinition.setId(id);
            final TaskDefinitionEntity taskDefinitionEntity = ModelEntityTransformer.modelToEntity(taskDefinition);
            final TaskDefinitionEntity saved = taskDefinitionRepository.save(taskDefinitionEntity);
            return ModelEntityTransformer.entityToModel(saved);
        });
    }

    public void deleteTaskDefinition(final Long id) {
        log.info("Deleting task definition {}", id);

        if(!taskDefinitionRepository.existsById(id))
            throw new TaskDefinitionEntityNotFoundException(id);

        taskDefinitionRepository.deleteById(id);
    }
}
