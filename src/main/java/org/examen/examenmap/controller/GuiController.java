package org.examen.examenmap.controller;

import org.examen.examenmap.service.Service;

public abstract class GuiController {
    protected static Service srv;

    public static void setSrv(Service srv) {
        GuiController.srv = srv;
    }

//    protected static Stage currentStage;
//    public static void setCurrentStage(Stage stage) {
//        GuiController.currentStage = stage;
//    }

//    public void closeWindow() {
//        currentStage.close();
//    }
}
