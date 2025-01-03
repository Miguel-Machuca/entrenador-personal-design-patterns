package com.example.primerparcial.presentacion.factoryMethod;

import com.google.gson.Gson;

import java.util.List;

public class ExportadorJSON implements ExportadorCronograma {
    @Override
    public String exportar(List<String> cronograma) {
        Gson gson = new Gson();
        return gson.toJson(cronograma);
    }
}
