import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class EvaluacionesApp extends Application {

    private TableView<Evaluacion> table = new TableView<>();
    private EvaluacionDAO dao = new EvaluacionDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Evaluaciones");

        TableColumn<Evaluacion, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        TableColumn<Evaluacion, String> apellidoCol = new TableColumn<>("Apellido");
        apellidoCol.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());

        TableColumn<Evaluacion, String> dniCol = new TableColumn<>("DNI");
        dniCol.setCellValueFactory(cellData -> cellData.getValue().DNIProperty());

        TableColumn<Evaluacion, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        // Agrega más columnas según sea necesario

        table.getColumns().addAll(nombreCol, apellidoCol, dniCol, emailCol);

        Button btnNuevo = new Button("Nuevo");
        btnNuevo.setOnAction(e -> showNewEvaluacionDialog());

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(btnNuevo);

        loadData();

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadData() {
        try {
            List<Evaluacion> evaluaciones = dao.readAll(0, 20);
            ObservableList<Evaluacion> data = FXCollections.observableArrayList(evaluaciones);
            table.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja el error de manera adecuada, por ejemplo, mostrando un mensaje al usuario
        }
    }

    private void showNewEvaluacionDialog() {
        // Implementa el método para mostrar un diálogo de nueva evaluación
    }

    public static void main(String[] args) {
        launch(args);
    }
}

