package com.example.primerparcial.presentacion.factoryMethod;

import java.util.List;

public class ExportadorCSV implements ExportadorCronograma {

    @Override
    public String exportar(List<String> cronogramas) {

        StringBuilder csv = new StringBuilder();

        for (String elemento : cronogramas) {
            csv.append(elemento).append("\n");
        }

        return csv.toString();
    }
}
