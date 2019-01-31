<%--
  Created by IntelliJ IDEA.
  User: ijaaztello
  Date: 2019-01-30
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body class="container">
<form action="personCollection" method="post">
    <label for="firstNameAdd">First name</label>
    <input type="text" name="firstName" id="firstNameAdd">
    <label for="lastNameAdd">Last name</label>
    <input type="text" name="lastName" id="lastNameAdd">
    <label for="eyeColorAdd">Eye color</label>
    <input type="text" name="eyeColor" id="eyeColorAdd">
    <label for="hairColorAdd">Hair color</label>
    <input type="text" name="hairColor" id="hairColorAdd">
    <label for="heightAdd">Height</label>
    <input type="text" name="height" id="heightAdd">
    <label for="weightAdd">Weight</label>
    <input type="text" name="weight" id="weightAdd">
    <input type="submit" value="Add">
</form>
<table class="table">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Eye Color</th>
        <th>Hair Color</th>
        <th>Height</th>
        <th>Weight</th>
    </tr>

    <form action="personCollection" method="post">
    <c:forEach items="${people}" var="person">
    <tr>
        <td><input type="text" name="firstName" value="${person.firstName}"></td>
        <td><input type="text" name="lastName" value="${person.lastName}"></td>
        <td><input type="text" name="eyeColor" value="${person.eyeColor}"></td>
        <td><input type="text" name="hairColor" value="${person.hairColor}"></td>
        <td><input type="text" name="height" value="${person.height}"></td>
        <td><input type="text" name="weight" value="${person.weight}"></td>
        <td><button type="submit" name="_method" value="PUT">Edit</button></td>
        <td><button type="submit" name="_method" value="DELETE">Delete</button></td>
        <input type="hidden" name="id" value="${person.id}">
    </tr>
    </form>
</c:forEach>
</table>
</body>
</html>
