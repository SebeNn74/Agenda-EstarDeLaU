package com.uptc.is.view.swing;

import com.uptc.is.view.custom_components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TopPanel extends JPanel {

    public TopPanel(MainView mainView){
        this.setLayout(new GridBagLayout());
        this.configPanel(mainView);
    }

    private void configPanel(MainView mainView){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;

        //Label : Titulo
        JLabel title = new JLabel("AGENDAMIENTO LABORAL: ESTAR DE LA U");
        title.setFont(new Font("Arial", Font.BOLD, 20));
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
        CustomButton muButton = new CustomButton("MANUAL DE USUARIO");
        muButton.setColors(new Color(92, 205, 87  ), new Color(124, 231, 119  ));
        muButton.setTextColor(Color.WHITE);
        muButton.setButtonSize( 200, 30);
        muButton.setClickAction(e -> mainView.openUserManual());
        gbc.gridx = 4; gbc.gridy = 0;
        this.add(muButton, gbc);

        //Boton: Salir
        CustomButton exitButton = new CustomButton("SALIR [Esc]");
        exitButton.setColors(new Color(200, 50, 50), new Color(255, 100, 100));
        exitButton.setTextColor(Color.WHITE);
        exitButton.setButtonSize( 125, 30);
        exitButton.setClickAction(e -> mainView.closeView());
        gbc.gridx = 5; gbc.gridy = 0;
        this.add(exitButton, gbc);

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "salir");

        getActionMap().put("salir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButton.doClick();
            }
        });
    }

}
