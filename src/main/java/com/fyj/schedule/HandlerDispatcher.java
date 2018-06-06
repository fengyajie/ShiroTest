package com.fyj.schedule;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerDispatcher implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(HandlerDispatcher.class);
	
	private Executor messageExecutor;
	
	private Map<String,MessageQueue> sessionMsgQ;
	
	private boolean running;
	
	private long sleepTime=200;
	private int coreCpuNum = 5;
	
	public void init() {
		if(!running) {
			running= true;
			sessionMsgQ = new ConcurrentHashMap<String,MessageQueue>();
			messageExecutor = Executors.newFixedThreadPool(coreCpuNum);
		}
	}

	public void stop() {
		running=false;
	}
	
	
	public void run() {
		while(running) {
			Set<Map.Entry<String, MessageQueue>> entrySet = sessionMsgQ.entrySet();
			for(Map.Entry<String, MessageQueue> entry:entrySet) {
				String key = entry.getKey();
				MessageQueue messageQueue = sessionMsgQ.get(key);
				if(messageQueue == null || messageQueue.size() <= 0 || messageQueue.isRunning()) {
					continue;
				}
				MessageWorker messageWorker = new MessageWorker(messageQueue);
				
				this.messageExecutor.execute(messageWorker);
				
			}
			try {
				Thread.sleep(sleepTime);
			}catch(InterruptedException e){
				logger.error("", e);
			}
		}

	}
	
	public void addMessageQueue(String channelId,MessageQueue messageQueue) {
		sessionMsgQ.put(channelId, messageQueue);
	}
	
	public boolean addMessage(Message message) {
		boolean added = false;
		String channelId = message.getChannelId();
		MessageQueue messageQueue = sessionMsgQ.get(channelId);
		if(messageQueue == null) {
			logger.error("", new IllegalStateException());
		}else {
			added = messageQueue.add(message);
		}
		return added;	
	}

	public boolean checkMessageQueue(String key) {
		return sessionMsgQ.containsKey(key);
	}
	
	public void removeMessageQueue(String key) {
		MessageQueue queue = sessionMsgQ.remove(key);
		if(queue != null) {
			queue.clear();
		}
	}
	
	
	private final class MessageWorker implements Runnable{
		private MessageQueue messageQueue;
		private Message message;
		
		public MessageWorker(MessageQueue messageQueue) {
			messageQueue.setRunning(true);
			message = messageQueue.getMessageQueue().poll();
			this.messageQueue = messageQueue;
		}
		

		public void run() {
			try {
				handMessageQueue();
			}finally {
				messageQueue.setRunning(false);
			}
		}
		
		private void handMessageQueue() {
			MessageCallBack messageCallBack = message.getCallBack();
			messageCallBack.process(message.getDataObject());
		}
	}

}


