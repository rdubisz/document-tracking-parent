package net.rd.doctracking.service.model;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * Query result contains query parameters and the result value
 */
public class FileStatsQueryResultModel {

    private Long taskDefinitionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Double resultValue;

    public FileStatsQueryResultModel() {
        // Do-nothing constructor
    }

    public FileStatsQueryResultModel(
            final Long taskDefinitionId,
            final LocalDateTime startTime,
            final LocalDateTime endTime,
            final Double resultValue) {
        this.taskDefinitionId = taskDefinitionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.resultValue = resultValue;
    }

    /**
     * Copy query values from the query param object
     */
    public FileStatsQueryResultModel(final PersonQueryParamModel param) {
//        this.setStartTime(param.getStartTime());
//        this.setEndTime(param.getEndTime());
//        this.setTaskDefinitionId(param.getTaskDefinitionId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FileStatsQueryResultModel.class.getSimpleName() + "[", "]")
                .add("taskDefinitionId=" + taskDefinitionId)
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .add("resultValue=" + resultValue)
                .toString();
    }

    public Long getTaskDefinitionId() {
        return taskDefinitionId;
    }

    public void setTaskDefinitionId(final Long taskDefinitionId) {
        this.taskDefinitionId = taskDefinitionId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(final LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(final LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getResultValue() {
        return resultValue;
    }

    public void setResultValue(final Double resultValue) {
        this.resultValue = resultValue;
    }
}
