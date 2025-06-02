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
        this.scheduleRepo = scheduleRepository;
        this.cashierRepo = cashierRepository;
        this.view = scheduleView;
        this.view.setPresenter(this);
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
        schedule2.addTimeSlot(ts);
        scheduleRepo.update(schedule2);
        System.out.println(schedule2);

    }

    public void showSchedules(){
        view.displaySchedules(scheduleRepo.getAll());
    }

    public void createSchedule(){
        if(validSchedule()){
            madeSchedule();
            scheduleRepo.create(currentSchedule);
            view.displaySchedules(scheduleRepo.getAll());
            view.clearForm();
            System.out.println("Horario "+currentSchedule.getID()+" creado");
        }
    }

    private void madeSchedule(){
        Schedule schedule = new Schedule();
        schedule.setCashier(view.getNuipInput());
        schedule.setDate(view.getDateInput());
        currentSchedule = schedule;
    }

    public void selectSchedule(){
        String message;
        if(view.getSelectedScheduleId().isEmpty()){
            Optional<Schedule> schedule = scheduleRepo.searchById(view.getSelectedScheduleId());
            if(schedule.isPresent()){
                currentSchedule = schedule.get();
                view.showScheduleDetails(schedule.get());
                System.out.println("Horario "+currentSchedule.getID()+" encontrado");
            }else{
                message = "No se ha registrado ningun horario con ese id";
                view.displayError(message);
            }
        }else{
            message = "Ingrese el id del horario";
            view.displayError(message);
        }
    }

    public void removeSchedule(){
        String message;
        scheduleRepo.remove(currentSchedule.getID());
        message = "El registro del horario se eliminó con exito";
        view.displayMessage(message);
        System.out.println("Horario "+currentSchedule.getID()+" eliminado");
    }

    private boolean validSchedule(){
        String field;
        if(view.getNuipInput().isEmpty()){
            field = "El Cajero (Humano)";
        } else if (view.getDateInput() == null) {
            field = "La fecha";
        }else{
            return true;
        }
        //Si algun campo de las entradas esta vacio
        String message = field+" no puede estar vacío";
        view.displayError(message);
        return false;
    }

    public void createTimeSlot(){
        if (validTimeSlot()){
            madeTimeSlot();
            if(currentSchedule.addTimeSlot(currentTimeSlot)){
                scheduleRepo.update(currentSchedule);
                view.displaySchedules(scheduleRepo.getAll());
                view.clearForm();
                System.out.println("Franja "+currentTimeSlot.getID()+" creada");
            }
        }
    }

    private void madeTimeSlot(){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDay(view.getDayOfWeekInput());
        timeSlot.setStartTime(view.getStartTimeInput());
        timeSlot.setEndTime(view.getEndTimeInput());
        currentTimeSlot = timeSlot;
    }

    public void removeTimeSlot(){
        String message;
        if(currentSchedule.removeTimeSlot(view.getSelectedTimeSlotId())){
            scheduleRepo.update(currentSchedule);
            message = "La franja horaria se eliminó con exito";
            view.displayMessage(message);
            System.out.println("Franja "+currentSchedule.getID()+" eliminada");
        }
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
            view.displayError(message);
            return false;
        }
        view.displayError("Franja horaria invalida");
        return false;
    }

    public void selectCashier(String nuip){
        String message;
        if(!nuip.isEmpty()){
            Optional<Cashier> cashier = cashierRepo.searchById(nuip);
            if(cashier.isPresent()){
                view.showCashierNuip(cashier.get().getNuip());
                currentCashier = cashier.get();
                selectSchedule();
            }else{
                message = "No se ha registrado ningun cajero (humano) con ese número de identidad";
                view.displayError(message);
            }
        }else{
            message = "Ingrese el número de identidad del cajero (humano)";
            view.displayError(message);
        }
    }

    public void setCashier(Cashier cashier) {
        this.currentCashier = cashier;
        view.displaySchedules(this.scheduleRepo.getAllById(cashier.getNuip()));
    }

}
