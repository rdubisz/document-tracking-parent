package net.rd.doctracking.service.model;

import java.time.LocalDateTime;

public interface Auditable {

    LocalDateTime getCreatedAt();

    void setCreatedAt(final LocalDateTime createdAt);

}
