package net.rd.doctracking.service.jpa.repository;

import net.rd.doctracking.service.jpa.entity.TaskDefinitionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskDefinitionRepository extends CrudRepository<TaskDefinitionEntity, Long> {

}
