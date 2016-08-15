<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="pages.title"/></title>
    <%@ include file="../inc/header.jspf" %>
</head>
<body>
<div class="container-fluid">
    <%@ include file="../inc/menu.jspf" %>
    <%@ include file="../inc/messages.jspf" %>

    <div class="panel panel-default content">
        <div class="panel-body">
            ADMIN USERS DASHBOARD
            <sec:authentication property="authorities"/>
        </div>
    </div>

    <%@ include file="../inc/footer.jspf" %>
</div>
</body>
</html>
