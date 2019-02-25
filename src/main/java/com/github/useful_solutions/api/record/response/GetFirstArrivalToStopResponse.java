package com.github.useful_solutions.api.record.response;

import com.github.useful_solutions.api.record.pojo.ArrivalTransport;
import com.github.useful_solutions.api.record.pojo.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetFirstArrivalToStopResponse {

    @JsonProperty(value = "arrival")
    public List<ArrivalTransport> arrivalTransports;

    @JsonProperty(value = "messages")
    public List<Message> messages;

}