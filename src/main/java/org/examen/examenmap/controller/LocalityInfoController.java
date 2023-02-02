package org.examen.examenmap.controller;

import javafx.scene.control.Label;
import org.examen.examenmap.domain.Locality;

public class LocalityInfoController extends GuiController {
    public Label localityName;
    public Label riverName;
    public Label riverAlt;
    public Label minAlt;
    public Label maxAlt;
    private Locality locality;

    public void setLocality(Locality locality) {
        this.locality = locality;
        localityName.setText(locality.getName());
        riverName.setText(srv.findRiverById(locality.getRiverId()).getName());
        riverAlt.setText(String.valueOf(srv.findRiverById(locality.getRiverId()).getAvgAltitude()));
        minAlt.setText(String.valueOf(locality.getMinRiskAlt()));
        maxAlt.setText(String.valueOf(locality.getMaxRiskAlt()));
    }

}
