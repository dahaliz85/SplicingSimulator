package com.uvm.biomedica.model;

public class ExonData extends FeatureGenetica {
    public ExonData(int inicio, int fin) { super(inicio, fin); }
    public String getTipo() { return "Exón"; }
}