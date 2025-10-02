package net.rd.doctracking.service.jpa;

import net.rd.doctracking.service.jpa.repository.PersonRepository;
import net.rd.doctracking.service.jpa.repository.TeamRepository;
import net.rd.doctracking.service.jpa.entity.TeamEntity;
import net.rd.doctracking.service.jpa.entity.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DatabaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public CommandLineRunner initialData(
            final TeamRepository teamRepository,
            final PersonRepository personRepository) {
        return (_) -> {
            log.info("Data initialization");
            final TeamEntity aTeam = teamRepository.save(new TeamEntity("A-team"));
            final TeamEntity bTeam = teamRepository.save(new TeamEntity("B-team"));
            final TeamEntity cTeam = teamRepository.save(new TeamEntity("C-team"));

            final PersonEntity person1 = personRepository.save(new PersonEntity(
                    "john.doe@company.com", "John", "Doe", aTeam));
            final PersonEntity person2 = personRepository.save(new PersonEntity(
                    "jane.doe@company.com", "Jane", "Doe", aTeam));
            final PersonEntity person3 = personRepository.save(new PersonEntity(
                    "bill@gates.windows", "Bill", "Gates", bTeam));
            final PersonEntity person4 = personRepository.save(new PersonEntity(
                    "lem@s-f.edu", "Stanislaw", "Lem", bTeam));

            final Iterable<TeamEntity> definitionEntities = teamRepository.findAll();
            for (final TeamEntity entity : definitionEntities) {
                log.info("{}", entity);
            }

            final Iterable<PersonEntity> opsEntities = personRepository.findAll();
            for (final PersonEntity entity : opsEntities) {
                log.info("{}", entity);
            }
        };
    }
}
