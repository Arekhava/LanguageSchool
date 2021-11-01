package Strings;

public class StringMain {
	static String str = "hello";
	public static void main(String[] args) {
		String str0 = "hello";
		System.out.println(str == str0);
		char[] ch = {'n', 'a', 't', 'a'};
		System.out.println(new String(ch));
		byte[] b = {48, 65, 89, 76};
		System.out.println(new String (b));
		String str1 = 12 + Integer.parseInt("12")+"hello";
		System.out.println(str1);
		String str2 = "hello"+12 + Integer.parseInt("12")+false+null;
		System.out.println(str2);
		str1 +=str0;
	}
}
