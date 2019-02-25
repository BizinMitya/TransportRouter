package com.github.useful_solutions.api.record.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetFirstArrivalToStopRequest {

    @JsonProperty(value = "method")
    private final String method = "getFirstArrivalToStop";

    @JsonProperty(value = "KS_ID")
    private final List<Integer> ksIds;

    @JsonProperty(value = "COUNT")
    private final Integer count;

    public GetFirstArrivalToStopRequest(List<Integer> ksIds, Integer count) {
        this.ksIds = ksIds;
        this.count = count;
    }

    public List<Integer> getKsIds() {
        return ksIds;
    }

    public Integer getCount() {
        return count;
    }
}