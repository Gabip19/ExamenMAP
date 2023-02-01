package org.examen.examenmap.service;

import org.examen.examenmap.domain.MenuItem;
import org.examen.examenmap.domain.Order;
import org.examen.examenmap.domain.Table;
import org.examen.examenmap.repository.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Service {
    private final Repository<Integer, Table> tableRepo;
    private final Repository<Integer, MenuItem> menuRepo;
    private final Repository<Integer, Order> orderRepo;

    public Service(Repository<Integer, Table> tableRepo, Repository<Integer, MenuItem> menuRepo, Repository<Integer, Order> orderRepo) {
        this.tableRepo = tableRepo;
        this.menuRepo = menuRepo;
        this.orderRepo = orderRepo;
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

    public void placeOrder(int tableId, ArrayList<Integer> menuItemsIds) {
        Order order = new Order(tableId, menuItemsIds, LocalDateTime.now(), Order.OrderStatus.PLACED);
        orderRepo.save(order);
    }
}
