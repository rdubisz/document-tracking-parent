package net.rd.doctracking.service.transformer;

import net.rd.doctracking.service.jpa.entity.TaskDefinitionEntity;
import net.rd.doctracking.service.jpa.entity.TaskOperationEntity;
import net.rd.doctracking.service.model.TaskDefinitionModel;
import net.rd.doctracking.service.model.TaskOperationModel;
import org.springframework.beans.BeanUtils;

public class ModelEntityTransformer {

    public static TaskDefinitionEntity modelToEntity(final TaskDefinitionModel model) {
        if(model != null) {
            final TaskDefinitionEntity entity = new TaskDefinitionEntity();
            BeanUtils.copyProperties(model, entity);
            return entity;
        } else
            return null;
    }

    public static TaskDefinitionModel entityToModel(final TaskDefinitionEntity entity) {
        if(entity != null) {
            final TaskDefinitionModel model = new TaskDefinitionModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        } else
            return null;
    }

    public static TaskOperationEntity modelToEntity(final TaskOperationModel model) {
        if(model != null) {
            final TaskOperationEntity entity = new TaskOperationEntity();
            BeanUtils.copyProperties(model, entity);
            if(model.getTaskDefinitionId() != null) {
                final TaskDefinitionEntity taskDefinitionEntity = new TaskDefinitionEntity();
                taskDefinitionEntity.setId(model.getTaskDefinitionId());
                entity.setTaskDefinitionEntity(taskDefinitionEntity);
            }
            return entity;
        } else
            return null;
    }

    public static TaskOperationModel entityToModel(final TaskOperationEntity entity) {
        if(entity != null) {
            final TaskOperationModel model = new TaskOperationModel();
            BeanUtils.copyProperties(entity, model);
            if(entity.getTaskDefinitionEntity() != null)
                model.setTaskDefinitionId(entity.getTaskDefinitionEntity().getId());
            return model;
        } else
            return null;
    }
}
