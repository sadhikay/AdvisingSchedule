/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;


import java.util.ArrayList;
import java.util.Date;
import uta.cse4361.businessobjects.Appointment;
import uta.cse4361.businessobjects.Slot;
import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.StudentAdvisor;

public interface DatabaseImpInterface {
    public String saveSlots(ArrayList<Slot> slots);
    public String registerStudent(StudentAdvisor aa);
    public String saveAppointment(Appointment appt);
    public ArrayList<Appointment> getAppointments();
    public Appointment getAppointment(int apptID);
    public String modifyAppointment(int id, Appointment appt);
    public String modifySlot(Date d, int startHour, int endHour, int startMin, int endMin, int slotID);
    public ArrayList<Slot> getSlot();
    public ArrayList<Slot> getApptSlots();
    public ArrayList<Slot> getAvailSlots();
    public ArrayList<Slot> getAvailSlotsByTime(Date d, int startHour, int endHour, int startMin, int endMin);
    public String register(AdvisorAccount aa);
    public String validate(String email, String password);
    public AdvisorAccount getAccount(String email);
     public String addToWaitlist(Appointment appt);
    public ArrayList<Appointment> getAllWaitlist();
     public ArrayList<Appointment> getAllUserAppointments(String email);
}
