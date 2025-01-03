// Contexto.java
package com.example.primerparcial.negocio.estado;

public class Contexto {
    private EstadoCronograma estado;

    public Contexto() {
        // Estado inicial
        this.estado = new Pendiente();
    }

    public void setEstado(EstadoCronograma estado) {
        this.estado = estado;
    }

    public EstadoCronograma getEstado() {
        return estado;
    }

    public void avanzarEstado() {
        estado.avanzarEstado(this);
    }

    public void retrocederEstado() {
        estado.retrocederEstado(this);
    }

    public String obtenerDescripcionEstado() {
        return estado.obtenerDescripcion();
    }
}
