package net.rd.doctracking.service.transformer;

import net.rd.doctracking.CommonUtils;
import net.rd.doctracking.service.jpa.entity.DocumentEntity;
import net.rd.doctracking.service.jpa.entity.TeamEntity;
import net.rd.doctracking.service.jpa.entity.PersonEntity;
import net.rd.doctracking.model.DocumentModel;
import net.rd.doctracking.model.TeamModel;
import net.rd.doctracking.model.PersonModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelEntityTransformerTest {


    private final TeamModel teamModel = new TeamModel(1L, "T1", CommonUtils.TS_1);
    private final TeamEntity teamEntity = new TeamEntity(1L, "T2", CommonUtils.TS_2);
    private final PersonModel personModel = new PersonModel(1L, "abc@def.net", "Abc", "Def", teamModel.getId(), CommonUtils.TS_1);
    private final PersonEntity personEntity = new PersonEntity(1L, "xyz@aaa.net", "Qwerty", "Xyz", teamEntity, CommonUtils.TS_2);
    private final DocumentModel documentModel = new DocumentModel(1L, "File-name", "Some words", CommonUtils.TS_1, "user1@dot.com", 2L);
    private final DocumentEntity documentEntity = new DocumentEntity(1L, "File-name", "Some words", personEntity, "user2@dot.com", CommonUtils.TS_2);


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

    @Test
    public void testModelToEntityDocument() {
        assertEquals(documentModel, ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity(documentModel)));
        assertEquals(2L, ModelEntityTransformer.modelToEntity(documentModel).getCreatedById().getId());
        assertNull(ModelEntityTransformer.entityToModel(ModelEntityTransformer.modelToEntity((DocumentModel) null)));

        documentModel.setCreatedById(null);
        assertNull(ModelEntityTransformer.modelToEntity(documentModel).getCreatedById());
    }

    @Test
    public void testEntityToModelDocument() {
        assertEquals(documentEntity, ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel(documentEntity)));
        assertEquals(1L, ModelEntityTransformer.entityToModel(documentEntity).getCreatedById());
        assertNull(ModelEntityTransformer.modelToEntity(ModelEntityTransformer.entityToModel((DocumentEntity) null)));

        documentEntity.setCreatedById(null);
        assertNull(ModelEntityTransformer.entityToModel(documentEntity).getCreatedById());
    }
}
