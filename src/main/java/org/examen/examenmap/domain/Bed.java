package org.examen.examenmap.domain;

import java.util.UUID;

public class Bed extends Entity<UUID> {
    private boolean ventilation;
    private String patientCNP;
    private BedType type;

    public enum BedType {
        TIC,
        TIM,
        TIIP
    }

    public Bed(boolean ventilation, String patientCNP, BedType type) {
        this.ventilation = ventilation;
        this.patientCNP = patientCNP;
        this.type = type;
        setId(UUID.randomUUID());
    }

    public Bed(UUID id, boolean ventilation, String patientCNP, BedType type) {
        this.ventilation = ventilation;
        this.patientCNP = patientCNP;
        this.type = type;
        setId(id);
    }

    public boolean hasVentilation() {
        return ventilation;
    }

    public void setVentilation(boolean ventilation) {
        this.ventilation = ventilation;
    }

    public String getPatientCNP() {
        return patientCNP;
    }

    public void setPatientCNP(String patientCNP) {
        this.patientCNP = patientCNP;
    }

    public BedType getType() {
        return type;
    }

    public void setType(BedType type) {
        this.type = type;
    }
}
