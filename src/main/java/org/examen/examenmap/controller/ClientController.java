package org.examen.examenmap.controller;

import javafx.scene.control.TableView;
import org.examen.examenmap.domain.Client;
import org.examen.examenmap.domain.SpecialOffer;

public class ClientController extends GuiController {
    public TableView<SpecialOffer> offersTableView;
    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }
}
