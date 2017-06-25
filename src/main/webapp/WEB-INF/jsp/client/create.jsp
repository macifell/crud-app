<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file="../jsAndCssFragment.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Client</title>
    </head>
    <body class="container">
        <h1 class="text-center">Create Client</h1>
        <c:if test="${fn:length(errors) gt 0}">
          <%@include file="../errorFragment.jsp" %>
        </c:if>
        <form class="form-horizontal" action="${pageContext.request.contextPath}/client/create" method="POST">
          <%@include file="clientFormFragment.jsp" %>
        </form>
    </body>
</html>
