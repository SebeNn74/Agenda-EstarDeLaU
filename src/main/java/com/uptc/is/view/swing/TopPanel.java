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
        this.setBackground(Color.darkGray);
    }

    private void configPanel(MainView mainView){
        this.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;

        //Label : Titulo
        JLabel title = new JLabel("AGENDAMIENTO LABORAL : ESTAR DE LA U");
        title.setFont(FontLoader.loadFont("/fonts/Roboto-SemiBold.ttf", 21f));
        title.setForeground(Color.WHITE);

        //Boton: Salir
        ModernButton exitBtn = new ModernButton("SALIR");
        exitBtn.setBackgroundColors(new Color(220, 53, 69),
                                    new Color(227, 78, 91),
                                    new Color(194, 47, 60));
        exitBtn.setBorderColor(new Color(220, 53, 69));
        exitBtn.setForegroundColors(Color.WHITE, Color.WHITE);
        exitBtn.setFontSize(15f);
        exitBtn.setButtonSize( 100, 32);
        exitBtn.setClickAction(e -> mainView.closeView());

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 0;
        this.add(title, gbc);

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

}
