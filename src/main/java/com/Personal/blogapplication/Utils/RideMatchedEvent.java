package com.Personal.blogapplication.Utils;

import org.springframework.context.ApplicationEvent;

public class RideMatchedEvent extends ApplicationEvent {
    private final Long rideId;

    public RideMatchedEvent(Object source, Long rideId) {
        super(source); // Source is the object that publishes the event
        this.rideId = rideId;
    }

    public Long getRideId() {
        return rideId;
    }
}