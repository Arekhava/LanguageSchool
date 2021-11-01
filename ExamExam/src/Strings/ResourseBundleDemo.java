package Strings;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourseBundleDemo {
	public static void main(String[] args) {
		Locale locale = new Locale ("kz","KZ" );
		ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
		String s1 = bundle.getString("str1");
		String s2 = bundle.getString("str2");
		System.out.println(s1 + " "+ s2);
	}

}
