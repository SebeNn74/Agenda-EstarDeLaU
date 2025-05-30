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

    public static int width = 1200;
    public static int height = 730;

    private TopPanel topPanel;
    private SideBarPanel sideBarPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private ICashierView cashierView;
    private IScheduleView scheduleView;

    public MainView(ICashierView cashierView, IScheduleView scheduleView){
        this.cashierView = cashierView;
        this.scheduleView = scheduleView;
        frameConfig();
        configTopPanel();
        configMainPanel();
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
        topPanel = new TopPanel(this);
        topPanel.setPreferredSize(new Dimension(width, 60));

        this.add(topPanel, BorderLayout.NORTH);
    }

    private void configMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        sideBarPanel = new SideBarPanel(this);
        sideBarPanel.setPreferredSize(new Dimension((int) (width * 0.23), 0));

        LeftPanel leftPanel = new LeftPanel(null);
        leftPanel.setPreferredSize(new Dimension((int) (width * 0.23), 0));

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(new RightPanel(null), "main");
        contentPanel.add(cashierView.getPanel(), "cashiers");
        contentPanel.add(scheduleView.getPanel(), "schedules");

        // Default
        cardLayout.show(contentPanel, "main");

        mainPanel.add(sideBarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void showPanelView(String key) {
        cardLayout.show(contentPanel, key);
    }

    public void openUserManual(){
        this.presenter.openUserManual();
    }

    @Override
    public void showView() {
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
