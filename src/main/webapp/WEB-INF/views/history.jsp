<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Weather APP - Search</title>

    <!-- Bootstrap -->
    <link href="<c:url value = "/resources/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value = "/resources/js/jquery.min.js"/>"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value = "/resources/js/bootstrap.min.js"/>"></script>
    <style>
        .grey {
            background-color: lightgrey;
        }
        .top-margin {
            margin-top: 25%;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Weather APP</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/">Search</a></li>
            <li><a href="/city">Cities</a></li>
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

        <div class="list-group">
            <c:forEach items="${reports}" var="report">
                <div class="col-md-12 grey">
                    <div class="col-md-2">
                        <img class="top-margin" src="http://openweathermap.org/img/w/${report.icon}.png"/>
                    </div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-2">
                                <h3><span class="label label-success">${report.city.name}</span></h3>
                            </div>
                            <div class="col-md-2">
                                <h3><span class="label label-info">${report.status}</span></h3>
                            </div>
                            <div class="col-md-2">
                                <h3><span class="label label-default">${report.temp} C</span></h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <h3><span class="label label-default"><fmt:formatDate value="${report.date}" pattern="yyyy-MM-dd"/></span></h3>
                            </div>
                            <div class="col-md-2">
                                <h3><span class="label label-primary">${report.pressure} hpa</span></h3>
                            </div>
                            <div class="col-md-2">
                                <h3><span class="label label-warning">${report.windSpeed} m/s</span></h3>
                            </div>
                            <div class="col-md-2">
                                <h3><span class="label label-default">${report.humid} %</span></h3>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <a class="btn btn-danger top-margin" type="button" href="/weather/remove/${report.id}/${report.city.name}">Delete</a>
                    </div>
                </div>
                <div class="col-md-8" style="height:10px;"></div>
            </c:forEach>

        </div>
        <div class="col-md-12" style="height:50px;"></div>
        <div class="col-md-12">
            <a type="button" class="btn btn-primary" href="/city">Back</a>
        </div>
    </div>
</div>
</body>
</html>