package org.examen.examenmap;

import org.examen.examenmap.controller.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.repository.database.OfferDatabaseRepo;
import org.examen.examenmap.service.Service;

import java.io.IOException;
import java.util.Scanner;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Service srv = createNetwork();
        GuiController.setSrv(srv);

        System.out.print("Employee number: ");
        Scanner scanner = new Scanner(System.in);
        int employeeNum = scanner.nextInt();

        for (int i = 0; i < employeeNum; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("offers-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.setTitle("Employee " + i);
            stage1.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private Service createNetwork() {
        return new Service(
                new OfferDatabaseRepo(
                        "offers",
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                )
        );
    }
}