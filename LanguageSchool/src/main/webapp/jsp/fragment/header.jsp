<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local"/>
<fmt:message key="local.locbutton.name.en" var="en_button"/>
<fmt:message key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message key="local.slogan" var="slogan"/>
<fmt:message key="local.look_for" var="look_for"/>
<fmt:message key="local.search" var="search"/>
<fmt:message key="local.liked" var="liked"/>
<fmt:message key="local.catalog" var="catalog"/>
<fmt:message key="local.profile" var="profile"/>
<fmt:message key="local.personal_data" var="personal_data"/>
<fmt:message key="local.subscriptions" var="subscriptions"/>
<fmt:message key="local.lang" var="lang" />
<fmt:message key="local.add_course" var="add_course" />
<fmt:message key="local.supplies" var="supplies" />
<fmt:message key="local.issue" var="issue" />
<fmt:message key="local.students" var="students" />
<fmt:message key="local.my_subscriptions" var="my_subscriptions"/>
<fmt:message key="local.sign_out" var="sign_out"/>
<fmt:message key="local.courses_in_subscription" var="courses_on_subscription"/>
<header>
    <div class="header_top">
        <div >
            <span class="slogan">
            ${slogan}
            </span>
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="find_courses_by_name" />
                <input type="text" required placeholder ="${look_for}" name="courseName"/>
                <input type="submit" value="${search}"/>
            </form>
            <nav class="menu">
                    <ul class="menu1" id="menu1">
                        <c:if test="${sessionScope.role != 'ADMIN'}">
                        <li><a href="${pageContext.request.contextPath}/controller?command=go_to_liked_page">${liked}</a></li>
                        </c:if>
                        <li><a href="${pageContext.request.contextPath}/controller?command=go_to_main_page">${catalog}</a>
                            <ul class="menu2">
                            <c:forEach var="language" items="${sessionScope.languages}">
                                <li><a href="${pageContext.request.contextPath}/controller?command=show_courses_by_language&languageId=${language.languageId}"><fmt:message key="local.language.${language.languageName}"/></a></li>
                            </c:forEach>
                            <c:if test="${sessionScope.role == 'ADMIN'}">
                                <li><a href="${pageContext.request.contextPath}/jsp/admin/adding_course.jsp">${add_course}</a></li>
                                <li><a href="${pageContext.request.contextPath}/controller?command=show_courses_in_subscription_subscription&page=1">${courses_in_subscription}</a></li>
                            </c:if>
                            </ul>
                        </li>
                        <c:if test="${sessionScope.role == 'ADMIN'}">
                        <li><a href="${pageContext.request.contextPath}/controller?command=find_subscriptions_by_status&status=course_added">${subscriptions}</a></li>
                  
                        </li>
                        </c:if>
                        <c:if test="${sessionScope.role == 'STUDENT'}">
                        <li><a href="${pageContext.request.contextPath}/controller?command=go_to_profile_page">${profile}</a>
                            <ul class="menu2">
                                <li><a href="${pageContext.request.contextPath}/controller?command=go_to_profile_page">${personal_data}</a></li>
                                <li><a href="${pageContext.request.contextPath}/controller?command=go_to_subscriptions_page">${my_subscriptions}</a></li>
                                <li> <form action="${pageContext.request.contextPath}/controller" method="post">
				                     <input type="hidden" name="command" value="sign_out"/>
				                     <input type="submit" value="${sign_out}"/>
				                     </form></li>
                            </ul>
                        </li>
                        </c:if>
                        <c:if test="${sessionScope.role == 'ADMIN'}">
                        <li><a href="${pageContext.request.contextPath}/controller?command=go_to_students_page">${students}</a></li>
                        </c:if>
                        <li><a>${lang}</a>
                            <ul class="menu2">
                                <li>
                                    <form action="${pageContext.request.contextPath}/controller" method="post" class="locale">
                                        <input type="hidden" name="command" value="en" />
                                        <input type="submit" value="${en_button}" />
                                    </form>
                                </li>
                                <li>
                                    <form action="${pageContext.request.contextPath}/controller" method="post" class="locale">
                                        <input type="hidden" name="command" value="ru" />
                                        <input type="submit" value="${ru_button}" />
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </ul>
            </nav>
        </div>
    </div>
</header>