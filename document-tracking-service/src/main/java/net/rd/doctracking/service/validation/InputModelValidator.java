package net.rd.doctracking.service.validation;

import net.rd.doctracking.service.model.*;
import org.springframework.util.StringUtils;

public class InputModelValidator {

    public static boolean valid(final TaskDefinitionModel model) {
        if(model == null)
            return false;

        if(!StringUtils.hasLength(model.getName()))
            return false;

        if(model.getName().length() > 1000)
            return false;

        if(!StringUtils.hasLength(model.getCode()))
            return false;

        if(model.getCode().length() > 255)
            return false;

        return true;
    }

    public static boolean valid(final TaskOperationModel model) {
        if(model == null)
            return false;

        if(model.getTaskDefinitionId() == null)
            return false;

        if(model.getDuration() == null)
            return false;

        if(model.getDuration() < 0)
            return false;

        if(model.getStartTime() == null)
            return false;

        return true;
    }

    public static boolean valid(final TaskOperationQueryParamModel model) {
        if(model == null)
            return false;

        if(model.getStartTime() == null)
            return false;

        if(model.getEndTime() == null)
            return false;

        if(model.getEndTime().isBefore(model.getStartTime()))
            return false;

        return true;
    }

}
