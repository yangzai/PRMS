<%--
  Created by IntelliJ IDEA.
  User: yao
  Date: 15/09/16
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
    <fmt:setBundle basename="ApplicationResources" />
    <title> <fmt:message key="title.scheduleProgramList"/> </title>
</head>
<body>
<h1><fmt:message key="label.scheduleProgramList"/></h1>
<c:url var="url" scope="page" value="/nocturne/addeditrp">
    <c:param name="duration" value=""/>
    <c:param name="dateOfProgram" value=""/>
    <c:param name="radioprogram" value=""/>
    <c:param name="presenter" value=""/>
    <c:param name="producer" value=""/>
</c:url>
<a href="${url}"><fmt:message key="label.scheduleProgramList.add"/></a>
<br/><br/>
<table class="borderAll">
    <tr>
        <th><fmt:message key="label.scheduleProgramList.duration"/></th>
        <th><fmt:message key="label.scheduleProgramList.edit"/> <fmt:message key="label.scheduleProgramList.delete"/></th>
    </tr>
    <c:forEach var="scheduleProgramList" items="${rps}" varStatus="status">
        <tr class="${status.index%2==0?'even':'odd'}">
            <td class="nowrap">${scheduleProgramList.typicalDuration}</td>
            <td class="nowrap">
                <c:url var="updurl" scope="page" value="/nocturne/addeditschedule">
                    <c:param name="typicalDuration" value="${scheduleProgramList.typicalDuration}"/>
                </c:url>
                <a href="${updurl}"><fmt:message key="label.scheduleProgramList.edit"/></a>
                &nbsp;&nbsp;&nbsp;
                <c:url var="delurl" scope="page" value="/nocturne/deleteschedule">
                    <c:param name="name" value="${scheduleProgramList.name}"/>
                </c:url>
                <a href="${delurl}"><fmt:message key="label.scheduleProgramList.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
