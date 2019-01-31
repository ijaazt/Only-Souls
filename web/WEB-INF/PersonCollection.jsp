<%--
  Created by IntelliJ IDEA.
  User: ijaaztello
  Date: 2019-01-30
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Person Collection</title>
    <style>
        @media (min-width: 576px) {
            .card-columns {
                column-count: 2;
            }
        }

        @media (min-width: 768px) {
            .card-columns {
                column-count: 2;
            }
        }

        @media (min-width: 992px) {
            .card-columns {
                column-count: 4;
            }
        }

        @media (min-width: 1200px) {
            .card-columns {
                column-count: 4;
            }
        }
    </style>
</head>
<body class="container">
<div class="row">
    <div class=col align="center">
        <h1 class="display-2">Person Collection</h1>
    </div>
</div>
<form action="personCollection" class="mx-5 my-1" method="post">
    <div class="form-row">
        <div class="col">
            <label for="firstNameAdd">First name</label>
            <input type="text" class=form-control name="firstName" id="firstNameAdd">
        </div>
        <div class="col">
            <label for="lastNameAdd">Last name</label>
            <input type="text" name="lastName" class=form-control id="lastNameAdd">
        </div>
    </div>

    <div class="form-row">
        <div class="col">
            <label for="eyeColorAdd">Eye color</label>
            <input type="text" name="eyeColor" class=form-control id="eyeColorAdd">
        </div>
        <div class="col">
            <label for="hairColorAdd">Hair color</label>
            <input type="text" name="hairColor" class=form-control id="hairColorAdd">
        </div>
    </div>
    <div class="form-row">
        <div class="col">
            <label for="heightAdd">Height</label>
            <input type="text" name="height" class="form-control" id="heightAdd">
        </div>
        <div class="col">
            <label for="weightAdd">Weight</label>
            <input type="text" name="weight" class=form-control id="weightAdd">
        </div>
    </div>
    <div class="form-row mt-1">
        <div class="col">
            <button type="submit" class="btn btn-primary" name="_method" value="POST">Add</button>
        </div>
    </div>
</form>
<c:forEach items="${people}" varStatus="loop" step="4">
    <div class="row">
        <d:forEach items="${people}" begin="${loop.index}" var="person" end="${loop.index + 3}">
            <div class="card m-2  bg-light">
                <div class="card-header">Person</div>
                <form action="personCollection" method="post" class="card-body">
                    <div class="form-group">
                        <label for="firstName${person.id}">First name</label>
                        <input type="text" name="firstName" class="form-control" id="firstName${person.id}"
                               value="${person.firstName}">
                    </div>

                    <div class="form-group">
                        <label for="lastName${person.id}">Last name</label>
                        <input type="text" name="lastName" class=form-control id="lastName${person.id}"
                               value="${person.lastName}">
                    </div>

                    <div class="form-group">
                        <label for="eyeColor${person.id}">Eye color</label>
                        <input type="text" name="eyeColor" class=form-control id="eyeColor${person.id}"
                               value="${person.eyeColor}">
                    </div>
                    <div class="form-group">
                        <label for="hairColor${person.id}">Hair color</label>
                        <input type="text" name="hairColor" class=form-control id="hairColor${person.id}"
                               value="${person.hairColor}">
                    </div>
                    <div class="form-group">
                        <label for="height${person.id}">Height</label>
                        <input type="text" name="height" class=form-control id="height${person.id}"
                               value="${person.height}">
                    </div>
                    <div class="form-group">
                        <label for="weight${person.id}">Weight</label>
                        <input type="text" name="weight" class=form-control id="weight${person.id}"
                               value="${person.weight}">
                    </div>


                    <button type="submit" class="btn btn-secondary" name="_method" value="PUT">Edit</button>
                    <button type="submit" name="_method" class="btn btn-secondary" value="DELETE">Delete</button>
                    <input type="hidden" name="id" value="${person.id}">
                </form>
            </div>
        </d:forEach>
    </div>
</c:forEach>


</body>
</html>
