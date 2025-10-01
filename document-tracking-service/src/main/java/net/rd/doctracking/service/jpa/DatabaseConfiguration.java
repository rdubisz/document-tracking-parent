package net.rd.doctracking.service.jpa;

import net.rd.doctracking.service.jpa.repository.TaskOperationRepository;
import net.rd.doctracking.service.jpa.repository.TaskDefinitionRepository;
import net.rd.doctracking.service.jpa.entity.TaskDefinitionEntity;
import net.rd.doctracking.service.jpa.entity.TaskOperationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DatabaseConfiguration {

    /**
     * The day with an example data
     * 2023-01-01
     */
    public static final LocalDateTime THE_DAY = LocalDateTime.of(2023, 2, 1, 0, 0, 0);

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public CommandLineRunner initialData(
            final TaskDefinitionRepository defsRepository,
            final TaskOperationRepository opsRepository) {
        return (_) -> {
            log.info("Data initialization");
            final TaskDefinitionEntity taskClean = defsRepository.save(new TaskDefinitionEntity(null, "Cleaning", "cl"));
            final TaskDefinitionEntity taskCf1 = defsRepository.save(new TaskDefinitionEntity(null, "Making cappuccino", "cf-1"));
            final TaskDefinitionEntity taskCf2 = defsRepository.save(new TaskDefinitionEntity(null, "Making latte", "cf-2"));
            final TaskDefinitionEntity taskCf3 = defsRepository.save(new TaskDefinitionEntity(null, "Making americano", "cf-3"));

            //Cleaning hourly, sometimes delayed
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(0), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(3), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 58000L, THE_DAY.plusHours(12).plusMinutes(2), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 62060L, THE_DAY.plusHours(13).plusMinutes(0), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 61008L, THE_DAY.plusHours(14).plusMinutes(0), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 63070L, THE_DAY.plusHours(15).plusMinutes(0), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 60045L, THE_DAY.plusHours(16).plusMinutes(1), taskClean));
            opsRepository.save(new TaskOperationEntity(null, 59080L, THE_DAY.plusHours(17).plusMinutes(0), taskClean));

            //People making coffee
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(1), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(21), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(48), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(55), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 60000L, THE_DAY.plusHours(10).plusMinutes(59), taskCf3));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(4), taskCf3));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(39), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(44), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 60100L, THE_DAY.plusHours(11).plusMinutes(58), taskCf3));
            opsRepository.save(new TaskOperationEntity(null, 58000L, THE_DAY.plusHours(12).plusMinutes(3), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 58000L, THE_DAY.plusHours(12).plusMinutes(49), taskCf2));
            opsRepository.save(new TaskOperationEntity(null, 62060L, THE_DAY.plusHours(13).plusMinutes(16), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 60045L, THE_DAY.plusHours(16).plusMinutes(37), taskCf3));
            opsRepository.save(new TaskOperationEntity(null, 59080L, THE_DAY.plusHours(17).plusMinutes(2), taskCf1));
            opsRepository.save(new TaskOperationEntity(null, 59080L, THE_DAY.plusHours(17).plusMinutes(4), taskCf3));

            final Iterable<TaskDefinitionEntity> definitionEntities = defsRepository.findAll();
            for (final TaskDefinitionEntity entity : definitionEntities) {
                log.info("{}", entity);
            }

            final Iterable<TaskOperationEntity> opsEntities = opsRepository.findAll();
            for (final TaskOperationEntity entity : opsEntities) {
                log.info("{}", entity);
            }
        };
    }
}
