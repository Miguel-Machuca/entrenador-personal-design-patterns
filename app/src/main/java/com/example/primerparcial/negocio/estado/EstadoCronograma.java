
package com.example.primerparcial.negocio.estado;

public interface EstadoCronograma {
    void avanzarEstado(Contexto contexto);
    void retrocederEstado(Contexto contexto);
    String obtenerDescripcion();
}
