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
  <fmt:message key="local.title.courses" var="title"/>
  <fmt:message key="local.article" var="article"/>
  <fmt:message key="local.course" var="course"/>
  <fmt:message key="local.price" var="price"/>
  <fmt:message key="local.pc" var="pc"/>
  <fmt:message key="local.previous" var="previous"/>
  <fmt:message key="local.next" var="next"/>
  <title>${title}</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/header.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/footer.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/error_info.css" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/products/style.css" type="text/css" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/selectMenu.js"></script>
</head>
<body onload="selectMenu(0)">
<div class="wrapper">
	<%@ include file="/jsp/fragment/header.jsp" %>
	<%@ include file="/jsp/fragment/error_info.jsp" %>


	
		<nav>
        <ul class="pagination">
            <c:if test="${requestScope.page > 1}">
                <li class="page-item"><a class="page-link"
                    href="${sessionScope.currentPage}&page=${requestScope.page-1}">${previous}</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${requestScope.page == i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                            href="${sessionScope.currentPage}&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.page < requestScope.numberOfPages}">
                <li class="page-item"><a class="page-link"
                    href="${sessionScope.currentPage}&page=${requestScope.page+1}">${next}</a>
                </li>
            </c:if>
        </ul>
        </nav>
    </div>
	</c:if>
</div>
<mytag:copyright/>
 
</body>
</html>