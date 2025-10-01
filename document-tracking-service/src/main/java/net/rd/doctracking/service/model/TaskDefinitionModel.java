package net.rd.doctracking.service.model;


import java.util.Objects;
import java.util.StringJoiner;

public class TaskDefinitionModel {

    private Long id;
    private String name;
    private String code;

    public TaskDefinitionModel() {
        // Do-nothing constructor
    }

    public TaskDefinitionModel(
            final String name,
            final String code) {
        this.name = name;
        this.code = code;
    }

    public TaskDefinitionModel(
            final Long id,
            final String name,
            final String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TaskDefinitionModel that = (TaskDefinitionModel) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskDefinitionModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("code='" + code + "'")
                .toString();
    }
}
