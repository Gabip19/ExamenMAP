package org.examen.examenmap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.domain.Table;
import org.examen.examenmap.repository.Repository;
import org.examen.examenmap.repository.file.TableFileRepository;

import java.io.IOException;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainFxmlLoader = new FXMLLoader(GUI.class.getResource("hello-view.fxml"));
        Scene mainScene = new Scene(mainFxmlLoader.load(), 320, 240);
        stage.setTitle("Staff");
        stage.setScene(mainScene);
        stage.show();

        Repository<Integer, Table> tableRepo =
                new TableFileRepository("src/main/resources/org/examen/examenmap/Tables.txt");

        for (Table table : tableRepo.findAll()) {
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            Stage stage1 = new Stage();
            stage1.setTitle("Table " + table.getId());
            stage1.setScene(scene);
            stage1.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}