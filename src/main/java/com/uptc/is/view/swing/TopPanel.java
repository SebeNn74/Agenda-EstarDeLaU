package com.uptc.is.view.swing;

import com.uptc.is.util.FontLoader;
import com.uptc.is.view.custom_components.ModernButton;
import com.uptc.is.view.custom_components.ModernButtonFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TopPanel extends JPanel {

    private JLabel titleLb;

    public TopPanel(MainView mainView){
        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(5, 25, 5, 25));
        this.configPanel(mainView);
    }

    private void configPanel(MainView mainView){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;

        //Label : Titulo
        titleLb = new JLabel();
        titleLb.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 22f));
        titleLb.setForeground(new Color(15, 14, 29));

        //Boton: Salir
        ModernButton exitBtn = ModernButtonFactory.danger("SALIR");
        exitBtn.setFontSize(15f);
        exitBtn.setButtonSize( 100, 32);
        exitBtn.addClickAction(e -> mainView.closeView());

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 0;
        this.add(titleLb, gbc);

        this.add(Box.createVerticalGlue(), gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
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

    public void setTitle(String title){
        titleLb.setText(title);
    }

}
