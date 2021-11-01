package Strings;

import java.util.Arrays;
import java.util.regex.Pattern;

public class RegularDemo {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("java");
		String[] res = pattern.split("nullaesfcjavasedfwef javaersgfvwejava123", 2 );
		System.out.println(Arrays.toString(res));
		//Pattern.matches("y+z", "yzz");
		System.out.println(Pattern.matches("y?z", "yyz"));
	}

}
