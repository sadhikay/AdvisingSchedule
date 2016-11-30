/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author includetech
 */
public class GetAllUserAppointments extends RDBImplCommand{
    private String email = null;
    private String sqlQuery = "SELECT * FROM APPOINTMENT where StudentEmail = ?";
    
    public GetAllUserAppointments(String email) {
        super();
        this.email = email;
    }

    @Override
    public void queryDB() throws SQLException {
        try {
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, this.email);
            resultSet = statement.executeQuery();
            processResult();
        } catch (SQLException e) {
            System.out.println("failed");
            conn.close();
        } finally {
            statement.close();
        }
    }

    @Override
    public void processResult() {
        try {
            result = new ArrayList<Appointment>();
            while (resultSet.next()) {
                Appointment appt = new Appointment();
                int id = resultSet.getInt("ApptID");
                Date date = resultSet.getDate("ApptDate");
                int sHour = resultSet.getInt("ApptStartHour");
                int sMinute = resultSet.getInt("ApptStartMin");
                int eHour = resultSet.getInt("ApptEndHour");
                int eMinute = resultSet.getInt("ApptEndMin");
                String type = resultSet.getString("ApptType");
                String description = resultSet.getString("Description");
                String sID = resultSet.getString("StudentID");
                String sName = resultSet.getString("StudentName");
                String sEmail = resultSet.getString("StudentEmail");
                String aName = resultSet.getString("AdvisorName");
                String sMajor = resultSet.getString("StudentMajor");
                String aEmail = resultSet.getString("AdvisorEmail");
                String priority = resultSet.getString("Priority");
                String defaulted = resultSet.getString("Defaulted");
                appt.setApptID(id);
                if (appt.initialize(sMajor, sName, sID, sEmail, aName, type, description, date, sHour, eHour, sMinute, eMinute, null, aEmail, priority, defaulted)) {
                    ((ArrayList<Appointment>) result).add(appt);
                }
            }
        } catch (SQLException e) {
            System.out.println("GetAppointments Failed");
        }
    }
}
