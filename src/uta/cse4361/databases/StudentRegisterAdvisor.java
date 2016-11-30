/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import uta.cse4361.businessobjects.StudentAdvisor;

/**
 *
 * @author Andrew
 */
public class StudentRegisterAdvisor extends RDBImplCommand {
    
    private StudentAdvisor aa;
    private String sqlQuery = "INSERT INTO USER(UserEmail, UserPassword,"
            + " UserName, UserDepartment, UserRank,SecurityQuestion,Answer,Phone, Contact) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
    public StudentRegisterAdvisor(StudentAdvisor aa){
        this.aa = aa;
    }
    
    @Override
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, aa.getEmail());
            statement.setString(2, aa.getTempPassword());
            statement.setString(3, aa.getName());
            statement.setString(4, aa.getDepartment());
            statement.setInt(5, aa.getRank());
            statement.setString(6,aa.getQuestion());
            statement.setString(7, aa.getAnswer());
            statement.setString(8,aa.getPhone());
            statement.setString(9,aa.getContact());
            statement.executeUpdate();
            processResult();
        }
        catch(SQLException e){
        System.out.println("RegisterAdvisor Failed");
            conn.close();
        } finally {
            statement.close();
        }
    }
    
    @Override
    public void processResult(){
        result ="";
    }
}
