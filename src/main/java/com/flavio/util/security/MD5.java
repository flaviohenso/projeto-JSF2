package com.flavio.util.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class MD5 {
	public static Log log = LogFactory.getLog(MD5.class);

	public static String convertPasswordMd5(String senha) {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.error("Não encontrou algoritmo de criptografia MD5", e);
		}
	     m.update(senha.getBytes(),0,senha.length());
	     return new BigInteger(1,m.digest()).toString(16);
	}
	 
	
}
