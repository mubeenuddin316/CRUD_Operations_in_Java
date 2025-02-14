<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Data</title>
    <link rel="stylesheet" type="text/css" href="css/update-style.css">
</head>
<body bgcolor="#aed6f1">
    <%
    String id = request.getParameter("id");
    %>
    <h2>Update data of id :   <%= id %></h2>
    <form action="updateServlet1" method="post">
        <input type="hidden" name="id" value="<%= id %>">
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" placeholder="Enter new name"><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Enter new email"><br><br>
        
        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" placeholder="Enter new phone"><br><br>
        
        <input type="submit" value="Update">
    </form>
</body>
</html>