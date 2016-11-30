/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import java.rmi.server.UID;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
/**
 *
 * @author sadhika
 */
public class Email {
    /**
     * 
     */
    private static String from = "cse6329.project@gmail.com";
    private static String pw = "advancese";
    private static String host = "smtp.gmail.com";
    private static String port = "587";
    private static SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");
 
    private Properties getEmailProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.user",from);
        properties.put("mail.smtp.password",pw);
        properties.put("mail.smtp.port",port);
        properties.put("mail.smtp.auth","true");
       
        
        //properties.put("mail.smtp.socketFactory.port","465");
        //properties.put("mail.smtp.socketFactory.class",
        //                                "javax.net.ssl.SSLSocketFactory");
        return properties;
    }
    
    public Session getSession() {
        Session session = Session.getInstance(getEmailProperties(),
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("cse6329.project@gmail.com", "advancese");
                    }
            });
        return session;
    }
    public void sendSimpleEmail(String recipient, String subject, String body) {
        try{     
        	
            javax.mail.Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
            message.setText(body);
            message.setSubject(subject);
           
           
            Transport.send(message);
            System.out.println("Message sent successfully.");
        }
        catch(Exception mex){
                mex.printStackTrace();
        }
    }
    
    /**
     * Outlook Calendar appointment syncing.
     * @param student - student email.
     * @param advisor - advisor email.
     * @param subject - title of email.
     * @param body - description of email.
     * @param starttime - Start time of the appointment.
     * @param duration - Length of the appointment in minutes.
     * @param uid - unique identification of the appointment (stored in db).
     * @param requestType - 
     *          "REQUEST" - used for creating the meeting, maybe modifying (not tested).
     *          "CANCEL" - used for canceling the meeting.
     */
    public void sendOutlookEmail(String student, String advisor, String subject, String body, Date starttime, int duration, String requestType) {
        try{
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(from));
            
            long t = starttime.getTime();
            Date endtime = new Date(t + (duration * 60000));
            
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(student));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(advisor));
            
            message.addHeaderLine("method=REQUEST");  
            message.addHeaderLine("charset=UTF-8");   
            message.addHeaderLine("component=VEVENT");   
            message.setFrom(new InternetAddress(from));
            message.setSubject("Meeting Request from Appointment Schedular");   
            String details = 
                "BEGIN:VCALENDAR\r\n"+
                                "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\r\n"+  
                                "VERSION:2.0\r\n" +  
                                "METHOD:" + requestType + "\r\n" +
                                "BEGIN:VTIMEZONE\r\n"+
                                        "TZID:Central Standard Time\r\n"+
                                        "BEGIN:STANDARD\r\n"+
                                                "DTSTART:16011104T020000\r\n"+
                                                "RRULE:FREQ=YEARLY;BYDAY=1SU;BYMONTH=11\r\n"+
                                                "TZOFFSETFROM:-0500\r\n"+
                                                "TZOFFSETTO:-0600\r\n"+
                                        "END:STANDARD\r\n"+
                                        "BEGIN:DAYLIGHT\r\n"+
                                                "DTSTART:16010311T020000\r\n"+
                                                "RRULE:FREQ=YEARLY;BYDAY=2SU;BYMONTH=3\r\n"+
                                                "TZOFFSETFROM:-0600\r\n"+
                                                "TZOFFSETTO:-0500\r\n"+
                                        "END:DAYLIGHT\r\n"+
                                "END:VTIMEZONE\r\n"+
                                "BEGIN:VEVENT\r\n" +  
                                        "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:" + student + "; " + advisor + "\r\n" +   
                                        "ORGANIZER:MAILTO:" + from + "\r\n" +   
                                        "DTSTAMP:" + iCalendarDateFormat.format(starttime) + "\n" +
                                        "DTSTART:" + iCalendarDateFormat.format(starttime)+ "\n" +
                                        "DTEND:"  + iCalendarDateFormat.format(endtime)+ "\n" +
                                        "LOCATION:Advisor Office\r\n" +  
                                        "TRANSP:OPAQUE\r\n" +  
                                        "SEQUENCE:" + 0 + "\r\n" +  
                                        //"UID:"+uid+"\r\n"+ 
                                        "DTSTAMP:20141118T120102\r\n" +   
                                        "CATEGORIES:Meeting\r\n" +  
                                        "DESCRIPTION: " + body + "\r\n" +  
                                        "SUMMARY: " + subject + "\r\n" +   
                                        "PRIORITY:1\r\n" +  
                                        "CLASS:PUBLIC\r\n" +   
                                        "BEGIN:VALARM\r\n" +   
                                                "TRIGGER:PT1440M\r\n" +  
                                                "ACTION:DISPLAY\r\n" +   
                                                "DESCRIPTION:Reminder\r\n" +  
                                        "END:VALARM\r\n" +  
                                "END:VEVENT\r\n" +
                "END:VCALENDAR";
            // Create the message part  
            BodyPart messageBodyPart = new MimeBodyPart();   
            // Fill the message    
            messageBodyPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");  
            messageBodyPart.setHeader("Content-ID","calendar_message");   
            messageBodyPart.setDataHandler(new DataHandler( new ByteArrayDataSource(details, "text/calendar")));//very important  
            // Create a Multipart    
            Multipart multipart = new MimeMultipart("alternative");    
            // Add part one   
            multipart.addBodyPart(messageBodyPart);      
            // Put parts in message  
            message.setContent(multipart);    
            // send message   
            Transport.send(message);  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Email e = new Email();
        
        //Temp Password sample
        /*String username = "DivyaGore";
        String password = "tempPassword";
        e.sendSimpleEmail("divya.gore9@gmail.com", "UTA AdvsingSchedule Temporarry Password", "Divya, \n\tHere are your crdentials:\n\tUsername: " + username + "\n\tPassword: " + password + "\n\n\tYou have 24 hours before your password expires.\n\nThanks, the UTA Advising Scheduler Devs!");
        */
        
        //Create meeting example
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 2, 1, 19, 30);//The month is zero based for some off reason.
        Date starttime = cal.getTime();
        UID uid = new UID();//unique email id.  HAS to be saved for cancellations/modifications.
        System.out.println(uid.toString());
        String uidStr = uid.toString();
        e.sendOutlookEmail("ethan.bowen@mavs.uta.edu", "tracy.oguni@mavs.uta.edu", "Create Appointment!", "See the details in the outlook invitation.", starttime, 15, "CANCEL");
    }
}