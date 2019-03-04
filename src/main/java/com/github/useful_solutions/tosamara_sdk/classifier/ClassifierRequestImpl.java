package com.github.useful_solutions.tosamara_sdk.classifier;

import com.github.useful_solutions.tosamara_sdk.classifier.pojo.*;
import com.github.useful_solutions.tosamara_sdk.exception.APIResponseException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.net.HttpURLConnection.HTTP_OK;

public class ClassifierRequestImpl implements ClassifierRequest {

    private static final String CLASSIFIERS_URL = "https://tosamara.ru/api/classifiers";

    private static final String ROUTES = "routes.xml";
    private static final String STOPS = "stops.xml";
    private static final String STOPS_FULL_DB = "stopsFullDB.xml";
    private static final String ROUTES_AND_STOPS = "routesAndStopsCorrespondence.xml";
    private static final String GEOPORTAL_ROUTES = "GeoportalRoutesCorrespondence.xml";
    private static final String GEOPORTAL_STOPS = "GeoportalStopsCorrespondence.xml";

    private static final String STOPS_URL = CLASSIFIERS_URL + "/" + STOPS;
    private static final String STOPS_FULL_URL = CLASSIFIERS_URL + "/" + STOPS_FULL_DB;
    private static final String ROUTES_URL = CLASSIFIERS_URL + "/" + ROUTES;
    private static final String ROUTES_AND_STOPS_CORRESPONDENCE_URL = CLASSIFIERS_URL + "/" + ROUTES_AND_STOPS;
    private static final String GEOPORTAL_STOPS_CORRESPONDENCE_URL = CLASSIFIERS_URL + "/" + GEOPORTAL_STOPS;
    private static final String GEOPORTAL_ROUTES_CORRESPONDENCE_URL = CLASSIFIERS_URL + "/" + GEOPORTAL_ROUTES;
    private static final String ALL_CLASSIFIERS = CLASSIFIERS_URL + "/classifiers.zip";

    private static final Serializer SERIALIZER = new Persister(new AnnotationStrategy());
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    private <T> T doClassifierRequest(Class<T> classifierType, String url) throws Exception {
        Response response = OK_HTTP_CLIENT.newCall(
                new Request.Builder()
                        .url(url)
                        .get().build()
        ).execute();
        int statusCode = response.code();
        if (statusCode == HTTP_OK) {
            String content = Optional.ofNullable(response.body())
                    .orElseThrow(() -> new APIResponseException(APIResponseException.RESPONSE_BODY_IS_NULL))
                    .string();
            if (SERIALIZER.validate(classifierType, content)) {
                return SERIALIZER.read(classifierType, content);
            } else {
                throw new Exception(String.format("Content %s can't be deserialized", content));
            }
        } else {
            throw new APIResponseException(statusCode);
        }
    }

    @Override
    public List<Classifier> getClassifiers() throws Exception {
        Classifiers classifiers = doClassifierRequest(Classifiers.class, CLASSIFIERS_URL);
        return classifiers.files;
    }

    @Override
    public AllClassifiers getAllClassifiers() throws Exception {
        Response response = OK_HTTP_CLIENT.newCall(
                new Request.Builder()
                        .url(ALL_CLASSIFIERS)
                        .get()
                        .build()
        ).execute();
        int statusCode = response.code();
        if (statusCode == HTTP_OK) {
            AllClassifiers allClassifiers = new AllClassifiers();
            ResponseBody responseBody = Optional.ofNullable(response.body())
                    .orElseThrow(() -> new APIResponseException(APIResponseException.RESPONSE_BODY_IS_NULL));
            try (ZipInputStream zipInputStream = new ZipInputStream(responseBody.byteStream())) {
                ZipEntry zipEntry;
                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    switch (zipEntry.getName()) {
                        case ROUTES: {
                            allClassifiers.setRoutes(SERIALIZER.read(Routes.class, zipInputStream));
                            break;
                        }
                        case STOPS: {
                            allClassifiers.setStops(SERIALIZER.read(Stops.class, zipInputStream));
                            break;
                        }
                        case STOPS_FULL_DB: {
                            allClassifiers.setFullStops(SERIALIZER.read(FullStops.class, zipInputStream));
                            break;
                        }
                        case ROUTES_AND_STOPS: {
                            allClassifiers.setRoutesWithStops(SERIALIZER.read(RoutesWithStops.class, zipInputStream));
                            break;
                        }
                        case GEOPORTAL_ROUTES: {
                            allClassifiers.setRoutesOnMap(SERIALIZER.read(RoutesOnMap.class, zipInputStream));
                            break;
                        }
                        case GEOPORTAL_STOPS: {
                            allClassifiers.setStopsOnMap(SERIALIZER.read(StopsOnMap.class, zipInputStream));
                            break;
                        }
                    }
                    zipInputStream.closeEntry();
                }
                return allClassifiers;
            }
        } else {
            throw new APIResponseException(statusCode);
        }
    }

    @Override
    public List<Stop> getStops() throws Exception {
        Stops stops = doClassifierRequest(Stops.class, STOPS_URL);
        return stops.stops;
    }

    @Override
    public List<FullStop> getFullStops() throws Exception {
        FullStops fullStops = doClassifierRequest(FullStops.class, STOPS_FULL_URL);
        return fullStops.fullStops;
    }

    @Override
    public List<Route> getRoutes() throws Exception {
        Routes routes = doClassifierRequest(Routes.class, ROUTES_URL);
        return routes.routes;
    }

    @Override
    public List<RouteWithStops> getRoutesWithStops() throws Exception {
        RoutesWithStops routesWithStops = doClassifierRequest(RoutesWithStops.class, ROUTES_AND_STOPS_CORRESPONDENCE_URL);
        return routesWithStops.routeWithStops;
    }

    @Override
    public StopsOnMap getStopsOnMap() throws Exception {
        return doClassifierRequest(StopsOnMap.class, GEOPORTAL_STOPS_CORRESPONDENCE_URL);
    }

    @Override
    public List<RouteOnMap> getRoutesOnMap() throws Exception {
        RoutesOnMap routesOnMap = doClassifierRequest(RoutesOnMap.class, GEOPORTAL_ROUTES_CORRESPONDENCE_URL);
        return routesOnMap.routesOnMap;
    }

}
