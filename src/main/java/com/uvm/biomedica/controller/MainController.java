package com.uvm.biomedica.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;

public class MainController {

    // Componentes de la Barra Superior
    @FXML private Button btnCargarSano;
    @FXML private Button btnCargarMutado;
    @FXML private Button btnSimular;
    @FXML private Label lblEstado;

    // Componentes del Panel Izquierdo (Gen Sano)
    @FXML private TableView<?> tblCoordenadasSanas;
    @FXML private TableColumn<?, ?> colExonSano;
    @FXML private TableColumn<?, ?> colRangoSano;
    @FXML private TextArea txtSecuenciaSana;

    // Componentes del Panel Derecho (Variante Mutada)
    @FXML private TableView<?> tblCoordenadasMutadas;
    @FXML private TableColumn<?, ?> colExonMutado;
    @FXML private TableColumn<?, ?> colRangoMutado;
    @FXML private TextArea txtSecuenciaMutada;

    // Componentes del Panel Inferior (Control y Gráfica)
    @FXML private TextField txtIteraciones;
    @FXML private LineChart<Number, Number> chrConvergencia;

    // Componentes de la Tarjeta de Resultados
    @FXML private Label lblEficienciaSana;
    @FXML private Label lblEficienciaMutada;
    @FXML private Label lblDiagnostico;

    /**
     * Este método se ejecuta automáticamente cuando JavaFX termina de cargar el FXML.
     * Aquí podemos configurar estados iniciales o limpiar componentes.
     */
    @FXML
    public void initialize() {
        System.out.println("✅ Controlador de la UI inicializado con éxito.");

        // Configuramos un texto inicial amigable en las consolas de ADN
        txtSecuenciaSana.setText("Use el botón superior para cargar el archivo FASTA del gen silvestre (Sano)...");
        txtSecuenciaMutada.setText("Use el botón superior para cargar el archivo FASTA con la variante hEDS...");

        // Inicializar la gráfica con un contenedor de datos vacío para que no se vea rota
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Progreso de Monte Carlo");
        chrConvergencia.getData().add(series);
    }

    /**
     * Acción para el botón "Cargar Secuencia Sana"
     */
    @FXML
    void handleCargarSano() {
        File archivo = abrirSelectorArchivo();
        if (archivo != null) {
            leerArchivoYMostrar(archivo, txtSecuenciaSana, "Sano");
        }
    }

    /**
     * Acción para el botón "Cargar Secuencia Mutada"
     */
    @FXML
    void handleCargarMutado() {
        File archivo = abrirSelectorArchivo();
        if (archivo != null) {
            leerArchivoYMostrar(archivo, txtSecuenciaMutada, "Mutado");
        }
    }

    /**
     * Lógica para leer el archivo y actualizar la UI
     */
    private void leerArchivoYMostrar(File archivo, TextArea areaTexto, String tipo) {
        try {
            // Leemos el contenido
            String contenido = Files.readString(archivo.toPath());

            // Aquí puedes llamar a tu parser de GenBank después
            areaTexto.setText(contenido);

            lblEstado.setText("● " + tipo + " cargado: " + archivo.getName());

        } catch (Exception e) {
            lblEstado.setText("Error al cargar " + tipo);
            e.printStackTrace();
        }
    }

    /**
     * Lógica compartida para abrir el selector
     */
    private File abrirSelectorArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo GenBank");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos GenBank", "*.gb", "*.gbk")
        );
        return fileChooser.showOpenDialog(null);
    }

    /**
     * Acción para el botón "CORRER SIMULACIÓN"
     */
    @FXML
    void handleEjecutarSimulacion() {
        System.out.println("⚡ Botón: Iniciar simulación presionado.");
        String iteracionesTexto = txtIteraciones.getText();

        try {
            int iteraciones = Integer.parseInt(iteracionesTexto);
            lblEstado.setText("● Ejecutando simulación (n=" + iteraciones + ")...");

            // Simulación visual temporal para probar que la gráfica responde al dar clic
            XYChart.Series<Number, Number> series = chrConvergencia.getData().get(0);
            series.getData().clear(); // Limpiamos la gráfica anterior

            // Dibujamos una curva rápida de prueba
            for (int i = 0; i <= iteraciones; i += iteraciones / 10) {
                double eficienciaSimulada = 14.5 + (80.0 / (i + 1)); // Curva matemática de ejemplo
                series.getData().add(new XYChart.Data<>(i, eficienciaSimulada));
            }

            // Actualizamos los textos simulados de la entrega
            lblEficienciaSana.setText("[ Eficiencia Sano: 98.2% ]");
            lblEficienciaMutada.setText("[ Eficiencia Mutado: 14.5% ]");
            lblDiagnostico.setText("[ Diagnóstico: Exon Skipping Detectado ]");
            lblEstado.setText("● Simulación completada con éxito.");

        } catch (NumberFormatException e) {
            lblEstado.setText("❌ Error: Las iteraciones deben ser un número entero.");
            lblDiagnostico.setText("[ Diagnóstico: Error de parámetros ]");
        }
    }
}