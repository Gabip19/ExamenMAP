package org.examen.examenmap.domain;

import java.util.UUID;

public class Locality extends Entity<UUID> {

    private String name;
    private UUID riverId;
    private int minRiskAlt;
    private int maxRiskAlt;

    public Locality(UUID id, String name, UUID riverId, int minRiskAlt, int maxRiskAlt) {
        this.name = name;
        this.riverId = riverId;
        this.minRiskAlt = minRiskAlt;
        this.maxRiskAlt = maxRiskAlt;
        setId(id);
    }

    public Locality(String name, UUID riverId, int minRiskAlt, int maxRiskAlt) {
        this.name = name;
        this.riverId = riverId;
        this.minRiskAlt = minRiskAlt;
        this.maxRiskAlt = maxRiskAlt;
        setId(UUID.randomUUID());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getRiverId() {
        return riverId;
    }

    public void setRiverId(UUID riverId) {
        this.riverId = riverId;
    }

    public int getMinRiskAlt() {
        return minRiskAlt;
    }

    public void setMinRiskAlt(int minRiskAlt) {
        this.minRiskAlt = minRiskAlt;
    }

    public int getMaxRiskAlt() {
        return maxRiskAlt;
    }

    public void setMaxRiskAlt(int maxRiskAlt) {
        this.maxRiskAlt = maxRiskAlt;
    }
}
