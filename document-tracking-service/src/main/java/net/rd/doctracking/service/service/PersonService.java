package net.rd.doctracking.service.service;

import net.rd.doctracking.service.exception.QueryParamInvalidException;
import net.rd.doctracking.service.exception.TeamEntityNotFoundException;
import net.rd.doctracking.service.exception.PersonEntityNotFoundException;
import net.rd.doctracking.service.exception.PersonInvalidException;
import net.rd.doctracking.service.jpa.entity.TeamEntity;
import net.rd.doctracking.service.model.InactivePersonsQueryModel;
import net.rd.doctracking.service.transformer.ModelEntityTransformer;
import net.rd.doctracking.service.validation.InputModelValidator;
import net.rd.doctracking.service.jpa.entity.PersonEntity;
import net.rd.doctracking.service.jpa.repository.PersonRepository;
import net.rd.doctracking.service.jpa.repository.TeamRepository;
import net.rd.doctracking.service.model.PersonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TeamRepository teamRepository;
    private final PersonRepository personRepository;

    public PersonService(
            final TeamRepository teamRepository,
            final PersonRepository personRepository) {
        this.teamRepository = teamRepository;
        this.personRepository = personRepository;
    }

    public Iterable<PersonModel> getAllPersons() {
        final Iterable<PersonEntity> entitiesList = personRepository.findAll();
        final List<PersonModel> modelsList = new ArrayList<>();
        for (final PersonEntity personEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(personEntity));
        }
        return modelsList;
    }

    public Iterable<PersonModel> getTeamPersons(final Long id) {
        log.info("Getting persons in a team {}", id);

        final List<PersonEntity> entitiesList = personRepository.findByTeamId(id);
        final List<PersonModel> modelsList = new ArrayList<>();
        for (final PersonEntity personEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(personEntity));
        }
        return modelsList;
    }

    @Transactional
    public PersonModel createPerson(final PersonModel personModel) {
        log.info("Creating person {}", personModel);

        if (!InputModelValidator.valid(personModel))
            throw new PersonInvalidException(personModel);

        if(!teamRepository.existsById(personModel.getTeamId()))
            throw new TeamEntityNotFoundException(personModel.getTeamId());

        if (personModel.getCreatedAt() == null)
            personModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        final PersonEntity personEntity = ModelEntityTransformer.modelToEntity(personModel);
        final PersonEntity saved = personRepository.save(personEntity);

        return ModelEntityTransformer.entityToModel(saved);
    }


    public PersonModel updateOrCreatePerson(
            final PersonModel personModel,
            final Long id) {
        log.info("Updating person {} with id {}", personModel, id);

        return personRepository.findById(id).map(r -> {
            if(!Objects.equals(r.getTeamEntity().getId(), personModel.getTeamId())) {
                final TeamEntity updatedTeamEntity = teamRepository
                        .findById(personModel.getTeamId())
                        .orElseThrow(() -> new TeamEntityNotFoundException(personModel.getTeamId()));
                r.setTeamEntity(updatedTeamEntity);
            }
            r.setEmail(personModel.getEmail());
            r.setFirstName(personModel.getFirstName());
            r.setLastName(personModel.getLastName());
            final PersonEntity saved = personRepository.save(r);
            return ModelEntityTransformer.entityToModel(saved);
        }).orElseGet(() -> {
            final PersonEntity personEntity = ModelEntityTransformer.modelToEntity(personModel);
            personEntity.setId(id);
            final PersonEntity saved = personRepository.save(personEntity);
            return ModelEntityTransformer.entityToModel(saved);
        });
    }

    public PersonModel getOnePerson(final Long id) {
        log.info("Getting person {}", id);

        final PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new PersonEntityNotFoundException(id));
        return ModelEntityTransformer.entityToModel(personEntity);
    }

    public void deletePerson(final Long id) {
        log.info("Deleting person {}", id);

        if (!personRepository.existsById(id))
            throw new PersonEntityNotFoundException(id);

        personRepository.deleteById(id);
    }

    public InactivePersonsQueryModel inactivePersonsQuery(
            final LocalDateTime startTime,
            final LocalDateTime endTime) {
        log.info("Querying ({} - {})", startTime, endTime);

        if (!InputModelValidator.valid(startTime, endTime))
            throw new QueryParamInvalidException(startTime, endTime);

        final Long result = personRepository.inactiveUsersQuery(startTime, endTime);

        return new InactivePersonsQueryModel(startTime, endTime, result);
    }

}
