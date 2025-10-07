package net.rd.doctracking.model;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class TeamModel implements Auditable {

    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public TeamModel() {
        // Do-nothing constructor
    }

    public TeamModel(final String name) {
        this.name = name;
    }

    public TeamModel(
            final Long id,
            final String name,
            final LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        final TeamModel that = (TeamModel) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TeamModel.class.getSimpleName() + "{", "}")
                .add("\"id\":" + id)
                .add("\"name\":\"" + name + "\"")
                .add("\"createdAt\":" + createdAt)
                .toString();
    }
}
