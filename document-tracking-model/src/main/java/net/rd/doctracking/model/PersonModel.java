package net.rd.doctracking.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class PersonModel implements Auditable {

    private Long id;
    private Long teamId;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

    public PersonModel() {
        // Do-nothing constructor
    }

    public PersonModel(
            final Long id,
            final String email,
            final String firstName,
            final String lastName,
            final Long teamId,
            final LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamId = teamId;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(final Long teamId) {
        this.teamId = teamId;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final PersonModel that = (PersonModel) o;
        return Objects.equals(id, that.id)
                && Objects.equals(teamId, that.teamId)
                && Objects.equals(email, that.email)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamId, email, firstName, lastName, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PersonModel.class.getSimpleName() + "{", "}")
                .add("\"id\":" + id)
                .add("\"teamId\":" + teamId)
                .add("\"email\":\"" + email + "\"")
                .add("\"firstName\":\"" + firstName + "\"")
                .add("\"lastName\":\"" + lastName + "\"")
                .add("\"createdAt\":" + createdAt)
                .toString();
    }
}
