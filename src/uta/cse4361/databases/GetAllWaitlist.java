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
 * @author sadhika
 */
public class GetAllWaitlist extends RDBImplCommand {
	private String sqlQuery = "SELECT * FROM Waitlist";

	public GetAllWaitlist() {
		super();
	}

	@Override
	public void queryDB() throws SQLException {
		try {
			statement = conn.prepareStatement(sqlQuery);
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
                int id = resultSet.getInt("WaitlistID");
                Date date = resultSet.getDate("WaitlistDate");                
                String sName = resultSet.getString("StudentName");
                String sEmail = resultSet.getString("StudentEmail");                
                appt.setApptID(id);
                appt.initialize(sName, sEmail, date);
                    ((ArrayList<Appointment>) result).add(appt);
                
            }
        } catch (SQLException e) {
            System.out.println("GetAllWaitlist Failed");
        }
    }
}
