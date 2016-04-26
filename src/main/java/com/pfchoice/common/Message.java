package com.pfchoice.common;

/**
 * Message delivery class.
 *
 * @author Sarath
 */
public class Message {
	
	public static final String SUCCESS = "SUCCESS";

	public static final String FAIL = "FAIL";

	private String status = FAIL;

	private String messages ;

	private Object data ;
	
	/**
	 * 
	 */
	private Message() {
	}

	/**
	 * 
	 * @param status
	 * @param messages
	 * @param data
	 */
	private Message(String status, String messages, Object data) {
		this.status = status;
		this.messages = messages;
		this.data = data;
	}

	/**
	 * 
	 * @param status
	 * @param messages
	 */
	private Message(String status, String messages) {
		this(status, messages, null);
	}

	/**
	 * 
	 * @param messages
	 * @return
	 */
	public static Message failMessage(String messages) {
		return new Message(FAIL, messages);
	}

	/**
	 * 
	 * @param messages
	 * @param data
	 * @return
	 */
	public static Message successMessage(String messages, Object data) {
		return new Message(SUCCESS, messages, data);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessages() {
		return messages;
	}

	/**
	 * 
	 * @param messages
	 */
	public void setMessages(String messages) {
		this.messages = messages;
	}

	/**
	 * 
	 * @return
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
