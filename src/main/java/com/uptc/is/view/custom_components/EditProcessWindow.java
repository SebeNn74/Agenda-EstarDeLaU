package com.uptc.is.view.custom_components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditProcessWindow extends JWindow {

    private final JTextField processNameTF;
    private final NumericTextField processSizeTF;
    private final NumericTextField processTimeTF;

    public EditProcessWindow(Frame parent, ActionListener listener) {
        super(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        Toolkit.getDefaultToolkit().beep();

        JPanel contentPanel = madeContentPanel();

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(209, 209, 209));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        Dimension dim_tf = new Dimension(100, 25);

        JLabel ProcesssLabel = new JLabel("EDITAR PROCESO");
        ProcesssLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.weightx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(15, 15, 5, 10);
        inputPanel.add(ProcesssLabel, gbc);

        JLabel ProcessNameLabel = new JLabel("Nombre :");
        ProcessNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        processNameTF = new JTextField();
        processNameTF.setPreferredSize(dim_tf);
        processNameTF.setMinimumSize(dim_tf);

        gbc.gridx = 0; gbc.gridy = 8;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 15, 15, 10);
        gbc.fill = GridBagConstraints.NONE;
        inputPanel.add(ProcessNameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 8;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 5, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(processNameTF, gbc);

        JLabel ProcessSizeLabel = new JLabel("Tamaño :");
        ProcessSizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        processSizeTF = new NumericTextField(50);
        processSizeTF.setPreferredSize(dim_tf);
        processSizeTF.setMinimumSize(dim_tf);

        gbc.gridx = 0; gbc.gridy = 9;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 15, 15, 10);
        gbc.fill = GridBagConstraints.NONE;
        inputPanel.add(ProcessSizeLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 9;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 5, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(processSizeTF, gbc);

        JLabel ProcessTimeLabel = new JLabel("Tiempo :");
        ProcessTimeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        processTimeTF = new NumericTextField(50);
        processTimeTF.setPreferredSize(dim_tf);
        processTimeTF.setMinimumSize(dim_tf);

        gbc.gridx = 0; gbc.gridy = 10;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 15, 15, 10);
        gbc.fill = GridBagConstraints.NONE;
        inputPanel.add(ProcessTimeLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 10;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 5, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(processTimeTF, gbc);

        JButton cancelButton = new JButton("CERRAR");
        cancelButton.setBackground(new Color(200, 50, 50));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cancelButton.addActionListener(e -> dispose());

        JButton acceptButton = new JButton("GUARDAR");
        acceptButton.setBackground(new Color(50, 150, 50));
        acceptButton.setForeground(Color.WHITE);
        acceptButton.setFocusPainted(false);
        acceptButton.setFont(new Font("Arial", Font.BOLD, 14));
        acceptButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        acceptButton.addActionListener(listener);
        acceptButton.setActionCommand("verifEditProcess");
        acceptButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(cancelButton);
        buttonPanel.add(acceptButton);

        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(contentPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private static JPanel madeContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(108, 117, 117));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 15, 15);
            }
        };

        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setLayout(new BorderLayout(10, 10));
        return contentPanel;
    }


    //GETTERS Y SETTERS DE LAS ENTRADAS

}
