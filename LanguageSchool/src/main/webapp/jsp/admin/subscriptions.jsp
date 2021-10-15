<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtag" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<fmt:setLocale value="${sessionScope.locale}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/header.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/footer.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/error_info.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/students/style.css"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/selectMenu.js"></script>
</head>
<body onload="selectMenu(1)">
	<div class="wrapper">
		<%@ include file="/jsp/fragment/header.jsp"%>
		<div class="main">
        
        <%@ include file="/jsp/fragment/error_info.jsp"%>
			<br />
		
		<c:if test="${not empty requestScope.subscriptions}">
				<table>
					<c:forEach var="subscription" items="${requestScope.subscriptions}">
						<tr align="center" valign="center">

							<td>${subscription.subscriptionId}</td>

							<td>${subscription.userId}</td>
							
							<td>${subscription.subscriptionStatus}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
        </div>
	<mytag:copyright />
		</body>
</html>