package org.examen.examenmap.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.examen.examenmap.domain.MenuItem;

public class MenuItemRow extends HBox {
    private MenuItem item;

    public MenuItemRow(MenuItem item) {
        super();
        this.item = item;
        getChildren().addAll(
                new Label(item.getItem()),
                new Label(item.getPrice() + " " + item.getCurrency())
        );
        setSpacing(10d);
    }

    public MenuItem getItem() {
        return item;
    }
}
