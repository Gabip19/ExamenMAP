module org.examen.examenmap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.examen.examenmap to javafx.fxml;
    exports org.examen.examenmap;
    opens org.examen.examenmap.controller to javafx.fxml;
    exports org.examen.examenmap.controller;
}