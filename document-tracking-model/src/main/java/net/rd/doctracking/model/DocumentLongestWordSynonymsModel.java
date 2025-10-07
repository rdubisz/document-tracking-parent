package net.rd.doctracking.model;

import java.util.List;

/**
 * Query result contains query parameter: document id, longest word and its synonyms
 */
public record DocumentLongestWordSynonymsModel(
        Long documentId,
        String longestWord,
        List<String> synonyms){
}
