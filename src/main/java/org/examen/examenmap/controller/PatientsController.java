package org.examen.examenmap.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.examen.examenmap.domain.Patient;

public class PatientsController extends GuiController {
    public TableView<Patient> patientsTableView;
    private ObservableList<Patient> patients = FXCollections.observableArrayList(srv.getWaitingPatients());

    public void initialize() {
        TableColumn<Patient, String> cnpColumn = new TableColumn<>("CNP");
        cnpColumn.setCellValueFactory(new PropertyValueFactory<>("CNP"));
        TableColumn<Patient, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<Patient, Integer> diagnosisColumn = new TableColumn<>("Diagnosis");
        diagnosisColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));

        patientsTableView.getColumns().add(0, cnpColumn);
        patientsTableView.getColumns().add(1, ageColumn);
        patientsTableView.getColumns().add(2, diagnosisColumn);
        patientsTableView.setItems(patients);
    }
}
