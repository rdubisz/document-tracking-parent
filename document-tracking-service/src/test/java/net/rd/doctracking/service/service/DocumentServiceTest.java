package net.rd.doctracking.service.service;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DocumentServiceTest {

    final DocumentService tested = new DocumentService(null, null);

    @Test
    void calculateWordsFrequencyNull() {
        final Map<String, Long> result = tested.calculateWordsFrequency(null);
        assertEquals(0L, result.size());
    }

    @Test
    void calculateWordsFrequencySimple() {
        final String text = "One Two one two Me me The the I i of Of OF Zero ";

        final Map<String, Long> result = tested.calculateWordsFrequency(text);
        assertEquals(2L, result.get("one"));
        assertEquals(2L, result.get("two"));
        assertEquals(1L, result.get("zero"));
        assertNull(result.get("i"));
        assertNull(result.get("the"));
        assertNull(result.get("of"));
        assertNull(result.get("me"));
    }

    @Test
    void calculateWordsFrequencyComplex() {
        final String text = """
                A very nice sentence.
                I'm @#$%^&! now
                The blue apple;
                Me, I and myself?
                 Sentence {number} 5
                And Mambo No.5
                Apple is healthy /usually\\
                """;

        final Map<String, Long> result = tested.calculateWordsFrequency(text);
        assertEquals(2L, result.get("apple"));
        assertEquals(2L, result.get("sentence"));
        assertEquals(1L, result.get("blue"));
        assertEquals(1L, result.get("usually"));
        assertEquals(1L, result.get("number"));
        assertNull(result.get("i"));
        assertNull(result.get("the"));
        assertNull(result.get("The"));
        assertNull(result.get("a"));
    }
}