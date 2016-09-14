<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
    <fmt:setBundle basename="ApplicationResources" />
    <title> <fmt:message key="title.cruduser"/> </title>
</head>
<body>
<h1><fmt:message key="label.cruduser"/></h1>
<c:url var="url" scope="page" value="/nocturne/addedituser">
    <c:param name="id" value=""/>
    <c:param name="name" value=""/>
    <c:param name="roles" value=""/>
    <c:param name="insert" value="true"/>
</c:url>
<a href="${url}"><fmt:message key="label.cruduser.add"/></a>
<br/><br/>
<table class="borderAll">
    <tr>
        <th><fmt:message key="label.cruduser.id"/></th>
        <th><fmt:message key="label.cruduser.name"/></th>
        <th><fmt:message key="label.cruduser.roles"/></th>
        <th><fmt:message key="label.cruduser.edit"/> <fmt:message key="label.cruduser.delete"/></th>
    </tr>
    <c:forEach var="cruduser" items="${ul}" varStatus="status">
        <tr class="${status.index%2==0?'even':'odd'}">
            <td class="nowrap">${cruduser.id}</td>
            <td class="nowrap">${cruduser.name}</td>
            <td class="nowrap">
                <c:forEach var="role" items="${cruduser.roles}">
                    <c:out value="${role.role}"></c:out>
                </c:forEach>
            </td>
            <td class="nowrap">
                <c:url var="updurl" scope="page" value="/nocturne/addedituser">
                    <c:param name="id" value="${cruduser.id}"/>
                    <c:param name="name" value="${cruduser.name}"/>
                    <c:param name="insert" value="false"/>
                </c:url>
                <a href="${updurl}"><fmt:message key="label.cruduser.edit"/></a>
                &nbsp;&nbsp;&nbsp;
                <c:url var="delurl" scope="page" value="/nocturne/deleteuser">
                    <c:param name="name" value="${cruduser.name}"/>
                </c:url>
                <a href="${delurl}"><fmt:message key="label.cruduser.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>