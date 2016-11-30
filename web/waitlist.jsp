<%-- 
    Document   : CheckAppointments
    Created on : Mar 5, 2016, 8:10:32 PM
    Author     : sadhika
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="uta.cse4361.businessobjects.Appointment"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Waitlist</title>
</head>
<body>
	<jsp:include page="navigationbar.jsp" />
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<div id="accordion" class="centerthis">
			<h3>Waitlist</h3>
			<div>				
					<table class="display" id="allWaitlists"
						cellpadding="3" cellspacing="0">
						<col width="80">
						<col width="180">
						<col width="150">
						<col width="100">
						<col width="200">
						<col width="80">
						<thead>
							<th>Date</th>							
							<th>Student Name</th>														
						</thead>
						<tbody>
							<%
								DatabaseManager dm = new DatabaseManager();
								String email1 = (String) session.getAttribute("email");
								ArrayList<Appointment> waitlists = dm.getAllWaitlist();
								Calendar now = Calendar.getInstance();
								for (Appointment a : waitlists) {									
									if (a.getDate().after(new SimpleDateFormat("dd/MM/yyyy")
											.parse("01/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR)))) {
										out.print("<tr>");
										out.print("<td>");
										out.print(a.getDate().getMonth()+1+"/"+a.getDate().getDate()+"/"+(a.getDate().getYear()+1900));										
										out.print("</td>");
										
										out.print("<td>");
										out.print(a.getStudentName());
										out.print("</td>");										
									}
								}
							%>
						</tbody>
					</table>
			</div>

		</div>
	</div>
</body>
<jsp:include page="footer.jsp" />
<script>
$( "#accordion" ).accordion({ heightStyle: "content" });
$(document).ready( function () {
    $('#allWaitlists').DataTable({
        "paging":   true,
        "ordering": true,
        "info":     true
    });
} );
</script>
</html>
