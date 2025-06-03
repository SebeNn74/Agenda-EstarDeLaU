package com.uptc.is.view.swing;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.view.contracts.ICashierView;
import com.uptc.is.view.custom_components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CashierFormPanel extends JPanel {

    private JPanel inputPanel;
    private JPanel listPanel;
    private FormField nuip;
    private FormField names;
    private FormField surnames;
    private FormField studentCode;
    private FormField telNumber;
    private FormField email;
    private FormField cashierSearched;
    private ModernButton addCashier;
    private ModernButton updateCashier;
    private BasicCashierTable tableModel;
    private CustomTable cashierTable;
    private JScrollPane spRecords;

    private boolean deleteActive;

    public CashierFormPanel(ICashierView cashierView) {
        this.setLayout(new BorderLayout());
        this.configInputPanel(cashierView);
        this.configListPanel(cashierView);
        this.configSplitPane();
        this.configResponsiveColumns();
    }

    private void configInputPanel(ICashierView cashierView){
        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 1;
        gbc.gridx = 0;

        Dimension dimFF = new Dimension(0, 35);
        int labelWidth = 85;

        nuip = new FormField("C.C :", labelWidth);
        nuip.setPreferredSize(dimFF);
        nuip.addActionListener(e -> names.requestFocusInWindow());

        names = new FormField("Nombres :", labelWidth);
        names.setPreferredSize(dimFF);
        names.addActionListener(e -> surnames.requestFocusInWindow());

        surnames = new FormField("Apellidos :", labelWidth);
        surnames.setPreferredSize(dimFF);
        surnames.addActionListener(e -> studentCode.requestFocusInWindow());

        studentCode = new FormField("COD :", labelWidth);
        studentCode.setPreferredSize(dimFF);
        studentCode.addActionListener(e -> telNumber.requestFocusInWindow());

        telNumber = new FormField("Teléfono :", labelWidth);
        telNumber.setPreferredSize(dimFF);
        telNumber.addActionListener(e -> email.requestFocusInWindow());

        email = new FormField("Correo :", labelWidth);
        email.setPreferredSize(dimFF);
        email.addActionListener(e -> addCashier.doClick());

        addCashier = new ModernButton("CREAR NUEVO CAJERO");
        addCashier.addClickAction(e -> {if(deleteActive) cashierView.clearForm();});
        addCashier.addClickAction(e -> {if(!deleteActive) cashierView.createCashier();});
        addCashier.setButtonSize( 200, 35);

        updateCashier = ModernButtonFactory.success("ACTUALIZAR CAJERO");
        updateCashier.addClickAction(e -> cashierView.updateCashier());
        updateCashier.setButtonSize( 190, 35);
        updateCashier.setVisible(false);

        ModernButton deleteCashier = ModernButtonFactory.danger("ELIMINAR CAJERO");
        deleteCashier.addClickAction(e -> cashierView.removeCashier());
        deleteCashier.setButtonSize( 190, 35);

        gbc.gridy = 0;
        inputPanel.add(nuip, gbc);
        gbc.gridy = 1;
        inputPanel.add(names, gbc);
        gbc.gridy = 2;
        inputPanel.add(surnames, gbc);
        gbc.gridy = 3;
        inputPanel.add(studentCode, gbc);
        gbc.gridy = 4;
        inputPanel.add(telNumber, gbc);
        gbc.gridy = 5;
        inputPanel.add(email, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 6;
        inputPanel.add(updateCashier, gbc);
        gbc.gridy = 7;
        inputPanel.add(deleteCashier, gbc);
        gbc.gridy = 8;
        inputPanel.add(addCashier, gbc);

        gbc.weighty = 1;
        inputPanel.add(Box.createVerticalGlue(), gbc);
    }

    private void configListPanel(ICashierView cashierView){
        listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(new EmptyBorder(20, 20, 30, 20));

        JPanel topPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy = 0;

        ModernButton searchCashier = new ModernButton("BUSCAR");
        searchCashier.addClickAction(e -> cashierView.searchCashier(cashierSearched.getText()));
        searchCashier.setButtonSize( 100, 35);

        cashierSearched = new FormField("Cajero (C.C) :", 100);
        cashierSearched.setPreferredSize(new Dimension(0, 35));
        cashierSearched.addActionListener(e -> searchCashier.doClick());

        gbc.weightx = 2;
        gbc.gridx = 0;
        topPanel.add(cashierSearched, gbc);
        gbc.weightx = 0;
        gbc.gridx = 1;
        topPanel.add(searchCashier, gbc);

        tableModel = new BasicCashierTable();
        cashierTable = new CustomTable(tableModel);
        cashierTable.setFillsViewportHeight(true);
        cashierTable.setRowHeight(25);

        cashierTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    cashierView.searchCashier(getSelectedCashierNuip());
                }
            }
        });

        spRecords = new JScrollPane(cashierTable);
        spRecords.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        spRecords.setBorder(new EmptyBorder(10, 0, 10, 0));
        spRecords.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        listPanel.add(topPanel, BorderLayout.NORTH);
        listPanel.add(spRecords, BorderLayout.CENTER);
    }

    private void configSplitPane(){
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, listPanel);
        splitPane.setResizeWeight(0.15);
        splitPane.setDividerSize(2);
        splitPane.setContinuousLayout(true);

        this.add(splitPane, BorderLayout.CENTER);
    }

    private void configResponsiveColumns() {
        double[] widths = {0.25, 0.25, 0.5};

        spRecords.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int totalWidth = spRecords.getViewport().getWidth();
                TableColumnModel columnModel = cashierTable.getColumnModel();
                for (int i = 0; i < widths.length; i++) {
                    columnModel.getColumn(i).setPreferredWidth((int) (totalWidth * widths[i]));
                }
            }
        });
    }

    public void updateCashiers(List<Cashier> cashiers){
        tableModel.setRecords(cashiers);
        tableModel.fireTableDataChanged();
        cashierTable.scrollRectToVisible(cashierTable.getCellRect(cashierTable.getRowCount() - 1, 0, true));
    }

    public void clearForm(){
        nuip.clearField();
        names.clearField();
        surnames.clearField();
        studentCode.clearField();
        telNumber.clearField();
        email.clearField();

        updateCashier.setVisible(false);
        deleteActive = false;
        nuip.setEditable(true);
    }

    public void clearSearchField(){cashierSearched.setText("");}

    public String getNuip(){
        return nuip.getText();
    }

    public String getNames(){
        return names.getText();
    }

    public String getSurmanes(){
        return surnames.getText();
    }

    public String getStudentCode(){
        return studentCode.getText();
    }

    public String getTelNumber(){
        return telNumber.getText();
    }

    public String getEmail(){
        return email.getText();
    }

    public String getSelectedCashierNuip(){
        int selectedRow = cashierTable.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = cashierTable.convertRowIndexToModel(cashierTable.getSelectedRow());
            return cashierTable.getModel().getValueAt(modelRow, 0).toString();
        }
        return null;
    }

    public void setCashier(Cashier cashier){
        nuip.setText(cashier.getNuip());
        names.setText(cashier.getNames());
        surnames.setText(cashier.getSurnames());
        studentCode.setText(cashier.getStudentCode());
        telNumber.setText(cashier.getTelNumber());
        email.setText(cashier.getEmail());

        updateCashier.setVisible(true);
        deleteActive = true;
        nuip.setEditable(false);
    }

}

