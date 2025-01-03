package com.example.primerparcial.presentacion.factoryMethod;

public class CreadorExportadorCSV extends CreadorExportador {

    @Override
    public ExportadorCronograma crearExportador() {
        return new ExportadorCSV();
    }
}
