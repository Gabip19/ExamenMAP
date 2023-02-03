module org.examen.examenmap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.examen.examenmap.gui to javafx.fxml;
    exports org.examen.examenmap.gui;
    opens org.examen.examenmap.domain to javafx.fxml;
    exports org.examen.examenmap.domain;
    opens org.examen.examenmap to javafx.fxml;
    exports org.examen.examenmap;
    exports org.examen.examenmap.controller;
    opens org.examen.examenmap.controller to javafx.fxml;
}