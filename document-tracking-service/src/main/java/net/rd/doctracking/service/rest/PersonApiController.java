package net.rd.doctracking.service.rest;

import net.rd.doctracking.service.model.PersonModel;
import net.rd.doctracking.service.model.InactivePersonsQueryModel;
import net.rd.doctracking.service.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Person REST controller.
 */
@RestController
public class PersonApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PersonService personService;

    public PersonApiController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/api/v1/person")
    public Iterable<PersonModel> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/api/v1/team/{id}/person")
    public Iterable<PersonModel> getTeamPersons(@PathVariable final Long id) {
        return personService.getTeamPersons(id);
    }

    @PostMapping("/api/v1/person")
    public PersonModel createPerson(@RequestBody final PersonModel personModel) {
        return personService.createPerson(personModel);
    }

    /**
     * Only value can be updated
     */
    @PutMapping("/api/v1/person/{id}")
    public PersonModel updateOrCreatePerson(
            @RequestBody final PersonModel personModel,
            @PathVariable final Long id) {
        return personService.updateOrCreatePerson(personModel, id);
    }

    @GetMapping("/api/v1/person/{id}")
    public PersonModel getOnePerson(@PathVariable final Long id) {
        return personService.getOnePerson(id);
    }

    @DeleteMapping("/api/v1/person/{id}")
    public void deletePerson(@PathVariable final Long id) {
        personService.deletePerson(id);
    }

    @GetMapping("/api/v1/person/inactive")
    public InactivePersonsQueryModel  inactivePersonsQuery(
            @RequestParam final LocalDateTime startTime,
            @RequestParam final LocalDateTime endTime) {
        return personService.inactivePersonsQuery(startTime, endTime);
    }
}
