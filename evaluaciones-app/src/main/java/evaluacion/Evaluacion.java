package evaluacion;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Evaluacion {
    private final StringProperty nombre;
    private final StringProperty apellido;

    public Evaluacion(String nombre, String apellido) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }
}
