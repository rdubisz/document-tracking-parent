package net.rd.doctracking.service.jpa.repository;

import net.rd.doctracking.service.jpa.entity.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Query("select o from PersonEntity o where o.team.id = ?1")
    List<PersonEntity> findByTeamId(final Long teamId);

    @Query("""
            select count(d) from DocumentEntity d where d.createdAt between ?1 and ?2 \
            and exists (select p from PersonEntity p where d.createdById.id = p.id and p.createdAt < ?1)\s""")
    Long inactiveUsersQuery(
            final LocalDateTime startTime,
            final LocalDateTime endTime);

}
