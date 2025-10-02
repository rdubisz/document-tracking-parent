package net.rd.doctracking.service.validation;

import net.rd.doctracking.service.CommonUtils;
import net.rd.doctracking.service.model.TeamModel;
import net.rd.doctracking.service.model.PersonModel;
import net.rd.doctracking.service.transformer.ModelEntityTransformerTest;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputModelValidatorTest {

    private final TeamModel teamModel = new TeamModel( 101L, "A Team", CommonUtils.TS_1);
    private final PersonModel personModel = new PersonModel(
            999L, "xyz@bubble.com", "First", "Name", 321L, CommonUtils.TS_2);

    @Test
    public void testValidTeamModelName() {
        assertTrue(InputModelValidator.valid(teamModel));

        teamModel.setName(null);
        assertFalse(InputModelValidator.valid(teamModel));

        teamModel.setName("");
        assertFalse(InputModelValidator.valid(teamModel));

        teamModel.setName("   ");
        assertFalse(InputModelValidator.valid(teamModel));

        teamModel.setName("a".repeat(InputModelValidator.MAX_STR_LENGTH + 1));
        assertFalse(InputModelValidator.valid(teamModel));
    }

    @Test
    public void testValidPersonModelEmail() {
        assertTrue(InputModelValidator.valid(personModel));

        personModel.setEmail(null);
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setEmail("");
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setEmail("    ");
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setEmail("a".repeat(InputModelValidator.MAX_STR_LENGTH + 1));
        assertFalse(InputModelValidator.valid(personModel));
    }

    @Test
    public void testValidPersonModelFirstName() {
        assertTrue(InputModelValidator.valid(personModel));

        personModel.setFirstName(null);
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setFirstName("");
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setFirstName("    ");
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setFirstName("a".repeat(InputModelValidator.MAX_STR_LENGTH + 1));
        assertFalse(InputModelValidator.valid(personModel));
    }

    @Test
    public void testValidPersonModelLastName() {
        assertTrue(InputModelValidator.valid(personModel));

        personModel.setLastName(null);
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setLastName("");
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setLastName("    ");
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setLastName("a".repeat(InputModelValidator.MAX_STR_LENGTH + 1));
        assertFalse(InputModelValidator.valid(personModel));
    }

    @Test
    public void testValidPersonModelTeamId() {
        assertTrue(InputModelValidator.valid(personModel));

        personModel.setTeamId(null);
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setTeamId(-1L);
        assertFalse(InputModelValidator.valid(personModel));

        personModel.setTeamId(0L);
        assertFalse(InputModelValidator.valid(personModel));
    }

}
