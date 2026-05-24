package com.uvm.biomedica.model;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class FeatureGenetica {
    protected final SimpleIntegerProperty inicio;
    protected final SimpleIntegerProperty fin;

    public FeatureGenetica(int inicio, int fin) {
        this.inicio = new SimpleIntegerProperty(inicio);
        this.fin = new SimpleIntegerProperty(fin);
    }

    public int getInicio() { return inicio.get(); }
    public int getFin() { return fin.get(); }

}