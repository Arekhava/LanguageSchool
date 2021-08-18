package com.languageschool.controller.command;

import java.util.EnumMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.languageschool.controller.command.impl.AddCourseToCatalogCommand;
import com.languageschool.controller.command.impl.AddCourseToLikedCommand;
import com.languageschool.controller.command.impl.BlockUserCommand;
import com.languageschool.controller.command.impl.CancelSubscriptionCommand;
import com.languageschool.controller.command.impl.ChangeCourseDataCommand;
import com.languageschool.controller.command.impl.ChangeLocaleCommand;
import com.languageschool.controller.command.impl.ChangePasswordCommand;
import com.languageschool.controller.command.impl.ChangeUserDataCommand;
import com.languageschool.controller.command.impl.CheckoutCommand;
import com.languageschool.controller.command.impl.ConfirmRegistrationCommand;
import com.languageschool.controller.command.impl.DefaultCommand;
import com.languageschool.controller.command.impl.FindCoursesByNameCommand;
import com.languageschool.controller.command.impl.FindSubscriptionByIdCommand;
import com.languageschool.controller.command.impl.FindSubscriptionsByStatusCommand;
import com.languageschool.controller.command.impl.FindUserByIdCommand;
import com.languageschool.controller.command.impl.FindUserByLoginCommand;
import com.languageschool.controller.command.impl.FindUserSubscriptionsCommand;
import com.languageschool.controller.command.impl.ForgotPasswordCommand;
import com.languageschool.controller.command.impl.GoToLikedPageCommand;
import com.languageschool.controller.command.impl.GoToMainPageCommand;
import com.languageschool.controller.command.impl.GoToProfilePageCommand;
import com.languageschool.controller.command.impl.GoToStudentsPageCommand;
import com.languageschool.controller.command.impl.GoToSubscriptionsPageCommand;
import com.languageschool.controller.command.impl.ProcessSubscriptionCommand;
import com.languageschool.controller.command.impl.RemoveCourseFromLikedCommand;
import com.languageschool.controller.command.impl.SendMessageCommand;
import com.languageschool.controller.command.impl.ShowCoursesByLanguageCommand;
import com.languageschool.controller.command.impl.SignInCommand;
import com.languageschool.controller.command.impl.SignOutCommand;
import com.languageschool.controller.command.impl.SignUpCommand;
import com.languageschool.controller.command.impl.UnblockUserCommand;

/**
 * CommandProvider is used for defining command
 * 
 * @author N
 */
public final class CommandProvider {
	private static final Logger logger = LogManager.getLogger();
	private static Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);

	static {
		commands.put(CommandType.ADD_COURSE_TO_LIKED, new AddCourseToLikedCommand());
		commands.put(CommandType.ADD_COURSE_TO_CATALOG, new AddCourseToCatalogCommand());
		commands.put(CommandType.BLOCK_USER, new BlockUserCommand());
		commands.put(CommandType.CANCEL_SUBSCRIPTION, new CancelSubscriptionCommand());
		commands.put(CommandType.CHANGE_PASSWORD, new ChangePasswordCommand());
		commands.put(CommandType.CHANGE_COURSE_DATA, new ChangeCourseDataCommand());
		commands.put(CommandType.CHANGE_USER_DATA, new ChangeUserDataCommand());
		commands.put(CommandType.CHECKOUT, new CheckoutCommand());
		commands.put(CommandType.CONFIRM_REGISTRATION, new ConfirmRegistrationCommand());
		commands.put(CommandType.DEFAULT, new DefaultCommand());
		commands.put(CommandType.EN, new ChangeLocaleCommand());
		commands.put(CommandType.FIND_SUBSCRIPTION_BY_ID, new FindSubscriptionByIdCommand());
		commands.put(CommandType.FIND_SUBSCRIPTIONS_BY_STATUS, new FindSubscriptionsByStatusCommand());
		commands.put(CommandType.FIND_COURSES_BY_NAME, new FindCoursesByNameCommand());
		commands.put(CommandType.FIND_USER_BY_ID, new FindUserByIdCommand());
		commands.put(CommandType.FIND_USER_BY_LOGIN, new FindUserByLoginCommand());
		commands.put(CommandType.FIND_USER_SUBSCRIPTIONS, new FindUserSubscriptionsCommand());
		commands.put(CommandType.FORGOT_PASSWORD, new ForgotPasswordCommand());
		commands.put(CommandType.GO_TO_LIKED_PAGE, new GoToLikedPageCommand());
		commands.put(CommandType.GO_TO_STUDENTS_PAGE, new GoToStudentsPageCommand());
		commands.put(CommandType.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
		commands.put(CommandType.GO_TO_SUBSCRIPTIONS_PAGE, new GoToSubscriptionsPageCommand());
		commands.put(CommandType.GO_TO_PROFILE_PAGE, new GoToProfilePageCommand());
		commands.put(CommandType.PROCESS_SUBSCRIPTION, new ProcessSubscriptionCommand());
		commands.put(CommandType.REMOVE_COURSE_FROM_LIKED, new RemoveCourseFromLikedCommand());
		commands.put(CommandType.RU, new ChangeLocaleCommand());
		commands.put(CommandType.SEND_MESSAGE, new SendMessageCommand());
		commands.put(CommandType.SHOW_COURSES_BY_LANGUAGE, new ShowCoursesByLanguageCommand());
		commands.put(CommandType.SIGN_IN, new SignInCommand());
		commands.put(CommandType.SIGN_OUT, new SignOutCommand());
		commands.put(CommandType.UNBLOCK_USER, new UnblockUserCommand());
		commands.put(CommandType.SIGN_UP, new SignUpCommand());
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
