package com.uptc.is.model.repository;



import com.uptc.is.model.domain.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    /**
     * Crea un nuevo horario.
     */
    void create(Schedule schedule);

    /**
     * Busca un horario a partir de si id.
     */
    Optional<Schedule> searchById(String id);

    /**
     * Obtiene todos los horarios.
     */
    List<Schedule> getAll();

    /**
     * Obtiene todos los horarios a partir del nuip del cajero.
     */
    List<Schedule> getAllById(String cashierId);

    /**
     * Actualiza un horario mandado por parametro.
     */
    void update(Schedule schedule);

    /**
     * Elimina un horario a partir de su id.
     */
    void remove(String id);
}
