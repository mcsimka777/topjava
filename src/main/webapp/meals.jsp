<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1" cellpadding="5" cellspacing="1">
    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>
    <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
    <c:forEach items="${mealsTo}" var="meal">
        <c:if test="${meal.excess}">
            <tr style="color: red; ">
        </c:if>
        <c:if test="${not meal.excess}">
            <tr style="color: green; ">
        </c:if>
        <td>${meal.date} ${meal.time}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>