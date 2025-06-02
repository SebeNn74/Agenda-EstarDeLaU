package com.uptc.is.view.swing;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.view.contracts.ICashierView;
import com.uptc.is.view.custom_components.CustomScrollBarUI;
import com.uptc.is.view.custom_components.CustomTable;
import com.uptc.is.view.custom_components.LargeCashierTable;
import com.uptc.is.view.custom_components.ModernButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class CashierListPanel extends JPanel {

    private LargeCashierTable tableModel;
    private CustomTable cashierTable;
    private JScrollPane spRecords;

    public CashierListPanel(ICashierView cashierView){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 30, 30, 30));
        configTopPanel(cashierView);
        configTable();
    }

    private void configTopPanel(ICashierView cashierView){
        ModernButton showListBtn = new ModernButton("REGISTRAR NUEVO CAJERO");
        showListBtn.setBackgroundColors(new Color(227, 93, 48),
                new Color(247, 133, 88),
                new Color(150, 50, 20));
        showListBtn.setBorderColor(new Color(227, 93, 48));
        showListBtn.setFontSize(15f);
        showListBtn.setButtonSize( 300, 32);
        showListBtn.addClickAction(e -> cashierView.showCashierFormPanel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setPreferredSize(new Dimension(300, 38));
        topPanel.add(showListBtn);

        this.add(topPanel, BorderLayout.NORTH);
    }

    private void configTable(){
        tableModel = new LargeCashierTable();
        cashierTable = new CustomTable(tableModel);
        cashierTable.setFillsViewportHeight(true);
        cashierTable.setRowHeight(25);

        spRecords = new JScrollPane(cashierTable);
        spRecords.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        spRecords.setBorder(new EmptyBorder(10, 0, 10, 0));
        spRecords.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        this.add(spRecords, BorderLayout.CENTER);
    }

    public void updateCashiers(List<Cashier> cashiers){
        tableModel.setRecords(cashiers);
        tableModel.fireTableDataChanged();
        cashierTable.scrollRectToVisible(cashierTable.getCellRect(cashierTable.getRowCount() - 1, 0, true));
    }

}
