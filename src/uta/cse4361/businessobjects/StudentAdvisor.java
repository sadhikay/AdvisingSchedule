/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

/**
 *
 * @author Andrew
 */
public class StudentAdvisor {
    
    private String name;
    private String email;
    private String department;
    private int ID;
    private String tempPassword;
    private int rank;
    private String question = null;
    private String answer = null;
    private String phone = null;
    private String contact = null;
    
    public StudentAdvisor(){
        
    }
    
    public boolean initialize(String name, String email, String department, String tempPassword, int rank, String question, String answer, String phone, String contact){
        boolean result = true;
        
        if(name != null && !name.isEmpty() && 
                email != null && !email.isEmpty() && 
                department != null && !department.isEmpty() && 
                tempPassword != null && !tempPassword.isEmpty())
        {
            this.name = name;
            this.email = email;
            this.department = department;
            this.tempPassword = hashPassword(tempPassword);
            this.rank = rank;
            this.question = question;
            this.answer = answer;
            this.phone = phone;
            this.contact = contact;
            System.out.println("**************************"+question);
        }
        else
        {
            result = false;
        }
        
        return result;
    }
    
    public boolean initialize(String name, String email, String department, int ID, int rank, String question, String answer, String phone, String contact){
        boolean result = true;
        
        if(name != null && !name.isEmpty() && 
                email != null && !email.isEmpty() && 
                department != null && !department.isEmpty())
        {
            this.name = name;
            this.email = email;
            this.department = department;
            this.rank = rank;
            this.question = question;
            this.answer = answer;
            this.phone = phone;
            this.contact = contact;
        }
        else
        {
            result = false;
        }
        
        return result;
    }
    
    public static String hashPassword(String tempPassword)
    {
        int hash = tempPassword.hashCode();
        return Integer.toString(hash);
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setDepartment(String department){
        this.department = department;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setTempPassword(String tempPassword){
        this.tempPassword = tempPassword;
    }
    
    public void setRank(int rank){
        this.rank = rank;
    }
    
    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getDepartment(){
        return department;
    }
    
    public int getID(){
        return ID;
    }
    
    public String getTempPassword(){
        return tempPassword;
    }
    
    public int getRank(){
        return rank;
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
    
    
}
