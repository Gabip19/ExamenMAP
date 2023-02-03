package org.examen.examenmap;

import org.examen.examenmap.config.ApplicationContext;
import org.examen.examenmap.controller.ClientController;
import org.examen.examenmap.controller.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.repository.database.ClientDatabaseRepo;
import org.examen.examenmap.repository.database.HotelDatabaseRepo;
import org.examen.examenmap.repository.database.LocationDatabaseRepo;
import org.examen.examenmap.repository.database.SpecialOfferDatabaseRepo;
import org.examen.examenmap.service.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {

    static ArrayList<String> arguments;

    @Override
    public void start(Stage stage) throws IOException {
        Service srv = createService();
        GuiController.setSrv(srv);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hotels-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        stage.setTitle("Hotels");
        stage.setScene(scene);
        stage.show();
        arguments.forEach(arg -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("client-view.fxml"));
            try {
                Scene clientScene = new Scene(loader.load(), 500, 500);
                Stage clientStage = new Stage();
                ((ClientController) loader.getController()).setClient(srv.getClientWithId(Long.parseLong(arg)));
                ((ClientController) loader.getController()).updateTable();
                clientStage.setTitle("Client " + arg);
                clientStage.setScene(clientScene);
                clientStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        arguments = new ArrayList<>(List.of(args));
        launch();
    }

    private Service createService() {
        return new Service(
                new LocationDatabaseRepo(
                        "locations",
                        ApplicationContext.DATABASE_URL,
                        ApplicationContext.USER,
                        ApplicationContext.PASSWORD
                ),
                new HotelDatabaseRepo(
                        "hotels",
                        ApplicationContext.DATABASE_URL,
                        ApplicationContext.USER,
                        ApplicationContext.PASSWORD
                ),
                new SpecialOfferDatabaseRepo(
                        "offers",
                        ApplicationContext.DATABASE_URL,
                        ApplicationContext.USER,
                        ApplicationContext.PASSWORD
                ),
                new ClientDatabaseRepo(
                        "clients",
                        ApplicationContext.DATABASE_URL,
                        ApplicationContext.USER,
                        ApplicationContext.PASSWORD
                )
        );
    }
}