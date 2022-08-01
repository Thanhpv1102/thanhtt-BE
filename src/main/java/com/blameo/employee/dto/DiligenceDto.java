package com.blameo.employee.dto;

import java.time.Instant;

public class DiligenceDto {

    private Integer id;
    private Integer userId;
    private Instant timeStart;
    private Instant timeStop;
    private Double totalWorking;
    private String status;

    public DiligenceDto() {
    }

    public DiligenceDto(Integer id, Integer userId, Instant timeStart, Instant timeStop, Double totalWorking, String status) {
        this.id = id;
        this.userId = userId;
        this.timeStart = timeStart;
        this.timeStop = timeStop;
        this.totalWorking = totalWorking;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Instant getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

    public Instant getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(Instant timeStop) {
        this.timeStop = timeStop;
    }

    public Double getTotalWorking() {
        return totalWorking;
    }

    public void setTotalWorking(Double totalWorking) {
        this.totalWorking = totalWorking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
