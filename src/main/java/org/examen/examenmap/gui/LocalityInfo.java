package org.examen.examenmap.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.controller.LocalityInfoController;
import org.examen.examenmap.domain.Locality;

import java.io.IOException;

public class LocalityInfo {
    public LocalityInfo(Locality rowData) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/examen/examenmap/locality-view.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            ((LocalityInfoController) loader.getController()).setLocality(rowData);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
