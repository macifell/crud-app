<%-- 
    Document   : list
    Created on : Apr 22, 2011, 2:25:22 PM
    Author     : FMilens
--%>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@taglib prefix='local' uri='/WEB-INF/taglib/functions.tld' %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file="../jsAndCssFragment.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Listing</title>
    </head>
    <body class="container">
        <h1 class="text-center">Person Listing</h1>     
        <p>
            <a class="btn btn-success" role="button" href="${pageContext.request.contextPath}/person/create">
                Create New Person
            </a>
        </p>
        <c:choose>
            <c:when test="${fn:length(persons) gt 0}">
                <table class="table">
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email Address</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${persons}" var="person">
                            <tr>
                                <td>${local:escapeHtml(person.firstName)}</td>
                                <td>${local:escapeHtml(person.lastName)}</td>
                                <td>${local:escapeHtml(person.emailAddress)}</td>
                                <td>
                                    <a
                                       class="btn btn-primary"
                                       role="button"
                                       href="${pageContext.request.contextPath}/person/edit/${person.personId}"
                                       >
                                      Edit Person
                                    </a>
                                    <a
                                       class="btn btn-danger"
                                       role="button"
                                       href="${pageContext.request.contextPath}/person/delete/${person.personId}"
                                       >
                                      Delete Person
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p class="text-center">No results found.</p>
            </c:otherwise>
        </c:choose>
        <p class="text-right"><a href="${pageContext.request.contextPath}/">Return Home</a></p>
    </body>
</html>
