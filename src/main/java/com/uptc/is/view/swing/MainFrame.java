package com.uptc.is.view.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public static int width = 1200;
    public static int height = 730;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public MainFrame(ActionListener listener){
        frameConfig();
        configTopPanel();
        configMainPanel(listener);
        setVisible(true);
    }

    public void frameConfig(){
        setTitle("Agenda Laboral : Estar de la U");
        setSize(width, height);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //PANTALLA COMPLETA
        /*
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(this);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
         */
    }

    private void configTopPanel(){
        TopPanel topPanel = new TopPanel();
        topPanel.setBackground(new Color(136, 165, 255));
        topPanel.setPreferredSize(new Dimension(width, 60));

        this.add(topPanel, BorderLayout.NORTH);
    }

    private void configMainPanel(ActionListener listener){
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

        leftPanel = new LeftPanel(listener);
        leftPanel.setPreferredSize(new Dimension((int) (width * 0.23), 0));

        rightPanel = new RightPanel(listener);
        rightPanel.setBackground(new Color(209, 209, 209));
        rightPanel.setPreferredSize(new Dimension((int) (width * 0.5), 0));

        panelCentral.add(leftPanel, BorderLayout.WEST);
        panelCentral.add(rightPanel, BorderLayout.CENTER);

        this.add(panelCentral, BorderLayout.CENTER);
    }

}
