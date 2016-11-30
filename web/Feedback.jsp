<%-- 
    Document   : Feedback
    Created on : Mar 10, 2016, 1:52:20 AM
    Author     : Deepak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Create Advisor Account</h3>
                <form role="form" id="create" action="AccountConfirmation.jsp" method="POST">
                    <!--                    <div class="form-group">
                                            <label for="username">Username</label>
                                            <input class="form-control" type="text" name="username" id="username" value="">
                                        </div>-->
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input class="form-control" type="text" name="email" id="email" value="">
                    </div>
                    <div class="form-group">
                        <label for="password">Name</label>
                        <input class="form-control" type="name" name="name" id="password" value="">
                    </div>
                    <div class="form-group">
                        <label for="feedback">Feedback</label>
                        <input class="form-control" type="password" name="passwordConfirm" id="passwordConfirm" value="">
                    </div>
                    
                    <div class="centerthis">
                        <input type="submit" value="Create Account" id="submitBtn" class="btn btn-default">
                        <input type="reset" value="Reset" id="resetBtn" class="btn btn-default">
                    </div>
                </form>
            </div>
        </div
</html>
