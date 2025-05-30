package com.uptc.is.util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    public static Font loadFont(String path, float size) {
        try (InputStream is = FontLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Font not found at: " + path);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (IOException | FontFormatException e) {
            System.err.println("Error loading font: " + e.getMessage());
            // Devuelve una fuente por defecto si falla la carga
            return new Font("SansSerif", Font.PLAIN, (int) size);
        }
    }

}
