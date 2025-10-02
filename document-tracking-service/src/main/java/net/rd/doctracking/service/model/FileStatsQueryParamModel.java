package net.rd.doctracking.service.model;

import java.time.LocalDateTime;

public class FileStatsQueryParamModel {

    private Long teamId;
    private LocalDateTime email;
    private LocalDateTime firstname;
    private LocalDateTime lastName;

    public FileStatsQueryParamModel() {
        // Do-nothing constructor
    }

    public FileStatsQueryParamModel(
            final Long teamId,
            final LocalDateTime email,
            final LocalDateTime firstname,
            final LocalDateTime lastName) {
        this.teamId = teamId;
        this.email = email;
        this.firstname = firstname;
        this.lastName = lastName;
    }
}
