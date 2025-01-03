package com.example.primerparcial.presentacion.factoryMethod;

public class CreadorExportadorJSON extends CreadorExportador {
    @Override
    public ExportadorCronograma crearExportador() {
        return new ExportadorJSON();
    }
}