package org.examen.examenmap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.controller.GuiController;
import org.examen.examenmap.domain.MenuItem;
import org.examen.examenmap.domain.Table;
import org.examen.examenmap.repository.Repository;
import org.examen.examenmap.repository.file.MenuFileRepository;
import org.examen.examenmap.repository.file.TableFileRepository;
import org.examen.examenmap.service.Service;

import java.io.IOException;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainFxmlLoader = new FXMLLoader(GUI.class.getResource("hello-view.fxml"));
        Scene mainScene = new Scene(mainFxmlLoader.load(), 320, 240);
        stage.setTitle("Staff");
        stage.setScene(mainScene);
        stage.show();

        Service srv = createService();
        GuiController.setSrv(srv);

        srv.getAllTables().forEach(table -> {
            FXMLLoader loader = new FXMLLoader(GUI.class.getResource("table-view.fxml"));
            Scene scene = null;
            Stage newStage = new Stage();
            try {
                scene = new Scene(loader.load(), 320, 240);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newStage.setTitle("Table " + table.getId());
            newStage.setScene(scene);
            newStage.show();
        });
    }

    private Service createService() {
        Repository<Integer, Table> tableRepo =
                new TableFileRepository("src/main/resources/org/examen/examenmap/Tables.txt");
        Repository<Integer, MenuItem> menuRepo =
                new MenuFileRepository("src/main/resources/org/examen/examenmap/Menu.txt");
        return new Service(tableRepo, menuRepo);
    }

    public static void main(String[] args) {
        launch();
    }
}