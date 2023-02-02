package org.examen.examenmap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.controller.GuiController;
import org.examen.examenmap.repository.database.DatabaseTables;
import org.examen.examenmap.repository.database.LocalityDatabaseRepo;
import org.examen.examenmap.repository.database.RiverDatabaseRepo;
import org.examen.examenmap.service.Service;

import java.io.IOException;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        GuiController.setSrv(createNetwork());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rivers-view.fxml"));
        Scene riversScene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Rivers");
        stage.setScene(riversScene);
        stage.show();

        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("warnings-view.fxml"));
        Scene warningsScene = new Scene(fxmlLoader2.load(), 500, 500);
        Stage stage2 = new Stage();
        stage2.setTitle("Warnings");
        stage2.setScene(warningsScene);
        stage2.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Service createNetwork() {
        return new Service(
                new RiverDatabaseRepo(
                        DatabaseTables.rivers.toString(),
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                ),
                new LocalityDatabaseRepo(
                        DatabaseTables.localities.toString(),
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                )
        );
    }
}