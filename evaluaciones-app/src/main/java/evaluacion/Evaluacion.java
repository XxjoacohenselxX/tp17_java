package evaluacion;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Timestamp;

public class Evaluacion {

    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty apellido = new SimpleStringProperty();
    private final StringProperty DNI = new SimpleStringProperty();
    private final ObjectProperty<Timestamp> fechaProgramadaExamen = new SimpleObjectProperty<>();
    private final ObjectProperty<Timestamp> fechaInicioExamen = new SimpleObjectProperty<>();
    private final ObjectProperty<Timestamp> fechaFinExamen = new SimpleObjectProperty<>();
    private final DoubleProperty puntaje = new SimpleDoubleProperty();
    private final StringProperty email = new SimpleStringProperty();

    public String getNombre() { return nombre.get(); }
    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public StringProperty nombreProperty() { return nombre; }

    public String getApellido() { return apellido.get(); }
    public void setApellido(String apellido) { this.apellido.set(apellido); }
    public StringProperty apellidoProperty() { return apellido; }

    public String getDNI() { return DNI.get(); }
    public void setDNI(String string) { this.DNI.set(string); }
    public StringProperty DNIProperty() { return DNI; }

    public Timestamp getFechaProgramadaExamen() { return fechaProgramadaExamen.get(); }
    public void setFechaProgramadaExamen(Timestamp fechaProgramadaExamen) { this.fechaProgramadaExamen.set(fechaProgramadaExamen); }
    public ObjectProperty<Timestamp> fechaProgramadaExamenProperty() { return fechaProgramadaExamen; }

    public Timestamp getFechaInicioExamen() { return fechaInicioExamen.get(); }
    public void setFechaInicioExamen(Timestamp fechaInicioExamen) { this.fechaInicioExamen.set(fechaInicioExamen); }
    public ObjectProperty<Timestamp> fechaInicioExamenProperty() { return fechaInicioExamen; }

    public Timestamp getFechaFinExamen() { return fechaFinExamen.get(); }
    public void setFechaFinExamen(Timestamp fechaFinExamen) { this.fechaFinExamen.set(fechaFinExamen); }
    public ObjectProperty<Timestamp> fechaFinExamenProperty() { return fechaFinExamen; }

    public double getPuntaje() { return puntaje.get(); }
    public void setPuntaje(double puntaje) { this.puntaje.set(puntaje); }
    public DoubleProperty puntajeProperty() { return puntaje; }

    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }
    public StringProperty emailProperty() { return email; }
	public void setDNI(int int1) {
		// TODO Auto-generated method stub
		
	}
	public void setId(int int1) {
		// TODO Auto-generated method stub
		
	}
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
