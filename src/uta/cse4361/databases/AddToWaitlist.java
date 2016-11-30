/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author sadhika
 */
public class AddToWaitlist extends RDBImplCommand {

    private Appointment appointment;
    private String sqlQuery = "INSERT INTO WAITLIST (WaitlistDate, StudentEmail, StudentName)"
            + "VALUES (?, ?, ?)";

    public AddToWaitlist(Appointment appt) {
        super();
        this.appointment = appt;
    }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setDate(1, new java.sql.Date(appointment.getDate().getTime()));
            statement.setString(2, appointment.getStudentEmail());
            statement.setString(3, appointment.getStudentName());            
            statement.executeUpdate();
            //System.out.println("Insert Appointment: \n" + appointment.toString());
            processResult();
        } catch (SQLException e) {
            System.out.println("Add To Waitlist query Failed");
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        result = "";
    }

}
