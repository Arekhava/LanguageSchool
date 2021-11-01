package Strings;

import java.util.List;

public class StringUsingDemo {
	static String str = "hello";
	public static void main(String[] args) {
		String str0 = new String ("hello");
		//System.out.println(str == str0);
		str0 = str0.intern();
		//System.out.println(str == str0);
		String str1 = " ";
		//System.out.println(str1.isEmpty());
		//System.out.println(str1.isBlank());
		String str2 = "  8247 \n  923847   19247  ";
		str2 = str2.strip();
		//System.out.println(str2);
		System.out.println(str2.replaceAll("\\s+", " " ));
		String xss = "<script> alert() </script>";
		xss.replaceAll("</?script>", "");
		str1 = "928374skdfcn___eksjdb3981---";
		String[] strArr = str1.split("\\D+");
		//System.out.println(Arrays.toString(strArr));
		String str3 = String.format("nata %.2s he%nllo %5.2f", "arekhava", 76.876764542);
		//System.out.printf(str3);
		String str4 = String.join("; ", List.of("asd", "asd", "2"));
		System.out.println(str4);
	}
}
