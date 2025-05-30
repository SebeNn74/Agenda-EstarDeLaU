package com.uptc.is.view.swing;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.ScheduleType;
import com.uptc.is.presenter.CashierPresenter;
import com.uptc.is.view.contracts.ICashierView;
import com.uptc.is.view.custom_components.MessageDialog;

import javax.swing.*;
import java.util.List;

public class CashierView extends JPanel implements ICashierView {

    private CashierPresenter presenter;
    private JFrame frame;

    @Override
    public void displayCashierList(List<Cashier> cashiers) {

    }

    @Override
    public void clearForm() {

    }

    @Override
    public void createCashier() {
        presenter.createCashier();
    }

    @Override
    public void updateCashier() {
        presenter.updateCashier();
    }

    @Override
    public void removeCashier() {
        presenter.removeCashier();
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
    public void displayError(String title, String message) {
        new MessageDialog(this.frame, message, MessageDialog.MessageType.ERROR);
    }

    @Override
    public void displayMessage(String title, String message) {
        new MessageDialog(frame, message, MessageDialog.MessageType.SUCCESS);
    }

    @Override
    public void openScheduleManagementView(String cashierNuip, ScheduleType scheduleType) {

    }

    @Override
    public void setPresenter(CashierPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void setParentFrame(JFrame parentFrame) {
        this.frame = parentFrame;
    }

}
