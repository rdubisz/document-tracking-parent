package net.rd.doctracking.service.validation;

import net.rd.doctracking.service.model.TaskDefinitionModel;
import net.rd.doctracking.service.model.TaskOperationModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputModelValidatorTest {

    private TaskDefinitionModel taskDefinitionModel = new TaskDefinitionModel(
            1L, "S1", "SN:1");
    private TaskOperationModel taskOperationModel = new TaskOperationModel(
            1L, 1L, 1L, LocalDateTime.of(2023, 1, 1, 1, 1, 1));

    @Test
    public void testValidTaskDefinitionModel() {
        assertTrue(InputModelValidator.valid(taskDefinitionModel));

        taskDefinitionModel.setCode("");
        assertFalse(InputModelValidator.valid(taskDefinitionModel));

        taskDefinitionModel.setCode("a");
        taskDefinitionModel.setName("");
        assertFalse(InputModelValidator.valid(taskDefinitionModel));
    }

    @Test
    public void testValidTaskOperationModel() {
        assertTrue(InputModelValidator.valid(taskOperationModel));

        taskOperationModel.setDuration(null);
        assertFalse(InputModelValidator.valid(taskOperationModel));

        taskOperationModel.setDuration(-1L);
        assertFalse(InputModelValidator.valid(taskOperationModel));

        taskOperationModel.setDuration(1L);
        taskOperationModel.setStartTime(null);
        assertFalse(InputModelValidator.valid(taskOperationModel));
    }

}
