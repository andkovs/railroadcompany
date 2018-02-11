<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Spring MVC and List Example</h2>

<c:if test="${not empty tests}">

    <ul>
        <c:forEach var="test" items="${tests}">
            <li>${test.testId}+${test.testTitle}</li>
        </c:forEach>
    </ul>

</c:if>
</body>
</html>
