package net.rd.doctracking.model;

import java.util.Map;

/**
 * Query result contains query parameter: document id and map of word:frequency
 */
public record DocumentWordsFrequencyModel(
        Long documentId,
        Map<String, Long> stats){
}
