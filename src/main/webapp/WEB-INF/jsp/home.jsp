<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file="jsAndCssFragment.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body class="container">
        <h1 class="text-center">What would you like to do?</h1>     
        <p class="text-center"><a href="${pageContext.request.contextPath}/client/list">List Clients</a></p>
        <p class="text-center"><a href="${pageContext.request.contextPath}/person/list">List People</a></p>
    </body>
</html>
