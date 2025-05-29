package com.uptc.is.presenter;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.repository.CashierRepository;
import com.uptc.is.view.contracts.ICashierView;

import java.util.Optional;
import java.util.function.Consumer;

public class CashierPresenter {

    private CashierRepository cashierRepo;
    private ICashierView view;
    private Consumer<Cashier> onCashierSelected; // Listener

    public CashierPresenter(CashierRepository cashierRepository, ICashierView cashierView){
        this.cashierRepo = cashierRepository;
        this.view = cashierView;
        this.view.setPresenter(this);
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

    public void showCashiers(){
        view.displayCashierList(cashierRepo.getAll());
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
                view.displayError("Cajero (Humano) Existente",message);
            }
        }
    }

    private Cashier madeCashier(){
        Cashier cashier = new Cashier(view.getNuipInput(),view.getStudentCodeInput());
        cashier.setNames(view.getNamesInput());
        cashier.setSurnames(view.getSurnamesInput());
        cashier.setTelNumber(view.getTelNumberInput());
        cashier.setEmail(view.getTelNumberInput());
        return cashier;
    }

    public void selectCashier(){
        String message;
        if(view.getNuipInput().isEmpty()){
            Optional<Cashier> cashier = cashierRepo.searchById(view.getNuipInput());
            if(cashier.isPresent()){
                view.showCashierDetails(cashier.get());
                System.out.println("Cajero "+view.getNuipInput()+" encontrado");
            }else{
                message = "No se ha registrado ningun cajero (humano) con ese número de identidad";
                view.displayError("Titulo",message);
            }
        }else{
            message = "Ingrese el número de identidad del cajero (humano)";
            view.displayError("Titulo",message);
        }
    }

    public void updateCashier(){
        String message;
        if(validCashier()){
            cashierRepo.update(madeCashier());
            System.out.println("Cajero "+view.getNuipInput()+" actualizado");
            message = "Los cambios en el cajero (humano) se guardaron con exito";
            view.displayMessage("Titulo",message);
        }
    }

    public void removeCashier(){
        String message;
        cashierRepo.remove(view.getNuipInput());
        System.out.println("Cajero "+view.getNuipInput()+" eliminado");
        message = "El registro del cajero (humano) se eliminó con exito";
        view.displayMessage("Titulo",message);
    }

    private boolean validCashier(){
        String field;
        if(view.getNuipInput().isEmpty()){
            field = "Número de identidad";
        } else if (view.getStudentCodeInput().isEmpty()) {
            field = "Código estudiantil";
        } else if (view.getNamesInput().isEmpty()) {
            field = "Nombres";
        } else if (view.getSurnamesInput().isEmpty()) {
            field = "Apellidos";
        } else if (view.getTelNumberInput().isEmpty()) {
            field = "Número de teléfono";
        } else if (view.getEmailInput().isEmpty()) {
            field = "Correo electrónico";
        }else{
            return true;
        }
        //Si algun campo de las entradas esta vacio
        String message = "El "+field+" del cajero (humano) no puede estar vacío";
        view.displayError("Titulo", message);
        return false;
    }

    public void setOnCashierSelected(Consumer<Cashier> listener) {
        this.onCashierSelected = listener;
    }

}
