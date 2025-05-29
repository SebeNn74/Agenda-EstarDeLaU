package com.uptc.is.view;

import com.uptc.is.view.custom_components.CustomButton;
import com.uptc.is.view.custom_components.CustomScrollBarUI;
import com.uptc.is.view.custom_components.PartitionsTable;
import com.uptc.is.view.custom_components.ProcessTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel {

    private JComboBox<String> genRecordsCombo;
    private JComboBox<String> parExecutedCombo;
    private PartitionsTable partitionsModel;
    private ProcessTable ProcessModel;
    private JTable partitionsTable;
    private JTable processTable;
    private JScrollPane spRecords;

    public RightPanel(ActionListener listener){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.configPanel(listener);
    }

    private void configPanel(ActionListener listener){
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(209, 209, 209));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        //Label : Registro
        JLabel history = new JLabel("Registro:");
        history.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(history);

        //ComboBox: Registros
        String[] options = {"","Todas las Particiones", "Todos los Procesos", "Todas las Condensaciones"
                ,"Todos los Huecos","Todas las Compactaciones", "Finalización de Procesos","Ejecución de procesos"
                ,"Expiración de procesos"};
        genRecordsCombo = new JComboBox<>(options);
        genRecordsCombo.setPreferredSize(new Dimension(170, 30));
        genRecordsCombo.setSelectedItem("Todas las Particiones");
        genRecordsCombo.addActionListener(listener);
        genRecordsCombo.setActionCommand("genRecords");
        topPanel.add(genRecordsCombo);

        topPanel.add(Box.createHorizontalStrut(30));

        JLabel ProcessRecordsLabel = new JLabel("<html><div style='text-align: center;'>Procesos Ejecutados<br>en Partición:</div></html>");
        ProcessRecordsLabel.setFont(new Font("Arial", Font.BOLD, 13));
        topPanel.add(ProcessRecordsLabel);

        parExecutedCombo = new JComboBox<>();
        parExecutedCombo.setPreferredSize(new Dimension(100, 30));
        parExecutedCombo.addActionListener(listener);
        parExecutedCombo.setActionCommand("parExecRecords");
        topPanel.add(parExecutedCombo);

        //Table: Particiones
        partitionsModel = new PartitionsTable();
        partitionsTable = new JTable(partitionsModel);
        partitionsTable.setFillsViewportHeight(true);
        partitionsTable.setRowHeight(25);
        partitionsTable.getTableHeader().setBackground(new Color(141, 195, 255 ));

        topPanel.add(Box.createHorizontalStrut(30));

        //Table: Particiones
        partitionsModel = new PartitionsTable();
        partitionsTable = new JTable(partitionsModel);
        partitionsTable.setFillsViewportHeight(true);
        partitionsTable.setRowHeight(27);
        partitionsTable.getTableHeader().setBackground(new Color(141, 195, 255 ));
        partitionsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        partitionsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

        //Table: Tareas
        ProcessModel = new ProcessTable();
        processTable = new JTable(ProcessModel);
        processTable.setFillsViewportHeight(true);
        processTable.setRowHeight(27);
        processTable.getTableHeader().setBackground(new Color(141, 195, 255 ));
        processTable.setFont(new Font("Arial", Font.PLAIN, 14));
        processTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        processTable.getColumnModel().getColumn(3).setPreferredWidth(230);

        spRecords = new JScrollPane(partitionsTable);
        spRecords.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        spRecords.setBackground(new Color(209, 209, 209));
        spRecords.setBorder(new EmptyBorder(10, 0, 10, 0));
        spRecords.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        spRecords = new JScrollPane(partitionsTable);
        spRecords.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        spRecords.setBackground(new Color(209, 209, 209));
        spRecords.setBorder(new EmptyBorder(10, 0, 10, 0));
        spRecords.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        CustomButton editProcess = new CustomButton("EDITAR PROCESO");
        editProcess.setPreferredSize(new Dimension(200, 30));
        editProcess.setColors(new Color(46, 149, 191 ), new Color(57, 178, 227 ));
        editProcess.setTextColor(Color.WHITE);
        editProcess.addActionListener(listener);
        editProcess.setActionCommand("searchEditProcess");

        CustomButton delProcess = new CustomButton("ELIMINAR PROCESO");
        delProcess.setPreferredSize(new Dimension(200, 30));
        delProcess.setColors(new Color(200, 50, 50), new Color(255, 100, 100));
        delProcess.setTextColor(Color.WHITE);
        delProcess.addActionListener(listener);
        delProcess.setActionCommand("searchDelProcess");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(209, 209, 209));
        buttonPanel.add(editProcess);
        buttonPanel.add(delProcess);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(spRecords, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    //GETTERS Y SETTERS DE LAS ENTRADAS Y LAS SALIDAS

}
