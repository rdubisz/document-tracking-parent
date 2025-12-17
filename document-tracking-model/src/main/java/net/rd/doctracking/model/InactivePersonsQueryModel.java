package net.rd.doctracking.model;

import java.time.LocalDateTime;

public record InactivePersonsQueryModel(
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long number) {
}
