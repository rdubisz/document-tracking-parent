package net.rd.doctracking.service.transformer;

import net.rd.doctracking.service.jpa.entity.TeamEntity;
import net.rd.doctracking.service.jpa.entity.PersonEntity;
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
}
