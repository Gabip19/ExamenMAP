package org.examen.examenmap.service;

import org.examen.examenmap.domain.Bed;
import org.examen.examenmap.domain.Patient;
import org.examen.examenmap.domain.exceptions.NoEmptyBedsException;
import org.examen.examenmap.repository.Repository;
import org.examen.examenmap.repository.database.PatientDatabaseRepo;
import org.examen.examenmap.utils.events.ChangeEventType;
import org.examen.examenmap.utils.events.PatientEvent;
import org.examen.examenmap.utils.observers.ConcreteObservable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class Service extends ConcreteObservable<PatientEvent> {
    private final PatientDatabaseRepo patientRepo;
    private final Repository<UUID, Bed> bedRepo;

    public Service(PatientDatabaseRepo patientRepo, Repository<UUID, Bed> bedRepo) {
        this.patientRepo = patientRepo;
        this.bedRepo = bedRepo;

//        patientRepo.save(new Patient("1234567891234", 10, false, "blabla", 1));
//        bedRepo.save(new Bed(false, "1234567891234", Bed.BedType.TIIP));
    }

    public ArrayList<Patient> getAllPatients() {
        return new ArrayList<>((Collection<Patient>) patientRepo.findAll());
    }

    public ArrayList<Bed> getAllBeds() {
        return new ArrayList<>((Collection<Bed>) bedRepo.findAll());
    }

    public ArrayList<Patient> getWaitingPatients() {
        return patientRepo.getWaitingPatients();
    }

    public void giveBedToPatient(Patient patient, Bed.BedType bedType, boolean ventilation) {
        Optional<Bed> emptyBedOpt = getAllBeds()
                .stream()
                .filter(bed -> {
                    if (bed.getType().equals(bedType) && bed.getPatientCNP() == null) {
                        if (ventilation) {
                            return bed.hasVentilation();
                        }
                        return true;
                    }
                    return false;
                })
                .findFirst();
        if (emptyBedOpt.isPresent()) {
            Bed bed = emptyBedOpt.get();
            bed.setPatientCNP(patient.getCNP());
            bedRepo.update(bed);
        } else {
            throw new NoEmptyBedsException("No empty beds for given type.\n");
        }
        notifyObservers(new PatientEvent(ChangeEventType.ADD, patient));
    }
}
