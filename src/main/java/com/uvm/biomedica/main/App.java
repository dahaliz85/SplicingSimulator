package com.uvm.biomedica.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Cargamos el FXML usando la ruta relativa desde tus recursos
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/uvm/biomedica/views/vista_principal.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("COL3A1 Splicing Analyzer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}