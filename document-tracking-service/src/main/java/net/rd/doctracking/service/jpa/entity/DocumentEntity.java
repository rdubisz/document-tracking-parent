package net.rd.doctracking.service.jpa.entity;

import jakarta.persistence.*;
import net.rd.doctracking.service.CommonUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "document")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String name;
    @Column(nullable = false)
    private String content;
    @Column
    private Long fileLength;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(length = 500, nullable = false)
    private String createdByEmail;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false, foreignKey = @ForeignKey(name = "document_person_fk"))
    private PersonEntity createdById;

    public DocumentEntity() {
        // Do-nothing constructor
    }

    public DocumentEntity(
            final Long id,
            final String name,
            final String content,
            final PersonEntity createdById,
            final String createdByEmail,
            final LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.fileLength = content != null ? (long) content.length() : 0L;
        this.createdById = createdById;
        this.createdByEmail = createdByEmail;
        this.createdAt = CommonUtils.paramOrNow(createdAt);
    }

    public DocumentEntity(
            final String name,
            final String content,
            final String createdByEmail,
            final PersonEntity createdById) {
        this.name = name;
        this.content = content;
        this.fileLength = content != null ? (long) content.length() : 0L;
        this.createdByEmail = createdByEmail;
        this.createdById = createdById;
        this.createdAt = CommonUtils.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public void setFileLength(final Long fileLength) {
        this.fileLength = fileLength;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PersonEntity getCreatedById() {
        return createdById;
    }

    public void setCreatedById(final PersonEntity createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        final DocumentEntity that = (DocumentEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(content, that.content)
                && Objects.equals(fileLength, that.fileLength)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(createdByEmail, that.createdByEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, fileLength, createdAt, createdByEmail);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DocumentEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("content='" + content + "'")
                .add("fileLength=" + fileLength)
                .add("createdAt=" + createdAt)
                .add("createdByEmail='" + createdByEmail + "'")
                .add("createdById=" + createdById)
                .toString();
    }
}
