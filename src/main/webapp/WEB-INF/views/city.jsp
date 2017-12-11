<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Weather APP - Cities</title>

    <!-- Bootstrap -->
    <link href="<c:url value = "/resources/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value = "/resources/js/jquery.min.js"/>"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value = "/resources/js/bootstrap.min.js"/>"></script>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Weather APP</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/">Search</a></li>
            <li class="active"><a href="/city">Cities</a></li>
        </ul>
    </div>
</nav>
<div class="col-md-12" style="height:50px;"></div>
<div class="d-flex align-items-center">
    <div class="container">
        <c:if test="${error != null}">
            <div class="alert alert-warning">
                    ${error}
            </div>
        </c:if>
        <c:if test="${message != null}">
            <div class="alert alert-success">
                    ${message}
            </div>
        </c:if>
        <c:if test="${!empty listCities}">
        <h3>Current cities</h3>
        <table class="table table-striped">
            <tr>
                <th width="80">City ID</th>
                <th width="120">City Name</th>
                <th width="120">Country Code</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
                <th width="100">View History Data</th>
            </tr>
            <c:forEach items="${listCities}" var="city">
            <tr>
                <td>${city.id}</td>
                <td>${city.name}</td>
                <td>${city.countryCode}</td>
                <td><a href="<c:url value='/city/edit/${city.name}'/>">Edit</a></td>
                <td><a href="<c:url value='/city/remove/${city.id}'/>">Delete</a></td>
                <td><a href="<c:url value='/city/viewHistory/${city.name}'/>">View</a></td>
            </tr>
            </c:forEach>

        </table>

        <div class="col-md-12" style="height:50px;"></div>
        </c:if>

        <div class="col-md-8">
            <c:url var="addAction" value="/city/add"/>
            <h3>Add a <span class="label label-info">new</span> city</h3>
            <form:form action="${addAction}" commandName="city">
                <c:if test="${!empty city.name}">
                <div class="form-group">
                    <form:label path="id">City ID</form:label>
                    <form:input type="number" class="form-control" path="id" disabled="true"/>
                    <form:hidden path="id"/>
                </div>
                </c:if>
                <div class="form-group">
                    <form:label path="name">City Name</form:label>
                    <form:input type="text" class="form-control" path="name" placeholder="City name" required="true"/>
                </div>
                <div class="form-group">
                    <form:label path="countryCode">Country Code</form:label>
                    <form:input type="text" class="form-control" path="countryCode" placeholder="Country code" required="true"/>
                </div>
                <c:if test="${empty city.name}">
                <button type="submit" class="btn btn-primary">Add City</button>
                </c:if>
                <c:if test="${!empty city.name}">
                    <button type="submit" class="btn btn-primary">Edit City</button>
                </c:if>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>