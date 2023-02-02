package org.examen.examenmap.utils.events;


import org.examen.examenmap.domain.Patient;

public class PatientEvent implements Event {
    private ChangeEventType type;
    private Patient patient;

    public PatientEvent(ChangeEventType type, Patient patient) {
        this.type = type;
        this.patient = patient;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Patient getOrder() {
        return patient;
    }
}
