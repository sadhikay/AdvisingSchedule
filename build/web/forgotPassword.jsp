<%-- 
    Document   : forgotPassword
    Created on : 17 Nov, 2016, 9:45:16 PM
    Author     : Sadhika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uta.cse4361.businessobjects.CustomEmail" %>
<!DOCTYPE html>
<html>
    <head>
        <%
            //Uncomment when there's a proper way to create an admin account
                //this code redirects anyone who is not an admin back to the index page
            int rank = 2;
            int sessionid = 2;
//            if ((session.getAttribute("id") == null) || (session.getAttribute("rank") == null)) {
//               response.sendRedirect("index.jsp");
//            }
//            if (!(session.getAttribute("id") == null)) {
//                    sessionid = Integer.parseInt((String) session.getAttribute("id"));
//                }
//                if (!(session.getAttribute("rank") == null)) {
//                    rank = Integer.parseInt((String) session.getAttribute("rank"));
//            }
//            if(rank != 1)
//                {
//                    response.sendRedirect("index.jsp");
//                }
        %>
        <script type="text/javascript">
            function validate() {
//                var username = document.forms["create"]["username"].value;
                var email = document.forms["create"]["email"].value;
                var atpos = email.indexOf("@");
                var dotpos = email.lastIndexOf(".");
                var mavs = email.indexOf("mavs.uta.edu");
                var name = document.forms["create"]["name"].value;

//                if (username === null || username === "") {
//                    $("#username").notify("Please enter your username", "error",
//                            {elementPosition: 'bottom center',
//                                globalPosition: 'bottom center'})
//                    return false;
//                }
                if (email === null || email === "") {
                    $("#email").notify("Please enter your email", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }
                if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length || mavs < 0) {
                    $("#email").notify("Please enter a valid mavs email", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }
               
                if (name === null || name === "") {
                    $("#name").notify("Please enter your name", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }
                
               

            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Forgot Password</h3>
                <form>
                    <div>
                        <input class="form-control" type="text" placeholder="Mail Id" name="email"><br>
                        <input type="radio" value="email" name="forgot">Send Email <br><br>
                        <input type="radio" value="secQuestion" name="forgot">Security Question<br><br> 
                        <input type="submit" value="Select" id="submitBtn" class="btn btn-default" class="btn btn-default">
                       				
                    </div>
                </form>
                 <%
                     String email = request.getParameter("email");
                 if(request.getParameter("forgot")!=null){
                     String radioValue= request.getParameter("forgot");
                     if(radioValue.equals("email")){
                         CustomEmail Email = new CustomEmail("cse6329.project@gmail.com","advancese",email,"Change Password Link","http://localhost:8084/AdvisingScheduler/changePassword.jsp?name="+email);
			Email.createEmailMessage();
			Email.sendEmail();
                        response.sendRedirect("http://localhost:8084/AdvisingScheduler/index.jsp");
                         
                     }else if(radioValue.equals("secQuestion")){
                        response.sendRedirect("http://localhost:8084/AdvisingScheduler/secQuestion.jsp?email="+email);  
                        
                     }else{
                         out.println("Please Select One Option");
                     }
                 }
                 %>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/CreateAccount.js"></script>
</html>