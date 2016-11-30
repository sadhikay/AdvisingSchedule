<%-- 
    Document   : secQuestion
    Created on : 21 Nov, 2016, 7:56:21 PM
    Author     : Sadhika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="newadvisor" class="uta.cse4361.beans.CreateStudentAccountBean"/> 
        <jsp:setProperty name="newadvisor" property="question" value='<%=request.getParameter("email")%>' />
        <jsp:setProperty name="newadvisor" property="answer" value='<%=request.getParameter("dept")%>' />
        <script type="text/javascript">
            function validate() {
                //                var username = document.forms["create"]["username"].value;
                var email = document.forms["create"]["email"].value;
                var atpos = email.indexOf("@");
                var dotpos = email.lastIndexOf(".");
                var mavs = email.indexOf("mavs.uta.edu");


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
                <%

                    String email = request.getParameter("email");
                    newadvisor.setEmail(email);
                    String question = newadvisor.getUserQuestion();
                    if (question.equals("none")) {
                        out.println("<h3 style='color:red'>EmailId doesnot Exist.</h3>");
                    }

                %>
                <h3>Change Password</h3>
                <form action="#" method="post">
                    <!-- Default panel contents -->


                    <div class="panel-body resize-body center-block">

                        <div class="form-group"> 

                            <label for="currentpassword">Email Id</label> 
                            <br>
                            <input type="text" class="form-control" name=email value="<%=email%>">
                            <div class="form-group">
                                <label for="quesstion"> Security Question</label>
                                <input class="form-control" type="text" id="question" name="question" value="<%=question%>" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="answer">Answer</label>
                                <input class="form-control" type="text" name="answer" id="answer" value="">
                            </div>
                        </div>

                    </div>
                    <div class="centerthis">
                        <input type="submit" value="Submit" id="submitBtn" class="btn btn-default" name="submit">
                    </div>
                </form>

                <%
                    String answer = request.getParameter("answer");
                    if (answer != null) {
                        if (answer.equals(newadvisor.getUserAnswer())) {
                            response.sendRedirect("http://localhost:8084/AdvisingScheduler/changePassword.jsp?name=" + email);
                        } else {
                            out.println("Incorrect Answer");
                        }
                    }


                %>
            </div>
        </div>

    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/CreateAccount.js"></script>
</html>