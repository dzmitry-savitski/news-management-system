<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title><spring:message code="pages.title"/></title>
    <%@ include file="inc/header.jspf" %>
    <script src="<c:url value="/resources/js/news.js"/>"></script>
    <script>
        // variables will be used in news.js
        var contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body>
<div class="container-fluid">
    <%@ include file="inc/menu.jspf" %>

    <div id="alert" hidden="hidden">
        <a id="alertClose" class="close">&times;</a>
        <span id="alertBody"></span>
    </div>

    <div class="panel panel-default">
        <div class="panel-body">
            <form role="form" class="col-lg-4 col-md-6">

                <%--title--%>
                <div class="form-group row">
                    <label class="control-label col-sm-4" for="title">
                        <spring:message code="pages.news.form.label.title"/>
                    </label>
                    <div class="col-sm-8">
                        <input id="title"
                               type="text"
                               value="${news.title}"
                               maxlength="${appConfig['news.title.length.max']}"
                               class="form-control"/>
                    </div>
                </div>

                <%--date--%>
                <div class="form-group row">
                    <label class="control-label col-sm-4" for="date">
                        <spring:message code="pages.news.form.label.date"/>
                    </label>
                    <div class="col-sm-8">
                        <input id="date"
                               type="text"
                               value="<spring:eval expression="news.date" />"
                               placeholder="YYYY-MM-DD"
                               maxlength="10"
                               class="form-control "/>
                    </div>
                </div>

                <%--body--%>
                <div class="form-group row">
                    <label class="control-label col-sm-4" for="body">
                        <spring:message code="pages.news.form.label.body"/>
                    </label>
                    <div class="col-sm-8">
                        <textarea id="body"
                                  class="form-control"
                                  maxlength="${appConfig['news.body.length.max']}"
                                  rows="10"
                                  cols="10">${news.body}</textarea>
                    </div>
                </div>

                <%--submit button--%>
                <div class="form-group row">
                    <div class="col-sm-offset-4 col-sm-8">
                        <input id="newsId" type="hidden" value="${news.id}">
                        <button id="newsSubmitButton" type="submit" class="btn btn-default">
                            <spring:message code="pages.news.form.button.submit"/>
                        </button>
                        <img id="ajaxLoader" src="<c:url value="/resources/img/ajax-loader.gif"/>" hidden>
                    </div>
                </div>

            </form>
        </div>
    </div>
    <%@ include file="inc/footer.jspf" %>
</div>
</body>
</html>
