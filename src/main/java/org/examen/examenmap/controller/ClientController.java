package org.examen.examenmap.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.examen.examenmap.domain.Client;
import org.examen.examenmap.domain.SpecialOffer;

public class ClientController extends GuiController {
    public TableView<SpecialOffer> offersTableView;
    private final ObservableList<SpecialOffer> clientOffers = FXCollections.observableArrayList();
    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public void initialize() {
        initTableView();
    }

    private void updateClientOffers() {
        clientOffers.setAll(srv.getOffersForClient(client));
    }

    private void initTableView() {
        TableColumn<SpecialOffer, String> hotel = new TableColumn<>("Hotel");
        hotel.setCellValueFactory(cellData -> {
            SpecialOffer offer = cellData.getValue();
            Double hotelId = offer.getHotelId();
            String hotelName = srv.getHotelWithId(hotelId).getHotelName();
            return new SimpleStringProperty(hotelName);
        });
        TableColumn<SpecialOffer, String> location = new TableColumn<>("Location");
        location.setCellValueFactory(cellData -> {
            SpecialOffer offer = cellData.getValue();
            Double locationId = srv.getHotelWithId(offer.getHotelId()).getLocationId();
            String locationName = srv.getLocationWithId(locationId).getLocationName();
            return new SimpleStringProperty(locationName);
        });

        TableColumn<SpecialOffer, String> start = new TableColumn<>("Start Date");
        start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        TableColumn<SpecialOffer, Integer> end = new TableColumn<>("End Date");
        end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        TableColumn<SpecialOffer, Double> percents = new TableColumn<>("Percents");
        percents.setCellValueFactory(new PropertyValueFactory<>("percent"));

        offersTableView.getColumns().add(0, hotel);
        offersTableView.getColumns().add(1, location);
        offersTableView.getColumns().add(2, start);
        offersTableView.getColumns().add(3, end);
        offersTableView.getColumns().add(4, percents);

        offersTableView.setItems(clientOffers);
    }

    public void updateTable() {
        updateClientOffers();
    }
}
