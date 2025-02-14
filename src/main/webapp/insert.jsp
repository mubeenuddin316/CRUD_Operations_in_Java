<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert page</title>
<link rel="stylesheet" type="text/css" href="css/insert-style.css">
</head>
<body>
<h2>Fill The Details:</h2>
<form action="InsertServlet51" method="post">   <!-- Form Action to insert servlet (insertServlet.java)-->
    <label>Enter ID:</label>
    <input type="number" name="id" placeholder="Enter the id" required>

    <label>Enter Name:</label>
    <input type="text" name="name" placeholder="Enter the name" required>

    <label>Enter Email:</label>
    <input type="email" name="email" placeholder="Enter the email" required>

    <label>Enter Phone:</label>
    <input type="number" name="phone" placeholder="Enter the phone" required>

    <input type="submit" value="Submit"> <!-- id, name, email and phone inputs -->
</form>
</body>
</html>