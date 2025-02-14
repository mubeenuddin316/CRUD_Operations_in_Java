<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome SREC</title>
<link rel="stylesheet" type="text/css" href="css/index-style.css"> <!-- linking CSS style.css in css folder -->
</head>
<body>
<h2>SR Student Details (CRUD Operations)</h2>

<table style="border: 1px; border-collapse: collapse;">	<!-- Table Starts Here -->
    <tr>
    	<td>
    		<label>Show All student Details:</label>
    		<br><br>
    		<label>Register New Student:</label></td>
        <td>
        	<form action="FullDServlet51" method="post">
                <input type="submit" value="Get Full Data"> <!-- Form Action for Getting Full Data (FullDServlet.java -servlet)-->
            </form>
            <form action="insert.jsp">
            	<input type="submit" value="Register New">  <!-- Form Action to go to insert Page (insert.jsp)-->
            </form>
        </td>
    </tr>
    <tr>
    <td><label>Search By ID:</label></td>
    	<td>
        	<form action="searchServlet21" method="post">
            	<input type="number" name="id" placeholder="Enter the id" required>
            	<input type="submit" value="Search">     <!-- Form action for search servlet (searchServlet.java) -->
        	</form>
    	</td>
	</tr>  
    <tr>
    <td><label>Enter ID You Want to Delete:</label></td>
    <td>
        <form action="DelRecServlet21" method="post">
            <input type="number" name="id" placeholder="Enter the id" required>
            <input type="submit" value="Delete">     <!-- Form Action to delete servlet (DelRecServlet.java) -->
        </form>
    </td>
</tr>

</table>

</body>
</html>