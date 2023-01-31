package org.examen.examenmap.domain;

import java.util.Objects;

public class MenuItem extends Entity<Integer> {

    private String category;
    private String item;
    private float price;
    private String currency;

    public MenuItem(int id, String category, String item, float price, String currency) {
        this.category = category;
        this.item = item;
        this.price = price;
        this.currency = currency;
        super.setId(id);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Float.compare(menuItem.price, price) == 0 && Objects.equals(category, menuItem.category) && Objects.equals(item, menuItem.item) && Objects.equals(currency, menuItem.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, item, price, currency);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "category='" + category + '\'' +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
