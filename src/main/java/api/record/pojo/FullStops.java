package api.record.pojo;

import org.simpleframework.xml.ElementList;

import java.util.List;

public class FullStops {

    @ElementList(entry = "stop", inline = true)
    public List<FullStop> stops;

}