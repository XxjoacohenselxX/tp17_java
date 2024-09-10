import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluacionDAOTest {

    private EvaluacionDAO dao;

    @BeforeEach
    public void setUp() {
        dao = new EvaluacionDAO();
        // Asegúrate de que la base de datos de prueba esté configurada y limpia antes de cada prueba
    }

    @Test
    public void testCreate() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNombre("Juan");
        evaluacion.setApellido("Pérez");
        evaluacion.setDNI("12345678901");
        evaluacion.setFechaProgramadaExamen(new Timestamp(System.currentTimeMillis()));
        evaluacion.setFechaInicioExamen(new Timestamp(System.currentTimeMillis()));
        evaluacion.setFechaFinExamen(new Timestamp(System.currentTimeMillis()));
        evaluacion.setPuntaje(85.5);
        evaluacion.setEmail("juan.perez@example.com");

        try {
            dao.create(evaluacion);
            // Verifica que la evaluación se haya insertado correctamente en la base de datos
            // Puedes hacer esto consultando la base de datos para ver si el registro existe
        } catch (SQLException e) {
            fail("SQLException was thrown: " + e.getMessage());
        }
    }
}
