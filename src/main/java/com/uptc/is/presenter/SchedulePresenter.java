package com.uptc.is.presenter;

import com.uptc.is.model.repository.ScheduleRepository;
import com.uptc.is.view.contracts.IScheduleView;
import com.uptc.is.view.swing.ScheduleView;

public class SchedulePresenter {

    private ScheduleRepository scheduleRepository;
    private IScheduleView scheduleView;

    public SchedulePresenter(ScheduleRepository scheduleRepository, IScheduleView scheduleView){
        this.scheduleRepository = scheduleRepository;
        this.scheduleView = new ScheduleView();
    }



}
