package com.uptc.is.view.contracts;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.presenter.CashierPresenter;

import javax.swing.*;
import java.util.List;

public interface ICashierView {

    /**
     * Muestra una lista de cajeros.
     * @param cashiers Lista de objetos Cashier a mostrar.
     */
    void displayCashierList(List<Cashier> cashiers);

    /**
     * Limpia los campos del input de entrada/edición de datos del cajero.
     */
    void clearForm();


    /**
     * Limpia los campo de busqueda de cajero.
     */
    void clearSearchField();

    /**
     * Solicitud para creación de un Cashier
     */
    void createCashier();

    /**
     * Solicitud para modificación de un Cashier
     */
    void updateCashier();

    /**
     * Solicitud para eliminación de un Cashier
     */
    void removeCashier();

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
     * Petición de busqueda de un cajero a partir de su nuip.
     * @param nuip N.I. del cajero a buscar.
     */
    void searchCashier(String nuip);

    /**
     * Muestra el panel de formulario de cajeros.
     */
    void showCashierFormPanel();

    /**
     * Muestra el panel de listado de cajeros.
     */
    void showCashierListPanel();

    /**
     * Rellena los campos del input con los datos de un cajero específico (para edición).
     * @param cashier El objeto Cashier cuyos datos se mostrarán.
     */
    void showCashierDetails(Cashier cashier);

    /**
     * Muestra un mensaje de error específico de la gestión de cajeros.
     * @param message El mensaje de error a mostrar.
     */
    void displayError(String message);

    /**
     * Muestra un mensaje de éxito específico de la gestión de cajeros.
     * @param message El mensaje de éxito a mostrar.
     */
    void displayMessage(String message);

    /**
     * Establece el presentador para esta vista.
     * @param presenter El presentador que controlará esta vista.
     * (Se podría usar una interfaz ICashierPresenter específica)
     */
    void setPresenter(CashierPresenter presenter);

    JPanel getPanel();

}
