package com.uvm.biomedica.controller;

import com.uvm.biomedica.model.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;

import java.util.*;
import java.util.regex.*;

public class MainController {

    // Componentes de la Barra Superior
    @FXML private Button btnCargarSano;
    @FXML private Button btnCargarMutado;
    @FXML private Button btnSimular;
    @FXML private Label lblEstado;

    // Componentes del Panel Izquierdo (Gen Sano)
    @FXML private TableView<FeatureGenetica> tblCoordenadasSanas;
    @FXML private TableColumn<?, ?> colExonSano;
    @FXML private TableColumn<?, ?> colRangoSano;
    @FXML private TextArea txtSecuenciaSana;
    @FXML private TableColumn<FeatureGenetica, String> colTipoSano;
    @FXML private TableColumn<FeatureGenetica, Integer> colInicioSano;
    @FXML private TableColumn<FeatureGenetica, Integer> colFinSano;

    // Componentes del Panel Derecho (Variante Mutada)
    @FXML private TableView<FeatureGenetica> tblCoordenadasMutadas;
    @FXML private TableColumn<?, ?> colExonMutado;
    @FXML private TableColumn<?, ?> colRangoMutado;
    @FXML private TableColumn<FeatureGenetica, String> colTipoMut;
    @FXML private TableColumn<FeatureGenetica, Integer> colInicioMut;
    @FXML private TableColumn<FeatureGenetica, Integer> colFinMut;
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

        colTipoSano.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colInicioSano.setCellValueFactory(new PropertyValueFactory<>("inicio"));
        colFinSano.setCellValueFactory(new PropertyValueFactory<>("fin"));

        colTipoMut.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colInicioMut.setCellValueFactory(new PropertyValueFactory<>("inicio"));
        colFinMut.setCellValueFactory(new PropertyValueFactory<>("fin"));
    }

    /**
     * Acción para el botón "Cargar Secuencia Sana"
     */
    @FXML
    void handleCargarSano() {
        File archivo = abrirSelectorArchivo();
        if (archivo != null) {
            try {
                List<FeatureGenetica> datos = procesarGenBank(archivo);

                // Convertimos la lista a ObservableList para la tabla
                ObservableList<FeatureGenetica> itemsTabla = FXCollections.observableArrayList(datos);

                // Asignamos a tu tabla (asegúrate de que el nombre sea el correcto)
                tblCoordenadasSanas.setItems(itemsTabla);

                lblEstado.setText("● Datos cargados: " + datos.size() + " segmentos identificados.");
            } catch (Exception e) {
                lblEstado.setText("Error al procesar el archivo.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Acción para el botón "Cargar Secuencia Mutada"
     */
    @FXML
    void handleCargarMutado() {
        File archivo = abrirSelectorArchivo(); // Reutiliza el mismo método del FileChooser
        if (archivo != null) {
            try {
                List<FeatureGenetica> datos = procesarGenBank(archivo);
                tblCoordenadasMutadas.getItems().setAll(datos);
                lblEstado.setText("● Mutado cargado correctamente.");
            } catch (Exception e) {
                lblEstado.setText("Error al cargar el mutado.");
                e.printStackTrace();
            }
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

    private List<FeatureGenetica> procesarGenBank(File archivo) throws Exception {
        List<FeatureGenetica> listaFeatures = new ArrayList<>();
        List<ExonData> exones = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            Pattern patron = Pattern.compile("exon\\s+(\\d+)\\.\\.(\\d+)");

            while ((linea = reader.readLine()) != null) {
                Matcher m = patron.matcher(linea);
                if (m.find()) {
                    exones.add(new ExonData(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
                }
            }
        }

        // Ordenar exones por posición inicial
        exones.sort(Comparator.comparingInt(ExonData::getInicio));

        // Agregar exones a la lista final
        listaFeatures.addAll(exones);

        // Deducir intrones entre los exones
        for (int i = 0; i < exones.size() - 1; i++) {
            int finExonActual = exones.get(i).getFin();
            int inicioExonSiguiente = exones.get(i + 1).getInicio();

            if (inicioExonSiguiente > finExonActual + 1) {
                listaFeatures.add(new IntronData(finExonActual + 1, inicioExonSiguiente - 1));
            }
        }

        // Ordenar todo por posición para que la tabla se vea bien
        listaFeatures.sort(Comparator.comparingInt(FeatureGenetica::getInicio));
        return listaFeatures;
    }

}