package com.uptc.is.view.contracts;

import com.uptc.is.presenter.MainPresenter;

public interface IMainView {

    /**
     * Muestra la ventana principal de la aplicación.
     */
    void showView();

    /**
     * Cierra la ventana principal de la aplicación.
     */
    void closeView();

    /**
     * Muestra un mensaje de error general en la aplicación.
     * @param title Título del mensaje de error.
     * @param message Contenido del mensaje de error.
     */
    void displayError(String title, String message);

    /**
     * Muestra un mensaje informativo general en la aplicación.
     * @param title Título del mensaje.
     * @param message Contenido del mensaje.
     */
    void displayMessage(String title, String message);

    /**
     * Establece el presentador para esta vista.
     * @param presenter El presentador que controlará esta vista.
     * (Se podría usar una interfaz IMainPresenter específica)
     */
    void setPresenter(MainPresenter presenter);

}
