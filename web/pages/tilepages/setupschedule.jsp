<%--
  Created by IntelliJ IDEA.
  User: yao
  Date: 15/09/16
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%--
    Changed by xuemin
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <fmt:setBundle basename="ApplicationResources" />

    <title><fmt:message key="title.setupschedule" /></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/nocturne/enterps" method=post>
    <center>
        <table cellpadding=4 cellspacing=2 border=0>
            <tr>
                <td><fmt:message key="label.scheduleProgramList.dateOfProgram" /></td>
                <td><c:if test="${param['insert'] == 'true'}">
                    <input type="text" name="dateOfProgram" value="${param['dateOfProgram']}" size=15
                           maxlength=20>
                    <input type="hidden" name="ins" value="true" />
                </c:if>
                    <c:if test="${param['insert']=='false'}">
                        <input type="text" name="dateOfProgram" value="${param['dateOfProgram']}" size=15
                               maxlength=20 >
                        <input type="hidden" name="ins" value="false" />
                    </c:if></td>
            </tr>
            <tr>
                <td><fmt:message key="label.scheduleProgramList.duration" /></td>
                <td><input type="text" name="duration"
                           value="${param['duration']}" size=15 maxlength=20></td>
            </tr>
            <tr>
                <td><fmt:message key="label.scheduleProgramList.radioProgram" /></td>
                <td><input type="text" name="radioProgram"
                           value="${param['radioProgram']}" size=15 maxlength=20></td>
                <td>
                    <c:url var="selectrpurl" scope="page" value="/nocturne/selectrp">
                    </c:url>
                    <a href="${selectrpurl}"><fmt:message key="label.scheduleProgramList.select"/></a>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="label.scheduleProgramList.presenter" /></td>
                <td><input type="text" name="presenterName"
                           value="${param['presenterName']}" size=15 maxlength=20></td>
                <td>
                    <c:url var="selectPresenterurl" scope="page" value="/nocturne/selectpresenter">
                    </c:url>
                    <a href="${selectPresenterurl}"><fmt:message key="label.scheduleProgramList.select"/></a>
                </td>

            </tr>
            <tr>
                <td><fmt:message key="label.scheduleProgramList.producer" /></td>
                <td><input type="text" name="producerName"
                           value="${param['producerName']}" size=15 maxlength=20></td>
                <td>
                    <c:url var="selectpProducerurl" scope="page" value="/nocturne/selectproducer">
                    </c:url>
                    <a href="${selectpProducerurl}"><fmt:message key="label.scheduleProgramList.select"/></a>
                </td>
            </tr>
        </table>
    </center>
    <input type="submit" value="Submit"> <input type="reset"
                                                value="Reset">
</form>

</body>
</html>
