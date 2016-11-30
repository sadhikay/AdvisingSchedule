<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*"   %>
<%@ page import="com.google.gson.*" %>
<%@page import="uta.cse4361.interfaces.FusionCharts" %>

<%
    
System.out.println("inside jsp");
String hostdb = "localhost:3306";  // MySQl host
String userdb = "root";  // MySQL username
String passdb = "sadhika";  // MySQL password
String namedb = "advising";  // MySQL database name

    // Establish a connection to the database
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Connection con = DriverManager.getConnection("jdbc:mysql://" + hostdb + "/" + namedb , userdb , passdb);
   System.out.println("Database is connected");
    %>
 

   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Statistics Page</title>
<script type="text/javascript" src="http://static.fusioncharts.com/code/latest/fusioncharts.js"></script>
<script type="text/javascript" src="fusioncharts/fusioncharts.js"></script>


<body>
    <jsp:include page="navigationbar.jsp" />
    <div id="wrapper">
		<jsp:include page="header.jsp" />
   <div id="chart"></div>

    
     
    <%
     /*
        google-gson

        Gson is a Java library that can be used to convert Java Objects into 
        their JSON representation. It can also be used to convert a JSON string to 
        an equivalent Java object. Gson can work with arbitrary Java objects including
        pre-existing objects that you do not have source-code of.
        link : https://github.com/google/gson    
     */

        Gson gson = new Gson();
        
        
        
        String sql="SELECT UserName, COUNT(UserDepartment) as Total  FROM user GROUP BY UserRank";
        System.out.println(sql);

        // Execute the query.
        PreparedStatement pt=con.prepareStatement(sql);    
        ResultSet rs=pt.executeQuery();
        System.out.println(rs);
        
        // The 'chartobj' map object holds the chart attributes and data.
        Map<String, String> chartobj = new HashMap<String, String>();
        
        chartobj.put("caption" , "Student - Advisor");
        chartobj.put("paletteColors" , "#0075c2");
        chartobj.put("bgColor" , "#ffffff");
        chartobj.put("borderAlpha", "20");
        chartobj.put("canvasBorderAlpha", "0");
        chartobj.put("usePlotGradientColor", "0");
        chartobj.put("plotBorderAlpha", "10");
        chartobj.put("showXAxisLine", "1");
        chartobj.put("xAxisLineColor" , "#999999");
        chartobj.put("showValues" , "0");
        chartobj.put("divlineColor" , "#999999");
        chartobj.put("divLineIsDashed" , "1");
        chartobj.put("showAlternateHGridColor" , "0");

        // Push the data into the array using map object.
        ArrayList arrData = new ArrayList();
        while(rs.next())
        {
            Map<String, String> lv = new HashMap<String, String>();
            lv.put("label", rs.getString("UserName"));
            lv.put("value", rs.getString("Total"));
            arrData.add(lv);             
        }
        System.out.println(arrData);
        //close the connection.
        rs.close();

        //create 'dataMap' map object to make a complete FC datasource.
        
         Map<String, String> dataMap = new LinkedHashMap<String, String>();  
    /*
        gson.toJson() the data to retrieve the string containing the
        JSON representation of the data in the array.
    */
         dataMap.put("chart", gson.toJson(chartobj));
         dataMap.put("data", gson.toJson(arrData));
        FusionCharts column2DChart= new FusionCharts(
                    "column2d",// chartType
                    "chart1",// chartId
                    "600","400",// chartWidth, chartHeight
                    "chart",// chartContainer
                    "json",// dataFormat
                    gson.toJson(dataMap) //dataSource
                );
       
    
        System.out.println(dataMap);
    
        %>
        
<!--    Step 5: Render the chart    -->                
        <%=column2DChart.render()%>
        
</body>
<jsp:include page="footer.jsp" />
</html>