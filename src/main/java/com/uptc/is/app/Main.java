package com.uptc.is.app;

import com.uptc.is.model.repository.CashierRepository;
import com.uptc.is.model.repository.ScheduleRepository;
import com.uptc.is.persistence.JsonCashierRepository;
import com.uptc.is.persistence.JsonScheduleRepository;
import com.uptc.is.persistence.JsonService;
import com.uptc.is.presenter.CashierPresenter;
import com.uptc.is.presenter.MainPresenter;
import com.uptc.is.presenter.SchedulePresenter;
import com.uptc.is.view.contracts.ICashierView;
import com.uptc.is.view.contracts.IMainView;
import com.uptc.is.view.contracts.IScheduleView;
import com.uptc.is.view.swing.CashierView;
import com.uptc.is.view.swing.MainView;
import com.uptc.is.view.swing.ScheduleView;

public class Main {

    private static final String CASHIERS_JSON_PATH = "data/cashiers.json";
    private static final String SCHEDULES_JSON_PATH = "data/schedules.json";

    public static void main(String[] args) {
        JsonService jsonService = new JsonService();

        CashierRepository cashiersRepository = new JsonCashierRepository(CASHIERS_JSON_PATH, jsonService);
        ScheduleRepository scheduleRepository = new JsonScheduleRepository(SCHEDULES_JSON_PATH, jsonService);

        ICashierView cashierView = new CashierView();
        IScheduleView scheduleView = new ScheduleView();

        new CashierPresenter(cashiersRepository, cashierView);
        new SchedulePresenter(scheduleRepository, cashiersRepository ,scheduleView);

        IMainView mainView = new MainView(cashierView, scheduleView);
        new MainPresenter(mainView);

    }

}