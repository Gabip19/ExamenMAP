package org.examen.examenmap.domain;

public class Location extends Entity<Double> {
    private String locationName;

    public Location(Double id, String locationName) {
        this.locationName = locationName;
        setId(id);
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return locationName;
    }
}
