package com.uptc.is.view.swing;

import com.uptc.is.view.contracts.IMainView;
import com.uptc.is.view.custom_components.ModernButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class SideBarPanel extends JPanel {

    public SideBarPanel(MainView mainView){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.gray);
        this.configPanel(mainView);
    }

    private void configPanel(MainView mainView){
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        menuPanel.setBackground(Color.gray);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 35, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.gridx = 0;

        JLabel icon = getLogo();

        ModernButton btnMain = new ModernButton("Inicio");
        btnMain.setButtonSize( 0, 40);
        ModernButton btnCashiers = new ModernButton("Cajeros (Empleados)");
        btnCashiers.setButtonSize( 0, 40);
        ModernButton btnSchedules = new ModernButton("Horarios de Trabajo");
        btnSchedules.setButtonSize( 0, 40);
        ModernButton btnGenSchedule = new ModernButton("Calendario General");
        btnGenSchedule.setButtonSize( 0, 40);

        btnMain.addActionListener(e -> mainView.showPanelView("main"));
        btnCashiers.addActionListener(e -> mainView.showPanelView("cashiers"));
        btnSchedules.addActionListener(e -> mainView.showPanelView("schedules"));
        btnGenSchedule.addActionListener(e -> mainView.showPanelView("genSchedule"));

        gbc.gridy = 0;
        assert icon != null;
        menuPanel.add(icon, gbc);
        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridy = 1;
        menuPanel.add(btnMain, gbc);
        gbc.gridy = 2;
        menuPanel.add(btnCashiers, gbc);
        gbc.gridy = 3;
        menuPanel.add(btnSchedules, gbc);
        gbc.gridy = 4;
        menuPanel.add(btnGenSchedule, gbc);

        gbc.weighty = 1;
        menuPanel.add(Box.createVerticalGlue(), gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridy = 6;
        menuPanel.add(exitButton(mainView), gbc);

        this.add(menuPanel);
    }

    private JLabel getLogo(){
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/home_icon.png")));
            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("No se pudo encontrar el ícono en: " + "/icons/home_icon.png");
        }
        return null;
    }
    
    private ModernButton exitButton(IMainView mainView){
        ModernButton exitBtn = getModernButton(mainView);

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "salir");

        getActionMap().put("salir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitBtn.doClick();
            }
        });
        
        return exitBtn;
    }

    private static ModernButton getModernButton(IMainView mainView) {
        ModernButton exitBtn = new ModernButton("SALIR");
        exitBtn.setNormalBackgroundColor(new Color(220, 53, 69));
        exitBtn.setHoverBackgroundColor(new Color(227, 78, 91));
        exitBtn.setPressedBackgroundColor(new Color(194, 47, 60));
        exitBtn.setBorderColor(new Color(220, 53, 69));
        exitBtn.setNormalForegroundColor(Color.WHITE);
        exitBtn.setHoverForegroundColor(Color.WHITE);
        exitBtn.setButtonSize( 110, 35);
        exitBtn.setClickAction(e -> mainView.closeView());
        return exitBtn;
    }

}
