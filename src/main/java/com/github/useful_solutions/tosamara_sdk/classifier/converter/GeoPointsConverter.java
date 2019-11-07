package com.github.useful_solutions.tosamara_sdk.classifier.converter;

import com.github.useful_solutions.tosamara_sdk.api.record.pojo.GeoPoint;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GeoPointsConverter implements Converter<List<GeoPoint>> {

    @Override
    public List<GeoPoint> read(InputNode node) throws Exception {
        String value = node.getValue();
        if (value != null) {
            String[] stringPoints = value.split(" ");
            List<GeoPoint> points = new ArrayList<>(stringPoints.length);
            for (String stringPoint : stringPoints) {
                String[] coords = stringPoint.split(",");
                Double latitude = Double.parseDouble(coords[0]);
                Double longitude = Double.parseDouble(coords[1]);
                points.add(new GeoPoint(latitude, longitude));
            }
            return points;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void write(OutputNode node, List<GeoPoint> value) {
        if (value != null) {
            node.setValue(
                    value.stream()
                            .map(geoPoint -> geoPoint.latitude + "," + geoPoint.longitude)
                            .collect(Collectors.joining(" "))
            );
        }
    }

}
