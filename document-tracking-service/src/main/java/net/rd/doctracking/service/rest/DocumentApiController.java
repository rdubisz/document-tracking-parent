package net.rd.doctracking.service.rest;

import net.rd.doctracking.service.model.DocumentLongestWordSynonymsModel;
import net.rd.doctracking.service.model.DocumentModel;
import net.rd.doctracking.service.model.DocumentWordsFrequencyModel;
import net.rd.doctracking.service.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * File REST controller.
 */
@RestController
public class DocumentApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final DocumentService documentService;

    public DocumentApiController(final DocumentService personService) {
        this.documentService = personService;
    }

    /**
     * Not very useful without paging implemented
     */
    @GetMapping("/api/v1/document")
    public Iterable<DocumentModel> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @GetMapping("/api/v1/team/{id}/document")
    public Iterable<DocumentModel> getTeamPersons(@PathVariable final Long id) {
        return documentService.getPersonDocuments(id);
    }

    @PostMapping("/api/v1/document")
    public DocumentModel createDocument(@RequestBody final DocumentModel documentModel) {
        return documentService.createDocument(documentModel);
    }

    /**
     * Only value can be updated
     */
    @PutMapping("/api/v1/document/{id}")
    public DocumentModel updateOrCreateDocument(
            @RequestBody final DocumentModel documentModel,
            @PathVariable final Long id) {
        return documentService.updateOrCreateDocument(documentModel, id);
    }

    @GetMapping("/api/v1/document/{id}")
    public DocumentModel getOneDocument(@PathVariable final Long id) {
        return documentService.getOneDocument(id);
    }

    @DeleteMapping("/api/v1/document/{id}")
    public void deleteDocument(@PathVariable final Long id) {
        documentService.deleteDocument(id);
    }

    @GetMapping("/api/v1/document/{id}/stats/words-frequency")
    public DocumentWordsFrequencyModel documentStatsWordsFrequency(@PathVariable final Long id) {
        return documentService.documentStatsWordsFrequency(id);
    }

    @GetMapping("/api/v1/document/{id}/stats/synonyms")
    public DocumentLongestWordSynonymsModel documentLongestWordSynonyms(@PathVariable final Long id) {
        return documentService.documentLongestWordSynonyms(id);
    }
}
