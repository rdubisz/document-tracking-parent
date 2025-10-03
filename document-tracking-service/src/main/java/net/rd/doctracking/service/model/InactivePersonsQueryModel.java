package net.rd.doctracking.service.model;

import java.time.LocalDateTime;

public record InactivePersonsQueryModel(
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long number) {
}
