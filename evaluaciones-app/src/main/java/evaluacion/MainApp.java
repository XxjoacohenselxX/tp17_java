package evaluacion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Evaluaciones CRUD");

        BorderPane root = new BorderPane();
        TableView<Evaluacion> table = new TableView<>();
        TableColumn<Evaluacion, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        TableColumn<Evaluacion, String> apellidoCol = new TableColumn<>("Apellido");
        apellidoCol.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());

        table.getColumns().addAll(nombreCol, apellidoCol);

        Button btnNuevo = new Button("Nuevo");
        btnNuevo.setOnAction(e -> showNewDialog());

        root.setCenter(table);
        root.setBottom(btnNuevo);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showNewDialog() {
        // Implementación del diálogo para crear una nueva evaluación
    }

    public static void main(String[] args) {
        launch(args);
    }
}
