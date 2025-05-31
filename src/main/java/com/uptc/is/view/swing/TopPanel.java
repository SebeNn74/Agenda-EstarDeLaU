package com.uptc.is.view.swing;

import com.uptc.is.util.FontLoader;
import com.uptc.is.view.custom_components.ModernButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TopPanel extends JPanel {

    public TopPanel(MainView mainView){
        this.setLayout(new GridBagLayout());
        this.configPanel(mainView);
        this.setBackground(Color.BLACK);
    }

    private void configPanel(MainView mainView){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;

        //Label : Titulo
        JLabel title = new JLabel("AGENDAMIENTO LABORAL : ESTAR DE LA U");
        title.setFont(FontLoader.loadFont("/fonts/Roboto-SemiBold.ttf", 21f));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0;
        this.add(title, gbc);

        //Espacio
        gbc.gridx = 1; gbc.gridy = 0;
        this.add(new JLabel(), gbc);
        gbc.gridx = 2; gbc.gridy = 0;
        this.add(new JLabel(), gbc);
        gbc.gridx = 3; gbc.gridy = 0;
        this.add(new JLabel(), gbc);

        //Boton: Manual de usuario
        ModernButton muBtn = new ModernButton("MANUAL DE USUARIO");

        muBtn.setButtonSize( 200, 30);
        muBtn.setClickAction(e -> mainView.openUserManual());
        gbc.gridx = 4; gbc.gridy = 0;
        this.add(muBtn, gbc);

        //Boton: Salir
        ModernButton exitBtn = new ModernButton("SALIR");
        exitBtn.setNormalBackgroundColor(new Color(220, 53, 69));
        exitBtn.setHoverBackgroundColor(new Color(227, 78, 91));
        exitBtn.setPressedBackgroundColor(new Color(194, 47, 60));
        exitBtn.setBorderColor(new Color(220, 53, 69));
        exitBtn.setNormalForegroundColor(Color.WHITE);
        exitBtn.setHoverForegroundColor(Color.WHITE);
        exitBtn.setButtonSize( 100, 30);
        exitBtn.setClickAction(e -> mainView.closeView());
        gbc.gridx = 5; gbc.gridy = 0;
        this.add(exitBtn, gbc);

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "salir");

        getActionMap().put("salir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitBtn.doClick();
            }
        });
    }

}
