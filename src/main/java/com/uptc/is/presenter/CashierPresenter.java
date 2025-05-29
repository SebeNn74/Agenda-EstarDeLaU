package com.uptc.is.presenter;

import com.uptc.is.model.repository.CashierRepository;
import com.uptc.is.view.contracts.ICashierView;

public class CashierPresenter {

    private CashierRepository cashierRepository;
    private ICashierView cashierView;

    public CashierPresenter(CashierRepository cashierRepository, ICashierView cashierView){
        this.cashierRepository = cashierRepository;
        this.cashierView = cashierView;
    }

    private void showCashiers(){

    }

    private void addCashier(){

    }

}
