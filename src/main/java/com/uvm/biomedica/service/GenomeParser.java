package com.uvm.biomedica.service;
import com.uvm.biomedica.model.Intervalo;

import java.util.*;
import java.util.regex.*;

public class GenomeParser {

    public static List<Intervalo> procesarGenoma(String contenido) {
        List<Intervalo> exones = new ArrayList<>();
        List<Intervalo> listaResultante = new ArrayList<>();

        // Regex mejorado para capturar las coordenadas de los exones
        Pattern p = Pattern.compile("exon\\s+(\\d+)\\.\\.(\\d+)");
        Matcher m = p.matcher(contenido);

        while (m.find()) {
            int inicio = Integer.parseInt(m.group(1));
            int fin = Integer.parseInt(m.group(2));
            exones.add(new Intervalo(inicio, fin, "Exón"));
        }

        // Ordenamos por posición por si el archivo no viene en orden
        Collections.sort(exones, Comparator.comparingInt(e -> e.inicio));

        // Lógica de detección de intrones (el "hueco" entre exones)
        for (int i = 0; i < exones.size() - 1; i++) {
            listaResultante.add(exones.get(i)); // Agregar Exón

            int finActual = exones.get(i).fin;
            int inicioSiguiente = exones.get(i + 1).inicio;

            // Si hay un espacio, es un intrón
            if (inicioSiguiente > finActual + 1) {
                listaResultante.add(new Intervalo(finActual + 1, inicioSiguiente - 1, "Intrón"));
            }
        }
        // Agregar el último exón
        if (!exones.isEmpty()) {
            listaResultante.add(exones.get(exones.size() - 1));
        }

        return listaResultante;
    }


}