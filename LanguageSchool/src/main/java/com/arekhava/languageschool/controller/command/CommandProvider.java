package com.arekhava.languageschool.controller.command;

import static com.arekhava.languageschool.controller.command.CommandType.*;

import java.util.EnumMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.arekhava.languageschool.controller.command.impl.AddCourseToCatalogCommand;
import com.arekhava.languageschool.controller.command.impl.AddCourseToLikedCommand;
import com.arekhava.languageschool.controller.command.impl.BlockUserCommand;
import com.arekhava.languageschool.controller.command.impl.CancelSubscriptionCommand;
import com.arekhava.languageschool.controller.command.impl.ChangeCourseDataCommand;
import com.arekhava.languageschool.controller.command.impl.ChangeLocaleCommand;
import com.arekhava.languageschool.controller.command.impl.ChangePasswordCommand;
import com.arekhava.languageschool.controller.command.impl.ChangeUserDataCommand;
import com.arekhava.languageschool.controller.command.impl.CheckoutCommand;
import com.arekhava.languageschool.controller.command.impl.ConfirmRegistrationCommand;
import com.arekhava.languageschool.controller.command.impl.DefaultCommand;
import com.arekhava.languageschool.controller.command.impl.FindCoursesByNameCommand;
import com.arekhava.languageschool.controller.command.impl.FindSubscriptionByIdCommand;
import com.arekhava.languageschool.controller.command.impl.FindSubscriptionsByStatusCommand;
import com.arekhava.languageschool.controller.command.impl.FindUserByIdCommand;
import com.arekhava.languageschool.controller.command.impl.FindUserByLoginCommand;
import com.arekhava.languageschool.controller.command.impl.FindUserSubscriptionsCommand;
import com.arekhava.languageschool.controller.command.impl.ForgotPasswordCommand;
import com.arekhava.languageschool.controller.command.impl.GoToLikedPageCommand;
import com.arekhava.languageschool.controller.command.impl.GoToMainPageCommand;
import com.arekhava.languageschool.controller.command.impl.GoToProfilePageCommand;
import com.arekhava.languageschool.controller.command.impl.GoToStudentsPageCommand;
import com.arekhava.languageschool.controller.command.impl.GoToSubscriptionsPageCommand;
import com.arekhava.languageschool.controller.command.impl.ProcessSubscriptionCommand;
import com.arekhava.languageschool.controller.command.impl.RemoveCourseFromLikedCommand;
import com.arekhava.languageschool.controller.command.impl.SendMessageCommand;
import com.arekhava.languageschool.controller.command.impl.ShowCoursesFromLanguageCommand;
import com.arekhava.languageschool.controller.command.impl.SignInCommand;
import com.arekhava.languageschool.controller.command.impl.SignOutCommand;
import com.arekhava.languageschool.controller.command.impl.SignUpCommand;
import com.arekhava.languageschool.controller.command.impl.UnblockUserCommand;

/**
 * CommandProvider is used for defining command
 * 
 * @author N
 */
public final class CommandProvider {
	private static final Logger logger = LogManager.getLogger();
	private static Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);

	static {
		commands.put(ADD_COURSE_TO_LIKED, new AddCourseToLikedCommand());
		commands.put(ADD_COURSE_TO_CATALOG, new AddCourseToCatalogCommand());
		commands.put(BLOCK_USER, new BlockUserCommand());
		commands.put(CANCEL_SUBSCRIPTION, new CancelSubscriptionCommand());
		commands.put(CHANGE_PASSWORD, new ChangePasswordCommand());
		commands.put(CHANGE_COURSE_DATA, new ChangeCourseDataCommand());
		commands.put(CHANGE_USER_DATA, new ChangeUserDataCommand());
		commands.put(CHECKOUT, new CheckoutCommand());
		commands.put(CONFIRM_REGISTRATION, new ConfirmRegistrationCommand());
		commands.put(DEFAULT, new DefaultCommand());
		commands.put(EN, new ChangeLocaleCommand());
		commands.put(FIND_SUBSCRIPTION_BY_ID, new FindSubscriptionByIdCommand());
		commands.put(FIND_COURSES_BY_NAME, new FindCoursesByNameCommand());
		commands.put(FIND_USER_BY_ID, new FindUserByIdCommand());
		commands.put(FIND_USER_BY_LOGIN, new FindUserByLoginCommand());
		commands.put(FIND_USER_SUBSCRIPTIONS, new FindUserSubscriptionsCommand());
		commands.put(FORGOT_PASSWORD, new ForgotPasswordCommand());
		commands.put(GO_TO_LIKED_PAGE, new GoToLikedPageCommand());
		commands.put(GO_TO_STUDENTS_PAGE, new GoToStudentsPageCommand());
		commands.put(GO_TO_MAIN_PAGE, new GoToMainPageCommand());
		commands.put(GO_TO_SUBSCRIPTIONS_PAGE, new GoToSubscriptionsPageCommand());
		commands.put(GO_TO_PROFILE_PAGE, new GoToProfilePageCommand());
		commands.put(PROCESS_SUBSCRIPTION, new ProcessSubscriptionCommand());
		commands.put(REMOVE_COURSE_FROM_LIKED, new RemoveCourseFromLikedCommand());
		commands.put(RU, new ChangeLocaleCommand());
		commands.put(SEND_MESSAGE, new SendMessageCommand());
		commands.put(SHOW_COURSES_FROM_LANGUAGE, new ShowCoursesFromLanguageCommand());
		commands.put(SIGN_IN, new SignInCommand());
		commands.put(SIGN_OUT, new SignOutCommand());
		commands.put(UNBLOCK_USER, new UnblockUserCommand());
		commands.put(SIGN_UP, new SignUpCommand());
		commands.put(FIND_SUBSCRIPTIONS_BY_STATUS, new FindSubscriptionsByStatusCommand());
		
	}

	private CommandProvider() {
	}

	/**
	 * Defines command
	 * 
	 * @param commandName {@link String} command name
	 * @return {@link Command}
	 */
	public static Command defineCommand(String commandName) {
		if (commandName == null) {
			logger.error("null command");
			return commands.get(CommandType.DEFAULT);
		}
		CommandType commandType;
		try {
			commandType = CommandType.valueOf(commandName.toUpperCase());
		} catch (IllegalArgumentException e) {
			logger.error("no such command name " + commandName, e);
			commandType = CommandType.DEFAULT;
		}
		return commands.get(commandType);
	}
}
