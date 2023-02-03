package org.examen.examenmap.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.examen.examenmap.domain.Hotel;
import org.examen.examenmap.domain.Location;
import org.examen.examenmap.gui.OfferWindow;

import java.io.IOException;

public class HotelsController extends GuiController {

    public TableView<Hotel> hotelTableView;
    private final ObservableList<Hotel> hotels = FXCollections.observableArrayList();
    private final ObservableList<Location> locations = FXCollections.observableArrayList(srv.getAllLocations());
    public ComboBox<Location> locationComboBox;
    public Button searchBtn;

    public void initialize() {
        TableColumn<Hotel, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        TableColumn<Hotel, Integer> rooms = new TableColumn<>("Rooms");
        rooms.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
        TableColumn<Hotel, Double> price = new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        TableColumn<Hotel, String> type = new TableColumn<>("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

//        updateHotelsList();

        hotelTableView.getColumns().add(0, name);
        hotelTableView.getColumns().add(1, rooms);
        hotelTableView.getColumns().add(2, price);
        hotelTableView.getColumns().add(3, type);

        hotelTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        hotelTableView.setItems(hotels);

        initComboBox();
        initSearchButton();
        initTableViewClickAction(hotelTableView);
    }

    private void initSearchButton() {
        searchBtn.setOnAction(param -> {
            if (locationComboBox.getSelectionModel().getSelectedItem() != null) {
                Location location = locationComboBox.getSelectionModel().getSelectedItem();
                hotels.setAll(srv.getAllHotelsFromLocation(location));
            }
        });
    }

    private void initComboBox() {
        locationComboBox.setItems(locations);
    }

    private void initTableViewClickAction(TableView<Hotel> tableView) {
        tableView.setRowFactory(tv -> {
            TableRow<Hotel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Hotel rowData = row.getItem();
                    OfferWindow ow = new OfferWindow(rowData);
                }
            });
            return row;
        });
    }
}
