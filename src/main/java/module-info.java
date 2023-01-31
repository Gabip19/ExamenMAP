module org.examen.examenmap {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.examen.examenmap to javafx.fxml;
    exports org.examen.examenmap;
}