package net.rd.doctracking.service.jpa.entity;

import jakarta.persistence.*;
import net.rd.doctracking.CommonUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String email;
    @Column(length = 500, nullable = false)
    private String firstName;
    @Column(length = 500, nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false, foreignKey = @ForeignKey(name = "person_team_fk"))
    private TeamEntity team;

    public PersonEntity() {
        // Do-nothing constructor
    }

    public PersonEntity(
            final Long id,
            final String email,
            final String firstName,
            final String lastName,
            final TeamEntity team,
            final LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.createdAt = CommonUtils.paramOrNow(createdAt);
    }

    public PersonEntity(
            final String email,
            final String firstName,
            final String lastName,
            final TeamEntity team) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.createdAt = CommonUtils.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TeamEntity getTeamEntity() {
        return team;
    }

    public void setTeamEntity(final TeamEntity team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        final PersonEntity that = (PersonEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(email, that.email)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PersonEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("email='" + email + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("createdAt=" + createdAt)
                .add("team=" + team)
                .toString();
    }

}
