<%--
  Created by IntelliJ IDEA.
  User: NguyenTrung
  Date: 9/9/16
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <fmt:setBundle basename="ApplicationResources"/>

    <title><fmt:message key="title.setupuser"/></title>
</head>
<script type="text/javascript">
    function validateForm() {
        var checkboxes = document.getElementsByName("chkRoles");
        var okay = false;
        for (var i = 0, l = checkboxes.length; i < l; i++) {
            if (checkboxes[i].checked) {
                okay = true;
                break;
            }
        }
        if (okay == false) alert("Please select at least one role for this user");
        return okay
    }
</script>
<body>
<c:choose>
    <c:when test="${param['insert'] == 'true'}">
        <c:set var="action_path" scope="page" value="${pageContext.request.contextPath}/nocturne/enteruser"/>
    </c:when>
    <c:otherwise>
        <c:set var="action_path" scope="page" value="${pageContext.request.contextPath}/nocturne/resetUserPassword"/>
    </c:otherwise>
</c:choose>

<form action="${pageContext.request.contextPath}/nocturne/enteruser" method=post onsubmit="return validateForm();">
    <center>
        <table cellpadding=4 cellspacing=2 border=0>
            <tr>
                <th width="30%"><fmt:message key="label.cruduser.name"/></th>
                <th width="35%"><fmt:message key="label.cruduser.id"/></th>
                <th width="35%"><fmt:message key="label.cruduser.password"/></th>
                <th width="25%"><fmt:message key="label.cruduser.roles"/></th>
            </tr>
            <tr>
                <td><fmt:message key="label.cruduser.id"/></td>
                <td>
                    <c:if test="${param['insert'] == 'true'}">
                        <input type="text" name="id" value="" size=15
                               maxlength=20 required="required">
                        <input type="hidden" name="ins" value="true"/>
                    </c:if>
                    <c:if test="${param['insert']=='false'}">
                        <input type="text" name="id" value="${param['id']}" size=15
                               maxlength=20 readonly="readonly">
                        <input type="hidden" name="ins" value="false"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="label.cruduser.name"/></td>
                <td><input type="text" name="name"
                           value="${param['name']}" size=45 maxlength=20 required="required"></td>
            </tr>
            <tr>
                <td><fmt:message key="label.cruduser.password"/></td>
                <td><input type="password" name="password" required="required"
                           value="${pwd}" size=15 maxlength=20></td>
            </tr>
            <tr>
                <td><fmt:message key="label.cruduser.roles"/></td>
                <td>
                    <c:forEach items="${roles}" var="role">
                        <input type="checkbox" name="chkRoles" value="${role.role}"
                               <c:if test="${rolesMapping[role.role] == true }">checked</c:if>> ${role.role} <br/>
                    </c:forEach>
                </td>
            </tr>
        </table>
    </center>
    <input type="submit" name="submit" value="Submit">
    <c:if test="${param['insert'] == 'true'}"><input type="reset" value="Reset"></c:if>
</form>

</body>
</html>