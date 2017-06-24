<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Client</title>
    </head>
    <body>
        <h1>Edit Client</h1>
        <c:if test="${fn:length(errors) gt 0}">
            <p>Please correct the following errors in your submission:</p>
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
        <form action="${pageContext.request.contextPath}/client/edit" method="POST">
            <input type="hidden" name="clientId" value="${client.clientId}"/>
            <label for="companyName">Company Name:</label>
            <input type="text" name="companyName" value="${client.companyName}"/>
            <br/>
            <label for="websiteUri">Website Uri:</label>
            <input type="text" name="websiteUri" value="${client.websiteUri}"/>
            <br/>
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" name="phoneNumber" value="${client.phoneNumber}"/>
            <br/>
            <label for="mailingAddress">Mailing Address:</label>
            <input type="text" name="mailingAddress" value="${client.mailingAddress}"/>
            <br/>
            <input type="submit" name="Submit" value="Submit"/>
        </form>
    </body>
</html>
