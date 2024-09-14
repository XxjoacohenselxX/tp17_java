module evaluacionesapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Si usas JDBC, también necesitarás este módulo

    // Exportar el paquete que contiene tu aplicación principal y otros paquetes
    exports evaluacion;
    // Si usas fxml y necesitas acceder a paquetes que contienen controladores de fxml
    opens evaluacion to javafx.fxml;

    //opens evaluacion to javafx.controls;
}
