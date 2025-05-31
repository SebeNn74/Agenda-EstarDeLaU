package com.uptc.is.view.swing;

import com.uptc.is.view.custom_components.ModernButton;
import net.bytebuddy.matcher.StringMatcher;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class SideBarPanel extends JPanel {

    private ModernButton lastPressedBtn;

    public SideBarPanel(MainView mainView){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.BLACK);
        this.configPanel(mainView);
    }

    private void configPanel(MainView mainView){
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        menuPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 35, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.gridx = 0;

        JLabel icon = getLogo();

        ModernButton btnMain = new ModernButton("INICIO");
        btnMain.setButtonSize( 0, 40);
        ModernButton btnCashiers = new ModernButton("CAJEROS (EMPLEADOS)");
        btnCashiers.setButtonSize( 0, 40);
        ModernButton btnSchedules = new ModernButton("HORARIOS LABORALES");
        btnSchedules.setButtonSize( 0, 40);
        ModernButton btnGenSchedule = new ModernButton("CALENDARIO GENERAL");
        btnGenSchedule.setButtonSize( 0, 40);
        ModernButton btnUserManual = new ModernButton("MANUAL DE USUARIO");
        btnUserManual.setButtonSize( 180, 40);

        btnMain.addClickAction(e -> mainView.showPanelView("main"));
        btnMain.addClickAction(e -> setPressedButton(btnMain));
        btnCashiers.addClickAction(e -> mainView.showPanelView("cashiers"));
        btnCashiers.addClickAction(e -> setPressedButton(btnCashiers));
        btnSchedules.addClickAction(e -> mainView.showPanelView("schedules"));
        btnSchedules.addClickAction(e -> setPressedButton(btnSchedules));
        btnGenSchedule.addClickAction(e -> mainView.showPanelView("genSchedule"));
        btnGenSchedule.addClickAction(e -> setPressedButton(btnGenSchedule));
        btnUserManual.addClickAction(e -> mainView.openUserManual());

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
        menuPanel.add(btnUserManual, gbc);

        this.add(menuPanel);

        lastPressedBtn = btnMain;
        lastPressedBtn.setPressed(true);
    }

    private JLabel getLogo(){
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/logo_estardelau.png")));
            Image scaledImage = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("No se pudo encontrar el ícono en: " + "/icons/logo_estardelau.png");
        }
        return null;
    }

    private void setPressedButton(ModernButton button){
        lastPressedBtn.setPressed(false);
        button.setPressed(true);
        lastPressedBtn = button;
    }

}
