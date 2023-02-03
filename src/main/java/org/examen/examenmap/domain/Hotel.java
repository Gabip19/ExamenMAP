package org.examen.examenmap.domain;

public class Hotel extends Entity<Double> {
    private Double locationId;
    private String hotelName;
    private int noRooms;
    private double pricePerNight;

    private HotelType type;



    public enum HotelType {
        family,
        teenagers,
        oldpeople;
    }
    public Hotel(Double id, Double locationId, String hotelName, int noRooms, double pricePerNight, HotelType type) {
        this.locationId = locationId;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
        this.type = type;
        setId(id);
    }

    public Double getLocationId() {
        return locationId;
    }

    public void setLocationId(Double locationId) {
        this.locationId = locationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public HotelType getType() {
        return type;
    }

    public void setType(HotelType type) {
        this.type = type;
    }

}
