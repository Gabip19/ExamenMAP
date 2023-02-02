package org.examen.examenmap.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.examen.examenmap.domain.Bed;
import org.examen.examenmap.domain.Patient;
import org.examen.examenmap.domain.exceptions.NoEmptyBedsException;

public class PatientsController extends GuiController {
    public TableView<Patient> patientsTableView;
    private final ObservableList<Patient> patients = FXCollections.observableArrayList(srv.getWaitingPatients());
    public Button ticBtn;
    public Button timBtn;
    public Button tiipBtn;

    public void initialize() {
        TableColumn<Patient, String> cnpColumn = new TableColumn<>("CNP");
        cnpColumn.setCellValueFactory(new PropertyValueFactory<>("CNP"));
        TableColumn<Patient, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<Patient, String> diagnosisColumn = new TableColumn<>("Diagnosis");
        diagnosisColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        TableColumn<Patient, Integer> severityColumn = new TableColumn<>("Severity");
        severityColumn.setCellValueFactory(new PropertyValueFactory<>("severity"));

        patientsTableView.getColumns().add(0, cnpColumn);
        patientsTableView.getColumns().add(1, ageColumn);
        patientsTableView.getColumns().add(2, diagnosisColumn);
        patientsTableView.getColumns().add(3, severityColumn);

        initButtonActions();
        updatePatientList();

        patientsTableView.setItems(patients);
    }

    private void initButtonActions() {
        ticBtn.setOnAction(param -> {
            handleButtonPressed(Bed.BedType.TIC);
        });
        timBtn.setOnAction(param -> {
            handleButtonPressed(Bed.BedType.TIM);
        });
        tiipBtn.setOnAction(param -> {
            handleButtonPressed(Bed.BedType.TIIP);
        });
    }

    private void handleButtonPressed(Bed.BedType type) {
        try {
            Patient patient = patientsTableView.getSelectionModel().getSelectedItem();
            srv.giveBedToPatient(patient, type);
            updatePatientList();
        } catch (NoEmptyBedsException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            a.show();
        }
    }

    private void updatePatientList() {
        patients.setAll(srv.getWaitingPatients());
        patients.sort((a, b) -> {
            if (a.getSeverity() < b.getSeverity())
                return 1;
            else if (b.getSeverity() < a.getSeverity()) {
                return -1;
            }
            return 0;
        });
    }
}
