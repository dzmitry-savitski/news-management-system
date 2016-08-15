<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="pages.admin.priority.add.title"/> - <spring:message code="pages.title"/></title>
    <%@ include file="../inc/header.jspf" %>
    <script src="<c:url value="/resources/js/bootstrap-colorpicker.min.js"/>"></script>
    <script src="<c:url value="/resources/js/colorpicker.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-colorpicker.min.css"/>" type="text/css"/>
</head>
<body>
<div class="container-fluid">
    <%@ include file="../inc/menu.jspf" %>
    <%@ include file="../inc/messages.jspf" %>

    <div class="panel panel-default content">
        <div class="panel-heading">
            <spring:message code="pages.admin.priority.add.title"/>
        </div>
        <div class="panel-body">
            <form:form action="/admin/priorities/add" role="form" method="post" modelAttribute="priority" cssClass="col-lg-4 col-md-6">
                <div class="form-group">
                    <label class="control-label col-sm-4" for="name">
                        <spring:message code="pages.admin.priority.name"/>
                    </label>
                    <div class="col-sm-8">
                        <form:input type="text"
                                    id="name"
                                    path="name" cssClass="form-control "/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-4" for="color">
                        <spring:message code="pages.admin.priority.color"/>
                    </label>
                    <div class="col-sm-8">
                        <form:input type="text"
                                    id="color"
                                    path="color"
                                    cssClass="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-default">
                            <spring:message code="pages.admin.priority.add.button"/>
                        </button>
                    </div>
                </div>


            </form:form>
        </div>
    </div>

    <%@ include file="../inc/footer.jspf" %>
</div>
</body>
</html>