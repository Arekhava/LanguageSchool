package Strings;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ShaDemo {
	public static void main(String[] args) {
		String unencrypted = "ilovejava";
		byte[] bytes = null;
		try {
			MessageDigest mes = MessageDigest.getInstance("SHA-1");
				mes.update(unencrypted.getBytes("utf-8"));
				bytes = mes.digest();
		
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
 	
		}
		BigInteger bigInteger = new BigInteger(1, bytes);
		String encrypted = bigInteger.toString(16);
		System.out.println(encrypted);
	}
}
