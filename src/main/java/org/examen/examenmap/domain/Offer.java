package org.examen.examenmap.domain;

import java.util.UUID;

public class Offer extends Entity<UUID> {
    private String destination;
    private String hotelName;
    private int timePeriod;
    private float price;
    private int personNum;

    public Offer(String destination, String hotelName, int timePeriod, float price, int personNum) {
        this.destination = destination;
        this.hotelName = hotelName;
        this.timePeriod = timePeriod;
        this.price = price;
        this.personNum = personNum;
        setId(UUID.randomUUID());
    }

    public Offer(UUID id, String destination, String hotelName, int timePeriod, float price, int personNum) {
        this.destination = destination;
        this.hotelName = hotelName;
        this.timePeriod = timePeriod;
        this.price = price;
        this.personNum = personNum;
        setId(id);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }
}
