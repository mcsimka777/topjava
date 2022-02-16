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
<h2>Meals</h2>
<table>
    <th>От даты (включая)</th>
    <th>До даты (включая)</th>
    <th>От времени (включая)</th>
    <th>До времени (исключая)</th>
    <form method="post" action="meals">
        <input type="hidden" name="action" value="filter">
        <tr>
            <td>
                <input type="date" value="${startDate}" size=15 name="startDate">
            </td>
            <td>
                <input type="date" value="${endDate}" size=15 name="endDate">
            </td>
            <td>
                <input type="time" value="${startTime}" size=15 name="startTime">
            </td>
            <td>
                <input type="time" value="${endTime}" size=15 name="endTime">
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