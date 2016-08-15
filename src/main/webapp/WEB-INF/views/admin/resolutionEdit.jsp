<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="pages.admin.resolution.edit" arguments="${type.id}"/> - <spring:message code="pages.title"/></title>
    <%@ include file="../inc/header.jspf" %>
</head>
<body>
<div class="container-fluid">
    <%@ include file="../inc/menu.jspf" %>
    <%@ include file="../inc/messages.jspf" %>

    <div class="panel panel-default content">
        <div class="panel-heading">
            <spring:message code="pages.admin.resolution.edit" arguments="${type.id}"/>
        </div>
        <div class="panel-body">
            <form:form action="/admin/resolutions/update" role="form" method="post" modelAttribute="resolution" cssClass="form-inline">
                <label class="control-label" for="name">
                    <spring:message code="pages.admin.resolution.name"/>
                </label>

                <form:input type="text"
                            class="form-control"
                            id="name"
                            path="name"
                            maxlength="${appConfig['type.name.length.max']}"/>
                <form:input type="hidden" path="id"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-default">
                    <spring:message code="pages.admin.resolution.update"/>
                </button>
            </form:form>
        </div>
    </div>

    <%@ include file="../inc/footer.jspf" %>
</div>
</body>
</html>