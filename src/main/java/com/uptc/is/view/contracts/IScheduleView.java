package com.uptc.is.view.contracts;

import com.uptc.is.model.domain.Schedule;
import com.uptc.is.model.domain.ScheduleType;
import com.uptc.is.presenter.SchedulePresenter;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IScheduleView {

    /**
     * Muestra una lista de horarios en la interfaz de usuario.
     * @param schedules Lista de objetos Schedule a mostrar.
     */
    void displaySchedules(List<Schedule> schedules);

    /**
     * Establece un título o mensaje de contexto para la vista de horarios.
     * Ej: "-Tipo de horario- de -Nombre-"
     * @param contextMessage El mensaje de contexto.
     */
    void setViewContext(String contextMessage, ScheduleType type);

    /**
     * Limpia los campos del input de entrada/edición de datos del horario.
     */
    void clearForm();

    /**
     * Solicitud para creación de un horario
     */
    void createSchedule();

    /**
     * Solicitud para eliminación de un horario
     */
    void removeSchedule();

    /**
     * Solicitud para creación de una franja horaria
     */
    void createTimeSlot();

    /**
     * Solicitud para eliminación de una franja horaria
     */
    void removeTimeSlot();

    /**
     * Obtiene la fecha seleccionada por el usuario.
     * @return El LocalDate.
     */
    LocalDate getDateInput();

    /**
     * Obtiene el día de la semana seleccionado/ingresado por el usuario.
     * @return El DayOfWeek.
     */
    DayOfWeek getDayOfWeekInput();

    /**
     * Obtiene la hora de inicio seleccionada/ingresada por el usuario.
     * @return El LocalTime de inicio.
     */
    LocalTime getStartTimeInput();

    /**
     * Obtiene la hora de fin seleccionada/ingresada por el usuario.
     * @return El LocalTime de fin.
     */
    LocalTime getEndTimeInput();

    /**
     * Obtiene el ID del horario actualmente seleccionado en la lista/tabla.
     * Usado para identificar qué horario se quiere eliminar.
     * @return El ID del horario seleccionado, o null si no hay selección.
     */
    String getSelectedScheduleId();

    /**
     * Obtiene el ID de la franja horaria actualmente seleccionado en el horario.
     * Usado para identificar qué franja horaria se quiere eliminar.
     * @return El ID de la franja horaria seleccionada, o null si no hay selección.
     */
    String getSelectedTimeSlotId();

    /**
     * Muestra el panel de formulario de cajeros.
     */
    void showScheduleFormPanel();

    /**
     * Muestra el panel de listado de cajeros.
     */
    void showScheduleListPanel();

    /**
     * Rellena los campos del input con los datos de un horario específico (para edición).
     * @param schedule El objeto Schedule cuyos datos se mostrarán.
     */
    void showScheduleDetails(Schedule schedule);

    /**
     * Muestra un mensaje de error específico de la gestión de horarios.
     * @param message El mensaje de error a mostrar.
     */
    void displayError(String message);

    /**
     * Muestra un mensaje de éxito específico de la gestión de horarios.
     * @param message El mensaje de éxito a mostrar.
     */
    void displayMessage(String message);

    /**
     * Establece el presentador para esta vista.
     * @param presenter El presentador que controlará esta vista.
     * (Se podría usar una interfaz ISchedulePresenter específica)
     */
    void setPresenter(SchedulePresenter presenter);

    JPanel getPanel();

    void setParentFrame(JFrame parentFrame);

}
