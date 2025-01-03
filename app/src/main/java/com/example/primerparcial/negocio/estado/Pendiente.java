package com.example.primerparcial.negocio.estado;

public class Pendiente implements EstadoCronograma {
    @Override
    public void avanzarEstado(Contexto contexto) {
        contexto.setEstado(new EnProgreso());
    }

    @Override
    public void retrocederEstado(Contexto contexto) {
        System.out.println("El cronograma ya está en estado inicial (Pendiente).");
    }

    @Override
    public String obtenerDescripcion() {
        return "Pendiente";
    }
}