<%-- 
    Document   : CreateAccount
    Created on : Nov 22, 2014, 5:50:01 PM
    Author     : Melissa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
       <jsp:useBean id="newadvisor" class="uta.cse4361.beans.CreateStudentAccountBean"/> 
        <jsp:setProperty name="newadvisor" property="email" value='<%=request.getParameter("email")%>' />
        <jsp:setProperty name="newadvisor" property="department" value='<%=request.getParameter("dept")%>' />
        <jsp:setProperty name="newadvisor" property="name" value='<%=request.getParameter("name")%>' />
        <jsp:setProperty name="newadvisor" property="tempPassword" value='<%=request.getParameter("password")%>'/>
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
                <h3>Change Password</h3>
                
<form action="#" method="post">
  <!-- Default panel contents -->
  
 
  <div class="panel-body resize-body center-block">
  		
				<div class="form-group"> 
				
					<label for="currentpassword">Email Id</label> 
					<br>
                                        <input type="text" class="form-control" name=email value="<%=request.getParameter("name")%>">
					
					<label for="password">New Password (Length 8+ Required)</label> 
					<br>
					<input type="password" class="form-control" name=password>
					
					<label for="repeatPassword">Repeat Password</label> 
					<br>
					<input type="password" class="form-control" name=repeatpassword>
				</div>
			
		</div>
		<div class="centerthis">
                        <input type="submit" value="Change Password" id="submitBtn" class="btn btn-default" name="submit">
                </div>
      </form>
                                        <%
                                            String pswd = request.getParameter("password");
                                           String repeatPswd = request.getParameter("repeatpassword");
                                           if(request.getParameter("submit")!=null){
                                                 if(pswd.equals(repeatPswd)){
                                                   String email= request.getParameter("email");
                                                    newadvisor.setEmail(email);
                                                    newadvisor.setTempPassword(pswd);
                                                    if(newadvisor.changePassword()){
                                                        out.println("Changed");
                                                        response.sendRedirect("http://localhost:8084/AdvisingScheduler/index.jsp");
                                                    }
                                               }
                                               else{
                                                   out.println("Pasword's does not match, Please Enter again.");
                                               }
                                               
                                           }
                                        
                                        
                                        %>
            </div>
        </div>
  
   </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/CreateAccount.js"></script>
</html>