<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="<c:url value='/css/main.css'/>" rel="stylesheet"
	type="text/css" />

<table class="borderAll">
	<tr>
		<td colspan="2"><tiles:insertAttribute name="header" /></td>
	</tr>
	<tr>
		<td width="15%" align="center" valign="top"><tiles:insertAttribute name="menu" /></td>
		<td valign="top"><tiles:insertAttribute name="body" /></td>
	</tr>
	<tr>
		<td colspan="2"><tiles:insertAttribute name="footer" /></td>
	</tr>
</table>
