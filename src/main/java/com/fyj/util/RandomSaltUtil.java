package com.fyj.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.RandomStringUtils;

public class RandomSaltUtil {
	public static String randomSalt(){
		String randomString = RandomStringUtils.randomAlphanumeric(10);
		String randomStr = null;
		try {
			randomStr = new String(randomString.getBytes(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return randomStr;
	}

}
