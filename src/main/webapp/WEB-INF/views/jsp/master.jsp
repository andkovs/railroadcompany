<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FranceRR - ${title}</title>

    <script src="<c:url value="/resources/lib/jquery-3.3.1.min.js" /> "></script>
    <link href="<c:url value="/resources/lib/bootstrap/dist/css/bootstrap.css" />" rel="stylesheet">
</head>
<body>

<%--navigation--%>
<%@include file="shared/navbar.jsp" %>

<c:if test="${userClickHome == true}">
    <%@include file="home.jsp" %>
</c:if>

<c:if test="${userClickStations == true}">
    <%@include file="stations.jsp" %>
</c:if>

<c:if test="${userClickStation == true}">
    <%@include file="station.jsp" %>
</c:if>

</body>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD9D3FKnauqxAM4dYF9BEoiWpifX0ibQso&callback=initMap"
        type="text/javascript"></script>

</html>

