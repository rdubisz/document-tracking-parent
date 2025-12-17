package net.rd.doctracking.model;

import java.time.LocalDateTime;

public interface Auditable {

    LocalDateTime getCreatedAt();

    void setCreatedAt(final LocalDateTime createdAt);

}
