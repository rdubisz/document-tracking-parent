package net.rd.doctracking.service.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class TaskOperationModel {

    private Long id;
    private Long taskDefinitionId;
    private Long duration;
    private LocalDateTime startTime;

    public TaskOperationModel() {
        // Do-nothing constructor
    }

    public TaskOperationModel(
            final Long taskDefinitionId,
            final Long duration) {
        this.taskDefinitionId = taskDefinitionId;
        this.duration = duration;
    }

    public TaskOperationModel(
            final Long taskDefinitionId,
            final Long duration,
            final LocalDateTime startTime) {
        this.taskDefinitionId = taskDefinitionId;
        this.duration = duration;
        this.startTime = startTime;
    }

    public TaskOperationModel(
            final Long id,
            final Long taskDefinitionId,
            final Long duration,
            final LocalDateTime startTime) {
        this.id = id;
        this.taskDefinitionId = taskDefinitionId;
        this.duration = duration;
        this.startTime = startTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getTaskDefinitionId() {
        return taskDefinitionId;
    }

    public void setTaskDefinitionId(final Long taskDefinitionId) {
        this.taskDefinitionId = taskDefinitionId;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(final Long duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(final LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TaskOperationModel that = (TaskOperationModel) o;
        return Objects.equals(id, that.id)
                && Objects.equals(taskDefinitionId, that.taskDefinitionId)
                && Objects.equals(duration, that.duration)
                && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskDefinitionId, duration, startTime);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskOperationModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("taskDefinitionId=" + taskDefinitionId)
                .add("duration=" + duration)
                .add("startTime=" + startTime)
                .toString();
    }
}
