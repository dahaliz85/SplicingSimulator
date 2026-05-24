package com.uvm.biomedica.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Intento 1: Ruta absoluta estándar de Maven
        URL fxmlLocation = getClass().getResource("/com/uvm/biomedica/views/principal_view.fxml");

        // Intento 2: Por si acaso las carpetas se crearon planas con puntos
        if (fxmlLocation == null) {
            fxmlLocation = getClass().getResource("/com/uvm/biomedica/views/principal_view.fxml");
        }

        // Si ambos fallan, te arrojará este mensaje claro en la consola para saber qué pasó
        if (fxmlLocation == null) {
            System.out.println("❌ ERROR: No se encontró el archivo FXML.");
            System.out.println("Ruta intentada 1: /com/uvm/biomedica/views/principal_view.fxml");
            throw new IllegalStateException("Location is not set: El archivo FXML no existe en las rutas especificadas de resources.");
        }

        System.out.println("✅ FXML localizado con éxito en: " + fxmlLocation);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Parent root = fxmlLoader.load();

        // --- FUERZA LA CARGA DEL CSS ---
        String css = this.getClass().getResource("/com/uvm/biomedica/styles/styles.css").toExternalForm();
        root.getStylesheets().add(css);
        // -------------------------------
        stage.initStyle(StageStyle.UNDECORATED); // <--- Quita la barra blanca de Windows
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("COL3A1 Splicing Analyzer");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}