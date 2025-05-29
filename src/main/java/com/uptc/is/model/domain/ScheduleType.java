package com.uptc.is.model.domain;

public enum ScheduleType {

    WORKING("Horario Laboral"),
    CLASS("Horario de Clases");

    private final String description;

    ScheduleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
