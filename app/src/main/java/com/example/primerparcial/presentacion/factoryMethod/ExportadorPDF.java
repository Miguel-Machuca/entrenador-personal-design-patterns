package com.example.primerparcial.presentacion.factoryMethod;

import android.os.Environment;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExportadorPDF implements ExportadorCronograma {

    public String exportar(List<String> cronogramas) {

        File directorio = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Cronogramas");
        if (!directorio.exists() && !directorio.mkdirs()) {
            return "Error al crear el directorio";
        }

        File archivoPDF = new File(directorio, "cronograma.pdf");

        try {

            PdfWriter writer = new PdfWriter(new FileOutputStream(archivoPDF));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Cronograma Exportado").setBold().setFontSize(16));

            for (String cronograma : cronogramas) {
                document.add(new Paragraph(cronograma));
            }

            document.close();
            return "Archivo PDF guardado en: " + archivoPDF.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear el archivo PDF: " + e.getMessage();
        }
    }
}