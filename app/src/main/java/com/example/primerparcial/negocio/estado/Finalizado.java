package com.example.primerparcial.negocio.estado;

public class Finalizado implements EstadoCronograma {
    @Override
    public void avanzarEstado(Contexto contexto) {
        System.out.println("El cronograma ya está finalizado.");
    }

    @Override
    public void retrocederEstado(Contexto contexto) {
        contexto.setEstado(new EnProgreso());
    }

    @Override
    public String obtenerDescripcion() {
        return "Finalizado";
    }
}