package com.uptc.is.view.swing;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.ScheduleType;
import com.uptc.is.view.contracts.ICashierView;

import java.util.List;

public class CashierView implements ICashierView {

    @Override
    public void displayCashiers(List<Cashier> cashiers) {

    }

    @Override
    public void clearForm() {

    }

    @Override
    public String getNuipInput() {
        return "";
    }

    @Override
    public String getNamesInput() {
        return "";
    }

    @Override
    public String getSurnamesInput() {
        return "";
    }

    @Override
    public String getStudentCodeInput() {
        return "";
    }

    @Override
    public String getTelNumberInput() {
        return "";
    }

    @Override
    public String getEmailInput() {
        return "";
    }

    @Override
    public String getSelectedCashierNuip() {
        return "";
    }

    @Override
    public void showCashierDetails(Cashier cashier) {

    }

    @Override
    public void displayErrorMessage(String message) {

    }

    @Override
    public void displaySuccessMessage(String message) {

    }

    @Override
    public void openScheduleManagementView(String cashierNuip, ScheduleType scheduleType) {

    }

    @Override
    public void setPresenter(Object presenter) {

    }

}
