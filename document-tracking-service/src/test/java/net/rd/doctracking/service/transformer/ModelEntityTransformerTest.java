package net.rd.doctracking.service.transformer;

import net.rd.doctracking.service.CommonUtils;
import net.rd.doctracking.service.jpa.entity.TeamEntity;
import net.rd.doctracking.service.jpa.entity.PersonEntity;
import net.rd.doctracking.service.model.TeamModel;
import net.rd.doctracking.service.model.PersonModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelEntityTransformerTest {


    private final TeamModel teamModel = new TeamModel(1L, "S1", CommonUtils.TS_1);
    private final TeamEntity teamEntity = new TeamEntity(1L, "S1", CommonUtils.TS_2);
    private final PersonModel personModel = new PersonModel(1L, "abc@def@net", "Abc", "Def", teamModel.getId(), CommonUtils.TS_1);
    private final PersonEntity personEntity = new PersonEntity(1L, "abc@def@net", "Abc", "Def", teamEntity, CommonUtils.TS_2);


    @Test
    public void modelToEntityTeam() {
        assertEquals(teamModel, ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity(teamModel)));
        assertNull(ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity((TeamModel) null)));
    }

    @Test
    public void entityToModelTeam() {
        assertEquals(teamEntity, ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel(teamEntity)));
        assertNull(ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel((TeamEntity) null)));
    }

    @Test
    public void testModelToEntityPerson() {
        assertEquals(personModel, ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity(personModel)));
        assertEquals(1L, ModelEntityTransformer.modelToEntity(personModel).getTeamEntity().getId());
        assertNull(ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity((PersonModel) null)));

        personModel.setTeamId(null);
        assertNull(ModelEntityTransformer.modelToEntity(personModel).getTeamEntity());
    }

    @Test
    public void testEntityToModelPerson() {
        assertEquals(personEntity, ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel(personEntity)));
        assertEquals(1L, ModelEntityTransformer.entityToModel(personEntity).getTeamId());
        assertNull(ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel((PersonEntity) null)));

        personEntity.setTeamEntity(null);
        assertNull(ModelEntityTransformer.entityToModel(personEntity).getTeamId());
    }
}
