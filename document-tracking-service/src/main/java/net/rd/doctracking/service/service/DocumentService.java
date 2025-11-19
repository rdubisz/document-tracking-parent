package net.rd.doctracking.service.service;

import net.rd.doctracking.service.exception.DocumentEntityNotFoundException;
import net.rd.doctracking.service.exception.DocumentInvalidException;
import net.rd.doctracking.service.exception.TeamEntityNotFoundException;
import net.rd.doctracking.service.jpa.entity.DocumentEntity;
import net.rd.doctracking.service.jpa.repository.DocumentRepository;
import net.rd.doctracking.service.jpa.repository.PersonRepository;
import net.rd.doctracking.model.DocumentLongestWordSynonymsModel;
import net.rd.doctracking.model.DocumentModel;
import net.rd.doctracking.model.DocumentWordsFrequencyModel;
import net.rd.doctracking.service.transformer.ModelEntityTransformer;
import net.rd.doctracking.service.validation.InputModelValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class DocumentService {

    /**
     * Ignore those words for document statistics.
     */
    private static final Set<String> EXCLUDED = Set.of("the", "me", "you", "i", "of", "and", "a", "we", " ", "");

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final PersonRepository personRepository;
    private final DocumentRepository documentRepository;

    public DocumentService(
            final PersonRepository personRepository,
            final DocumentRepository documentRepository) {
        this.personRepository = personRepository;
        this.documentRepository = documentRepository;
    }

    public Iterable<DocumentModel> getAllDocuments() {
        final Iterable<DocumentEntity> entitiesList = documentRepository.findAll();
        final List<DocumentModel> modelsList = new ArrayList<>();
        for (final DocumentEntity personEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(personEntity));
        }
        return modelsList;
    }

    public Iterable<DocumentModel> getPersonDocuments(final Long id) {
        log.info("Getting documents created by {}", id);

        final List<DocumentEntity> entitiesList = documentRepository.findByCreatedById(id);
        final List<DocumentModel> modelsList = new ArrayList<>();
        for (final DocumentEntity documentEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(documentEntity));
        }
        return modelsList;
    }

    @Transactional
    public DocumentModel createDocument(final DocumentModel documentModel) {
        log.info("Creating person {}", documentModel);

        if (!InputModelValidator.valid(documentModel))
            throw new DocumentInvalidException(documentModel);

        if(!personRepository.existsById(documentModel.getCreatedById()))
            throw new TeamEntityNotFoundException(documentModel.getCreatedById());

        if (documentModel.getCreatedAt() == null)
            documentModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        final DocumentEntity personEntity = ModelEntityTransformer.modelToEntity(documentModel);
        final DocumentEntity saved = documentRepository.save(personEntity);

        return ModelEntityTransformer.entityToModel(saved);
    }


    public DocumentModel updateOrCreateDocument(
            final DocumentModel documentModel,
            final Long id) {
        log.info("Updating Document {} with id {}", documentModel, id);

        return documentRepository.findById(id).map(r -> {
            r.setContent(documentModel.getContent());
            r.setName(documentModel.getName());
            r.setFileLength(documentModel.getFileLength());
            final DocumentEntity saved = documentRepository.save(r);
            return ModelEntityTransformer.entityToModel(saved);
        }).orElseGet(() -> {
            final DocumentEntity documentEntity = ModelEntityTransformer.modelToEntity(documentModel);
            documentEntity.setId(id);
            final DocumentEntity saved = documentRepository.save(documentEntity);
            return ModelEntityTransformer.entityToModel(saved);
        });
    }

    public DocumentModel getOneDocument(final Long id) {
        log.info("Getting document {}", id);

        final DocumentEntity documentEntity = documentRepository.findById(id)
                .orElseThrow(() -> new DocumentEntityNotFoundException(id));
        return ModelEntityTransformer.entityToModel(documentEntity);
    }

    public void deleteDocument(final Long id) {
        log.info("Deleting document {}", id);

        if (!documentRepository.existsById(id))
            throw new DocumentEntityNotFoundException(id);

        documentRepository.deleteById(id);
    }

    public DocumentWordsFrequencyModel documentStatsWordsFrequency(final Long id) {
        log.info("Calculating document {} words frequency stats", id);

        final DocumentEntity documentEntity = documentRepository
                .findById(id)
                .orElseThrow(() -> new DocumentEntityNotFoundException(id));

        final Map<String, Long> wordsMap = calculateWordsFrequency(documentEntity.getContent());

        return new DocumentWordsFrequencyModel(id, wordsMap);
    }

    public DocumentLongestWordSynonymsModel documentLongestWordSynonyms(final Long id) {
        log.info("Finding document {} longest word synonyms", id);

        final DocumentEntity documentEntity = documentRepository
                .findById(id)
                .orElseThrow(() -> new DocumentEntityNotFoundException(id));

        final String longestWord = longestWord(documentEntity.getContent());

        final List<String> synonyms = new ArrayList<>();

        return new DocumentLongestWordSynonymsModel(id, longestWord, synonyms);
    }

    protected Map<String, Long> calculateWordsFrequency(final String text) {
        if(!StringUtils.hasLength(text))
            return Collections.emptyMap();
        final String content = cleanupText(text);
        if(!StringUtils.hasLength(content))
            return Collections.emptyMap();

        final Map<String, Long> map = new HashMap<>();
        for (final String word : content.split(" ")) {
            if(!EXCLUDED.contains(word)) {
                final Long count = map.getOrDefault(word, 0L);
                map.put(word, count + 1);
            }
        }
        return map;
    }

    protected String longestWord(final String text) {
        if(!StringUtils.hasLength(text))
            return null;
        final String content = cleanupText(text);
        if(!StringUtils.hasLength(content))
            return null;

        String longestSoFar = "";
        for (final String word : content.split(" ")) {
            if(!EXCLUDED.contains(word)) {
                if(longestSoFar.length() < word.length())
                    longestSoFar = word;
            }
        }
        return longestSoFar.isEmpty() ? null : longestSoFar;
    }

    private static String cleanupText(String text) {
        return text.trim()
                .replaceAll("[^a-zA-Z0-9\\s]"," ")
                .replaceAll("\n", " ")
                .toLowerCase();
    }


}
