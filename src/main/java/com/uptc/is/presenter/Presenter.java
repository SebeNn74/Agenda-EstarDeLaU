package com.uptc.is.presenter;

import com.uptc.is.view.custom_components.EditProcessWindow;
import com.uptc.is.view.custom_components.MessageDialog;
import com.uptc.is.view.custom_components.SearchWindow;
import com.uptc.is.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presenter implements ActionListener {

    private View view;
    private EditProcessWindow editProcessWindow;
    private SearchWindow search;

    public Presenter(){
        run();
    }

    public void run(){
        view = new View(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "records":
                changeRecords("");
                break;
            case "searchDelProcess":
                search = new SearchWindow(view.getFrame(), this, "ELIMINAR","delProcess");
                break;
            case "searchEditProcess":
                search = new SearchWindow(view.getFrame(), this, "EDITAR","editProcess");
                break;
            case "addProcess":
                addProcess();
                break;
            case "editProcess":
                editProcess();
                break;
            case "verifEditProcess":
                verifEditProcess();
                break;
            case "delProcess":
                deleteProcess();
                break;
        }
    }

    private void changeRecords(String selection){
        //SWITCH con los reportes
    }

    private void addProcess(){
        if(validProcessFields()){
            //Añado el proceso
            //Si existe un proceso con el mismo nombre Advertencia
            new MessageDialog(view.getFrame(),"Ya existe un proceso con el mismo nombre ingresado", MessageDialog.MessageType.WARNING);
            //Limpio entradas
        }
    }

    private void editProcess(){
        //Si el nombre del proceso es valido
        //Si no es valido Advertencia
        new MessageDialog(view.getFrame(), "No existe un proceso con ese nombre", MessageDialog.MessageType.WARNING);
        //Busco el nombre del proceso
        //Si no exite Advertencia
        new MessageDialog(view.getFrame(), "Ingrese el nombre del proceso a editar", MessageDialog.MessageType.WARNING);
        //Si existe desplegar ventana emergente con los datos del proceso respectivo
    }

    private void verifEditProcess(){
        //Edito el proceso
        new MessageDialog(view.getFrame(), "El proceso se editó con exito", MessageDialog.MessageType.SUCCESS);
    }

    private void deleteProcess(){
        //Si el nombre del proceso es valido
        //Si no es valido Advertencia
        new MessageDialog(view.getFrame(), "No existe un proceso con ese nombre", MessageDialog.MessageType.WARNING);
        //Busco el nombre del proceso
        //Si no exite Advertencia
        new MessageDialog(view.getFrame(), "Ingrese el nombre del proceso a eliminar", MessageDialog.MessageType.WARNING);
        //Si existe eliminar
        new MessageDialog(view.getFrame(), "El proceso se eliminó con exito", MessageDialog.MessageType.SUCCESS);
    }

    private boolean validProcessFields(){
        String field = "";
        //Evaluo entradas necesarias
        //Si algun campo de las entradas esta vacio Advertencia
        new MessageDialog(view.getFrame(),"El "+field+" de el proceso no puede estar vacío", MessageDialog.MessageType.ERROR);
        return false;
    }

}
