package org.examen.examenmap;

import org.examen.examenmap.controller.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.repository.database.HotelDatabaseRepo;
import org.examen.examenmap.repository.database.LocationDatabaseRepo;
import org.examen.examenmap.repository.database.SpecialOfferDatabaseRepo;
import org.examen.examenmap.service.Service;

import java.io.IOException;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Service srv = createService();
        GuiController.setSrv(srv);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotels-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        stage.setTitle("Hotels");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Service createService() {
        return new Service(
                new LocationDatabaseRepo(
                        "locations",
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                ),
                new HotelDatabaseRepo(
                        "hotels",
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                ),
                new SpecialOfferDatabaseRepo(
                        "offers",
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                )
        );
    }
}