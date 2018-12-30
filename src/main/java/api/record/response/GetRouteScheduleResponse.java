package api.record.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class GetRouteScheduleResponse {

    @JsonProperty(value = "KR_ID")
    public Integer krId;

    /**
     * Время окончания движения.
     */
    @JsonProperty(value = "endTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Samara")
    public Date endTime;

    /**
     * Информация о первом маршруте.
     */
    @JsonProperty(value = "firstRace")
    public FirstRace firstRace;

    /**
     * Интервал движения.
     */
    @JsonProperty(value = "interval")
    public Integer interval;

    /**
     * Информация о последнем маршруте.
     */
    @JsonProperty(value = "lastRace")
    public LastRace lastRace;

    /**
     * Модели транспортных средств.
     */
    @JsonProperty(value = "models")
    public List<String> models;

    /**
     * Расписание для каждой из остановок.
     */
    @JsonProperty(value = "schedule")
    public List<Schedule> schedules;

    /**
     * Время начала движения.
     */
    @JsonProperty(value = "startTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Samara")
    public Date startTime;

    /**
     * Остановки.
     */
    @JsonProperty(value = "stops")
    public List<String> stops;

    public static class FirstRace {

        @JsonProperty(value = "time")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Samara")
        public Date time;
        @JsonProperty(value = "controlPoint")
        private String controlPoint;

    }

    public static class LastRace {

        @JsonProperty(value = "endControlPoint")
        public String endControlPoint;

        @JsonProperty(value = "endTime")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Samara")
        public Date endTime;

        @JsonProperty(value = "startControlPoint")
        public String startControlPoint;

        @JsonProperty(value = "startTime")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Samara")
        public Date startTime;

    }

    public static class Schedule {

        @JsonProperty(value = "stopName")
        public String stopName;

        @JsonProperty(value = "time")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Europe/Samara")
        public List<Date> time;

    }

}