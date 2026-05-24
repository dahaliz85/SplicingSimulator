# In Silico Computational Modeling of Ribosomal Translation Kinetics & Splicing Simulator

A specialized bioinformatic desktop application developed in **JavaFX** and managed with **Maven**. This simulator performs predictive analytics and stochastic simulation (*Monte Carlo method*) on GenBank files (`.gb`/`.gbk`) to evaluate splicing efficiency and structural alterations in the ***COL3A1*** gene, specifically mapping pathogenic variants associated with hypermobile Ehlers-Danlos Syndrome (hEDS).

---

## 🧬 Biological Context & Overview

Alternative splicing is a critical post-transcriptional process where introns are removed and exons are joined. Mutations in splice sites or regulatory elements can trigger **Exon Skipping**, drastically shifting the open reading frame or deleting crucial structural domains in proteins like Collagen Type III Alpha 1 (*COL3A1*).

This tool provides an *in silico* framework to:
* **Parse & Extract:** Dynamically extract coordinate intervals for exons and introns from native GenBank files.
* **Structural Comparison:** Run real-time alignment algorithms between a Control (Wildtype) dataset and an Analyzed (Mutated/Patient) sequence.
* **Stochastic Modeling:** Execute a multi-iteration Monte Carlo simulation to plot convergence curves, incorporating Gaussian noise variables to represent physiological translation kinetics.

---

## 🛠️ Key Architectural Features

* **Dynamic UI Rendering:** Built with a fully responsive, modern dark-themed custom JavaFX interface.
* **Refined Resource Handling:** Native encapsulation of state icons (`awaiting_files`, `splicing_process`, `simulation_completed`, `variant_detected`) mapped straight to the Maven Classpath.
* **Decoupled Business Logic:** Clear separation between data parsing algorithms (`ExonData`, `IntronData`, `FeatureGenetica` inheritance models) and the JavaFX GUI Controller.
* **Real-time Visual Analytics:** Live tracking charts displaying stochastic convergence across parallel iteration points.

---

## 📁 Repository Structure

```text
SplicingSimulator/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── uvm/
│   │   │           └── biomedica/
│   │   │               ├── MainApp.java            # Application Entry Point
│   │   │               ├── controller/
│   │   │               │   └── MainController.java # UI Logic & Event Handlers
│   │   │               └── model/
│   │   │                   ├── FeatureGenetica.java # Abstract base class
│   │   │                   ├── ExonData.java        # Exon structural model
│   │   │                   └── IntronData.java      # Intron structural model
│   │   └── resources/
│   │       ├── com/
│   │       │   └── uvm/
│   │       │       └── biomedica/
│   │       │           ├── views.fxml              # Primary Layout Design
│   │       │           └── icons/                  # State-driven System PNG Icons
│   │       └── styles.css                          # Custom Dark Theme Stylesheet
├── pom.xml                                         # Maven Dependency Configuration
└── README.md                                       # Documentation