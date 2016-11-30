<%-- 
    Document   : CreateAccount
    Created on : Nov 22, 2014, 5:50:01 PM
    Author     : Melissa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            //Captha start
            function Captcha() {
                var alpha = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
                var i;
                for (i = 0; i < 6; i++) {
                    var a = alpha[Math.floor(Math.random() * alpha.length)];
                    var b = alpha[Math.floor(Math.random() * alpha.length)];
                    var c = alpha[Math.floor(Math.random() * alpha.length)];
                    var d = alpha[Math.floor(Math.random() * alpha.length)];
                    var e = alpha[Math.floor(Math.random() * alpha.length)];
                    var f = alpha[Math.floor(Math.random() * alpha.length)];
                    var g = alpha[Math.floor(Math.random() * alpha.length)];
                }
                var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' ' + f + ' ' + g;
                document.getElementById("mainCaptcha").value = code
            }
            
            function removeSpaces(string) {
                return string.split(' ').join('');
            }
            //captha end
            function validate() {
//                var username = document.forms["create"]["username"].value;
                var email = document.forms["create"]["email"].value;
                var atpos = email.indexOf("@");
                var dotpos = email.lastIndexOf(".");
                var mavs = email.indexOf("mavs.uta.edu");
                var name = document.forms["create"]["name"].value;
                var question = document.forms["create"]["question"].value;
                var answer = document.forms["create"]["answer"].value;
                
        //captcha        
        var string1 = removeSpaces(document.getElementById('mainCaptcha').value);
                var string2 = removeSpaces(document.getElementById('txtInput').value);
         //captcha

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

//captcha
                if (string1 != string2) {
                     $("#txtInput").notify("Captha mismatch", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }
//captha
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body onload="Captcha()">
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Create Student Account</h3>
                <form role="form" id="create"  onSubmit="return validate();" action="StudentAccountEdit.jsp" method="POST">
                    <!--                    <div class="form-group">
                                            <label for="username">Username</label>
                                            <input class="form-control" type="text" name="username" id="username" value="">
                                        </div>-->
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input class="form-control" type="text" name="email" id="email" value="">
                    </div>

                    <div class="form-group">
                        <label for="name">Name</label>
                        <input class="form-control" type="text" name="name" id="name" value="">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input class="form-control" type="password" name="password" id="password" value="">
                    </div>
                    <div class="form-group">
                        <label for="confirmpass">Confirm Password </label>
                        <input class="form-control" type="password" name="password" id="password" value="">
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input class="form-control" type="text" name="phone" id="phone" value="">
                    </div>
                    <div class="form-group">
                        <label for="dept">Department</label>
                        <select name="dept" id="dept" class="form-control" >
                            <option value="Computer Science">Computer Science</option>
                            <option value="Civil Engineering">Civil Engineering</option>
                            <option value="Electrical Engineering">Electrical Engineering</option>
                            <option value="Bioengineering">Bioengineering</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="contact">Mode Of Contact</label>
                        <select name="contact" id="dept" class="form-control" >
                            <option value="phcall">Phone Call</option>
                            <option value="text">Message</option>
                            <option value="mail">Email</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="quesstion">Question</label>
                        <select name="question" id="question" class="form-control" >
                            <option value="What is your favorite color">What is your favorite color</option>
                            <option value="Which city were you born in">Which city were you born in</option>
                         </select>
                    <div class="form-group">
                        <label for="answer">Answer</label>
                        <input class="form-control" type="text" name="answer" id="answer" value="">
                    </div>
                    </div>
                    
                    
                    <div >
                        <table>
                            <tr>
                                <td>
                                    Text Captcha<br />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="text" id="mainCaptcha"/>
                                    <input type="button" id="refresh" value="Refresh" onclick="Captcha();" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="text" id="txtInput"/>    
                                </td>
                            </tr>

                        </table>
                    </div>
                    <div class="centerthis">
                        <input type="submit" value="Create Account" id="submitBtn" class="btn btn-default">
                        <input type="reset" value="Reset" id="resetBtn" class="btn btn-default">
                    </div>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/CreateAccount.js"></script>
</html>
