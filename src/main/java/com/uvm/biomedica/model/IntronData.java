package com.uvm.biomedica.model;

import com.uvm.biomedica.util.Constants;

public class IntronData extends FeatureGenetica {
    public IntronData(int inicio, int fin) { super(inicio, fin); }
    public String getTipo() { return Constants.STR_TYPE_INTRON; }
}