module com.proyectointermodular {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;

    opens com.proyectointermodular to javafx.fxml;
    exports com.proyectointermodular;
}
