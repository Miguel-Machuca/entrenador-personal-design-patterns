package com.example.primerparcial.presentacion.factoryMethod;


public class CreadorExportadorPDF extends CreadorExportador {

    @Override
    public ExportadorCronograma crearExportador() {
        return new ExportadorPDF();
    }
}