package net.rd.doctracking.service.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class PersonQueryParamModel {

    private Long teamId;
    private String email;
    private String firstName;
    private String lastName;

    public PersonQueryParamModel() {
        // Do-nothing constructor
    }

    public PersonQueryParamModel(
            final Long teamId,
            final String email,
            final String firstName,
            final String lastName) {
        this.teamId = teamId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Map<String, ?> toMap() {
        final Map<String, Object> map = new HashMap<>();
        if(teamId != null)
            map.put("teamId", teamId);
        if(email != null)
            map.put("email", email);
        if(firstName != null)
            map.put("firstName", firstName);
        if(lastName != null)
            map.put("lastName", lastName);

        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        final PersonQueryParamModel that = (PersonQueryParamModel) o;
        return Objects.equals(teamId, that.teamId)
                && Objects.equals(email, that.email)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, email, firstName, lastName);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PersonQueryParamModel.class.getSimpleName() + "[", "]")
                .add("teamId=" + teamId)
                .add("email='" + email + "'")
                .add("firstname='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .toString();
    }
}
