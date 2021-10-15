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
  <fmt:message key="local.title.liked" var="title"/>
  <fmt:message key="local.save" var="save"/>
  <fmt:message key="local.delete" var="delete"/>
  <fmt:message key="local.checkout" var="checkout"/>
  <fmt:message key="local.in_total" var="in_total"/>
  <fmt:message key="local.course" var="course"/>
  <fmt:message key="local.price" var="price"/>
  <fmt:message key="local.nextStart" var="nextStart"/>
  <fmt:message key="local.cost" var="cost"/>
  <fmt:message key="local.delete" var="delete"/>
  <fmt:message key="local.on_subscription" var="on_subscription"/>
  <fmt:message key="local.payment_method" var="payment_method"/>
  <title>${title}</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/header.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/footer.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/error_info.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/liked/style.css" type="text/css" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/selectMenu.js"></script>
</head>
<body onload="selectMenu(0)">
<div class="wrapper">
	<%@ include file="/jsp/fragment/header.jsp" %>
	<%@ include file="/jsp/fragment/error_info.jsp" %>
	
	 <c:if test = "${not empty requestScope.subscription.courses}"> 
		<table>
			<thead bgcolor="#c9c9c9" align="center">
				<tr>
					<th>${course}</th>
					<th>${price}</th>
					<th>${nextStart}</th>
					<th>${delete}</th>
				</tr>
			</thead>
		<c:forEach var="course" items="${requestScope.subscription.courses}">
			<tr align="center" valign="center">
			
				<td>${course.courseName}</td>
				<td>${course.price}$</td>
				<td>${course.nextStart}</td>
				
				<td>
					<form action="${pageContext.request.contextPath}/controller" method="post">
						<input type="hidden" name="command" value="remove_course_from_liked"/>
						<input type="hidden" name="courseId" value="${course.courseId}"/>
						<input type="submit" value="${delete}"/>
					</form>
				</td>
			</tr>
        </c:forEach>
		</table> 
		<div class="subscription">
		
            <div class="final_price">${in_total}: ${requestScope.subscription.cost}$</div>
           <div class="payment">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="checkout"/>
                    <input type="hidden" name="cost" value="${requestScope.subscription.cost}"/>
                    <div>
                        <label for="paymentMethod">${payment_method}:</label>
                        <select size="1" id="paymentMethod" name="paymentMethod" required>
                                <c:forEach var="paymentMethod" items="${requestScope.paymentMethodList}">
                                    <option value="${paymentMethod}"><fmt:message key="local.payment_method.${paymentMethod}"/></option>
                                </c:forEach>
                        </select>
                    </div>
                    <input type="submit" value="${checkout}"/>
                </form>
            </div>
        </div>
      </c:if>  
</div>      
<mytag:copyright/>
<script>
    function openDiv(openId) {
		var openDiv = document.getElementById(openId);
		var allMethodInfoDiv = document.getElementById("method_info");
		if (allMethodInfoDiv.hasChildNodes()) {
			var children = allMethodInfoDiv.children;
			for (var i = 0; i < children.length; i++) {
				children[i].style.display = "none";
			}
		}
		if (openDiv != null) {
			openDiv.style.display = "block";
		}
    }
</script>
 
</body>
</html>