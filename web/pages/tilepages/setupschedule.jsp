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

<br/><br/>
<form action="${pageContext.request.contextPath}/nocturne/enterps" method=post>
    <center>
        <table cellpadding=4 cellspacing=2 border=0>
            <tr>
                <td><fmt:message key="label.pslot.date" /></td>
                <td>
                        <input type="text" name="dateOfProgram" value="${ps_dateOfProgram}" size=15
                               maxlength=20>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="label.pslot.duration" /></td>
                <td>
                    <input type="text" name="duration" value="${ps_duration}" size=15
                           maxlength=20>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="label.pslot.rp_name" /></td>
                <td>
                    <input type="text" name="radioProgramName" value="${ps_radioProgramName}" size=15
                           maxlength=20>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="hidden" name="step" value="${step}">
                </td>
            </tr>
        </table>
    </center>
    <input type="submit" value="Submit"> <input type="reset"
                                                value="Reset">
</form>

</body>
</html>
