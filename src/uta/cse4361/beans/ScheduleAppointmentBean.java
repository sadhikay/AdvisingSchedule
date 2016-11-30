/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import uta.cse4361.businessobjects.Appointment;
import uta.cse4361.businessobjects.Scheduler;
import uta.cse4361.databases.DatabaseManager;
import uta.cse4361.interfaces.Constants;
import  uta.cse4361.businessobjects.CustomEmail;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

/**
 *
 * @author Han
 */
public class ScheduleAppointmentBean implements Constants {

    private String studentMajor = null;
    private String studentName = null;
    private String studentID = null;
    private String studentEmail = null;
    private String advisorName = null;
    private String description = null;
    private String type = null;
    private int startHour = 0;
    private int startMinute = 0;
    private int endHour = 0;
    private int endMinute = 0;
    private Date date = null;
    private String timeReq = null;
    private String subEnrolled = null;
    private String semester = null;
    private String priority = null;
    private String students = null;
    private String fromDate = null;
    private String toDate = null;
    private String advisorEmail = null;
    private String defaulted = null;

    public String getAdvisorEmail() {
        return advisorEmail;
    }

    public void setAdvisorEmail(String advisorEmail) {
        this.advisorEmail = advisorEmail;
    }

    public String getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(String defaulted) {
        this.defaulted = defaulted;
    }
         

    public ScheduleAppointmentBean() {

    }

    

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

   
    public String scheduleAppointment() throws SQLException, ClassNotFoundException {
        String msg = SUCCESS_MESSAGE;
        Appointment a = new Appointment();
        
        boolean r = a.initialize(this.studentMajor, this.studentName, this.studentID, this.studentEmail, this.advisorName, this.type,
                this.description, this.date,
                this.startHour, this.endHour,
                this.startMinute, this.endMinute,
                this.priority, this.subEnrolled,
                this.timeReq, this.semester);
        if (r == false) {
            return this.INITIALIZE_APPOINTMENT_FAIL;
        }
        
        DatabaseManager dm = new DatabaseManager();
		ArrayList<Appointment> appointments = dm.getAllUserAppointments(studentEmail);
		Calendar now = Calendar.getInstance();
		now.set(2016, Calendar.APRIL, 25, 0, 0, 0);
		int totalAppointments = 0;
		for (Appointment appointment : appointments) {
			Calendar appDate = Calendar.getInstance();
			appDate.setTime(appointment.getDate());

			if ((now.get(Calendar.YEAR) == appDate.get(Calendar.YEAR)
					&& now.get(Calendar.MONTH) == appDate.get(Calendar.MONDAY)
					&& now.get(Calendar.DAY_OF_MONTH) == appDate.get(Calendar.DAY_OF_MONTH))
					|| now.get(Calendar.WEEK_OF_MONTH) == appDate.get(Calendar.WEEK_OF_MONTH)) {
				totalAppointments++;
				if (totalAppointments > 1) {
					msg = "Mutiple appointments";
					return msg;
				}
			}
		}
        Scheduler s = new Scheduler();
        msg = s.schedule(a);
       
        return msg;
    }
  
	public String addToWaitlist() {
		String msg = SUCCESS_MESSAGE;
		Appointment a = new Appointment();
		boolean r = a.initialize(this.studentMajor, this.studentName, this.studentID, this.studentEmail,
				this.advisorName, this.type, this.description, this.date, this.startHour, this.endHour,
				this.startMinute, this.endMinute, Constants.EMAIL_REQUEST, this.advisorEmail, this.priority, this.defaulted);
		if (r == false) {
			return this.INITIALIZE_APPOINTMENT_FAIL;
		}
		DatabaseManager dm = new DatabaseManager();
		dm.addToWaitlist(a);
		return msg;
	}
    public String generateStudentMessage() {
        String message = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");      
        String dd = sdf.format(date);
        message = "You have an appointment with " + advisorName + " at " + dd + " from " + startHour + ":" + startMinute + " to " + endHour + ":" + endMinute;
        return message;
    }

    public String generateAdvisorMessage() {
        String message = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");      
        String dd = sdf.format(date);
        message = "You have an appointment with " + studentName + " at " + dd + " from " + startHour + ":" + startMinute + " to " + endHour + ":" + endMinute;
        message += " for the following issues: \n"+description;
        return message;
    }
    
public void SendEmail(String stuMsg,String email) throws MessagingException, AddressException, UnsupportedEncodingException{
    CustomEmail Email = new CustomEmail("cse6329.project@gmail.com",
					"advancese", studentEmail,"Appointment Confirmation",
					stuMsg);
			Email.createEmailMessage();
			Email.sendEmail();
        
}
    public String getTimeReq() {
        return timeReq;
    }

    public void setTimeReq(String timeReq) {
        this.timeReq = timeReq;
    }

    public String getSubEnrolled() {
        return subEnrolled;
    }

    public void setSubEnrolled(String subEnrolled) {
        this.subEnrolled = subEnrolled;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    
//    public void sendEmail(String msg, String receiptEmail) {
//        String from = "cse4361fall14@gmail.com";
//        final String username = "cse4361fall14";
//        final String password = "design.pattern";
//        
//        String host = "smtp.gmail.com";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//
//        // Get the Session object.
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//            // Create a default MimeMessage object.
//            Message message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(receiptEmail));
//
//            // Set Subject: header field
//            message.setSubject("UTA Advising Appointment Confirmation");
//
//            // Now set the actual message
//            message.setText(msg);
//
//            // Send message
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // Setters
    public void setStudentMajor(String sMajor) {
        this.studentMajor = sMajor;
    }
    public void setStudentName(String sName) {
        this.studentName = sName;
    }

    public void setStudentID(String sID) {
        this.studentID = sID;
    }
    
    public void setStudentEmail(String sEmail) {
        this.studentEmail = sEmail;
    }

    public void setAdvisorName(String aName) {
        this.advisorName = aName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String dp) {
        this.description = dp;
    }

    public void setDate(Date d) {
        this.date = d;
    }

    public void setStartHour(int sH) {
        this.startHour = sH;
    }

    public void setEndHour(int eH) {
        this.endHour = eH;
    }

    public void setStartMinute(int sM) {
        this.startMinute = sM;
    }

    public void setEndMinute(int eM) {
        this.endMinute = eM;
    }
  

    // Getters
    public String getStudentMajor() {
        return this.studentMajor;
    }
    public String getStudentName() {
        return this.studentName;
    }

    public String getStudentID() {
        return this.studentID;
    }
    
    public String getStudentEmail() {
        return this.studentEmail;
    }

    public String getType() {
        return this.type;
    }

    public String getAdvisorName() {
        return this.advisorName;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public Date getDate() {
        return this.date;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public int getCountOfAppointments(){
        return new AppDao().getCountOfAppointments();
    }
  
}
