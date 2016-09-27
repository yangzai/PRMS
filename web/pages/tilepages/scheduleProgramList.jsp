<%--
  Created by IntelliJ IDEA.
  User: yao
  Date: 15/09/16
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%--
    changed by xuemin
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
<c:url var="url" scope="page" value="/nocturne/addeditps">
    <c:param name="duration" value=""/>
    <c:param name="dateOfProgram" value=""/>
    <c:param name="startTime" value=""/>
    <c:param name="radioprogram" value=""/>
    <c:param name="presenter" value=""/>
    <c:param name="producer" value=""/>
    <c:param name="insertps" value="true"/>
</c:url>
<a href="${url}"><fmt:message key="label.scheduleProgramList.add"/></a>
<br/><br/>
<table class="borderAll">
    <tr>
        <th><fmt:message key="label.scheduleProgramList.dateOfProgram"/></th>
        <th><fmt:message key="label.scheduleProgramList.startTime"/></th>
        <th><fmt:message key="label.scheduleProgramList.duration"/></th>
        <th><fmt:message key="label.scheduleProgramList.radioProgram"/></th>
        <th><fmt:message key="label.scheduleProgramList.presenter"/></th>
        <th><fmt:message key="label.scheduleProgramList.producer"/></th>
        <th><fmt:message key="label.scheduleProgramList.edit"/> <fmt:message key="label.scheduleProgramList.delete"/><fmt:message key="label.scheduleProgramList.copy"/></th>
    </tr>
    <c:forEach var="scheduleProgramList" items="${psl}" varStatus="status">
        <tr class="${status.index%2==0?'even':'odd'}">
            <td class="nowrap">${scheduleProgramList.dateOfProgram}</td>
            <td class="nowrap">${scheduleProgramList.startTime}</td>
            <td class="nowrap">${scheduleProgramList.duration}</td>
            <td class="nowrap">${scheduleProgramList.radioProgram.name}</td>
            <td class="nowrap">${scheduleProgramList.presenter.name}</td>
            <td class="nowrap">${scheduleProgramList.producer.name}</td>
            <td class="nowrap">
                <c:url var="updurl" scope="page" value="/nocturne/addeditps">
                    <c:param name="dateOfProgram" value="${scheduleProgramList.dateOfProgram}"/>
                    <c:param name="startTime" value="${scheduleProgramList.startTime}"/>
                    <c:param name="duration" value="${scheduleProgramList.duration}"/>
                    <c:param name="radioProgramName" value="${scheduleProgramList.radioProgram.name}"/>
                    <c:param name="presenterId" value="${scheduleProgramList.presenter.id}"/>
                    <c:param name="presenterName" value="${scheduleProgramList.presenter.name}"/>
                    <c:param name="producerId" value="${scheduleProgramList.producer.id}"/>
                    <c:param name="producerName" value="${scheduleProgramList.producer.name}"/>
                    <c:param name="insertps" value="false"/>
                </c:url>
                <a href="${updurl}"><fmt:message key="label.scheduleProgramList.edit"/></a>
                &nbsp;&nbsp;&nbsp;
                <c:url var="delurl" scope="page" value="/nocturne/deleteps">
                    <c:param name="dateOfProgram" value="${scheduleProgramList.dateOfProgram}"/>
                    <c:param name="startTime" value="${scheduleProgramList.startTime}"/>
                    <c:param name="duration" value="${scheduleProgramList.duration}"/>
                </c:url>
                <a href="${delurl}"><fmt:message key="label.scheduleProgramList.delete"/></a>
                &nbsp;&nbsp;&nbsp;
                <c:url var="copurl" scope="page" value="/nocturne/copyps">
                    <c:param name="dateOfProgram" value="${scheduleProgramList.dateOfProgram}"/>
                    <c:param name="startTime" value="${scheduleProgramList.startTime}"/>
                    <c:param name="duration" value="${scheduleProgramList.duration}"/>
                    <c:param name="radioProgramName" value="${scheduleProgramList.radioProgram.name}"/>
                    <c:param name="presenterId" value="${scheduleProgramList.presenter.id}"/>
                    <c:param name="presenterName" value="${scheduleProgramList.presenter.name}"/>
                    <c:param name="producerId" value="${scheduleProgramList.producer.id}"/>
                    <c:param name="producerName" value="${scheduleProgramList.producer.name}"/>
                    <c:param name="insertps" value="true"/>
                </c:url>
                <a href="${copurl}"><fmt:message key="label.scheduleProgramList.copy"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
