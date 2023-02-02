package org.examen.examenmap.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.examen.examenmap.domain.Locality;
import org.examen.examenmap.domain.River;
import org.examen.examenmap.utils.events.RiverEvent;
import org.examen.examenmap.utils.observers.Observer;

public class WarningsController extends GuiController implements Observer<RiverEvent> {

    public VBox mainVBox;
    public TableView<Locality> minRiskTableView;
    public TableView<Locality> avgRiskTableView;
    public TableView<Locality> hugeRiskTableView;

    private final ObservableList<Locality> minRiskLocalities = FXCollections.observableArrayList();
    private final ObservableList<Locality> avgRiskLocalities = FXCollections.observableArrayList();
    private final ObservableList<Locality> hugeRiskLocalities = FXCollections.observableArrayList();

    public void initialize() {
        initTableView(minRiskTableView);
        initTableView(avgRiskTableView);
        initTableView(hugeRiskTableView);

        updateLocalities();

        minRiskTableView.setItems(minRiskLocalities);
        avgRiskTableView.setItems(avgRiskLocalities);
        hugeRiskTableView.setItems(hugeRiskLocalities);

    }

    private void initTableView(TableView<Locality> tableView) {
        TableColumn<Locality, String> localityName = new TableColumn<>("Name");
        localityName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Locality, Integer> minRisk = new TableColumn<>("Min Risk");
        minRisk.setCellValueFactory(new PropertyValueFactory<>("minRiskAlt"));
        TableColumn<Locality, Integer> maxRisk = new TableColumn<>("Max Risk");
        maxRisk.setCellValueFactory(new PropertyValueFactory<>("maxRiskAlt"));
        TableColumn<Locality, String> riverName = new TableColumn<>("River");
        riverName.setCellValueFactory(cellData -> new SimpleStringProperty(
                srv.findRiverById(cellData.getValue().getRiverId()).getName()
        ));
        TableColumn<Locality, String> riverAlt = new TableColumn<>("River Avg Alt");
        riverAlt.setCellValueFactory(cellData -> new SimpleStringProperty(
                srv.findRiverById(cellData.getValue().getRiverId()).getAvgAltitude() + ""
        ));

        tableView.getColumns().add(0, localityName);
        tableView.getColumns().add(1, minRisk);
        tableView.getColumns().add(2, maxRisk);
        tableView.getColumns().add(3, riverName);
        tableView.getColumns().add(4, riverAlt);
    }

    private void updateLocalities() {
        minRiskLocalities.clear();
        avgRiskLocalities.clear();
        hugeRiskLocalities.clear();

        srv.getAllLocalities().forEach(locality -> {
            River river = srv.findRiverById(locality.getRiverId());
            if (river.getAvgAltitude() > locality.getMaxRiskAlt()) {
                hugeRiskLocalities.add(locality);
            } else if (river.getAvgAltitude() > locality.getMinRiskAlt()) {
                avgRiskLocalities.add(locality);
            } else {
                minRiskLocalities.add(locality);
            }
        });
    }

    @Override
    public void update(RiverEvent event) {
        updateLocalities();
    }
}
