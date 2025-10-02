package net.rd.doctracking.service.jpa.repository;

import net.rd.doctracking.service.jpa.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<TeamEntity, Long> {

}
