package org.examen.examenmap.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.examen.examenmap.domain.MenuItem;
import org.examen.examenmap.gui.MenuItemRow;

import java.util.ArrayList;
import java.util.HashMap;

public class TableController extends GuiController {
    @FXML
    public VBox categoryVBox;
    @FXML
    public Button placeOrderButton;

    private final ArrayList<ListView<MenuItemRow>> categoryViews = new ArrayList<>();

    public void initialize() {
        HashMap<String, ArrayList<MenuItem>> itemsByCategory = new HashMap<>();
        srv.getAllMenuItems().forEach(item -> {
            if (!itemsByCategory.containsKey(item.getCategory())) {
                itemsByCategory.put(item.getCategory(), new ArrayList<>());
            }
            itemsByCategory.get(item.getCategory()).add(item);
        });

        itemsByCategory.keySet().forEach(item -> {
            categoryVBox.getChildren().add(new Label(item));

            ListView<MenuItemRow> categoryView = new ListView<>();
            ObservableList<MenuItemRow> rows = FXCollections.observableArrayList();
            srv.getAllMenuItems().stream()
                    .filter(param -> param.getCategory().equals(item))
                    .forEach(x -> {
                        MenuItemRow itemRow = new MenuItemRow(x);
                        rows.add(itemRow);
                    });
            categoryView.setItems(rows);
            categoryView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            categoryViews.add(categoryView);
            categoryVBox.getChildren().add(categoryView);
        });

        placeOrderButton.setOnAction(param ->
            categoryViews.forEach(view ->
                    view.getSelectionModel().getSelectedItems()
                            .forEach(itemRow -> System.out.println(itemRow.getItem().getItem()))
            )
        );
    }
}
