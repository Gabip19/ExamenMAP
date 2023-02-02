package org.examen.examenmap.domain;

import java.util.UUID;

public class River extends Entity<UUID> {
    private String name;
    private int avgAltitude;

    public River(String name, int avgAltitude) {
        this.name = name;
        this.avgAltitude = avgAltitude;
    }

    public River(UUID id, String name, int avgAltitude) {
        this.name = name;
        this.avgAltitude = avgAltitude;
        setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvgAltitude() {
        return avgAltitude;
    }

    public void setAvgAltitude(int avgAltitude) {
        this.avgAltitude = avgAltitude;
    }
}
