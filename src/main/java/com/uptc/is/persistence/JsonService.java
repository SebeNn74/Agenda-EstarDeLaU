package com.uptc.is.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonService {

    private final ObjectMapper objectMapper;

    public JsonService() {
        this.objectMapper = new ObjectMapper();
        // Configurar ObjectMapper
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Json Identado
        this.objectMapper.registerModule(new JavaTimeModule()); // Soporte para Java Date/Time
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Fechas como String
    }

    /**
     * Lee una lista de objetos desde un archivo JSON.
     * @param filePath Ruta al archivo JSON.
     * @param typeReference TypeReference que describe el tipo de lista a leer (ej. new TypeReference<List<Cashier>>() {}).
     * @return Lista de objetos leídos. Devuelve una lista vacía si el archivo no existe o está vacío.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    public <T> List<T> readListFromFile(String filePath, TypeReference<List<T>> typeReference) throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            // Si el archivo no existe o está vacío, creamos el directorio si es necesario y devolvemos una lista vacía.
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, typeReference);
    }

    /**
     * Escribe una lista de objetos a un archivo JSON.
     * @param filePath Ruta al archivo JSON.
     * @param data Lista de objetos a escribir.
     * @throws IOException Si ocurre un error durante la escritura.
     */
    public <T> void writeListToFile(String filePath, List<T> data) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // Crea los directorios padres si no existen
        }
        objectMapper.writeValue(file, data);
    }

}
