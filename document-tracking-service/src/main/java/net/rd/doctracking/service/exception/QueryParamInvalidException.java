package net.rd.doctracking.service.exception;


import java.time.LocalDateTime;

public class QueryParamInvalidException extends RuntimeException {

    public QueryParamInvalidException(
            final LocalDateTime startTime,
            final LocalDateTime endTime) {
        super("Query-parameter invalid: (" + startTime + " - " + endTime + ")");
    }
}
