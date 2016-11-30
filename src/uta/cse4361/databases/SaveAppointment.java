/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import uta.cse4361.beans.AppDao;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author Han
 */
public class SaveAppointment extends RDBImplCommand {

    private Appointment appointment;
    
    private String sqlQuery = "INSERT INTO APPOINTMENT (ApptDate, ApptStartHour, ApptStartMin, ApptEndHour, ApptEndMin, "
            + "ApptType, Description, StudentID, StudentName, StudentMajor, StudentEmail, AdvisorName,Duration,CoursesEnrolled,Semester,AppointmentPriority) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public SaveAppointment(Appointment appt) {
        super();
        this.appointment = appt;
    }
    public SaveAppointment(){
        
    }
public boolean addToDb(Appointment appointment) throws SQLException, ClassNotFoundException{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://advising.csz3rqucvkcr.us-east-1.rds.amazonaws.com:3306/ADVISING","root","mypassword");
         PreparedStatement  ps = con.prepareStatement("INSERT INTO APPOINTMENT (ApptDate, ApptStartHour, ApptStartMin, ApptEndHour, ApptEndMin, "
            + "ApptType, Description, StudentID, StudentName, StudentMajor, StudentEmail, AdvisorName,Duration,CoursesEnrolled,Semester,AppointmentPriority) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?)");
         ps.setDate(1, new java.sql.Date(appointment.getDate().getTime()));
            ps.setInt(2, appointment.getStartHour());
            ps.setInt(3, appointment.getStartMinute());
            ps.setInt(4, appointment.getEndHour());
            ps.setInt(5, appointment.getEndMinute());
            ps.setString(6, appointment.getType());
            ps.setString(7, appointment.getDescription());
            ps.setString(8, appointment.getStudentID());
            ps.setString(9, appointment.getStudentName());
            ps.setString(10, appointment.getStudentMajor());
            ps.setString(11, appointment.getStudentEmail());
            ps.setString(12, appointment.getAdvisorName());
            ps.setString(13, appointment.getTimeReq());
            ps.setString(14, appointment.getSubEnrolled());
            ps.setString(15, appointment.getSemester());
            ps.setString(16, appointment.getPriority());
            AppDao dao = new AppDao();
            
            if(!dao.checkAppointments(appointment.getDate(), appointment.getStudentID())){
                   if (ps.executeUpdate()==1) {
                       System.out.println("saved ***************");
                       return true;
                   }else{
                       System.out.println("exist **************");
                       return false;
                   }
            }else{
                System.out.println("failed **************");
                return false;
            }
   }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            System.out.println(appointment.toString()+"***************");
            statement.setDate(1, new java.sql.Date(appointment.getDate().getTime()));
            statement.setInt(2, appointment.getStartHour());
            statement.setInt(3, appointment.getStartMinute());
            statement.setInt(4, appointment.getEndHour());
            statement.setInt(5, appointment.getEndMinute());
            statement.setString(6, appointment.getType());
            statement.setString(7, appointment.getDescription());
            statement.setString(8, appointment.getStudentID());
            statement.setString(9, appointment.getStudentName());
            statement.setString(10, appointment.getStudentMajor());
            statement.setString(11, appointment.getStudentEmail());
            statement.setString(12, appointment.getAdvisorName());
            statement.setString(13, appointment.getTimeReq());
            statement.setString(14, appointment.getSubEnrolled());
            statement.setString(15, appointment.getSemester());
            statement.setString(16, appointment.getPriority());
            AppDao dao = new AppDao();
            
            if(!dao.checkAppointments(appointment.getDate(), appointment.getStudentID())){
                   if (statement.executeUpdate()==1) {
                       System.out.println("saved ***************");
                       
                   }else{
                       System.out.println("exist **************");
                       //return false;
                   }
            }else{
                System.out.println("failed **************");
               // return false;
            }
         
        } catch (SQLException e) {
            System.out.println("SaveAppointment query Failed");
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        result = "";
    }
    
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SaveAppointment sa = new SaveAppointment();
        sa.appointment = new Appointment(100, "cse", "suri", "1210310259", "suri@gmail.com",
			"book", "desc", "type", 2, 2, 2,
			2, new Date(), "221", "CSE", "vas", "urgent");
        //sa.queryDB();
        sa.addToDb(sa.appointment);
    }
}
