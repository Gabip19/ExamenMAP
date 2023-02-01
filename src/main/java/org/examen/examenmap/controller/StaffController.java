package org.examen.examenmap.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.examen.examenmap.domain.Order;
import org.examen.examenmap.utils.Observer;

public class StaffController extends GuiController implements Observer {
    @FXML
    public VBox orderVBox;
    private final TableView<Order> tableView = new TableView<>();
    private ObservableList<Order> orders = FXCollections.observableArrayList(srv.getAllOrders());

    public void initialize() {
        TableColumn<Order, String> tableId = new TableColumn<>("Table");
        tableId.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        TableColumn<Order, String> date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Order, String> menuItem = new TableColumn<>("Items");
        menuItem.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue()
                        .getMenuItemIds()
                        .stream()
                        .map(x -> srv.getMenuItemWithId(x).getItem() + "\n")
                        .reduce("", (x, y) -> x + y)
                )
        );
        tableView.getColumns().add(0, tableId);
        tableView.getColumns().add(1, date);
        tableView.getColumns().add(2, menuItem);
        orders.sort((x, y) -> {
            if (x.getDate().isBefore(y.getDate()))
                return -1;
            else if (y.getDate().isBefore(x.getDate())) {
                return 1;
            }
            return 0;
        });
        tableView.setItems(orders);

        orderVBox.getChildren().add(tableView);
    }

    @Override
    public void update() {
        orders.clear();
        orders.addAll(srv.getAllOrders());
        orders.sort((x, y) -> {
            if (x.getDate().isBefore(y.getDate()))
                return -1;
            else if (y.getDate().isBefore(x.getDate())) {
                return 1;
            }
            return 0;
        });
    }
}
