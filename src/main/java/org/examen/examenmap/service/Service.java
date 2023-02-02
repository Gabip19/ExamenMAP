package org.examen.examenmap.service;

import org.examen.examenmap.domain.River;
import org.examen.examenmap.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Service {
    private final Repository<UUID, River> riverRepo;

    public Service(Repository<UUID, River> riverRepo) {
        this.riverRepo = riverRepo;
    }

    public ArrayList<River> getAllRivers() {
        return new ArrayList<>((Collection<River>) riverRepo.findAll());
    }
}
