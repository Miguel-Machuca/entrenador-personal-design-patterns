package com.example.primerparcial.negocio;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.primerparcial.dato.DCronograma;

import com.example.primerparcial.negocio.observer.Observer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NCronograma {
    private DCronograma dCronograma;

    private final List<Observer> observers = new ArrayList<>();
    private final List<String> fechasCronogramas = new ArrayList<>();

    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


    public void subscribe(Observer observer) {
        observers.add(observer);
    }


    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }


    private void notifySubscribers(String mensaje) {
        for (Observer observer : observers) {
            observer.update(mensaje);
        }
    }

    public void agregarCronograma(String fecha) {
        fechasCronogramas.add(fecha);
        verificarCronogramas();
    }


    public void verificarCronogramas() {
        Calendar hoy = Calendar.getInstance();
        StringBuilder mensajeRecordatorio = new StringBuilder("Cronogramas próximos:\n");

        for (String fecha : fechasCronogramas) {
            try {
                Date fechaCronograma = formatoFecha.parse(fecha);
                long diferencia = fechaCronograma.getTime() - hoy.getTimeInMillis();

                if (diferencia > 0 && diferencia <= 24 * 60 * 60 * 1000) { // Menos de 1 día
                    mensajeRecordatorio.append("- Cronograma el ").append(fecha).append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (!mensajeRecordatorio.toString().equals("Cronogramas próximos:\n")) {
            notifySubscribers(mensajeRecordatorio.toString());
        }
    }

    public NCronograma() {
        this.dCronograma = new DCronograma();
    }

    public NCronograma(int idCronograma, String fecha, int idCliente, int idRutina) {
        this.dCronograma = new DCronograma(idCronograma, fecha, idCliente, idRutina);
    }



    public void insertar(String fecha, int idCliente, int idRutina){
        try {
            dCronograma.insertar(fecha, idCliente, idRutina);
        } catch (Exception e) {
            System.err.println("Error al registrar el cronograma: " + e.getMessage());
        }
    }

    public void modificar(int idCronograma, String fecha, int idCliente, int idRutina){
        try {
            dCronograma.modificar(idCronograma, fecha, idCliente, idRutina);
        } catch (Exception e) {
            System.err.println("Error al actualizar el cronograma: " + e.getMessage());
        }
    }

    public void borrar(int idCronograma){
        try {
            dCronograma.borrar(idCronograma);
        } catch (Exception e) {
            System.err.println("Error al eliminar el cronograma: " + e.getMessage());
        }
    }

    public List<NCronograma> consultar() {
        List<NCronograma> lista = new ArrayList<>();
        for (DCronograma dc : dCronograma.consultar()) {
            lista.add(new NCronograma(dc.getIdCronograma(), dc.getFecha(), dc.getIdCliente(), dc.getIdRutina()));
        }
        return lista;
    }

    public NCronograma buscar(int idCronograma) {
        try {
            if (idCronograma != -1) {
                DCronograma dc = dCronograma.buscar(idCronograma);
                if (dc != null) {
                    return new NCronograma(dc.getIdCronograma(), dc.getFecha(), dc.getIdCliente(), dc.getIdRutina());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al buscar el cronograma: " + e.getMessage());
        }
        return null;
    }

    public void enviarMensajeWhatsAppSinImagen(Context context, String numeroTelefono, String mensaje) {
        try {
            // Crea un Intent para enviar el mensaje de texto
            Intent intent = new Intent(Intent.ACTION_VIEW);

            // Establece el tipo de contenido como texto
            intent.setType("text/plain");

            // Establece el texto a enviar
            intent.putExtra(Intent.EXTRA_TEXT, mensaje);

            // Añade el número de teléfono al Intent, si es necesario
            // Si deseas enviar el mensaje a un número específico, descomenta la siguiente línea
            // intent.putExtra("jid", numeroTelefono + "@s.whatsapp.net"); // Para enviar a un número específico

            // Asegura que el paquete de WhatsApp esté seleccionado para enviar el mensaje
            intent.setPackage("com.whatsapp");

            // Verifica si WhatsApp está instalado en el dispositivo
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent); // Abre WhatsApp para enviar el mensaje
            } else {
                Toast.makeText(context, "WhatsApp no está instalado en este dispositivo", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al abrir WhatsApp", Toast.LENGTH_LONG).show();
        }
    }


    public void enviarMensajeWhatsAppConImagen(Context context, String numeroTelefono, String mensaje, Uri imagenUri) {
        try {
            // Crea un Intent para enviar el mensaje con imagen
            Intent intent = new Intent(Intent.ACTION_SEND);

            // Establece el tipo de contenido como imagen y texto
            intent.setType("image/*");

            // Establece el texto y la imagen a enviar
            intent.putExtra(Intent.EXTRA_TEXT, mensaje);
            intent.putExtra(Intent.EXTRA_STREAM, imagenUri);

            // Asegura que el paquete de WhatsApp esté seleccionado para enviar el mensaje
            intent.setPackage("com.whatsapp");

            // Verifica si WhatsApp está instalado en el dispositivo
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent); // Abre WhatsApp para enviar el mensaje
            } else {
                Toast.makeText(context, "WhatsApp no está instalado en este dispositivo", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al abrir WhatsApp", Toast.LENGTH_LONG).show();
        }
    }

    public void enviarMensajeWhatsAppConImagenes(Context context, List<Uri> imagenesUri) {
        try {
            // Crea un Intent para enviar el mensaje con múltiples imágenes
            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);

            // Establece el tipo de contenido como imágenes
            intent.setType("image/*");

            // Establece la lista de imágenes a enviar
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, new ArrayList<>(imagenesUri)); // Asegúrate de que sea un ArrayList

            // Asegura que el paquete de WhatsApp esté seleccionado para enviar el mensaje
            intent.setPackage("com.whatsapp");

            // Verifica si WhatsApp está instalado en el dispositivo
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent); // Abre WhatsApp para enviar las imágenes
            } else {
                Toast.makeText(context, "WhatsApp no está instalado en este dispositivo", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al abrir WhatsApp", Toast.LENGTH_LONG).show();
        }
    }


    public void enviarImagenesWhatsApp(Context context, String numeroTelefono, List<Uri> imagenesUri) {
        try {
            // Crea un Intent para enviar el mensaje con múltiples imágenes
            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);

            // Establece el tipo de contenido como imágenes
            intent.setType("image/*");

            // Establece la lista de imágenes a enviar
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, new ArrayList<>(imagenesUri)); // Asegúrate de que sea un ArrayList

            // Asegura que el paquete de WhatsApp esté seleccionado para enviar el mensaje
            intent.setPackage("com.whatsapp");

            // Verifica si WhatsApp está instalado en el dispositivo
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent); // Abre WhatsApp para enviar las imágenes
            } else {
                Toast.makeText(context, "WhatsApp no está instalado en este dispositivo", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al abrir WhatsApp", Toast.LENGTH_LONG).show();
        }
    }

    public void enviarMensajeWhatsApp(Context context, String numeroTelefono, String mensaje) {
        try {
            // Crea un Intent para enviar un mensaje de texto
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://wa.me/" + numeroTelefono + "?text=" + Uri.encode(mensaje)));

            // Verifica si WhatsApp está instalado en el dispositivo
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent); // Abre WhatsApp para enviar el mensaje
            } else {
                Toast.makeText(context, "WhatsApp no está instalado en este dispositivo", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al abrir WhatsApp: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public DCronograma getdCronograma() {
        return dCronograma;
    }

    public void setdCronograma(DCronograma dCronograma) {
        this.dCronograma = dCronograma;
    }

    public void iniciarBD(Context context) {
        this.dCronograma.iniciarBD(context);
    }
}
