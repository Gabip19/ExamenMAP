package org.examen.examenmap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.controller.GuiController;
import org.examen.examenmap.repository.database.BedDatabaseRepo;
import org.examen.examenmap.repository.database.PatientDatabaseRepo;
import org.examen.examenmap.service.Service;

import java.io.IOException;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Service srv = createService();
        GuiController.setSrv(srv);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("beds-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);

        stage.setTitle("Beds");
        stage.setScene(scene);
        stage.show();

        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("patients-view.fxml"));
        Scene patientScene = new Scene(fxmlLoader1.load(), 500, 500);
        Stage stage1 = new Stage();
        stage1.setTitle("Patients");
        stage1.setScene(patientScene);
        stage1.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Service createService() {
        return new Service(
                new PatientDatabaseRepo(
                        "patients",
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                ),
                new BedDatabaseRepo(
                        "beds",
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                )
        );
    }
}