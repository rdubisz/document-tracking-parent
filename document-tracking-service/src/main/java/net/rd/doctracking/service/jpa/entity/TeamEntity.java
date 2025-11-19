package net.rd.doctracking.service.jpa.entity;

import jakarta.persistence.*;
import net.rd.doctracking.CommonUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "team", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<PersonEntity> persons = new HashSet<>();

    public TeamEntity() {
        // Do-nothing constructor
    }

    public TeamEntity(final String name) {
        this.name = name;
        this.createdAt = CommonUtils.now();
    }

    public TeamEntity(
            final Long id,
            final String name,
            final LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = CommonUtils.paramOrNow(createdAt);
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

    public Set<PersonEntity> getPersons() {
        return persons;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        final TeamEntity that = (TeamEntity) o;
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
        return new StringJoiner(", ", TeamEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("createdAt=" + createdAt)
                .toString();
    }
}
