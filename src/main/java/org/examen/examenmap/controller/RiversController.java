package org.examen.examenmap.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.examen.examenmap.domain.River;

public class RiversController extends GuiController {

    @FXML
    public TableView<River> riversTableView;
    public TextField riverAltTextField;
    public Button modifyAltBtn;

    private ObservableList<River> rivers = FXCollections.observableArrayList(srv.getAllRivers());

    public void initialize() {
        TableColumn<River, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<River, Integer> altitudeColumn = new TableColumn<>("Average Altitude");
        altitudeColumn.setCellValueFactory(new PropertyValueFactory<>("avgAltitude"));

        riversTableView.getColumns().add(0, nameColumn);
        riversTableView.getColumns().add(1, altitudeColumn);
        riversTableView.setItems(rivers);
    }
}
