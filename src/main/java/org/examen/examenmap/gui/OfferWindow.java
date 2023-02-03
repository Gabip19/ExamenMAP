package org.examen.examenmap.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.controller.SpecialOffersController;
import org.examen.examenmap.domain.Hotel;

import java.io.IOException;

public class OfferWindow {
    private Hotel hotel;

    public OfferWindow(Hotel hotel) {
        this.hotel = hotel;
        loadOfferStage();
    }

    private void loadOfferStage() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/examen/examenmap/offer-view.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 500, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((SpecialOffersController) fxmlLoader.getController()).setHotel(hotel);
        Stage stage = new Stage();
        stage.setTitle("Special Offers");
        stage.setScene(scene);
        stage.show();
    }
}
