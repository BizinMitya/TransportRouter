package com.github.useful_solutions.tosamara_sdk.classifier.pojo;

import org.simpleframework.xml.ElementList;

import java.util.List;

public class FullStopWrapper {

    @ElementList(entry = "stop", inline = true)
    public List<FullStop> fullStops;

}