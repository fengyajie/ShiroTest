package com.fyj.schedule;

import java.util.Queue;

public final class MessageQueue {
	private Queue<Message> messageQueue;
	
	private boolean running = false;

	public MessageQueue(Queue<Message> messageQueue) {
		this.messageQueue = messageQueue;
	}
	public Queue<Message> getMessageQueue() {
		return messageQueue;
	}

	public void setMessageQueue(Queue<Message> messageQueue) {
		this.messageQueue = messageQueue;
	}
	
	
	public void clear() {
		messageQueue.clear();
		messageQueue = null;
	}
	
	public int size() {
		return messageQueue !=null?messageQueue.size():0;
	}
	
	public boolean add(Message message) {
		return this.messageQueue.add(message);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
