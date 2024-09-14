module evaluacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base; 	
    requires javafx.graphics; 	
    requires javafx.media;
    requires javafx.swing;	
    requires javafx.web;    
    //opens evaluacion to javafx;
    requires java.sql;
    requires mysql.connector.java;
    requires junit.jupiter.api; // Opcional para pruebas

    // Exporta los paquetes para que otros módulos los puedan usar
    exports com.example;
    exports com.example.dao;
    exports com.example.model;
}