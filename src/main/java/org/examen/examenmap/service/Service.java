package org.examen.examenmap.service;

import org.examen.examenmap.domain.Bed;
import org.examen.examenmap.domain.Patient;
import org.examen.examenmap.repository.Repository;
import org.examen.examenmap.repository.database.PatientDatabaseRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Service {
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
}
