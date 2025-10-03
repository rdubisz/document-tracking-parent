package net.rd.doctracking.service.service;

import net.rd.doctracking.service.exception.DocumentEntityNotFoundException;
import net.rd.doctracking.service.exception.DocumentInvalidException;
import net.rd.doctracking.service.exception.PersonEntityNotFoundException;
import net.rd.doctracking.service.exception.TeamEntityNotFoundException;
import net.rd.doctracking.service.jpa.entity.DocumentEntity;
import net.rd.doctracking.service.jpa.entity.PersonEntity;
import net.rd.doctracking.service.jpa.repository.DocumentRepository;
import net.rd.doctracking.service.jpa.repository.PersonRepository;
import net.rd.doctracking.service.model.DocumentModel;
import net.rd.doctracking.service.model.PersonModel;
import net.rd.doctracking.service.transformer.ModelEntityTransformer;
import net.rd.doctracking.service.validation.InputModelValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private PersonRepository personRepository;
    private DocumentRepository documentRepository;

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

}
