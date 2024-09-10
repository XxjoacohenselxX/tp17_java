import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionDAO {

    public List<Evaluacion> readAll(int offset, int limit) throws SQLException {
        String query = "SELECT * FROM Evaluaciones LIMIT ? OFFSET ?";
        List<Evaluacion> evaluaciones = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Evaluacion evaluacion = new Evaluacion();
                evaluacion.setId(rs.getInt("id"));
                evaluacion.setNombre(rs.getString("nombre"));
                evaluacion.setApellido(rs.getString("apellido"));
                evaluacion.setDNI(rs.getString("DNI"));
                evaluacion.setFechaProgramadaExamen(rs.getTimestamp("fecha_programada_examen"));
                evaluacion.setFechaInicioExamen(rs.getTimestamp("fecha_inicio_examen"));
                evaluacion.setFechaFinExamen(rs.getTimestamp("fecha_fin_examen"));
                evaluacion.setPuntaje(rs.getDouble("puntaje"));
                evaluacion.setEmail(rs.getString("email"));
                evaluaciones.add(evaluacion);
            }
        }

        return evaluaciones;
    }
}
