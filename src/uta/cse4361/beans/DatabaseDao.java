package uta.cse4361.beans;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseDao {

public Connection getConnection(){
    Connection con = null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://advising.csz3rqucvkcr.us-east-1.rds.amazonaws.com:3306/ADVISING","root","mypassword");
        return con;
    }catch(Exception ex){
        System.out.println(ex.getMessage());
        return null;
    }
}
    
}
