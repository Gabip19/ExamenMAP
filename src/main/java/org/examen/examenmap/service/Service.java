package org.examen.examenmap.service;

import org.examen.examenmap.domain.Locality;
import org.examen.examenmap.domain.River;
import org.examen.examenmap.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Service {
    private final Repository<UUID, River> riverRepo;
    private final Repository<UUID, Locality> localityRepo;

    public Service(Repository<UUID, River> riverRepo, Repository<UUID, Locality> localityRepo) {
        this.riverRepo = riverRepo;
        this.localityRepo = localityRepo;
//        localityRepo.save(new Locality("Localitate 11111", UUID.fromString("ab649567-c16b-4246-9757-2d653b0fd0b6"), 10, 20));
//        localityRepo.save(new Locality("Localitate 22222", UUID.fromString("ab649567-c16b-4246-9757-2d653b0fd0b6"), 15, 40));
//        localityRepo.save(new Locality("Localitate 33333", UUID.fromString("679c7347-0c22-4354-b9e9-d5f7bbea63c8"), 20, 37));
//        localityRepo.save(new Locality("Localitate 44444", UUID.fromString("c1a3a011-25ef-4009-b1f2-6c7ea4294335"), 30, 50));
//        localityRepo.save(new Locality("Localitate 55555", UUID.fromString("499ee16e-6da1-44be-bc6a-8341e8371db0"), 40, 60));
//        localityRepo.save(new Locality("Localitate 66666", UUID.fromString("1a6c8a53-1b75-4093-96ea-cf4bc7594099"), 15, 20));
//        localityRepo.save(new Locality("Localitate 77777", UUID.fromString("1a6c8a53-1b75-4093-96ea-cf4bc7594099"), 20, 25));
    }

    public ArrayList<River> getAllRivers() {
        return new ArrayList<>((Collection<River>) riverRepo.findAll());
    }

    public ArrayList<Locality> getAllLocalities() {
        return new ArrayList<>((Collection<Locality>) localityRepo.findAll());
    }

    public River findRiverById(UUID riverId) {
        return riverRepo.findOne(riverId);
    }

    public void updateRiver(River riverToUpdate) {
        riverRepo.update(riverToUpdate);
    }
}
