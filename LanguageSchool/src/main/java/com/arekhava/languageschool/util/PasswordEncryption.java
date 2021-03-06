 package com.arekhava.languageschool.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The utility is responsible for encrypting passwords
 * 
 * @author N
 */
public final class PasswordEncryption {
	private static final Logger logger = LogManager.getLogger();
	private static final String MESSAGE_DIGEST_ALGORITHM = "SHA-1";
	private static final String ENCODING = "utf8";

	private PasswordEncryption() {
	}

	/**
	 * Encrypts password
	 * 
	 * @param password {@link String} password
	 * @return {@link String} encrypted password
	 */
	public static String encrypt(String password) {
		if (password == null) {
			logger.error("password is null");
			return StringUtils.EMPTY;
		}
		String encryptedPassword;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);
			messageDigest.update(password.getBytes(ENCODING));
			byte[] bytesEncoded = messageDigest.digest();
			BigInteger bigInt = new BigInteger(1, bytesEncoded);
			encryptedPassword = bigInt.toString(16);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("incorrect algorithm or encoding impossible", e);
			encryptedPassword = password;
		}
		return encryptedPassword;
	}
}