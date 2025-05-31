package com.uptc.is.view.testing;

import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {
    public MainApp() {
        setTitle("Gestión de Horarios - Cafetería");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        PanelLateral panelLateral = new PanelLateral();
        JPanel panelCentral = new JPanel(new CardLayout());

        // Paneles centrales
        PanelInicio panelInicio = new PanelInicio();
        PanelCajeros panelCajeros = new PanelCajeros();
        PanelHorariosClase panelHorarios = new PanelHorariosClase();
        PanelTurnosCafeteria panelTurnos = new PanelTurnosCafeteria();

        // Nombres clave
        panelCentral.add(panelInicio, "Inicio");
        panelCentral.add(panelCajeros, "Cajeros");
        panelCentral.add(panelHorarios, "Clases");
        panelCentral.add(panelTurnos, "Turnos");

        // Acciones desde el panel lateral
        panelLateral.setAction(command -> {
            CardLayout cl = (CardLayout) panelCentral.getLayout();
            cl.show(panelCentral, command);
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelLateral, BorderLayout.WEST);
        getContentPane().add(panelCentral, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}

