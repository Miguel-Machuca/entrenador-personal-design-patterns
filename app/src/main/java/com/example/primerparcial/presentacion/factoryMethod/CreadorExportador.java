package com.example.primerparcial.presentacion.factoryMethod;

import java.util.List;

public abstract class CreadorExportador {
    public abstract ExportadorCronograma crearExportador();

    public String exportarCronograma(Object cronograma) {
        if (cronograma instanceof List) {
            @SuppressWarnings("unchecked")
            List<String> cronogramaList = (List<String>) cronograma;
            ExportadorCronograma exportador = crearExportador();
            return exportador.exportar(cronogramaList);
        } else {
            return "Error: El formato de cronograma no es compatible.";
        }
    }
}
