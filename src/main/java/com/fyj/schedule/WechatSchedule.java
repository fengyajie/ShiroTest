package com.fyj.schedule;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WechatSchedule {
	
	private Logger logger = LoggerFactory.getLogger(WechatSchedule.class);
	
	private HandlerDispatcher handlerDispatcher;
	
	private final String channelId = getClass().getName();
	
	private void init() {
		handlerDispatcher = new HandlerDispatcher();
		handlerDispatcher.init();
		new Thread(handlerDispatcher).start();
		MessageQueue messageQueue = new MessageQueue(new ConcurrentLinkedQueue<Message>());
		handlerDispatcher.addMessageQueue(channelId, messageQueue);
	}
	
	@PostConstruct
	public void postConstruct() {
		init();
		Object data=null;
		handleSysnc(data);
	}
	
	public void handleSysnc(Object data) {
		Message message = new Message();
		message.setChannelId(channelId);
		message.setDataObject(data);
		message.setCallBack(new MessageCallBack() {

			public void process(Object dataObject) {
				hand(dataObject);
			}
			
		});
		handlerDispatcher.addMessage(message);
	}

	public void hand(Object data) {
		handleSysnc(data);
	}
}
