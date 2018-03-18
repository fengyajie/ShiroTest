package com.fyj.util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class Md5Util {
    public static String md5Degest(String message){
    	String encoderStr = null;
    	try {
    		MessageDigest md = MessageDigest.getInstance("MD5");
    		byte[] bytes = md.digest(message.getBytes("utf-8"));
    		BASE64Encoder encoder = new BASE64Encoder();
    		encoderStr =  encoder.encode(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return encoderStr;
    }
}
