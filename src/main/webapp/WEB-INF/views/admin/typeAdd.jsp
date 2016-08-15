<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="pages.admin.type.add.title"/> - <spring:message code="pages.title"/></title>
    <%@ include file="../inc/header.jspf" %>
</head>
<body>
<div class="container-fluid">
    <%@ include file="../inc/menu.jspf" %>
    <%@ include file="../inc/messages.jspf" %>

    <div class="panel panel-default content">
        <div class="panel-heading">
            <spring:message code="pages.admin.type.add.title"/>
        </div>
        <div class="panel-body">
            <form:form action="/admin/types/add" role="form" method="post" modelAttribute="type" cssClass="form-inline">
                <label class="control-label" for="name">
                    <spring:message code="pages.admin.type.name"/>
                </label>
                <form:input type="text"
                            class="form-control"
                            id="name"
                            path="name"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-default">
                    <spring:message code="pages.admin.type.add.button"/>
                </button>
            </form:form>
        </div>
    </div>

    <%@ include file="../inc/footer.jspf" %>
</div>
</body>
</html>