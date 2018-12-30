package api.record.request;

import api.record.pojo.GeoPoint;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSurroundingTransportsRequest {

    @JsonProperty(value = "method")
    private final String method = "getSurroundingTransports";

    @JsonProperty(value = "LATITUDE")
    private final Double latitude;

    @JsonProperty(value = "LONGITUDE")
    private final Double longitude;

    @JsonProperty(value = "RADIUS")
    private final Double radius;

    @JsonProperty(value = "COUNT")
    private final Integer count;

    public GetSurroundingTransportsRequest(GeoPoint geoPoint, Double radius, Integer count) {
        this.latitude = geoPoint.getLatitude();
        this.longitude = geoPoint.getLongitude();
        this.radius = radius;
        this.count = count;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getRadius() {
        return radius;
    }

    public Integer getCount() {
        return count;
    }

}
