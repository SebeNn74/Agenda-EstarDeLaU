package com.uptc.is.presenter;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.util.FileOpener;
import com.uptc.is.view.contracts.IMainView;

public class MainPresenter {

    private final IMainView mainView;
    private final CashierPresenter cashierPresenter;
    private final SchedulePresenter schedulePresenter;

    private Cashier currentCashier;

    public MainPresenter(IMainView mainView, CashierPresenter cashierPresenter, SchedulePresenter schedulePresenter){
        this.mainView = mainView;
        this.cashierPresenter = cashierPresenter;
        this.schedulePresenter = schedulePresenter;

        this.mainView.setPresenter(this);
        this.mainView.showView();
        setup();
    }

    private void setup(){
        cashierPresenter.setOnCashierSelected(cashier -> {
            currentCashier = cashier;                     // Guarda el cajero actual
            schedulePresenter.setCashier(cashier);        // Pasa el cajero al presenter de horarios
        });
    }

    public void closeApp(){
        System.exit(0);
    }

    public void openUserManual(){
        new FileOpener("M.U: Sistema de Agendamiento Estar de la U.pdf");
    }

}
