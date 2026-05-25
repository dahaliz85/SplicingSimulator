package com.uvm.biomedica.util;

import java.util.HashMap;
import java.util.Map;

public class BiologicalWeight {

    private Map<String, Double> mapBiologicalWeight;

    public BiologicalWeight(){
        this.mapBiologicalWeight = Map.of(
                "TNXB", 0.22,
                "COL1A1", 0.15,
                "COL3A1", 0.18,
                "COL5A1", 0.16
        );
    }

    public Map<String, Double> getMapBiologicalWeight() {
        return mapBiologicalWeight;
    }
}
