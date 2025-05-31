package com.uptc.is.view.swing;

import com.uptc.is.view.custom_components.ModernButton;
import com.uptc.is.view.custom_components.NumericTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel {

    private JTextField processNameTF;
    private NumericTextField processSizeTF;
    private NumericTextField processTimeTF;
    private ModernButton addProcess;

    public LeftPanel(ActionListener listener) {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 0));
        this.configProcessPanel(listener);
    }

    private void configProcessPanel(ActionListener listener){
        JPanel ProcessPanel = new JPanel(new GridBagLayout());
        ProcessPanel.setBackground(new Color(209, 209, 209));
        ProcessPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 20, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Dimension dim_tf = new Dimension(100, 25);

        JLabel ProcesssLabel = new JLabel("PROCESOS");
        ProcesssLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 15, 10, 10);
        ProcessPanel.add(ProcesssLabel, gbc);

        JLabel ProcessNameLabel = new JLabel("Nombre :");
        ProcessNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        processNameTF = new JTextField();
        processNameTF.setPreferredSize(dim_tf);
        processNameTF.setMinimumSize(dim_tf);
        processNameTF.addActionListener(e -> processTimeTF.requestFocusInWindow());

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 15, 15, 10);
        gbc.fill = GridBagConstraints.NONE;
        ProcessPanel.add(ProcessNameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 5, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ProcessPanel.add(processNameTF, gbc);

        JLabel ProcessTimeLabel = new JLabel("Tiempo :");
        ProcessTimeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        processTimeTF = new NumericTextField(50);
        processTimeTF.setPreferredSize(dim_tf);
        processTimeTF.setMinimumSize(dim_tf);
        processTimeTF.addActionListener(e -> processSizeTF.requestFocusInWindow());

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 15, 15, 10);
        gbc.fill = GridBagConstraints.NONE;
        ProcessPanel.add(ProcessTimeLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 5, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ProcessPanel.add(processTimeTF, gbc);

        JLabel ProcessSizeLabel = new JLabel("Tamaño :");
        ProcessSizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        processSizeTF = new NumericTextField(50);
        processSizeTF.setPreferredSize(dim_tf);
        processSizeTF.setMinimumSize(dim_tf);
        processSizeTF.addActionListener(e -> addProcess.doClick());

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 15, 15, 10);
        gbc.fill = GridBagConstraints.NONE;
        ProcessPanel.add(ProcessSizeLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 5, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ProcessPanel.add(processSizeTF, gbc);

        gbc.fill = GridBagConstraints.NONE;

        addProcess = new ModernButton("CREAR PROCESO");
        addProcess.setPreferredSize(new Dimension(200, 30));
        addProcess.addActionListener(listener);
        addProcess.setActionCommand("addProcess");
        addProcess.addActionListener(e -> processNameTF.requestFocusInWindow());

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 10, 15);
        ProcessPanel.add(addProcess, gbc);

        JLabel line = new JLabel("<html>_______________________</html>");
        line.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.weightx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 15, 20, 10);
        ProcessPanel.add(line, gbc);

        ModernButton execute = new ModernButton("EJECUTAR");
        execute.setPreferredSize(new Dimension(200, 30));
        execute.addActionListener(listener);
        execute.setActionCommand("execute");

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.insets = new Insets(10, 5, 15, 15);
        ProcessPanel.add(execute, gbc);

        this.add(ProcessPanel, BorderLayout.CENTER);
    }

}

