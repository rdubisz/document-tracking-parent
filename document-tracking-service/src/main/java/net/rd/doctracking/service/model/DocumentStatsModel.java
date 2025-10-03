package net.rd.doctracking.service.model;

import java.util.Map;

/**
 * Query result contains query parameter: document dd and map of word:frequency
 */
public record DocumentStatsModel(
        Long documentId,
        Map<String, Long> stats){
}
