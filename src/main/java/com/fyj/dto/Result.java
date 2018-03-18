package com.fyj.dto;

public class Result {

	private String message;
	
	private String status;
	
	protected static String Status_OK="sucess";
	
	protected static String Status_ERROR="error";
	
	public Result(String Status,String message){
		this.status = status;
		this.message = message;
	}
}
