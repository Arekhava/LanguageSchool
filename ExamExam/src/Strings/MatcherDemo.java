package Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherDemo {
	public static void main(String[] args) {
		String mailRegex = "\\b\\w{6,}@\\w+\\.\\p{Lower}{2,4}\\b";
		String input = "e-mail's: no1307441@gmail.com, language_school@mail.ru";
		Pattern pattern = Pattern.compile(mailRegex);
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.start());
			System.out.println(matcher.group());
			System.out.println(matcher.end());
		}
		System.out.println(matcher.replaceAll("________"));
	}
}
