<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
    <fmt:setBundle basename="ApplicationResources" />
    <title> <fmt:message key="title.annualSchedule"/> </title>
</head>
<body>
<h1><fmt:message key="label.annualSchedule"/></h1>
<table class="borderAll">
    <tr>
        <th><fmt:message key="label.annualSchedule.year"/></th>
        <th><fmt:message key="label.annualSchedule.assignedBy"/></th>
   </tr>
    <c:forEach var="annualSchedule" items="${annualScheduleList}" varStatus="status">
        <tr class="${status.index%2==0?'even':'odd'}">
            <td class="nowrap">${annualSchedule.year}</td>
            <td class="nowrap">${annualSchedule.assignedBy.id}</td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/nocturne/enter-details-annual-schedule" method=post>
    <table>
        <tr>
            <td>
                <label>
                    <fmt:message key="fieldLabel.year"/>
                    <input type="text" name="year" size=15 maxlength=4>
                </label>
            </td>
        </tr>
        <tr>
            <td align="center">
                <input type="submit" value="Create">
                &nbsp;&nbsp;
                <input type="reset" value="Reset">
            </td>
        </tr>
    </table>

</form>
</body>
</html>