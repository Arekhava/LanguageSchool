package com.arekhava.languageschool.controller.command;

public final class PagePath {
	
	private PagePath() {
	}

	
	public static final String CONFIRM_REGISTRATION = "controller?command=confirm_registration&userId=";
	public static final String GO_TO_MAIN_PAGE = "controller?command=go_to_main_page";
	public static final String ERROR = "jsp/error.jsp";
	public static final String MAIN = "jsp/main.jsp";
	public static final String GO_TO_PROFILE_PAGE = "controller?command=go_to_profile_page";
	public static final String GO_TO_SUBSCRIPTION_PAGE = "controller?command=go_to_subscription_page";
	public static final String REGISTRATION = "jsp/registration.jsp";
	public static final String COURSES = "jsp/admin/courses.jsp";
	public static final String GO_TO_SUBSCRIPTIONS_PAGE = "controller?command=go_to_subcription_page";
	public static final String SUBSCRIPTIONS = "jsp/subscriptions.jsp";
	public static final String FIND_COURSES_BY_NAME = "controller?command=find_course_by_name";
	public static final String FIND_USER_BY_EMAIL = "controller?command=find_user_by_email";
	public static final String STUDENTS = "jsp/admin/students.jsp";
	public static final String FORGOT_PASSWORD = "jsp/forgot_password.jsp";
	public static final String GO_TO_STUDENTS_PAGE = "controller?command=go_to_students_page";
	public static final String PROFILE = "jsp/student/profile.jsp";
	public static final String ADDED_COURSE = "jsp/admin/adding_course.jsp";
	public static final String SHOW_COURSES_FROM_LANGUAGE = "controller?command=show_courses_from_language&languageId=";
	public static final String GO_TO_LIKED_PAGE = "controller?command=go_to_liked_page";
	public static final String LIKED = "jsp/student/liked.jsp";
	public static final String FIND_USER_SUBSCRIPTIONS = "controller?command=find_user_subscriptions&login=";
	public static final String FIND_SUBSCRIPTION_BY_ID = "controller?command=find_subscription_by_id&subscriptionId=";
	public static final String FIND_SUBSCRIPTIONS_BY_STATUS = "controller?command=find_subscriptions_by_status&status=";
	public static final String LOGIN = "jsp/login.jsp";
	public static final String FIND_USER_BY_ID = "controller?command=find_user_by_id&userId=";
	public static final String FIND_USER_BY_LOGIN = "controller?command=find_user_by_login&login=";

	
	
	
}
