package com.uptc.is.view.testing;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;

public class PanelCalendario extends JPanel {
    public PanelCalendario() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("📅 Calendario General");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.BLACK);

        JPanel calendario = new JPanel(new GridLayout(0, 7, 5, 5));
        calendario.setBackground(Color.WHITE);

        String[] dias = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        for (String dia : dias) {
            JLabel lbl = new JLabel(dia, SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 14));
            lbl.setForeground(Color.BLACK);
            calendario.add(lbl);
        }

        LocalDate today = LocalDate.now();
        YearMonth mesActual = YearMonth.of(today.getYear(), today.getMonth());
        int primerDiaSemana = mesActual.atDay(1).getDayOfWeek().getValue();
        if (primerDiaSemana == 7) primerDiaSemana = 0;

        int diasMes = mesActual.lengthOfMonth();

        for (int i = 0; i < primerDiaSemana; i++) {
            calendario.add(new JLabel(""));
        }

        for (int dia = 1; dia <= diasMes; dia++) {
            JButton diaBtn = new JButton(String.valueOf(dia));
            diaBtn.setFocusPainted(false);
            diaBtn.setBackground(Color.WHITE);
            diaBtn.setForeground(Color.BLACK);
            calendario.add(diaBtn);
        }

        JScrollPane scroll = new JScrollPane(calendario);
        scroll.setBorder(null);

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}