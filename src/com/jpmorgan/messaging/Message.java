package com.jpmorgan.messaging;

import java.util.Date;

public class Message {
	private String messageId;
	private Date time;
	private String messageType;
	private String [] messageContent;
	public Date getTime() {
		return time;
	}
	
	
	public String getMessageId() {
		return messageId;
	}


	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}


	public void setTime(Date time) {
		this.time = time;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String[] getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String[] messageContent) {
		this.messageContent = messageContent;
	}
	
	
	
	
}
