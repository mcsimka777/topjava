<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr/>
<h2>Meals for ${userId}</h2>
<table>
    <thead>От даты (включая)</thead>
    <thead>До даты (включая)</thead>
    <thead>От времени (включая)</thead>
    <thead>До времени (исключая)</thead>
    <form method="post" action="meals">
        <tr>
            <td>
                <input type="date" value="${meal.description}" size=15 name="description1">
            </td>
            <td>
                <input type="date" value="${meal.description}" size=15 name="description2">
            </td>
            <td>
                <input type="time" value="${meal.description}" size=15 name="description3">
            </td>
            <td>
                <input type="time" value="${meal.description}" size=15 name="description4">
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Filter</button>
            </td>
        </tr>
    </form>
</table>
<br>
<a href="meals?action=create">Add Meal</a>
<br><br>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>
                    <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                    <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                    <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                    ${fn:formatDateTime(meal.dateTime)}
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</section>
</body>
</html>