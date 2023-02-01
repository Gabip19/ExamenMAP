package org.examen.examenmap.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Order extends Entity<Integer> {
    private int tableId;
    private ArrayList<Integer> menuItemIds;
    private LocalDateTime date;
    private OrderStatus orderStatus;

    public enum OrderStatus {
        PLACED,
        PREPARING,
        SERVED
    }

    public Order(int tableId, ArrayList<Integer> menuItemIds, OrderStatus orderStatus) {
        this.tableId = tableId;
        this.menuItemIds = menuItemIds;
        this.orderStatus = orderStatus;
    }

    public ArrayList<Integer> getMenuItemIds() {
        return menuItemIds;
    }

    public void setMenuItemIds(ArrayList<Integer> menuItemIds) {
        this.menuItemIds = menuItemIds;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
