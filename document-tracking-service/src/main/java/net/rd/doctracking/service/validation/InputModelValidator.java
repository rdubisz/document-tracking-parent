package net.rd.doctracking.service.validation;

import net.rd.doctracking.service.model.*;
import org.springframework.util.StringUtils;

public class InputModelValidator {

    public static final int MAX_STR_LENGTH = 500;

    public static boolean valid(final TeamModel model) {
        return model != null
                && validMandatoryString(model.getName());
    }

    public static boolean valid(final PersonModel model) {
        return model != null
                && validMandatoryString(model.getEmail())
                && validMandatoryString(model.getFirstName())
                && validMandatoryString(model.getLastName())
                && model.getTeamId() != null
                && model.getTeamId() > 0;
    }

    public static boolean valid(final PersonQueryParamModel model) {
        return model != null
                && validOptionalString(model.getEmail())
                && validOptionalString(model.getFirstName())
                && validOptionalString(model.getLastName())
                && (model.getTeamId() == null || model.getTeamId() > 0);
    }

    public static boolean valid(final DocumentModel model) {
        return model != null
                && validMandatoryString(model.getName())
                && validMandatoryString(model.getContent())
                && validMandatoryString(model.getCreatedByEmail())
                && model.getFileLength() != null
                && model.getFileLength() > 0
                && model.getCreatedById() != null
                && model.getCreatedById() > 0;
    }



    static boolean validOptionalString(final String input) {
        final String trimmed = StringUtils.trimAllWhitespace(input);

        if(!StringUtils.hasLength(trimmed))
            return true;

        if(trimmed.length() > MAX_STR_LENGTH)
            return false;

        // security checks go here

        return true;
    }

    static boolean validMandatoryString(final String input) {
        final String trimmed = StringUtils.trimAllWhitespace(input);

        if(!StringUtils.hasLength(trimmed))
            return false;

        if(trimmed.length() > MAX_STR_LENGTH)
            return false;

        // security checks go here

        return true;
    }
}
