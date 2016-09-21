<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setBundle basename="ApplicationResources" />
<c:set var="t" value="true" />
<title><fmt:message key="title.error" /></title>
</head>
<body>
	<h2>
        <br/>
		<h>
            Error Message Details:
            <br/>
            ${err_message}
        </h>
	</h2>
</body>
