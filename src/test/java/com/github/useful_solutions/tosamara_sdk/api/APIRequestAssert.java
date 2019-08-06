package com.github.useful_solutions.tosamara_sdk.api;

import com.github.useful_solutions.tosamara_sdk.api.record.pojo.ArrivalTransport;
import com.github.useful_solutions.tosamara_sdk.api.record.pojo.GeoPoint;
import com.github.useful_solutions.tosamara_sdk.api.record.pojo.Link;
import com.github.useful_solutions.tosamara_sdk.api.record.pojo.Message;
import com.github.useful_solutions.tosamara_sdk.api.record.response.FindShortestPathResponse;
import com.github.useful_solutions.tosamara_sdk.api.record.response.GetFirstArrivalToStopResponse;
import org.junit.jupiter.api.Assertions;

import java.util.List;

class APIRequestAssert {

    static GetFirstArrivalToStopResponse getFirstArrivalToStopResponseAssert(GetFirstArrivalToStopResponse getFirstArrivalToStopResponse) {
        Assertions.assertNotNull(getFirstArrivalToStopResponse);
        arrivalTransportsAssert(getFirstArrivalToStopResponse.arrivalTransports);
        messagesAssert(getFirstArrivalToStopResponse.messages);
        return getFirstArrivalToStopResponse;
    }

    static void findShortestPathResponseAssert(FindShortestPathResponse findShortestPathResponse) {
        Assertions.assertNotNull(findShortestPathResponse);
        Assertions.assertNotNull(findShortestPathResponse.price);
        Assertions.assertNotNull(findShortestPathResponse.time);
        Assertions.assertNotNull(findShortestPathResponse.length);
        Assertions.assertNotNull(findShortestPathResponse.transportTakes);
        actionsAssert(findShortestPathResponse.actions);
    }

    private static void actionsAssert(List<FindShortestPathResponse.Action> actions) {
        Assertions.assertNotNull(actions);
        for (FindShortestPathResponse.Action action : actions) {
            Assertions.assertNotNull(action.comment);
            //Assertions.assertNotNull(action.stopFrom);
            Assertions.assertNotNull(action.time);
            Assertions.assertNotNull(action.action);
            //Assertions.assertNotNull(action.stopTo);
            Assertions.assertNotNull(action.length);
            //Assertions.assertNotNull(action.routes);
            geoPointsAssert(action.geometry);
        }
    }

    private static void geoPointsAssert(List<GeoPoint> geoPoints) {
        Assertions.assertNotNull(geoPoints);
        for (GeoPoint geoPoint : geoPoints) {
            Assertions.assertNotNull(geoPoint.latitude);
            Assertions.assertNotNull(geoPoint.longitude);
        }
    }

    private static void arrivalTransportsAssert(List<ArrivalTransport> arrivalTransports) {
        Assertions.assertNotNull(arrivalTransports);
        for (ArrivalTransport arrivalTransport : arrivalTransports) {
            Assertions.assertNotNull(arrivalTransport);
            Assertions.assertNotNull(arrivalTransport.hullNo);
            Assertions.assertNotNull(arrivalTransport.nextStopName);
            Assertions.assertNotNull(arrivalTransport.number);
            Assertions.assertNotNull(arrivalTransport.stateNumber);
            Assertions.assertNotNull(arrivalTransport.timeInSeconds);
            Assertions.assertNotNull(arrivalTransport.time);
            //Assertions.assertNotNull(arrivalTransport.delayTime);
            Assertions.assertNotNull(arrivalTransport.forInvalid);
            Assertions.assertNotNull(arrivalTransport.krId);
            //Assertions.assertNotNull(arrivalTransport.modelTitle);
            Assertions.assertNotNull(arrivalTransport.nextStopId);
            //Assertions.assertNotNull(arrivalTransport.quality);
            Assertions.assertNotNull(arrivalTransport.remainingLength);
            //Assertions.assertNotNull(arrivalTransport.scheduleDepartureTime);
            //Assertions.assertNotNull(arrivalTransport.scheduleTimeTo);
            Assertions.assertNotNull(arrivalTransport.spanLength);
            Assertions.assertNotNull(arrivalTransport.type);
            Assertions.assertNotNull(arrivalTransport.requestedStopId);
        }
    }

    private static void messagesAssert(List<Message> messages) {
        Assertions.assertNotNull(messages);
        for (Message message : messages) {
            Assertions.assertNotNull(message);
            Assertions.assertNotNull(message.authorDeviceId);
            Assertions.assertNotNull(message.authorStatus);
            Assertions.assertNotNull(message.confirms);
            Assertions.assertNotNull(message.creationTimestamp);
            Assertions.assertNotNull(message.expireTimestamp);
            //Assertions.assertNotNull(message.id);
            //Assertions.assertNotNull(message.ksId);
            Assertions.assertNotNull(message.link);
            linksAssert(message.links);
            Assertions.assertNotNull(message.refutes);
            //Assertions.assertNotNull(message.selfVote);
            Assertions.assertNotNull(message.text);
            Assertions.assertNotNull(message.textEn);
            Assertions.assertNotNull(message.textEs);
            //Assertions.assertNotNull(message.transportHullNo);
        }
    }

    private static void linksAssert(List<Link> links) {
        Assertions.assertNotNull(links);
        Assertions.assertFalse(links.isEmpty());
        for (Link link : links) {
            Assertions.assertNotNull(link);
            //Assertions.assertNotNull(link.ksId);
            Assertions.assertNotNull(link.latitude);
            Assertions.assertNotNull(link.longitude);
            Assertions.assertNotNull(link.radius);
            //Assertions.assertNotNull(link.transportHullNo);
        }
    }

}
