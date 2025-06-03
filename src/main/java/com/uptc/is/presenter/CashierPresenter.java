package com.uptc.is.presenter;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.repository.CashierRepository;
import com.uptc.is.view.contracts.ICashierView;

import java.util.Optional;

public class CashierPresenter {

    private final CashierRepository cashierRepo;
    private final ICashierView view;

    public CashierPresenter(CashierRepository cashierRepository, ICashierView cashierView){
        this.cashierRepo = cashierRepository;
        this.view = cashierView;
        this.view.setPresenter(this);
        this.view.displayCashierList(cashierRepo.getAll());
    }

    public void createCashier(){
        if(validCashier()){
            if(cashierRepo.searchById(view.getNuipInput()).isEmpty()){
                cashierRepo.create(madeCashier());
                view.displayCashierList(cashierRepo.getAll());
                view.clearForm();
                view.displayMessage("Cajero creado con exito");
            }else{
                String message = "Ya existe un empleado con el mismo número de identificación";
                view.displayError(message);
            }
        }
    }

    private Cashier madeCashier(){
        Cashier cashier = new Cashier(view.getNuipInput(),view.getStudentCodeInput());
        cashier.setNames(view.getNamesInput());
        cashier.setSurnames(view.getSurnamesInput());
        cashier.setStudentCode(view.getStudentCodeInput());
        cashier.setTelNumber(view.getTelNumberInput());
        cashier.setEmail(view.getEmailInput());
        return cashier;
    }

    public void selectCashier(String nuip){
        String message;
        if(nuip == null) return;
        if(!nuip.isEmpty()){
            Optional<Cashier> cashier = cashierRepo.searchById(nuip);
            if(cashier.isPresent()){
                view.showCashierDetails(cashier.get());
                view.clearSearchField();
            }else{
                message = "No se ha registrado ningun cajero (humano) con ese número de identidad";
                view.displayError(message);
            }
        }else{
            message = "Ingrese el número de identidad del cajero (humano)";
            view.displayError(message);
        }
    }

    public void updateCashier(){
        if(validCashier()){
            cashierRepo.update(madeCashier());
            String message = "Los cambios en el cajero (humano) se guardaron con exito";
            view.displayMessage(message);
            view.displayCashierList(cashierRepo.getAll());
        }
    }

    public void removeCashier(){
        String message;
        if(!view.getNuipInput().isEmpty()){
            Optional<Cashier> cashier = cashierRepo.searchById(view.getNuipInput());
            if(cashier.isPresent()){
                message = "El registro del cajero (humano) se eliminó con exito";
                cashierRepo.remove(view.getNuipInput());
                view.displayMessage(message);
                view.clearForm();
                view.displayCashierList(cashierRepo.getAll());
            }else{
                message = "No se ha registrado ningun cajero (humano) con ese número de identidad";
                view.displayError(message);
            }
        }else{
            message = "Primero debe ingresar o seleccionar un cajero";
            view.displayError(message);
        }
    }

    private boolean validCashier(){
        String field;
        if(view.getNuipInput().isEmpty()){
            field = "El Número de identidad";
        } else if (view.getStudentCodeInput().isEmpty()) {
            field = "El Código estudiantil";
        } else if (view.getNamesInput().isEmpty()) {
            field = "Los Nombres";
        } else if (view.getSurnamesInput().isEmpty()) {
            field = "Los Apellidos";
        } else if (view.getTelNumberInput().isEmpty()) {
            field = "El Número de teléfono";
        } else if (view.getEmailInput().isEmpty()) {
            field = "El Correo electrónico";
        }else{
            return true;
        }
        //Si algun campo de las entradas esta vacio
        String message = field+" del cajero (humano) no puede estar vacío";
        view.displayError(message);
        return false;
    }

}
