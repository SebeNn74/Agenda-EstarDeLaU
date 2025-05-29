package com.uptc.is.util;

import java.awt.*;
import java.io.*;

public class FileOpener {

    public FileOpener(String name){
        try {
            //IDE
            File pdfFile = new File("resources/"+name);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
                return;
            }

            //JAR
            InputStream inputStream = FileOpener.class.getClassLoader().getResourceAsStream(name);
            if (inputStream == null) {
                System.out.println("No se encontró el archivo PDF dentro del JAR.");
                return;
            }

            // Crear un archivo temporal para extraer el PDF
            File tempFile = File.createTempFile("documento", ".pdf");
            tempFile.deleteOnExit();

            // Copiar el contenido del PDF desde el JAR al archivo temporal
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Abrir el PDF con la aplicación predeterminada
            Desktop.getDesktop().open(tempFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

