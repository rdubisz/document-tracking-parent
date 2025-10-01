package net.rd.doctracking.service.jpa.repository;

import net.rd.doctracking.service.jpa.entity.TaskOperationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskOperationRepository extends CrudRepository<TaskOperationEntity, Long> {
    @Query("select o from TaskOperationEntity o where o.taskDefinition.id = ?1")
    List<TaskOperationEntity> findByTaskDefinitionId(final Long taskDefinitionId);
}
