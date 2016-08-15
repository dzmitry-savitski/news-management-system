<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="pages.title"/></title>
    <%@ include file="inc/header.jspf" %>
</head>
<body>
<div class="container-fluid">
    <%@ include file="inc/menu.jspf" %>

    <div class="panel panel-default">
        <div class="panel-body">
            <%--News table--%>
            <table class="table table-striped table-responsive">
                <thead>
                <tr>
                    <th width="1%">
                        <spring:message code="pages.news.table.id"/>
                    </th>
                    <th width="1%">
                        <spring:message code="pages.news.table.date"/>
                    </th>
                    <th width="auto">
                        <spring:message code="pages.news.table.title"/>
                    </th>
                    <th width="1%">
                        <spring:message code="pages.news.table.edit"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="news" items="${newsPage.content}">
                    <tr>
                        <td>${news.id}</td>
                        <td><em><spring:eval expression="news.date"/></em></td>
                        <td>
                            <a href="<c:url value="/news/${news.id}"/>">
                                    ${news.title}
                            </a>
                        </td>
                        <td>
                            <a href="<c:url value="/news/modify/${news.id}"/>" class="btn btn-default">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <%--Add button--%>
            <a href="<c:url value="/news/modify"/>" class="btn btn-default">
                <spring:message code="pages.news.button.add"/>
            </a>

            <%--Pagination--%>
            <%@ include file="inc/newsPagination.jspf" %>
        </div>
    </div>
    <%@ include file="inc/footer.jspf" %>
</div>
</body>
</html>
