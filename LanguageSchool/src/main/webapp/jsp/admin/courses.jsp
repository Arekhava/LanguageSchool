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
<fmt:setBundle basename="local" />
<fmt:message key="local.title.courses" var="title" />
<fmt:message key="local.course" var="course" />
<fmt:message key="local.price" var="price" />
<fmt:message key="local.nextStart" var="nextStart" />
<fmt:message key="local.pc" var="pc" />
<fmt:message key="local.previous" var="previous" />
<fmt:message key="local.next" var="next" />
<title>${title}</title>
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
	href="${pageContext.request.contextPath}/css/courses/style.css"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/selectMenu.js"></script>
</head>
<body onload="selectMenu(0)">
	<div class="wrapper">
		<%@ include file="/jsp/fragment/header.jsp"%>
		<%@ include file="/jsp/fragment/error_info.jsp"%>

			<c:forEach var="course" items="${requestScope.courses}">
			<tr align="center" valign="center">
			    <td>${course.courseId}</td>

				<td>
					<img id="course_img" src="${pageContext.request.contextPath}/upload?imageName=${course.imageName}" />
				</td>

				<td>${course.courseName}</td>

				<td>${course.price}$</td>

				<td>${course.nextStart}</td>
			</tr>
			</c:forEach>
		</table>

	</c:if>
</div>
<mytag:copyright/>

</body>
</html>