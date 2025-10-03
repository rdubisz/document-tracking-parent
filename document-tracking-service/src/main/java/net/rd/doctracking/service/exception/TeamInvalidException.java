package net.rd.doctracking.service.exception;

import net.rd.doctracking.service.model.TeamModel;

public class TeamInvalidException extends RuntimeException {

    public TeamInvalidException(final TeamModel model) {
        super("Team invalid: " + model);
    }
}
