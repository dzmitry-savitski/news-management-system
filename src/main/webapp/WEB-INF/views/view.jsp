<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title><spring:message code="pages.title"/></title>
    <%@ include file="inc/header.jspf" %>
    <script src="<c:url value="/resources/js/comments.js"/>"></script>
    <script>
        var newsId = "${news.id}";
    </script>
</head>
<body>
<div class="container-fluid">
    <%@ include file="inc/menu.jspf" %>
    <div class="panel panel-default">

        <%--news title--%>
        <div class="panel-heading">
            <strong>${news.title}</strong>
            <spring:message code="pages.news.view.date"/> <em><spring:eval expression="news.date"/></em>
            <a href="<c:url value="/news/modify/${news.id}"/>" class="btn btn-default">
                <span class="glyphicon glyphicon-edit"></span><spring:message code="pages.news.view.edit"/>
            </a>
        </div>

        <%--news body--%>
        <div class="panel-body">
            ${news.body}
        </div>
    </div>
    <div class="panel panel-default">
        <%--comments--%>
        <div class="panel-heading">
            <spring:message code="pages.news.view.comments"/>
        </div>
        <div id="ajaxLoader" style="margin-top: 15px" hidden>
            <img src="<c:url value="/resources/img/ajax-loader.gif"/>" class="center-block">
        </div>
        <div class="panel-body">
            <table id="commentsTable" class="table table-hover table-responsive"></table>
        </div>

    </div>
    <div class="panel panel-default">

        <%--add comment form--%>
        <div class="panel-body">
            <form role="form">
                <label class="control-label" for="body">
                    <spring:message code="pages.news.view.comment.label"/>
                </label>
                <input id="body"
                       type="text"
                       class="form-control"/>
                <button id="submitComment"
                        type="submit"
                        class="btn btn-default">
                    <spring:message code="pages.news.view.comment.button.submit"/>
                </button>
            </form>
        </div>
    </div>
    <%@ include file="inc/footer.jspf" %>
</div>
</body>
</html>
