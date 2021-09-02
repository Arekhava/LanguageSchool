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
<fmt:message key="local.title.subscriptions" var="title" />
<fmt:message key="local.subscriptions_number" var="subscriptions_number" />
<fmt:message key="local.date_of_issue" var="date_of_issue" />
<fmt:message key="local.cost" var="cost" />
<fmt:message key="local.payment_method" var="payment_method" />
<fmt:message key="local.cancel" var="cancel" />

<fmt:message key="local.search" var="search" />
<fmt:message key="local.email" var="email" />
<fmt:message key="local.process" var="process" />
<fmt:message key="local.price" var="price" />
<fmt:message key="local.cost" var="cost" />
<fmt:message key="local.payment_method" var="payment_method" />
<fmt:message key="local.search_subscriptions_by"
	var="search_subscriptions_by" />
<fmt:message key="local.customer" var="customer" />
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
	href="${pageContext.request.contextPath}/css/subscriptions/style.css"
	type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/selectMenu.js"></script>
</head>
<c:if test="${sessionScope.role == 'ADMIN'}">
	<body onload="selectMenu(1)">
</c:if>
<c:if test="${sessionScope.role == 'STUDENT'}">
	<body onload="selectMenu(2)">
</c:if>
<div class="wrapper">
	<%@ include file="/jsp/fragment/header.jsp"%>
	<div class="main">
		<c:if test="${sessionScope.role == 'ADMIN'}">
			<div class="filters">
				<p>${search_subscriptions_by}:</p>
				<form action="${pageContext.request.contextPath}/controller"
					method="get">
					<label>${status}:</label> <input type="hidden" name="command"
						value="find_subscriptions_by_status" /> <select size="1"
						id="status" name="status" required>
						<c:forEach var="subscriptionStatus"
							items="${requestScope.subscriptionStatusList}">
							<option value="${subscriptionStatus}"><fmt:message
									key="local.subscription_status.${subscriptionStatus}" /></option>
						</c:forEach>
					</select> <input type="submit" value="${search}" />
				</form>
				<form action="${pageContext.request.contextPath}/controller"
					method="get">
					<label>${subscription_number}:</label> <input type="hidden"
						name="command" value="find_subscription_by_id" /> <input
						type="number" name="subscriptionsId" required placeholder="№"
						min="1" max="9223372036854775807" /> <input type="submit"
						value="${search}" />
				</form>
				<form action="${pageContext.request.contextPath}/controller"
					method="get">
					<label>${customer}:</label> <input type="hidden" name="command"
						value="find_user_subscriptions" /> <input type="email"
						name="login" required placeholder="${email}" maxlength="45" /> <input
						type="submit" value="${search}" />
				</form>
			</div>
		</c:if>

		<%@ include file="/jsp/fragment/error_info.jsp"%>

		<c:if test="${not empty requestScope.subscriptions}">
			<table class="subscriptions_table">
				<thead bgcolor="#c9c9c9" align="center">
					<tr>
						<th>№</th>
						<th>${date_of_issue}</th>
						<th>${cost}</th>
						<th>${payment_method}</th>
						<th colspan="2">${delivery_method}</th>
						<th>${status}</th>
						<c:if test="${sessionScope.role == 'STUDENT'}">
							<th>${cancel}</th>
						</c:if>
						<c:if test="${sessionScope.role == 'ADMIN'}">
							<th>${process}</th>
						</c:if>
						<th>${info}</th>
					</tr>
				</thead>
				<c:forEach var="subscription" items="${requestScope.subscriptions}">
					<tr align="center" valign="center">
						<td>${subscription.subscriptionId}</td>
						<td><fmt:parseDate value="${subscription.dateTime}"
								pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
							<fmt:formatDate pattern="dd.MM.yyyy HH:mm"
								value="${parsedDateTime }" /></td>
						<td>${subscription.cost}$</td>
						<td><fmt:message
								key="local.payment_method.${subscription.paymentMethod}" /></td>
						<td><fmt:message
								key="local.subscription_status.${subscription.subscriptionStatus}" /></td>

						<td valign="center" align="center"><c:if
								test="${sessionScope.role == 'ADMIN'}">
								<form class="process_subscription_form"
									action="${pageContext.request.contextPath}/controller"
									method="post">
									<input type="hidden" name="command"
										value="process_subscription" /> <input type="hidden"
										name="subscriptionId" value="${subscription.subscriptionId}" />
									<input type="hidden" name="status"
										value="${subscription.subscriptionStatus}" />
									<c:if
										test="${subscription.subscriptionStatus == 'COURSE_ADDED' || subscription.subscriptionStatus == 'ACCEPTED_TO_COURSE' || subscription.subscriptionStatus == 'ACTIVE_COURSE'}">
										<input type="submit" value="${process}" />
									</c:if>
									<c:if
										test="${subscription.subscriptionStatus != 'COURSE_ADDED' && subscription.subscriptionStatus != 'ACCEPTED_TO_COURSE' && subscription.subscriptionStatus != 'ACTIVE_COURSE'}">
										<input type="submit" value="${process}" disabled />
									</c:if>
								</form>
							</c:if>
							<form class="cancel_subscription_form"
								action="${pageContext.request.contextPath}/controller"
								method="post">
								<input type="hidden" name="command" value="cancel_subscription" />
								<input type="hidden" name="subscriptionId"
									value="${subscription.subscriptionId}" /> <input type="hidden"
									name="status" value="${subscription.subscriptionStatus}" />
								<c:if
									test="${sessionScope.role == 'STUDENT' && subscription.subscriptionStatus == 'COURSE_ADDED'}">
									<input type="submit" value="${cancel}" />
								</c:if>
								<c:if
									test="${sessionScope.role == 'STUDENT' && subscription.subscriptionStatus != 'COURSE_ADDED'}">
									<input type="submit" value="${cancel}" disabled />
								</c:if>
								<c:if
									test="${sessionScope.role == 'ADMIN' && subscription.subscriptionStatus != 'NOT_ACCETT_TO_COURSE'}">
									<input type="submit" value="${cancel}" />
								</c:if>
								<c:if
									test="${sessionScope.role == 'ADMIN' && subscription.subscriptionStatus == 'NOT_ACCETT_TO_COURSE'}">
									<input type="submit" value="${cancel}" disabled />
								</c:if>
							</form></td>
						<td valign="center" align="center" class="subscription_info">
							<c:if test="${sessionScope.role == 'ADMIN'}">
								<form action="${pageContext.request.contextPath}/controller"
									method="get">
									<input type="hidden" name="command" value="find_user_by_id" />
									<input type="hidden" name="userId"
										value="${subscription.userId}" /> <input type="submit"
										value="${customer}" />
								</form>
							</c:if>
							<button id="details_id_button_${subscription.subscriptionId}"
								onclick="openDetails('courses_${subscription.subscriptionId}', 'details_id_button_${subscription.subscriptionId}', 'close_details_id_button_${subscription.subscriptionId}')">${detail}</button>
							<button
								id="close_details_id_button_${subscription.subscriptionId}"
								style="display: none;"
								onclick="closeDetails('courses_${subscription.subscriptionId}', 'close_details_id_button_${subscription.subscriptionId}', 'details_id_button_${subscription.subscriptionId}')">${close_details}</button>
						</td>
					</tr>
					<tr id="courses_${subscription.subscriptionId}"
						style="display: none;">
						<td colspan="10">

							<table class="courses_table" border="1px" bordercolor="c9c9c9">
								<thead bgcolor="#fff" align="center">
									<tr>
										<th colspan="2"><fmt:message key="local.course" /></th>
										<th>${price}</th>
										<th>${cost}</th>
									</tr>
								</thead>
								<c:forEach var="course" items="${subscription.courses}">
									<tr align="center" valign="center">


										<td>${course.key.courseName}</td>

										<td>${course.key.price}$</td>

										<td>${course.value}</td>

										<td><c:out value="${course.key.price * course.value}" />$</td>

									</tr>
								</c:forEach>
							</table>

						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</div>
<mytag:copyright />
<script>
    function openDetails(id, buttonToCloseId, buttonToOpenId) {
		var openDetails = document.getElementById(id);
		openDetails.style.display = "table-row";
		var buttonToClose = document.getElementById(buttonToCloseId);
		buttonToClose.style.display = "none";
		var buttonToOpen = document.getElementById(buttonToOpenId);
		buttonToOpen.style.display = "block";
    }
	function closeDetails(id, buttonToCloseId, buttonToOpenId) {
		var closeDetails = document.getElementById(id);
		closeDetails.style.display = "none";
		var buttonToClose = document.getElementById(buttonToCloseId);
		buttonToClose.style.display = "none";
		var buttonToOpen = document.getElementById(buttonToOpenId);
		buttonToOpen.style.display = "block";
    }
</script>
</body>
</html>