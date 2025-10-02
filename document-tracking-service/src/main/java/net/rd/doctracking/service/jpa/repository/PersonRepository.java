package net.rd.doctracking.service.jpa.repository;

import net.rd.doctracking.service.jpa.entity.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    @Query("select o from PersonEntity o where o.team.id = ?1")
    List<PersonEntity> findByTeamId(final Long teamId);

//    Iterable<PersonEntity> personQuery(
//            final Long teamId,
//            final String email,
//            final String firstname,
//            final String lastName);
}
