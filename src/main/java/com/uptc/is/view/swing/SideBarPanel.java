package com.uptc.is.view.swing;

import com.uptc.is.view.contracts.IMainView;

import javax.swing.*;
import java.awt.*;

public class SideBarPanel extends JPanel {

    public SideBarPanel(MainView mainView){
        setLayout(new GridLayout(0, 1));
        configButtons(mainView);
    }

    public void configButtons(MainView mainView){
        JButton btnMain = new JButton("Inicio");
        JButton btnCashiers = new JButton("Cajeros");
        JButton btnSchedules = new JButton("Horarios");

        btnMain.addActionListener(e -> mainView.showPanelView("main"));
        btnCashiers.addActionListener(e -> mainView.showPanelView("cashiers"));
        btnSchedules.addActionListener(e -> mainView.showPanelView("schedules"));

        this.add(btnMain);
        this.add(btnCashiers);
        this.add(btnSchedules);
    }

}
