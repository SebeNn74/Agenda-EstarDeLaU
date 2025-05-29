package com.uptc.is.presenter;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.Schedule;
import com.uptc.is.model.domain.TimeSlot;
import com.uptc.is.model.repository.ScheduleRepository;
import com.uptc.is.view.contracts.IScheduleView;

import java.util.Optional;

public class SchedulePresenter {

    private ScheduleRepository scheduleRepo;
    private IScheduleView view;
    private Cashier currentCashier;
    private Schedule currentSchedule;

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

    public void selectSchedule(){
        String message;
        if(view.getSelectedScheduleId().isEmpty()){
            Optional<Schedule> schedule = scheduleRepo.searchById(view.getSelectedScheduleId());
            if(schedule.isPresent()){
                currentSchedule = schedule.get();
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

    public void removeSchedule(){
        String message;
        scheduleRepo.remove(view.getSelectedScheduleId());
        message = "El registro del horario se eliminó con exito";
        view.displayMessage("Titulo",message);
    }

    private boolean validSchedule(){
        String field;
        if(currentCashier.getNuip().isEmpty()){
            field = "El Cajero (Humano)";
        } else if (view.getDateInput() == null) {
            field = "La fecha";
        }else{
            return true;
        }
        //Si algun campo de las entradas esta vacio
        String message = field+" no puede estar vacío";
        view.displayError("Titulo", message);
        return false;
    }

    public void createTimeSlot(){
        if (validTimeSlot()){
            currentSchedule.addTimeSlot(madeTimeSlot());
            scheduleRepo.update(currentSchedule);
            view.displaySchedules(scheduleRepo.getAll());
            view.clearForm();
        }
    }

    private TimeSlot madeTimeSlot(){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDay(view.getDayOfWeekInput());
        timeSlot.setStartTime(view.getStartTimeInput());
        timeSlot.setEndTime(view.getEndTimeInput());
        return timeSlot;
    }

    public void removeTimeSlot(){
        String message;
        currentSchedule.removeTimeSlot(view.getSelectedTimeSlotId());
        scheduleRepo.update(currentSchedule);
        message = "La franja horaria se eliminó con exito";
        view.displayMessage("Titulo",message);
    }

    private boolean validTimeSlot(){
        String field;
        if(!currentCashier.getNuip().isEmpty() && !currentSchedule.getID().isEmpty()){
            if (view.getDayOfWeekInput() == null) {
                field = "El Día";
            } else if (view.getStartTimeInput() == null) {
                field = "La hora de inicio";
            } else if (view.getEndTimeInput() == null) {
                field = "La hora de fin";
            }else {
                return true;
            }
            String message = field+"  no puede estar vacío";
            view.displayError("Titulo", message);
            return false;
        }
        view.displayError("Titulo", "Franja horaria invalida");
        return false;
    }

    public void setCashier(Cashier cashier) {
        this.currentCashier = cashier;
        view.displaySchedules(this.scheduleRepo.getAllById(cashier.getNuip()));
    }

}
