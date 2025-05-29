package com.uptc.is.view.swing;

import com.uptc.is.presenter.MainPresenter;
import com.uptc.is.view.contracts.ICashierView;
import com.uptc.is.view.contracts.IMainView;
import com.uptc.is.view.contracts.IScheduleView;
import com.uptc.is.view.custom_components.MessageDialog;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements IMainView {

    private MainPresenter mainPresenter;

    public static int width = 1200;
    public static int height = 730;
    private ICashierView cashierPanel;
    private IScheduleView schedulePanel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public MainView(ICashierView cashierView, IScheduleView scheduleView){
        this.cashierPanel = cashierView;
        this.schedulePanel = scheduleView;
        frameConfig();
        configTopPanel();
        configMainPanel();
        showView();
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

    private void configMainPanel(){
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

        leftPanel = new LeftPanel(null);
        leftPanel.setPreferredSize(new Dimension((int) (width * 0.23), 0));

        rightPanel = new RightPanel(null);
        rightPanel.setBackground(new Color(209, 209, 209));
        rightPanel.setPreferredSize(new Dimension((int) (width * 0.5), 0));

        panelCentral.add(leftPanel, BorderLayout.WEST);
        panelCentral.add(rightPanel, BorderLayout.CENTER);

        this.add(panelCentral, BorderLayout.CENTER);
    }

    @Override
    public void showView() {
        this.setVisible(true);
    }

    @Override
    public void closeView() {
        this.setVisible(false);
    }

    @Override
    public void showCashierManagement() {

    }

    @Override
    public void showGeneralScheduleManagement() {

    }

    @Override
    public void displayError(String title, String message) {
        new MessageDialog(this, "ADVERTENCIA", MessageDialog.MessageType.ERROR);
    }

    @Override
    public void displayMessage(String title, String message) {
        new MessageDialog(this, "INFORMACIÓN", MessageDialog.MessageType.SUCCESS);
    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        mainPresenter = presenter;
    }

}
