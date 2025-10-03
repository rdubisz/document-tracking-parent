package net.rd.doctracking.service.rest;

import net.rd.doctracking.service.service.TeamService;
import net.rd.doctracking.service.jpa.entity.TeamEntity;
import net.rd.doctracking.service.model.TeamModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
 * Team API controller
 */
@RestController
public class TeamApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TeamService teamService;

    public TeamApiController(final TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/api/v1/team")
    public Iterable<TeamModel> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping("/api/v1/team")
    public TeamEntity createTeam(@RequestBody final TeamModel teamModel) {
        return teamService.createTeam(teamModel);
    }

    @GetMapping("/api/v1/team/{id}")
    public TeamModel getOneTeam(@PathVariable final Long id) {
        return teamService.getOneTeam(id);
    }

    @PutMapping("/api/v1/team/{id}")
    public TeamModel updateOrCreateTeam(
            @RequestBody final TeamModel teamModel,
            @PathVariable final Long id) {
        return teamService.updateOrCreateTeam(teamModel, id);
    }

    @DeleteMapping("/api/v1/team/{id}")
    public void deleteTeam(@PathVariable final Long id) {
        teamService.deleteTeam(id);
    }
}
