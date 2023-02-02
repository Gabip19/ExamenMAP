package org.examen.examenmap.controller;

import javafx.scene.control.Label;
import org.examen.examenmap.domain.Bed;
import org.examen.examenmap.utils.events.PatientEvent;
import org.examen.examenmap.utils.observers.Observer;

public class BedsController extends GuiController implements Observer<PatientEvent> {
    public Label emptyBedsLabel;
    public Label ticLabel;
    public Label timLabel;
    public Label tiipLabel;

    public void initialize() {
        computeBedsNumber();
    }

    private void computeBedsNumber() {
        int occupiedBeds = 0;
        int ticBeds = 0;
        int timBeds = 0;
        int tiipBeds = 0;

        for (Bed bed: srv.getAllBeds()) {
            if (bed.getPatientCNP() != null) {
                occupiedBeds++;
            } else if (bed.getType().equals(Bed.BedType.TIC)) {
                ticBeds++;
            } else if (bed.getType().equals(Bed.BedType.TIM)) {
                timBeds++;
            } else if (bed.getType().equals(Bed.BedType.TIIP)) {
                tiipBeds++;
            }
        }

        emptyBedsLabel.setText("Occupied beds: " + occupiedBeds);
        ticLabel.setText("TIC: " + ticBeds + " empty beds");
        timLabel.setText("TIM: " + timBeds + " empty beds");
        tiipLabel.setText("TIIP: " + tiipBeds + " empty beds");
    }

    @Override
    public void update(PatientEvent event) {
        computeBedsNumber();
    }
}
