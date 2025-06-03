package com.uptc.is.presenter;

import com.uptc.is.util.FileOpener;
import com.uptc.is.view.contracts.IMainView;

public class MainPresenter {

    public MainPresenter(IMainView mainView){
        mainView.setPresenter(this);
        mainView.showView();
    }

    public void closeApp(){
        System.exit(0);
    }

    public void openUserManual(){
        new FileOpener("manual/M.U_Sistema de Agendamiento Estar de la U.pdf");
    }

}
