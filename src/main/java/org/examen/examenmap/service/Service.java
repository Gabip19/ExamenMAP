package org.examen.examenmap.service;

import org.examen.examenmap.domain.MenuItem;
import org.examen.examenmap.domain.Table;
import org.examen.examenmap.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Service {
    private final Repository<Integer, Table> tableRepo;
    private final Repository<Integer, MenuItem> menuRepo;

    public Service(Repository<Integer, Table> tableRepo, Repository<Integer, MenuItem> menuRepo) {
        this.tableRepo = tableRepo;
        this.menuRepo = menuRepo;
    }

    public ArrayList<Table> getAllTables() {
        return new ArrayList<>((Collection<Table>) tableRepo.findAll());
    }

    public ArrayList<MenuItem> getAllMenuItems() {
        return new ArrayList<>((Collection<MenuItem>) menuRepo.findAll());
    }

    public List<MenuItem> getMenuItemsFromCategory(String category) {
        return getAllMenuItems().stream()
                .filter(item -> item.getCategory().equals(category)).toList();
    }
}
