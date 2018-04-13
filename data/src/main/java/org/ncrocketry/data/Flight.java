package org.ncrocketry.data;

import java.util.Date;

public class Flight {

    public final Date date;
    public final Flyer flyer;
    public final Rocket rocket;
    public final Motor motor;
    public final FlightInformation info;

    public Flight(Date date, Flyer flyter, Rocket rocket, Motor motor, FlightInformation info) {
        this.date= date;
        this.flyer= flyter;
        this.rocket= rocket;
        this.motor= motor;
        this.info= info;
    }

}
