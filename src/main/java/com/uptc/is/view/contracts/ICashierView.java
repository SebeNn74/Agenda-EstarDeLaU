package com.uptc.is.view.contracts;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.ScheduleType;

import java.util.List;

public interface ICashierView {

    /**
     * Muestra una lista de cajeros.
     * @param cashiers Lista de objetos Cashier a mostrar.
     */
    void displayCashiers(List<Cashier> cashiers);

    /**
     * Limpia los campos del input de entrada/edición de datos del cajero.
     */
    void clearForm();

    /**
     * Obtiene el NUIP ingresado por el usuario en el input.
     * @return El NUIP como String.
     */
    String getNuipInput();

    /**
     * Obtiene los nombres ingresados por el usuario en el input.
     * @return Los nombres como String.
     */
    String getNamesInput();

    /**
     * Obtiene los apellidos ingresados por el usuario en el input.
     * @return Los apellidos como String.
     */
    String getSurnamesInput();

    /**
     * Obtiene el código de estudiante ingresado por el usuario en el input.
     * @return El código de estudiante como String.
     */
    String getStudentCodeInput();

    /**
     * Obtiene el número de teléfono ingresado por el usuario en el input.
     * @return El número de teléfono como String.
     */
    String getTelNumberInput();

    /**
     * Obtiene el email ingresado por el usuario en el input.
     * @return El email como String.
     */
    String getEmailInput();

    /**
     * Obtiene el NUIP del cajero actualmente seleccionado en la lista/tabla.
     * @return El NUIP del cajero seleccionado, o null si no hay selección.
     */
    String getSelectedCashierNuip();

    /**
     * Rellena los campos del input con los datos de un cajero específico (para edición).
     * @param cashier El objeto Cashier cuyos datos se mostrarán.
     */
    void showCashierDetails(Cashier cashier);

    /**
     * Muestra un mensaje de error específico de la gestión de cajeros.
     * @param message El mensaje de error a mostrar.
     */
    void displayErrorMessage(String message);

    /**
     * Muestra un mensaje de éxito específico de la gestión de cajeros.
     * @param message El mensaje de éxito a mostrar.
     */
    void displaySuccessMessage(String message);

    /**
     * Abre la vista o diálogo para gestionar los horarios (laborales o de clase) de un cajero específico.
     * @param cashierNuip El NUIP del cajero para el cual se gestionarán los horarios.
     * @param scheduleType El tipo de horario a gestionar (WORKING o CLASS).
     */
    void openScheduleManagementView(String cashierNuip, ScheduleType scheduleType);

    /**
     * Establece el presentador para esta vista.
     * @param presenter El presentador que controlará esta vista.
     * (Se podría usar una interfaz ICashierPresenter específica)
     */
    void setPresenter(Object presenter); // Reemplazar Object con ICashierPresenter

}
