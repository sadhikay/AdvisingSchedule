package uta.cse4361.beans;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
public class AppDao {
DatabaseDao database = null;
public AppDao(){
    database =  new DatabaseDao();
}    

public boolean changePassword(String password,String email){
    Connection con = null;
    try{
        con = database.getConnection();
        PreparedStatement ps = con.prepareStatement("update user set UserPassword = ? where UserEmail=?");
        ps.setInt(1, password.hashCode());
        ps.setString(2, email);
        
        return ps.executeUpdate()==1?true:false;
    
    }catch(Exception ex){
        System.out.println(ex.getMessage());
        return false;
    }
}
public String getQuestion(String email){
    Connection con = null;
    ResultSet rs= null;
    try{
        con = database.getConnection();
        PreparedStatement ps = con.prepareStatement("select SecurityQuestion from user where UserEmail=?");
        ps.setString(1,email);
        rs = ps.executeQuery();
       while(rs.next()){
           return rs.getString("SecurityQuestion");
       }     
        return  "none";
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
        return "";
    }
}

public String getAnswer(String email){
    Connection con = null;
    ResultSet rs= null;
    try{
        con = database.getConnection();
        PreparedStatement ps = con.prepareStatement("select Answer from user where UserEmail=?");
        ps.setString(1,email);
        rs = ps.executeQuery();
       while(rs.next()){
           return rs.getString("Answer");
       }     
        return  "none";
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
        return "";
    }
}

public boolean checkAppointments(Date date,String studentId){
    Connection con = null;
    ResultSet rs= null;
    try{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        con = database.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from appointment where ApptDate=? and StudentID=?");
        ps.setDate(1,new java.sql.Date(date.getTime()));
        ps.setString(2, studentId);
        rs = ps.executeQuery();
       
        return  rs.next();
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
        return false;
    }
    
}

public int getCountOfAppointments(){
  Connection con = null;
    ResultSet rs= null;
    try{
        con = database.getConnection();
        PreparedStatement ps = con.prepareStatement("select count(*) as no from appointment");
        rs = ps.executeQuery();
        rs.next();
        out.println("***************************************************"+rs.getInt("no")+"**********************88");
         return rs.getInt("no");
         
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
        return 0;
    }  
}

}
