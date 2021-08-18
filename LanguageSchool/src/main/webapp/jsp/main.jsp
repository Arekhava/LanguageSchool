<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtag" prefix="mytag"%>

<html>
	<head>
		<meta charset="utf-8">
		<fmt:setLocale value="${sessionScope.locale}"/>
		<fmt:setBundle basename="local"/>
		<fmt:message key="local.title.main" var="title"/>
		<fmt:message key="local.sign_out" var="sign_out"/>
		<fmt:message key="local.sign_in" var="sign_in"/>
		<fmt:message key="local.sign_up" var="sign_up"/>
		<fmt:message key="local.login" var="login"/>
		<fmt:message key="local.email" var="email"/>
		<fmt:message key="local.password" var="password"/>
		<fmt:message key="local.in_subscription" var="local.in_subscription"/>
		<fmt:message key="local.add_to_liked" var="add_to_liked"/>
		<fmt:message key="local.welcome" var="welcome" />
		<fmt:message key="local.forgot_password" var="forgot_password"/>
		<fmt:message key="local.catalog" var="catalog"/>
		<fmt:message key="local.liked" var="liked"/>
		<fmt:message key="local.edit" var="edit"/>
		<fmt:message key="local.save" var="save"/>
		<fmt:message key="local.name" var="name"/>
		<fmt:message key="local.price" var="price"/>
		<fmt:message key="local.cheap_first" var="cheap_first"/>
		<fmt:message key="local.expensive_first" var="expensive_first"/>
		<title>${title}</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/style.css" type="text/css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/header.css" type="text/css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/footer.css" type="text/css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/error_info.css" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/selectMenu.js"></script>
	</head>
	<c:if test="${sessionScope.role != 'ADMIN'}">
    <body onload="selectMenu(1)">
    </c:if>
    <c:if test="${sessionScope.role == 'ADMIN'}">
    <body onload="selectMenu(0)">
    </c:if>
    
	<div class="wrapper">
	<%@ include file="/jsp/fragment/header.jsp" %>
	<%@ include file="/jsp/fragment/error_info.jsp" %>	
		
	<div class="contant clearfix">
		<div class="left">
		    <c:if test="${sessionScope.login == null}">
			<div class="login">
				<div class="login_text">${sign_in} - <a href="${pageContext.request.contextPath}/jsp/registration.jsp">${sign_up}</a></div>
				<div class="login_input">
				<form action="${pageContext.request.contextPath}/controller" method="post">
				<input type="hidden" name="command" value="sign_in"/>
					<p><label>${login}<p><input type="email" required placeholder="${email}" name="login" class="log" maxlength="45"></p></label></p>
            		<p><label>${password}<p><input type="password" required placeholder="${password}" name="password" class="log"></p></label></p>
					<a href="${pageContext.request.contextPath}/jsp/forgot_password.jsp">${forgot_password}</a>
            		<input type="submit" value="${sign_in}" class="button" onclick="fun1()"/>
				</form>
				</div>
			</div>
			</c:if>
			<c:if test="${sessionScope.login != null}">
			<div class="welcome">
                <div>
                    <p>${welcome}, ${sessionScope.login}!</p>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
				    <input type="hidden" name="command" value="sign_out"/>
				    <input type="submit" value="${sign_out}"/>
				    </form>
                </div>
            </div>
			</c:if>
			
		</div>
		<div class="center">
			<c:if test="${sessionScope.languages != null}">
			<div class="languagues">
				<c:forEach var="language" items="${sessionScope.languages}">
				<c:if test="${requestScope.languageId == language.languageId}">
				<figure class="language selected">
				</c:if>
				<c:if test="${requestScope.languageId != language.languageId}">
				<figure class="language">
				</c:if>
					<div class="name"><a href="${pageContext.request.contextPath}/controller?command=show_courses_by_language&languageId=${language.languageId}" title="${language.languageName}"><fmt:message key="local.language.${language.languageName}"/></a></div>
				</figure>
				</c:forEach>
			</div>
			</c:if>
	
		<c:if test="${requestScope.courses != null}">
			<div class="courses">
                <c:forEach var="course" items="${requestScope.courses}">
                    <figure class="course">
                        <div class="name"><p>${course.courseName}</p></div>
                        <div class="price"><p>${course.price}$</p></div>
                       
						<c:if test="${sessionScope.role == 'STUDENT'}">
                        <form action="${pageContext.request.contextPath}/controller" method="post" >
                            <input type="hidden" name="command" value="add_course_to_liked"/>
                            <input type="hidden" name="courseId" value="${course.courseId}"/>
                            <input type="submit" value="${add_to_liked}"/>
                        </form>
						</c:if>
						<c:if test="${sessionScope.role == 'ADMIN'}">
						<button id="button_edit_${course.courseId}" onclick="openEditForm('form_edit_${course.courseId}', 'button_edit_${course.courseId}')">${edit}</button>
						<form class="form_edit" id="form_edit_${course.courseId}" action="${pageContext.request.contextPath}/controller" method="post" >
                            <span>${edit}</span>
							<input type="hidden" name="command" value="change_course_data"/>
                            <input type="hidden" name="courseId" value="${course.courseId}"/>
                            <div><label>${name}:<input type="text" name="courseName" value="${course.courseName}"/></label></div>
                            <div><label>${price}:<input type="text" name="course" value="${course.price}"/>$</label></div>
                            <input type="submit" onclick="closeEditForm('form_edit_${course.courseId}', 'button_edit_${course.courseId}')" value="${save}"/>
                        </form>
						</c:if>
                    </figure>
                </c:forEach>
			</div>
			</c:if>
		</div>
		<hr/>
	</div>
	</div>
	<mytag:copyright/>

	<script>
		function openEditForm(editFormId, editButtonId) {
		  var editForm = document.getElementById(editFormId);
		  editForm.style.display = "block";
		  var editButton = document.getElementById(editButtonId);
		  editButton.style.display = "none";

		}

		function closeEditForm(editFormId, editButtonId) {
		  var editForm = document.getElementById(editFormId);
		  editForm.style.display = "none";
		  var editButton = document.getElementById(editButtonId);
		  editButton.style.display = "block";
		}
	</script>
</body>
</html>
