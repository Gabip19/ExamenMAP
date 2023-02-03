package org.examen.examenmap.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.examen.examenmap.domain.Hotel;
import org.examen.examenmap.domain.SpecialOffer;

public class SpecialOffersController extends GuiController {
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TableView<SpecialOffer> offersTableView;
    private final ObservableList<SpecialOffer> offers = FXCollections.observableArrayList();
    public Button searchBtn;

    private Hotel hotel;

    public void initialize() {
        initTableView();
        initSearchBtnAction();
    }

    private void initSearchBtnAction() {
        searchBtn.setOnAction(param -> {
            if (startDatePicker.getValue() != null && endDatePicker.getValue() != null) {
                offers.setAll(srv.getHotelOffersFromPeriod(hotel, startDatePicker.getValue(), endDatePicker.getValue()));
            }
        });
    }

    private void initTableView() {
        TableColumn<SpecialOffer, String> startDate = new TableColumn<>("Start Date");
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        TableColumn<SpecialOffer, Integer> endDate = new TableColumn<>("End Date");
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        TableColumn<SpecialOffer, Double> percents = new TableColumn<>("Percents");
        percents.setCellValueFactory(new PropertyValueFactory<>("percent"));

        offersTableView.getColumns().add(0, startDate);
        offersTableView.getColumns().add(1, endDate);
        offersTableView.getColumns().add(2, percents);

        offersTableView.setItems(offers);
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
