package com.uptc.is.model.repository;

import com.uptc.is.model.domain.Cashier;
import java.util.List;
import java.util.Optional;

public interface CashierRepository {

    /**
     * Crea un nuevo cajero.
     */
    void create(Cashier cashier);

    /**
     * Busca un cajero a partir de si nuip.
     */
    Optional<Cashier> searchById(String nuip);

    /**
     * Obtiene todos los cajeros.
     */
    List<Cashier> getAll();

    /**
     * Actualiza el cajero mandado por parametro.
     */
    void update(Cashier cashier);

    /**
     * Elimina un cajero a partir de su nuip.
     */
    void remove(String id);

}