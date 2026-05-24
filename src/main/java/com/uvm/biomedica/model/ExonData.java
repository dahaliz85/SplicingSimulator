package com.uvm.biomedica.model;

import com.uvm.biomedica.util.Constants;

public class ExonData extends FeatureGenetica {
    public ExonData(int inicio, int fin) { super(inicio, fin); }
    public String getTipo() { return Constants.STR_TYPE_EXON; }
}