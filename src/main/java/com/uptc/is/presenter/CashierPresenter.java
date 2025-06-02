package com.uptc.is.presenter;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.repository.CashierRepository;
import com.uptc.is.view.contracts.ICashierView;

import java.util.Optional;
import java.util.function.Consumer;

public class CashierPresenter {

    private final CashierRepository cashierRepo;
    private final ICashierView view;
    private Consumer<Cashier> onCashierSelected; // Listener

    public CashierPresenter(CashierRepository cashierRepository, ICashierView cashierView){
        this.cashierRepo = cashierRepository;
        this.view = cashierView;
        this.view.setPresenter(this);
        this.view.displayCashierList(cashierRepo.getAll());
    }

    public void test() {
        Cashier cashier = new Cashier("1056798612", "202211892");
        cashier.setNames("Sebastian");
        cashier.setSurnames("Niño Niño");
        cashier.setTelNumber("3124033131");
        cashier.setEmail("sebastian.nino06@gmail.com");

        Cashier cashier2 = new Cashier("1020304050", "202211892");
        cashier2.setNames("Juan Manuel");
        cashier2.setSurnames("Alba Novoa");
        cashier2.setTelNumber("312000000");
        cashier2.setEmail("juan.alba02@gmail.com");

        if (cashierRepo.searchById(cashier.getNuip()).isEmpty()) {
            cashierRepo.create(cashier);
            System.out.println(cashier);
        }
        if (cashierRepo.searchById(cashier2.getNuip()).isEmpty()) {
            cashierRepo.create(cashier2);
            System.out.println(cashier2);
        }
    }

    public void createCashier(){
        if(validCashier()){
            if(cashierRepo.searchById(view.getNuipInput()).isEmpty()){
                cashierRepo.create(madeCashier());
                view.displayCashierList(cashierRepo.getAll());
                view.clearForm();
                System.out.println("Cajero "+view.getNuipInput()+" creado");
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
        String message;
        if(validCashier()){
            cashierRepo.update(madeCashier());
            message = "Los cambios en el cajero (humano) se guardaron con exito";
            view.displayMessage(message);
            view.displayCashierList(cashierRepo.getAll());
        }
    }

    public void removeCashier(){
        String message = "El registro del cajero (humano) se eliminó con exito";
        cashierRepo.remove(view.getNuipInput());
        view.displayMessage(message);
        view.clearForm();
        view.displayCashierList(cashierRepo.getAll());
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

    public void setOnCashierSelected(Consumer<Cashier> listener) {
        this.onCashierSelected = listener;
    }

}
