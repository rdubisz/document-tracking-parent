package net.rd.doctracking.service.transformer;

import net.rd.doctracking.service.jpa.entity.DocumentEntity;
import net.rd.doctracking.service.jpa.entity.TeamEntity;
import net.rd.doctracking.service.jpa.entity.PersonEntity;
import net.rd.doctracking.service.model.DocumentModel;
import net.rd.doctracking.service.model.TeamModel;
import net.rd.doctracking.service.model.PersonModel;
import org.springframework.beans.BeanUtils;

public class ModelEntityTransformer {

    public static TeamEntity modelToEntity(final TeamModel model) {
        if(model != null) {
            final TeamEntity entity = new TeamEntity();
            BeanUtils.copyProperties(model, entity);
            return entity;
        } else
            return null;
    }

    public static TeamModel entityToModel(final TeamEntity entity) {
        if(entity != null) {
            final TeamModel model = new TeamModel();
            BeanUtils.copyProperties(entity, model);
            return model;
        } else
            return null;
    }

    public static PersonEntity modelToEntity(final PersonModel model) {
        if(model != null) {
            final PersonEntity entity = new PersonEntity();
            BeanUtils.copyProperties(model, entity);
            if(model.getTeamId() != null) {
                final TeamEntity teamEntity = new TeamEntity();
                teamEntity.setId(model.getTeamId());
                entity.setTeamEntity(teamEntity);
            }
            return entity;
        } else
            return null;
    }

    public static PersonModel entityToModel(final PersonEntity entity) {
        if(entity != null) {
            final PersonModel model = new PersonModel();
            BeanUtils.copyProperties(entity, model);
            if(entity.getTeamEntity() != null)
                model.setTeamId(entity.getTeamEntity().getId());
            return model;
        } else
            return null;
    }

    public static DocumentEntity modelToEntity(final DocumentModel model) {
        if(model != null) {
            final DocumentEntity entity = new DocumentEntity();
            BeanUtils.copyProperties(model, entity);
            if(model.getCreatedById() != null) {
                final PersonEntity personEntity = new PersonEntity();
                personEntity.setId(model.getCreatedById());
                entity.setCreatedById(personEntity);
            }
            return entity;
        } else
            return null;
    }

    public static DocumentModel entityToModel(final DocumentEntity entity) {
        if(entity != null) {
            final DocumentModel model = new DocumentModel();
            BeanUtils.copyProperties(entity, model);
            if(entity.getCreatedById() != null)
                model.setCreatedById(entity.getCreatedById().getId());
            return model;
        } else
            return null;
    }
}
