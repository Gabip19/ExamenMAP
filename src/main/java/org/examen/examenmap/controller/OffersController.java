package org.examen.examenmap.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.examen.examenmap.domain.Offer;

public class OffersController extends GuiController {

    public TextField searchTextField;
    public Button searchBtn;
    public TableView<Offer> offersTableView;
    private final ObservableList<Offer> offers = FXCollections.observableArrayList();
    public TableView<Offer> searchTableView = new TableView<>();
    private final ObservableList<Offer> searchOfferResult = FXCollections.observableArrayList();

    public void initialize() {
        updateOffersList();
        initTableView(offersTableView);
        offersTableView.setItems(offers);
        initTableView(searchTableView);
        searchTableView.setItems(searchOfferResult);
        initSearchButtonAction();
    }

    private void initTableView(TableView<Offer> tableView) {
        TableColumn<Offer, String> nameCol = new TableColumn<>("Destination");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("destination"));

        TableColumn<Offer, String> hotelCol = new TableColumn<>("Hotel");
        hotelCol.setCellValueFactory(new PropertyValueFactory<>("hotelName"));

        TableColumn<Offer, Integer> timeCol = new TableColumn<>("Days");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));

        TableColumn<Offer, Float> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Offer, Float> personCol = new TableColumn<>("Num of Persons");
        personCol.setCellValueFactory(new PropertyValueFactory<>("personNum"));

        tableView.getColumns().add(0, nameCol);
        tableView.getColumns().add(1, hotelCol);
        tableView.getColumns().add(2, timeCol);
        tableView.getColumns().add(3, priceCol);
        tableView.getColumns().add(4, personCol);
    }

    private void updateOffersList() {
        offers.setAll(srv.getAllOffers());
    }

    private void initSearchButtonAction() {
        searchBtn.setOnAction(param -> {
            if (!searchTextField.getText().isEmpty()) {
                searchOfferResult.setAll(srv.searchOfferByDestination(searchTextField.getText()));
            }
        });
    }
}
