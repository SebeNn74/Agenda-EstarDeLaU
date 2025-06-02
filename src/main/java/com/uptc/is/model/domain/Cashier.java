package com.uptc.is.model.domain;

import java.util.HashSet;
import java.util.Set;

public class Cashier {

    private String nuip;
    private String names;
    private String surnames;
    private String studentCode;
    private String telNumber;
    private String email;

    private Set<String> workingSchedule;

    public Cashier(){}

    public Cashier(String nuip, String studentCode){
        this.nuip = nuip;
        this.studentCode = studentCode;
        workingSchedule = new HashSet<>();
    }

    public void addScheduleId(String id){
        workingSchedule.add(id);
    }

    public void removeScheduleId(String id){
        workingSchedule.remove(id);
    }

    //Getters y Setters

    public String getNuip() {
        return nuip;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getWorkingSchedule() {
        return workingSchedule;
    }

    public void setWorkingSchedule(Set<String> workingSchedule) {
        this.workingSchedule = workingSchedule;
    }

    @Override
    public String toString() {
        return "Cashier{ nuip=" + nuip + '\''+
                ", studentCode=" + studentCode + '\'' +
                ", names=" + names + '\'' +
                ", surnames=" + surnames + '\'' +
                ", telNumber=" + telNumber + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
