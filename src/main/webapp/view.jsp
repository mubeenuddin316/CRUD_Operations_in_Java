<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="pack1.servDTO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View</title>
    <link rel="stylesheet" type="text/css" href="css/view-style.css">
</head>
<body bgcolor="lightgreen">
    <h2>All Students Data</h2>
    <%= "Students details of srec" %><br>
	<%
    	String errorMsg = (String) request.getAttribute("error"); 
    	if (errorMsg != null) { 
	%>
        <p style="color: red;"><%= errorMsg %></p> <!-- if errorMsg not null print in location -->
        <button onclick="location.href='index.jsp'">Try Again</button>
	<%
    	}
	%>
	
    <%  
    	@SuppressWarnings("unchecked")
        ArrayList<servDTO> data = (ArrayList<servDTO>) request.getAttribute("dataList");

        if (data != null && !data.isEmpty()) {
    %>
        <table border="1">  <!--  printing table data if above data array list is not empty -->
            <tr>
                <th>UserId</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>

            <% for(servDTO d: data) { %>
                <tr>
                    <td><%= d.getId() %></td>
                    <td><%= d.getName() %></td>
                    <td><%= d.getEmail() %></td>
                    <td><%= d.getPhone() %></td>
                    <td>
                        <form action="update.jsp" method="get">
                            <input type="hidden" name="id" value="<%= d.getId() %>">
                            <input type="submit" value="Update">
                        </form>
                    </td>
                </tr>
            <% } %>
            <!-- New row for inserting data (has warnings)
            	<form action="InsertServlet51" method="post">
   			 	<tr>
        			<td><input type="number" name="id" placeholder="Enter ID" required></td>
        			<td><input type="text" name="name" placeholder="Enter Name" required></td>
        			<td><input type="email" name="email" placeholder="Enter Email" required></td>
        			<td><input type="number" name="phone" placeholder="Enter Phone" required></td>
        			<td><input type="submit" value="Insert"></td>
    			</tr>
				</form>  -->
			
			<tr>
    			<td colspan="5">  <!-- Nested Table Approach --> 
        			<form action="InsertServlet51" method="post">
            		<table>
            			<tr>
                    		<td><input type="number" name="id" placeholder="Enter ID" required></td>
                    		<td><input type="text" name="name" placeholder="Enter Name" required></td>
                    		<td><input type="email" name="email" placeholder="Enter Email" required></td>
                    		<td><input type="number" name="phone" placeholder="Enter Phone" required></td>
                    		<td><input type="submit" value="Insert"></td>
                		</tr>
            		</table>
        			</form>
    			</td>
			</tr>
			
			<!--
			<tr>
    			<td colspan="5"> (alternate way)
        			<form action="InsertServlet51" method="post">
            			<input type="number" name="id" placeholder="Enter ID" required>
            			<input type="text" name="name" placeholder="Enter Name" required>
            			<input type="email" name="email" placeholder="Enter Email" required>
            			<input type="number" name="phone" placeholder="Enter Phone" required>
            			<input type="submit" value="Insert">
        			</form>
    			</td>
			</tr>
            -->
        </table> 
    <%
        } 
    %>
</body>
</html>
