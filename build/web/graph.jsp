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
<html>  
<head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = google.visualization.arrayToDataTable([
        ['City', '2010 Population',],
        ['New York City, NY', 8175000],
        ['Los Angeles, CA', 3792000],
        ['Chicago, IL', 2695000],
        ['Houston, TX', 2099000],
        ['Philadelphia, PA', 1526000]
      ]);

      var options = {
        title: 'Population of Largest U.S. Cities',
        chartArea: {width: '50%'},
        hAxis: {
          title: 'Total Population',
          minValue: 0
        },
        vAxis: {
          title: 'City'
        }
      };

      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

      chart.draw(data, options);
    }
</script>
</head>
<body>  
    <jsp:include page="navigationbar.jsp" />
    <div id="wrapper">
		<jsp:include page="header.jsp" />
<div id="chart_div"></div>

    </div>
</body> 
<jsp:include page="footer.jsp" />
</html>     