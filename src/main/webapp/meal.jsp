<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>${meal.id != null ? 'Edit Meal' : 'Add Meal'}</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${meal.id != null ? 'Edit Meal' : 'Add Meal'}</h2>
<form method="POST" action='meals' name="frmAddUser">
    <table>
        <tr>
            <td>Date</td>
            <td>
                <input type="hidden" name="id" value="${meal.id}"/>

                <input
                        type="datetime-local" name="date"
                        value="${meal.dateTime}"/> <br/>
            </td>
        </tr>
        <tr>
            <td>Description</td>
            <td>
                <input
                        type="text" name="description"
                        value="${meal.description}"/> <br/>
            </td>
        </tr>
        <tr>
            <td>Calories</td>
            <td>
                <input
                        type="number" name="calories"
                        value="${meal.calories}"/> <br/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Submit"/>
                <input type="button" onclick="window.location='meals'" value="Cancel"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>