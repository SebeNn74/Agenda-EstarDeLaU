package com.uptc.is.presenter;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.Schedule;
import com.uptc.is.model.domain.TimeSlot;
import com.uptc.is.model.repository.CashierRepository;
import com.uptc.is.model.repository.ScheduleRepository;
import com.uptc.is.view.contracts.IScheduleView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class SchedulePresenter {

    private final ScheduleRepository scheduleRepo;
    private final CashierRepository cashierRepo;
    private final IScheduleView view;
    private Cashier currentCashier;
    private Schedule currentSchedule;
    private TimeSlot currentTimeSlot;

    public SchedulePresenter(ScheduleRepository scheduleRepository, CashierRepository cashierRepository, IScheduleView scheduleView){
        this.cashierRepo = cashierRepository;
        this.scheduleRepo = scheduleRepository;
        this.view = scheduleView;
        this.view.setPresenter(this);
        this.view.displayCashierList(cashierRepo.getAll());
    }

    public void test(){
        Cashier cashier = new Cashier("1056798612", "202211892");
        cashier.setNames("Sebastian");
        cashier.setSurnames("Niño Niño");
        cashier.setTelNumber("3124033131");
        cashier.setEmail("sebastian.nino06@gmail.com");

        setCashier(cashier);

        Schedule schedule = new Schedule();
        schedule.setCashier(currentCashier.getNuip());
        schedule.setDate(LocalDate.of(2004, 12, 7));
        scheduleRepo.create(schedule);
        System.out.println(schedule);

        LocalDate date = LocalDate.of(2026, 5, 16);

        Schedule schedule2 = new Schedule();
        schedule2.setCashier(currentCashier.getNuip());
        schedule2.setDate(date);
        scheduleRepo.create(schedule2);
        System.out.println(schedule2);

        TimeSlot ts = new TimeSlot();
        ts.setDay(date.getDayOfWeek());
        ts.setStartTime(LocalTime.of(9,0));
        ts.setEndTime(LocalTime.of(11,0));
        schedule2.setTimeSlot(ts);
        scheduleRepo.update(schedule2);
        System.out.println(schedule2);

    }

    public void loadData(){
        view.displaySchedules(scheduleRepo.getAll());
        view.displayCashierList(cashierRepo.getAll());
    }

    public void createSchedule(){
        if(validSchedule()){
            madeSchedule();
            scheduleRepo.create(currentSchedule);
            currentCashier.addScheduleId(currentSchedule.getID());
            cashierRepo.update(currentCashier);
            view.displaySchedules(scheduleRepo.getAll());
            view.displayMessage("Horario asignado con exito");
            view.clearForm();
        }
    }

    private void madeSchedule(){
        Schedule schedule = new Schedule();
        schedule.setCashier(view.getNuipInput());
        schedule.setDate(view.getDateInput());
        madeTimeSlot();
        schedule.setTimeSlot(currentTimeSlot);
        currentSchedule = schedule;
    }

    public void removeSchedule(){
        scheduleRepo.remove(currentSchedule.getID());
        currentCashier.removeScheduleId(currentSchedule.getID());
        cashierRepo.update(currentCashier);
        view.displaySchedules(scheduleRepo.getAll());
        String message = "El registro del horario se eliminó con exito";
        view.displayMessage(message);
        view.clearForm();
    }

    private boolean validSchedule(){
        String message;
        if(view.getNuipInput().isEmpty()){
            message = "El Número de identidad del cajero no puede estar vació";
        } else if (view.getDateInput() == null) {
            message = "Debe seleccionar una fecha";
        }else if (validTimeSlot()){
            Optional<Cashier> cashier = cashierRepo.searchById(view.getNuipInput());
            if (cashier.isPresent()){
                currentCashier = cashier.get();
                return true;
            }else{
                message = "No se ha registrado ningun cajero (humano) con ese número de identidad";
            }
        }else{
            return false;
        }
        view.displayError(message);
        return false;
    }

    private void madeTimeSlot(){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDay(view.getDayOfWeekInput());
        timeSlot.setStartTime(view.getStartTimeInput());
        timeSlot.setEndTime(view.getEndTimeInput());
        currentTimeSlot = timeSlot;
    }

    private boolean validTimeSlot(){
        String field;
        if (view.getStartTimeInput() == null) {
            field = "La hora de inicio";
        } else if (view.getEndTimeInput() == null) {
            field = "La hora de fin";
        }else {
            return true;
        }
        String message = field+"  no puede estar vacía";
        view.displayError(message);
        return false;
    }

    public void selectSchedule(String id){
        String message;
        if(!id.isEmpty()){
            Optional<Schedule> schedule = scheduleRepo.searchById(id);
            if(schedule.isPresent()){
                view.showScheduleDetails(schedule.get());
                currentSchedule = schedule.get();
                view.displaySchedules(scheduleRepo.getAll());
            }else{
                message = "No se ha registrado ningun cajero (humano) con ese número de identidad";
                view.displayError(message);
            }
        }else{
            message = "Primero debe seleccionar un horario de la lista";
            view.displayError(message);
        }
    }

    public void selectCashier(String nuip){
        String message;
        if(!nuip.isEmpty()){
            Optional<Cashier> cashier = cashierRepo.searchById(nuip);
            if(cashier.isPresent()){
                view.showCashierNuip(cashier.get().getNuip());
                setCashier(cashier.get());
                view.clearSearchField();
            }else{
                message = "No se ha registrado ningun cajero (humano) con ese número de identidad";
                view.displayError(message);
            }
        }else{
            view.displaySchedules(scheduleRepo.getAll());
            message = "Ingrese el número de identidad del cajero (humano)";
            view.displayError(message);
        }
    }

    public void setCashier(Cashier cashier) {
        this.currentCashier = cashier;
        view.displaySchedules(this.scheduleRepo.getAllById(cashier.getNuip()));
    }

}
