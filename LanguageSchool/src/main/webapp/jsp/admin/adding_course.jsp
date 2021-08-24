<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib uri="customtag" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <fmt:setLocale value="${sessionScope.locale}"/>
  <fmt:setBundle basename="local"/>
  <fmt:message key="local.title.adding_course" var="title"/>
  <fmt:message key="local.course_name" var="course_name"/>
  <fmt:message key="local.price" var="price"/>
  <fmt:message key="local.add_to_catalog" var="add_to_catalog"/>
  <fmt:message key="local.add_course" var="add_course"/>
  <title>${title}</title> 
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/header.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/footer.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/error_info.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adding_course/style.css" type="text/css" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/selectMenu.js"></script>
</head>
<body onload="selectMenu(0)">
<div class="wrapper">
<%@ include file="/jsp/fragment/header.jsp" %>

	<div class="main">
	<p>${add_course}</p>
	<c:set var="currentPage" value="${pageContext.request.requestURI}" scope="session"> </c:set>
	
	<form class="add_course_form" action="${pageContext.request.contextPath}/upload" method="post">
		<input type="hidden" name="command" value="add_course_to_catalog"/>
		<div>
			<label for="language"><fmt:message key="local.language"/>:</label>
			<select size="1" id="language" name="languageId" required>
								<c:forEach var="language" items="${sessionScope.languages}">
									<option value="${language.languageId}"><fmt:message key="local.language.${language.languageName}"/></option>
								</c:forEach>
			</select>
			<label>${language_name}:</label>
			<input type="text" name="name" required placeholder="${course_name}" maxlength="45"/>
		</div>
		<div>
			<label>${price}:</label>
			<input type="text" name="price" required placeholder="${price}" pattern="\d{1,8}(\.\d{2})?"/> $
		</div>
			<div>
			<label>${upload_image}:</label>
			<input type="file" name="file" value="" height="150">
		</div>
		<div>
			<label>${next_start}:</label>
			<input type="date" name="nextStart" required placeholder="${next_start}" maxlength="45"/>
		</div>
		<div>
			<label>${course_id}:</label>
			<input type="text" name="courseId" required placeholder="${course_id}" maxlength="45"/>
		</div>
		<div>
			<label>${upload_image}:</label>
			<input type="file" name="file" value="" height="150">
		</div>
		<input type="submit" value="${add_to_catalog}">
	</form>
	</div>
	
	<%@ include file="/jsp/fragment/error_info.jsp" %>
</div>	
<mytag:copyright/>
</body>
</html>