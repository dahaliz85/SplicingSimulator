package com.uvm.biomedica.util;

public class Constants {

    // Constantes MainController.java
    public static final int INITIAL_OFFSET = 0;
    public static final int ICON_FIT_WIDTH = 16;
    public static final int ICON_FIT_HEIGHT = 16;
    public static final String ICONS_PATH = "/com/uvm/biomedica/icons/";
    public static final String ICONS_ICON_AWAITING_FILE = "awaiting_file";
    public static final String ICONS_ICON_SPLICING_PROCESS = "splicing_process";
    public static final String ICONS_ICON_SIMULATION_COMPLETED = "simulation_completed";
    public static final String ICONS_ICON_VARIANT_DETECTED = "variant_detected";
    public static final String ICONS_ICON_EXTENSION = ".png";
    public static final String INSTRUCTIONS_HEALTH_SEQUENCE = "Use el botón superior para cargar el archivo FASTA del gen silvestre (Sano)...";
    public static final String INSTRUCTIONS_MUTATED_SEQUENCE = "Use el botón superior para cargar el archivo FASTA con la variante hEDS...";
    public static final String LBL_MONTE_CARLO_PROGRESS = "Progreso de Monte Carlo";
    public static final String TBL_COL_HEADER_TIPO = "tipo";
    public static final String TBL_COL_HEADER_START = "inicio";
    public static final String TBL_COL_HEADER_END = "fin";
    public static final String LBL_STATUS_STARTED_SYSTEM = "Sistema Iniciado";
    public static final String LBL_STATUS_LOADED_DATA = "Datos cargados: ";
    public static final String LBL_STATUS_IDENTIFIED_SEGMENTS = " segmentos identificados.";
    public static final String LBL_STATUS_HEALTH_FILE_ERROR = "Error al procesar el archivo sano.";
    public static final String LBL_STATUS_MUTATED_LOADED_DATA = "Grupo mutado cargado correctamente.";
    public static final String LBL_STATUS_MUTATED_FILE_ERROR = "Error al cargar el archivo mutado.";
    public static final String LBL_STATUS_PROCESSING_SPLICING = " Splicing en curso..." ;
    public static final String LBL_STATUS_MISSING_FILE_ERROR = "❌ Carga ambos archivos antes de simular.";
    public static final String LBL_CHART_HEALTH_SERIES = "Secuencia Control";
    public static final String LBL_CHART_MUTATED_SERIES = "Secuencia de Prueba";
    public static final String LBL_CONTROL_GROUP_EFFICIENCY = "[ Eficiencia Sano: %.2f%% ]";
    public static final String LBL_TEST_GROUP_EFFICIENCY = "[ Eficiencia Mutado: %.2f%% ]";
    public static final String LBL_SIMULATION_COMPLETED = " Simulación completada";
    public static final String LBL_NORMAL_DIAGNOSIS = "Diagnóstico: Splicing Normal (Grupo Control / Variante Sin Impacto)";
    public static final String LBL_ALTERED_DIAGNOSIS = "Diagnóstico: Eficiencia de Splicing Alterada (Variante Significativa)";
    public static final String LBL_PATHOGENIC_VARIANT_DIAGNOSIS = "Diagnóstico: Exon Skipping Detectado (Variante Patogénica hEDS)";
    public static final String RGX_EXON_DATA = "exon\\s+(\\d+)\\.\\.(\\d+)";
    public static final String TXT_EXON_POINT = "> EXÓN ";
    public static final String TXT_EXON_CODIFICANT_SEQUENCE = "[Secuencia Codificante Spliced] ...\n\n";
    public static final String TXT_VARIANT_ALTERATION = " [Variante - Exon Skipping Alteration] ";
    public static final String TXT_NORMAL_SEQUENCE = " [Secuencia Codificante Spliced - Grupo Control] " ;

    // Constantes IntronData.java
    public static final String STR_TYPE_INTRON = "Intrón";

    // Constantes ExonData.java
    public static final String STR_TYPE_EXON = "Exón";

    // Constantes principal_view.fxml
    public static final String APP_TITLE = "COL3A1 Splicing Simulator v1.0";
}
