package net.rd.doctracking.service.jpa.repository;

import net.rd.doctracking.service.jpa.entity.DocumentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentRepository extends CrudRepository<DocumentEntity, Long> {

    @Query("select o from DocumentEntity o where o.createdById.id = ?1")
    List<DocumentEntity> findByCreatedById(final Long personId);

}
