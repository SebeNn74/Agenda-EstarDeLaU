package com.uptc.is.view.swing;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.ScheduleType;
import com.uptc.is.presenter.CashierPresenter;
import com.uptc.is.view.contracts.ICashierView;
import com.uptc.is.view.custom_components.MessageDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CashierView extends JPanel implements ICashierView {

    private CashierPresenter presenter;
    private JFrame frame;
    private CardLayout cardLayout;
    CashierFormPanel formPanel;
    CashierListPanel listPanel;

    public CashierView(){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        configPanel();
    }

    private void configPanel(){
        formPanel = new CashierFormPanel(this);
        listPanel = new CashierListPanel(this);

        this.add(formPanel, "form");
        this.add(listPanel, "list");
    }

    @Override
    public void displayCashierList(List<Cashier> cashiers) {
        listPanel.updateCashiers(cashiers);
        formPanel.updateCashiers(cashiers);
    }

    @Override
    public void clearForm() {
        formPanel.clearForm();
    }

    @Override
    public void clearSearchField() {
        formPanel.clearSearchField();
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
        return formPanel.getNuip();
    }

    @Override
    public String getNamesInput() {
        return formPanel.getNames();
    }

    @Override
    public String getSurnamesInput() {
        return formPanel.getSurmanes();
    }

    @Override
    public String getStudentCodeInput() {
        return formPanel.getStudentCode();
    }

    @Override
    public String getTelNumberInput() {
        return formPanel.getTelNumber();
    }

    @Override
    public String getEmailInput() {
        return formPanel.getEmail();
    }

    @Override
    public void searchCashier(String nuip) {
        presenter.selectCashier(nuip);
    }

    @Override
    public String getSelectedCashierNuip() {
        return "";
    }

    @Override
    public void showCashierFormPanel() {
        cardLayout.show(this, "form");
    }

    @Override
    public void showCashierListPanel() {
        cardLayout.show(this, "list");
    }

    @Override
    public void showCashierDetails(Cashier cashier) {
        formPanel.setCashier(cashier);
    }

    @Override
    public void displayError(String message) {
        new MessageDialog(this.frame, message, MessageDialog.MessageType.ERROR);
    }

    @Override
    public void displayMessage(String message) {
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
