/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Sadhika
 */
public class EmailManager {
    
    String to;
    String from;
    String full;
    String subject;
    String body;
    Date starttime;
    int duration;
    String uid;
    String requestType;
    
    public EmailManager(Appointment appointment) {
        System.out.println("Apointment: \n" + appointment.toString());
        setTo(appointment.getStudentEmail());
        setFrom("cse6329.project@gmail.com");
        setStarttime(appointment.getDate());
        int dur = 
                Math.abs((appointment.getStartHour() * 60 + appointment.getStartMinute()) -
                         (appointment.getEndHour() * 60 + appointment.getEndMinute()));
        setDuration(dur);
       // setUid(appointment.getapptID());
        setRequestType(appointment.getType());
        System.out.println("EmailManager:\n"  + toString());
    }
    public void sendSimpleEmail(String recipient, String subject, String body) {
        Email email = new Email();
        email.sendSimpleEmail(to, subject, body);
    }

    public void sendOutlookEmail() { 
        Email email = new Email();
        email.sendOutlookEmail(to, from, subject, body, starttime, duration, requestType);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "EmailManager{" + "to=" + to + ", from=" + from + ", full=" + full + ", subject=" + subject + ", body=" + body + ", starttime=" + starttime + ", duration=" + duration + ", uid=" + uid + ", requestType=" + requestType + '}';
    }
    
    
}