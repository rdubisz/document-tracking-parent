package net.rd.doctracking.model;



import net.rd.doctracking.CommonUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class DocumentModel implements Auditable {

    private Long id;
    private String name;
    private String content;
    private Long fileLength;
    private LocalDateTime createdAt;
    private String createdByEmail;
    private Long createdById;

    public DocumentModel() {
        // Do-nothing constructor
    }

    public DocumentModel(
            final Long id,
            final String name,
            final String content,
            final LocalDateTime createdAt,
            final String createdByEmail,
            final Long createdById) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.fileLength = content != null ? (long) content.length() : 0L;
        this.createdById = createdById;
        this.createdByEmail = createdByEmail;
        this.createdAt = CommonUtils.paramOrNow(createdAt);
    }

    public DocumentModel(
            final String name,
            final String content,
            final String createdByEmail,
            final Long createdById) {
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

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(final String createdByEmail) {
        this.createdByEmail = createdByEmail;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(final Long createdById) {
        this.createdById = createdById;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        final DocumentModel that = (DocumentModel) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(content, that.content)
                && Objects.equals(fileLength, that.fileLength)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(createdByEmail, that.createdByEmail)
                && Objects.equals(createdById, that.createdById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, fileLength, createdAt, createdByEmail, createdById);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DocumentModel.class.getSimpleName() + "{", "}")
                .add("\"id\":" + id)
                .add("\"name\":\"" + name + "\"")
                .add("\"content\":\"" + content + "\"")
                .add("\"fileLength\":" + fileLength)
                .add("\"createdAt\":" + createdAt)
                .add("\"createdByEmail\":\"" + createdByEmail + "\"")
                .add("\"createdById\":" + createdById)
                .toString();
    }
}
