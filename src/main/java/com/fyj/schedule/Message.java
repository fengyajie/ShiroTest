package com.fyj.schedule;

public class Message {
	
	private String channelId;
	
	private MessageCallBack callBack;
	
	private Object dataObject;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public MessageCallBack getCallBack() {
		return callBack;
	}

	public void setCallBack(MessageCallBack callBack) {
		this.callBack = callBack;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}
	
}
