package com.Personal.blogapplication.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RideMatchedListener {

    @Autowired
    private RideStatusHandler rideStatusHandler;

    @EventListener
    public void handleRideMatchedEvent(RideMatchedEvent event) {
        rideStatusHandler.notifyRider(event.getRideId(), "MATCHED");
    }
}
