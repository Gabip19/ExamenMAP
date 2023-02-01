package org.examen.examenmap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.examen.examenmap.controller.GuiController;
import org.examen.examenmap.controller.TableController;
import org.examen.examenmap.domain.MenuItem;
import org.examen.examenmap.domain.Order;
import org.examen.examenmap.domain.Table;
import org.examen.examenmap.repository.Repository;
import org.examen.examenmap.repository.database.MenuItemDatabaseRepo;
import org.examen.examenmap.repository.database.OrderDatabaseRepo;
import org.examen.examenmap.repository.database.TableDatabaseRepo;
import org.examen.examenmap.service.Service;

import java.io.IOException;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Service srv = createService();
        GuiController.setSrv(srv);

        FXMLLoader mainFxmlLoader = new FXMLLoader(GUI.class.getResource("staff-view.fxml"));
        Scene mainScene = new Scene(mainFxmlLoader.load(), 500, 500);
        srv.addObserver(mainFxmlLoader.getController());
        stage.setTitle("Staff");
        stage.setScene(mainScene);
        stage.show();

        srv.getAllTables().forEach(table -> {
            FXMLLoader loader = new FXMLLoader(GUI.class.getResource("table-view.fxml"));
            Scene scene;
            Stage newStage = new Stage();
            try {
                scene = new Scene(loader.load(), 400, 300);
                ((TableController) loader.getController()).setTable(table);
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
                new TableDatabaseRepo(
                        "tables",
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                );
        Repository<Integer, MenuItem> menuRepo =
                new MenuItemDatabaseRepo(
                        "menu_items",
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                );
        Repository<Integer, Order> orderRepo =
                new OrderDatabaseRepo(
                        "orders",
                        "jdbc:postgresql://localhost:5432/ExamData",
                        "postgres",
                        "postgres"
                );
        return new Service(tableRepo, menuRepo, orderRepo);
    }

    public static void main(String[] args) {
        launch();
    }
}