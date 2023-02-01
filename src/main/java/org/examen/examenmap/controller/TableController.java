package org.examen.examenmap.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

        itemsByCategory.keySet().forEach(category -> {
            categoryVBox.getChildren().add(new Label(category));

            TableView<MenuItem> categoryTableView = new TableView<>();
            categoryTableView.setMaxHeight(Double.MAX_VALUE);

            TableColumn<MenuItem, String> itemNameColumn = new TableColumn<>("ITEM");
            itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("item"));

            TableColumn<MenuItem, String> priceColumn = new TableColumn<>("PRICE");
            priceColumn.setCellValueFactory(cellData ->
                    new SimpleStringProperty(
                            cellData.getValue().getPrice() + " " + cellData.getValue().getCurrency())
            );

            categoryTableView.getColumns().add(0, itemNameColumn);
            categoryTableView.getColumns().add(1, priceColumn);

            ObservableList<MenuItem> obsItemList = FXCollections.observableArrayList(
                    srv.getMenuItemsFromCategory(category)
            );
            categoryTableView.setItems(obsItemList);

            categoryVBox.getChildren().add(categoryTableView);
        });

        setPlaceOrderButtonAction();
    }

    private void setPlaceOrderButtonAction() {
        placeOrderButton.setOnAction(param ->
            categoryViews.forEach(view ->
                    view.getSelectionModel().getSelectedItems()
                            .forEach(itemRow -> System.out.println(itemRow.getItem().getItem()))
            )
        );
    }
}
