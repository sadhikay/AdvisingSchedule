<%-- 
    Document   : index
    Created on : Sep 12, 2014, 2:12:40 PM
    Author     : Melissa
--%>

<%@page contentType='text/html' pageEncoding='UTF-8'%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>UTA Advising</title>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1'>
    </head>

    <body>

        <jsp:include page='navigationbar.jsp' />
        <div id='wrapper'>
            
<style type="text/css">
	#tfheader{
		background-color:#c3dfef;
	}
	#tfnewsearch{
		float:right;
		padding:20px;
	}
	.tftextinput{
		margin: 0;
		padding: 5px 15px;
		font-family: Arial, Helvetica, sans-serif;
		font-size:14px;
		border:1px solid #0076a3; border-right:0px;
		border-top-left-radius: 5px 5px;
		border-bottom-left-radius: 5px 5px;
	}
	.tfbutton {
		margin: 0;
		padding: 5px 15px;
		font-family: Arial, Helvetica, sans-serif;
		font-size:14px;
		outline: none;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
		color: #ffffff;
		border: solid 1px #0076a3; border-right:0px;
		background: #0095cd;
		background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));
		background: -moz-linear-gradient(top,  #00adee,  #0078a5);
		border-top-right-radius: 5px 5px;
		border-bottom-right-radius: 5px 5px;
	}
	.tfbutton:hover {
		text-decoration: none;
		background: #007ead;
		background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));
		background: -moz-linear-gradient(top,  #0095cc,  #00678e);
	}
	/* Fixes submit button height problem in Firefox */
	.tfbutton::-moz-focus-inner {
	  border: 0;
	}
	.tfclear{
		clear:both;
	}
</style>
<body>
	<!-- HTML for SEARCH BAR -->
	<div id="tfheader">
		<form id="tfnewsearch" method="get" action="http://cse.uta.edu">
		        <input type="text" class="tftextinput" name="q" size="21" maxlength="120"><input type="submit" value="search" class="tfbutton">
		</form>
	<div class="tfclear"></div>
	</div>
</body>


            <jsp:include page='header.jsp' />
             <% 
                            int rank = -1;
                            int sessionid = -1;
                            if(!(session.getAttribute("id") == null)){
                            sessionid = Integer.parseInt((String)session.getAttribute("id"));
                        }
                            if(!(session.getAttribute("rank") == null)){
                            rank = Integer.parseInt((String)session.getAttribute("rank"));
                        }
             %>
            <table class='centerthis' style='margin: 0 auto;'>
                <tr>
                    <%
                            if(rank == -1){
                                out.print("<td style='width: 640px'>");
                            }
                            %>
                    <div id='leftAccordion'>
                       
                        
                        <div>
                            <%
                            if (rank == -1){
                                out.print("Would you like to check on your current schedule?<br><br>"
                                        + " <input type='submit' value='Login to your account' id='loginBtn' class='btn btn-default'>"
                                        );
                            }
                            if (rank == 1){
                                out.print("Welcome administrator.");
                            }
                            if (rank == 0){
                                out.print("Welcome faculty member.");
                            }
                            if (rank == 2){
                                out.print("Welcome student.");
                                    }
                            
                            %>
                  
                        </div>

                    </div>
                            <%
                            if(rank == -1){
                                out.print("</td>"
                                        + "<td style='width: 640px'>"
                                        + "<div id='rightAccordion'>"
                                        + "<h3>Student</h3>"
                                        + "<div>"
                                        + "Would you like to schedule an appointment with an advisor?<br><br>"
                                        + "<form action='schedule.jsp'>"
                                        + "<input type='submit' value='Make an appointment' id='scheduleBtn' class='btn btn-default'>"
                                        + "</form>"
                                        + "</div>"
                                        + "</div>"
                                        + "</td>");
                            }
                            %>
                </tr>
            </table>


        </div>
              
                
    </body>
    <jsp:include page='footer.jsp' />
    
    

    <script type='text/javascript' src='js/index.js'></script>
</html>
