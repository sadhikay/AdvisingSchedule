/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import uta.cse4361.businessobjects.StudentAdvisor;
import uta.cse4361.databases.DatabaseManager;
import uta.cse4361.databases.RelationalDatabaseImpl;
import uta.cse4361.interfaces.Constants;
import static uta.cse4361.interfaces.Constants.SUCCESS_MESSAGE;

/**
 *
 * @author Nabin
 */
public class CreateStudentAccountBean implements Constants{
    private String name = null;
    private String email= null;
    private String department = null;
    private int ID= 0;
    private String tempPassword = null;
    private int rank = 2;
    private String question = null;
    private String answer = null;
    private String phone = null;
    private String contact = null;
    

    public CreateStudentAccountBean() {
    }

    
    public String Advisor(){
       String returnMessage = SUCCESS_MESSAGE;
       
       StudentAdvisor AA = new StudentAdvisor();
       boolean a = AA.initialize(this.name, this.email, this.department, this.tempPassword,  this.rank, this.question, this.answer, this.phone, this.contact);
       if (a == false)
           return this.CREATE_ADVISOR_FAIL;
       DatabaseManager dm = new DatabaseManager();
       returnMessage = dm.registerStudent(AA);
       return returnMessage;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public int getID() {
        return ID;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public int getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    
    public boolean changePassword(){
        AppDao dao = new AppDao();
        return dao.changePassword(tempPassword, email);
    }
    
    public String  getUserQuestion(){
        return new AppDao().getQuestion(email);
    }
    
    public String  getUserAnswer(){
        return new AppDao().getAnswer(email);
    }
}
