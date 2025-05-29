package com.uptc.is.presenter;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.Schedule;
import com.uptc.is.model.repository.ScheduleRepository;
import com.uptc.is.view.contracts.IScheduleView;
import com.uptc.is.view.swing.CashierView;
import com.uptc.is.view.swing.ScheduleView;

import java.util.Optional;

public class SchedulePresenter {

    private ScheduleRepository scheduleRepo;
    private IScheduleView view;
    private Cashier currentCashier;

    public SchedulePresenter(ScheduleRepository scheduleRepository, IScheduleView scheduleView){
        this.scheduleRepo = scheduleRepository;
        this.view = scheduleView;
        this.view.setPresenter(this);
    }

    public void showSchedules(){
        view.displaySchedules(scheduleRepo.getAll());
    }

    public void createSchedule(){
        if(validSchedule()){
            scheduleRepo.create(madeSchedule());
            view.displaySchedules(scheduleRepo.getAll());
            view.clearForm();
        }
    }

    private Schedule madeSchedule(){
        Schedule schedule = new Schedule();
        schedule.setCashier(currentCashier.getNuip());
        schedule.setDate(view.getDateInput());
        return schedule;
    }

    public void searchSchedule(){
        String message;
        if(view.getSelectedScheduleId().isEmpty()){
            Optional<Schedule> schedule = scheduleRepo.searchById(view.getSelectedScheduleId());
            if(schedule.isPresent()){
                view.showScheduleDetails(schedule.get());
            }else{
                message = "No se ha registrado ningun horario con ese id";
                view.displayError("Titulo",message);
            }
        }else{
            message = "Ingrese el id del horario";
            view.displayError("Titulo",message);
        }
    }

    public void updateSchedule(){
        String message;
        if(validSchedule()){
            scheduleRepo.update(madeSchedule());
            message = "Los cambios en el horario se guardaron con exito";
            view.displayMessage("Titulo",message);
        }
    }

    public void removeSchedule(){
        String message;
        scheduleRepo.remove(view.getSelectedScheduleId());
        message = "El registro del horario se eliminó con exito";
        view.displayMessage("Titulo",message);
    }

    private boolean validSchedule(){
        String field;
        if(currentCashier.getNuip().isEmpty()){
            field = "Cajero (Humano)";
        } else if (view.getDateInput() == null) {
            field = "Día";
        }else{
            return true;
        }
        //Si algun campo de las entradas esta vacio
        String message = "El "+field+"  no puede estar vacío";
        view.displayError("Titulo", message);
        return false;
    }

    public void setCashier(Cashier cashier) {
        this.currentCashier = cashier;
        view.displaySchedules(this.scheduleRepo.getAllById(cashier.getNuip()));
    }

}
