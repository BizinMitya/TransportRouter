package api.record.response;

import api.record.pojo.Transport;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetSurroundingTransportsResponse {

    @JsonProperty(value = "transports")
    public List<Transport> transports;

}
