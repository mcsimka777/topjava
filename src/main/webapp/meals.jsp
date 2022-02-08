<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p><a href="meals?action=insert">Add Meal</a></p>
<table border="1" cellpadding="5" cellspacing="1">
    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>
    <th></th>
    <th></th>
    <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
    <c:forEach items="${mealsTo}" var="meal">
        <tr style="color:${meal.excess ? 'red' : 'green'}">
            <td>${meal.date} ${meal.time}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>