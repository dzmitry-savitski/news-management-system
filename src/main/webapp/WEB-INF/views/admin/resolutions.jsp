<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="pages.admin.resolution.title"/> - <spring:message code="pages.title"/></title>
    <%@ include file="../inc/header.jspf" %>
</head>
<body>
<div class="container-fluid">
    <%@ include file="../inc/menu.jspf" %>
    <%@ include file="../inc/messages.jspf" %>

    <div class="panel panel-default content">
        <div class="panel-heading">
            <spring:message code="pages.admin.resolution.title"/>
        </div>
        <div class="panel-body">
            <table class="table table-striped table-responsive">
                <thead>
                <tr>
                    <th width="1%">
                        <spring:message code="pages.admin.resolution.table.id"/>
                    </th>
                    <th width="auto">
                        <spring:message code="pages.admin.resolution.table.name"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allResolutions}" var="resolution">
                    <tr>
                        <td>${resolution.id}</td>
                        <td><a href="<c:url value="/admin/resolutions/${resolution.id}"/>" class="btn btn-default">${resolution.name}</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="<c:url value="/admin/resolutions/add"/>" class="btn btn-primary">
                <spring:message code="pages.admin.resolution.add.button"/>
            </a>
        </div>
    </div>

    <%@ include file="../inc/footer.jspf" %>
</div>
</body>
</html>