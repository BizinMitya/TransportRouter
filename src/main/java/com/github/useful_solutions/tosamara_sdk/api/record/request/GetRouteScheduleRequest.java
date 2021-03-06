package com.github.useful_solutions.tosamara_sdk.api.record.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetRouteScheduleRequest {

    @JsonProperty
    private final String method = "getRouteSchedule";

    @JsonProperty(value = "KR_ID")
    private final Integer krId;

    private final String day;

    public GetRouteScheduleRequest(Integer krId, String day) {
        this.krId = krId;
        this.day = day;
    }

    public Integer getKrId() {
        return krId;
    }

    public String getDay() {
        return day;
    }
}
