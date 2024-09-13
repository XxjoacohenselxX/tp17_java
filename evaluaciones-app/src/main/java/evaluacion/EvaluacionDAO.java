package evaluacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionDAO {

    public List<Evaluacion> getAllEvaluaciones() throws SQLException {
        List<Evaluacion> evaluaciones = new ArrayList<>();
        String query = "SELECT nombre, apellido FROM Evaluaciones";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                evaluaciones.add(new Evaluacion(nombre, apellido));
            }
        }

        return evaluaciones;
    }

    public void insertEvaluacion(Evaluacion evaluacion) throws SQLException {
        String query = "INSERT INTO Evaluaciones (nombre, apellido) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, evaluacion.nombreProperty().get());
            pstmt.setString(2, evaluacion.apellidoProperty().get());
            pstmt.executeUpdate();
        }
    }
}
