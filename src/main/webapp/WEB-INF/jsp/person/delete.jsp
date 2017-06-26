<%-- 
    Document   : delete
    Created on : Apr 22, 2011, 3:55:55 PM
    Author     : FMilens
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix='local' uri='/WEB-INF/taglib/functions.tld' %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file="../jsAndCssFragment.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Person</title>
    </head>
    <body class="container">
        <h1 class="text-center">Delete Person</h1>
        <p>
            You are about to delete the person
            ${local:escapeHtml(person.firstName)} ${local:escapeHtml(person.lastName)}:
            Are you sure?
        </p>
        <form action="${pageContext.request.contextPath}/person/delete" method="post">
            <input type="hidden" name="personId" value="${person.personId}"/>
            <input class="btn btn-warning" type="submit" name="command" value="Cancel"/>
            <input class="btn btn-danger" type="submit" name="command" value="Delete"/>
        </form>
    </body>
</html>
