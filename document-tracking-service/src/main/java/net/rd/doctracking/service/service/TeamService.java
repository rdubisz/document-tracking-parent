package net.rd.doctracking.service.service;

import net.rd.doctracking.CommonUtils;
import net.rd.doctracking.service.exception.TeamEntityNotFoundException;
import net.rd.doctracking.service.exception.TeamInvalidException;
import net.rd.doctracking.service.transformer.ModelEntityTransformer;
import net.rd.doctracking.service.validation.InputModelValidator;
import net.rd.doctracking.service.jpa.entity.TeamEntity;
import net.rd.doctracking.service.jpa.repository.TeamRepository;
import net.rd.doctracking.model.TeamModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TeamRepository teamRepository;

    public TeamService(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Iterable<TeamModel> getAllTeams() {
        log.info("Get all teams");

        final Iterable<TeamEntity> entitiesList = teamRepository.findAll();
        final List<TeamModel> modelsList = new ArrayList<>();
        for (final TeamEntity teamEntity : entitiesList) {
            modelsList.add(ModelEntityTransformer.entityToModel(teamEntity));
        }

        return modelsList;
    }

    public TeamModel createTeam(final TeamModel teamModel) {
        log.info("Creating team {}", teamModel);

        if(!InputModelValidator.valid(teamModel))
            throw new TeamInvalidException(teamModel);

        teamModel.setCreatedAt(CommonUtils.paramOrNow(teamModel.getCreatedAt()));

        final TeamEntity teamEntity = ModelEntityTransformer.modelToEntity(teamModel);

        final TeamEntity savedEntity = teamRepository.save(teamEntity);
        return ModelEntityTransformer.entityToModel(savedEntity);
    }

    public TeamModel getOneTeam(final Long id) {
        final TeamEntity teamEntity = teamRepository
                .findById(id)
                .orElseThrow(() -> new TeamEntityNotFoundException(id));

        return ModelEntityTransformer.entityToModel(teamEntity);
    }

    public TeamModel updateOrCreateTeam(
            final TeamModel team,
            final Long id) {
        log.info("Updating team {}", id);

        return teamRepository.findById(id).map(s -> {
            final TeamEntity teamEntity = ModelEntityTransformer.modelToEntity(team);
            final TeamEntity saved = teamRepository.save(teamEntity);
            return ModelEntityTransformer.entityToModel(saved);
        }).orElseGet(() -> {
            team.setId(id);
            final TeamEntity teamEntity = ModelEntityTransformer.modelToEntity(team);
            final TeamEntity saved = teamRepository.save(teamEntity);
            return ModelEntityTransformer.entityToModel(saved);
        });
    }

    public void deleteTeam(final Long id) {
        log.info("Deleting team {}", id);

        if(!teamRepository.existsById(id))
            throw new TeamEntityNotFoundException(id);

        teamRepository.deleteById(id);
    }
}
