package com.jpmorgan.messaging;

import java.util.Map;

public interface MessageListener {
	
	public void onMessage(Map<String,String> message);

}
