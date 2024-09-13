module com.example.evaluacionescrud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires junit.jupiter.api; // Opcional para pruebas

    // Exporta los paquetes para que otros módulos los puedan usar
    exports com.example;
    exports com.example.dao;
    exports com.example.model;
}
