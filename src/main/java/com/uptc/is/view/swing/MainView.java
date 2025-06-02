package com.uptc.is.view.swing;

import com.uptc.is.presenter.MainPresenter;
import com.uptc.is.view.contracts.ICashierView;
import com.uptc.is.view.contracts.IMainView;
import com.uptc.is.view.contracts.IScheduleView;
import com.uptc.is.view.custom_components.MessageDialog;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements IMainView {

    private MainPresenter presenter;

    public static int width = 1230;
    public static int height = 740;

    private TopPanel topPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private final ICashierView cashierView;
    private final IScheduleView scheduleView;

    public MainView(ICashierView cashierView, IScheduleView scheduleView){
        this.cashierView = cashierView;
        this.scheduleView = scheduleView;
        frameConfig();
        configPanel();
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

    private void configPanel(){
        SideBarPanel sideBarPanel = new SideBarPanel(this);
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel(new BorderLayout());

        topPanel = new TopPanel(this);
        topPanel.setPreferredSize(new Dimension(0,60));

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(new HomePanel(), "home");
        contentPanel.add(cashierView.getPanel(), "cashiers");
        contentPanel.add(scheduleView.getPanel(), "schedules");

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideBarPanel, mainPanel);
        splitPane.setResizeWeight(0.18);
        splitPane.setDividerSize(0);
        splitPane.setContinuousLayout(true);

        this.add(splitPane, BorderLayout.CENTER);
    }

    public void showCashierForm() {
        cardLayout.show(contentPanel, "cashiers");
        cashierView.showCashierFormPanel();
        topPanel.setTitle("INGRESO DE CAJEROS");
    }

    public void showCashierList() {
        cardLayout.show(contentPanel, "cashiers");
        cashierView.showCashierListPanel();
        topPanel.setTitle("REGISTRO DE CAJEROS");
    }

    public void showScheduleForm() {
        cardLayout.show(contentPanel, "schedules");
        scheduleView.showScheduleFormPanel();
        topPanel.setTitle("INGRESO DE HORARIOS");
    }

    public void showGeneralSchedule() {
        cardLayout.show(contentPanel, "schedules");
        scheduleView.showScheduleListPanel();
        topPanel.setTitle("CALENDARIO GENERAL");
    }

    public void showHome(){
        cardLayout.show(contentPanel, "home");
        topPanel.setTitle("");
    }

    public void openUserManual(){
        this.presenter.openUserManual();
    }

    @Override
    public void showView() {
        cardLayout.show(contentPanel, "home");
        this.setVisible(true);
    }

    @Override
    public void closeView() {
        this.presenter.closeApp();
    }

    @Override
    public void showCashierManagement() {

    }

    @Override
    public void showGeneralScheduleManagement() {

    }

    @Override
    public void displayError(String title, String message) {
        new MessageDialog(this, message, MessageDialog.MessageType.ERROR);
    }

    @Override
    public void displayMessage(String title, String message) {
        new MessageDialog(this, message, MessageDialog.MessageType.SUCCESS);
    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

}
