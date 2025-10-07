package net.rd.doctracking.service.jpa;

import net.rd.doctracking.model.CommonUtils;
import net.rd.doctracking.service.jpa.entity.DocumentEntity;
import net.rd.doctracking.service.jpa.repository.DocumentRepository;
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
            final PersonRepository personRepository,
            final DocumentRepository documentRepository) {
        return (_) -> {
            log.info("Data initialization");
            final TeamEntity aTeam = teamRepository.save(new TeamEntity("A-team"));
            final TeamEntity bTeam = teamRepository.save(new TeamEntity("B-team"));
            final TeamEntity cTeam = teamRepository.save(new TeamEntity("C-team"));

            final PersonEntity person1 = personRepository.save(new PersonEntity(
                    null, "john.doe@company.com", "John", "Doe", aTeam, CommonUtils.TS_1));
            final PersonEntity person2 = personRepository.save(new PersonEntity(
                    null, "jane.doe@company.com", "Jane", "Doe", aTeam, CommonUtils.TS_1));
            final PersonEntity person3 = personRepository.save(new PersonEntity(
                    null, "bill@gate.windows", "Bill", "Gate", bTeam, CommonUtils.TS_1.plusMonths(1).plusDays(2))); // After July 1
            final PersonEntity person4 = personRepository.save(new PersonEntity(
                    null, "lem@s-f.edu", "Stanislaw", "Lem", bTeam, CommonUtils.TS_1));

            // Before July 1, person1
            final DocumentEntity document1 = documentRepository.save(new DocumentEntity(
                    null, "File-name-1", """
                A very nice sentence.
                I'm @#$%^&! now
                The blue apple;
                Me, I and myself?
                 Sentence {number} 5
                And Mambo No.5
                Apple is healthy /usually\\
                """, person1, person1.getEmail(), CommonUtils.TS_1));
            // July, person1
            final DocumentEntity document2 = documentRepository.save(new DocumentEntity(
                    null, "File-name-2", "Just three words", person1, person1.getEmail(), CommonUtils.TS_2.plusDays(1)));
            // After June 1, person1
            final DocumentEntity document3 = documentRepository.save(new DocumentEntity(
                    null, "File-name-3", "The contract. A deal.", person1, person1.getEmail(), CommonUtils.TS_3.plusDays(1)));
            // Before July 1, person2
            final DocumentEntity document4 = documentRepository.save(new DocumentEntity(
                    null, "File-name-4", "Other words", person2, person2.getEmail(), CommonUtils.TS_1.plusDays(2)));
            // July, person2
            final DocumentEntity document5 = documentRepository.save(new DocumentEntity(
                    null, "File-name-5", "I wrote the !@#$%^&*(){/} document", person2, person2.getEmail(), CommonUtils.TS_2.plusDays(2)));
            // After June 1, person2
            final DocumentEntity document6 = documentRepository.save(new DocumentEntity(
                    null, "File-name-6", "The contract. A deal.", person2, person2.getEmail(), CommonUtils.TS_3.plusDays(2)));
            // July, person3
            final DocumentEntity document7 = documentRepository.save(new DocumentEntity(
                    null, "File-name-7", "Bill wrote the document, but he registered too late", person3, person3.getEmail(), CommonUtils.TS_2.plusDays(3)));

            final Iterable<TeamEntity> teamEntities = teamRepository.findAll();
            for (final TeamEntity entity : teamEntities) {
                log.info("{}", entity);
            }

            final Iterable<PersonEntity> personEntities = personRepository.findAll();
            for (final PersonEntity entity : personEntities) {
                log.info("{}", entity);
            }

            final Iterable<DocumentEntity> docEntities = documentRepository.findAll();
            for (final DocumentEntity entity : docEntities) {
                log.info("{}", entity);
            }
        };
    }
}
