<div id="navbar">
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
            <ul class="navigation">
                <li>
                <h4><a href="http://outlook.com/mavs.uta.edu">Student Email</a></h4>
                </li>
                <li>
                <h4><a href="https://sis-portal-prod.uta.edu/psp/AEPPRD/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST">MYMAV</a></h4>
                </li><li>
                <h4><a href="AccountEdit.jsp">Create Account</a></h4>
                </li>
                </li><li>
                <h4><a href="Instruction.jsp">Instruction Page</a></h4>
                </li>
                </li><li>
                <h4><a href="Feedback.jsp">Feedback Page</a></h4>
                </li>
                
                <li class="home">
                    <a href="index.jsp" >Home</a>
                </li>
    <%
        if(session.getAttribute("rank") == null)
        {
            
        }
        else{
            if (rank == 1){
                out.print("<li class='account'>"
                        + "<a href='CreateAccount.jsp' >Create Account</a>"
                        + "</li>");
                out.print("<li class='statistics'>"
                        + "<a href='statistics.jsp' >Statistics</a>"
                        + "</li>");
            }
             if (rank == 2){
                out.print("<li class='account'>"
                        + "<a href='EditAccount.jsp' >Edit Account</a>"
                        + "</li>");
                out.print("<li class='Schedule'>"
                        + "<a href='schedule.jsp' >Schedule Appointment</a>"
                        + "</li>");
            }
            if (rank == 0){
                out.print("<li class='calendar'>"
                        + "<a href='AdvisorCalendar.jsp' >Calendar</a>"
                        + "</li>");
                out.print("<li class='timeslot'>"
                        + "<a href='modifyTimeslot.jsp' >Time slot</a>"
                        + "</li>");
                out.print("<li class='appointment'>"
                        + "<a href='modifyAppointment.jsp' >Appointment</a>"
                        + "</li>");
                out.print("<li class='account'>"
                        + "<a href='EditAccount.jsp' >Edit Account</a>"
                        + "</li>");
            }
            if (rank == 1 || rank == 0 || rank ==2){
                out.print("<li class='logout'>"
                        + "<a href='logout.jsp' >Log Out</a>"
                        + "</li>");
                
            }
        }
        if ((session.getAttribute("id") == null) || (session.getAttribute("rank") == null)){
            out.print("<li class='schedule'>"
                    + "<a href='schedule.jsp' >Schedule Appointment</a>"
                    + "</li>");
        }
    %>                
            </ul>
        </div>
