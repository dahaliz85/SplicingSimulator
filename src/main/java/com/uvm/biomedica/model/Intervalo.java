package com.uvm.biomedica.model; // Ajusta según el nombre de tu paquete

public class Intervalo {
    public int inicio;
    public int fin;
    public String tipo; // "Exón" o "Intrón"

    public Intervalo(int inicio, int fin, String tipo) {
        this.inicio = inicio;
        this.fin = fin;
        this.tipo = tipo;
    }

    // Agregamos getters por si los necesitas para tu tabla
    public int getInicio() { return inicio; }
    public int getFin() { return fin; }

    @Override
    public String toString() {
        return tipo + ": " + inicio + ".." + fin;
    }
}