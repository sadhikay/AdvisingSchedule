<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <jsp:include page="navigationbar.jsp" />
    <div id="wrapper">
		<jsp:include page="header.jsp" />
 <form for ="formSubmit" class="form-inline" id="search" action="Statistics1.jsp" method ="post">
<div  class="form-group">
        <fieldset id="inputs">
          <legend font -family:"verdana"align="center"><strong>Statistical Information</strong></legend>
           <label for="formScenario ">Scenario</label>
        <select form="search" name='scenario' size=0 >
              <option value="1" selected="selected">Students</option>
              <option value="2">Advisor</option>
              <option value="3"> Appointments</option>
            </select>
          </p>
         

<input class="btn btn-info btn-primary btn-md" id ="lbsumbit" type="submit" value="Submit" name="submit" />
 </div>

</body>
<jsp:include page="footer.jsp" />
</html>