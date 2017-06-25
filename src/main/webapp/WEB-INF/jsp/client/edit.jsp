<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
            <input type="hidden" name="client.clientId" value="${clientForm.client.clientId}"/>
            <label for="companyName">Company Name:</label>
            <input type="text" name="client.companyName" value="${clientForm.client.companyName}"/>
            <br/>
            <label for="client.websiteUri">Website Uri:</label>
            <input type="text" name="client.websiteUri" value="${clientForm.client.websiteUri}"/>
            <br/>
            <label for="client.phoneNumber">Phone Number:</label>
            <input type="text" name="client.phoneNumber" value="${clientForm.client.phoneNumber}"/>
            <br/>
            <label for="client.mailingAddress">Mailing Addr:</label>
            <input type="text" name="client.mailingAddress" value="${clientForm.client.mailingAddress}"/>
            <br/>
                <c:forEach items="${clientForm.people}" var="person">
                  <form:checkbox
                    path="clientForm.selectedPersonIds"
                    value="${person.personId}"
                    label="${person.firstName} ${person.lastName}"
                    ></form:checkbox>
                </c:forEach>
            <br/>
            <input type="submit" name="Submit" value="Submit"/>
        </form>
    </body>
</html>
