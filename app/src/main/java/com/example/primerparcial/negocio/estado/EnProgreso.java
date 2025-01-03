package com.example.primerparcial.negocio.estado;

public class EnProgreso implements EstadoCronograma {
    @Override
    public void avanzarEstado(Contexto contexto) {
        contexto.setEstado(new Finalizado());
    }

    @Override
    public void retrocederEstado(Contexto contexto) {
        contexto.setEstado(new Pendiente());
    }

    @Override
    public String obtenerDescripcion() {
        return "En Progreso";
    }
}