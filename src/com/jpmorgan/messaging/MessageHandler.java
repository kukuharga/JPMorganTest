package com.jpmorgan.messaging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHandler {
	private List<MessageListener> listeners = new ArrayList<MessageListener>();
	public static final String MSG_TYP_1 = "type1";
	public static final String MSG_TYP_2 = "type2";
	public static final String MSG_TYP_3 = "type3";
	public static final String MSG_TYPE = "MSG_TYPE";
	public static final String PRODUCT_TYPE = "PRODUCT_TYPE";
	public static final String VALUE = "VALUE";
	public static final String OPERATION = "OPERATION";
	public static final String QUANTITY = "QTY";
	public static final String PROCESSED_PATH_NM = "processed";
	public static final String PAUSE_FLAG = "pause";
	public static final String REPORT_FLAG = "report";
	private int msgCounter = 0;

	void addListener(MessageListener listener) {
		this.listeners.add(listener);
	}

	List<MessageListener> getListeners() {
		return listeners;
	}

	void fireListener(Map<String, String> message) {
		this.msgCounter += 1;
		if(this.msgCounter % 10 == 0) message.put(REPORT_FLAG, "TRUE");
		if(this.msgCounter % 50 == 0) message.put(PAUSE_FLAG, "TRUE");
		listeners.stream().forEach(n -> n.onMessage(message));
	}
	


	// Pass File path as the first program argument
	public static void main(String[] args) {
		try {
			if (args.length == 0)
				throw new Exception("Please provide file path as the first argument!");
			String csvFile = args[0];

			MessageHandler handler = new MessageHandler();
			File filePath = handler.getFilePath(csvFile);
			File processedFilePath = new File(csvFile + File.separator + PROCESSED_PATH_NM);
			boolean processedPathExist = true;

			if (!processedFilePath.exists()) {

				processedPathExist = processedFilePath.mkdirs();

			}

			handler.addListener(new SalesMessageListener());

			while (true) {
				if (filePath.list().length > 0) {
					for (String f : filePath.list()) {
						File msgFile = new File(filePath.getPath() + File.separator + f);
						if (handler.isValid(msgFile)) {
							System.out.println("Processing file " + f + "..");
							List<Map<String, String>> msgList = handler.constructMessage(msgFile);
							for (Map<String, String> msg : msgList) {
								handler.fireListener(msg);
							}
						}
						if (processedPathExist) {
							msgFile.renameTo(new File(processedFilePath + File.separator + f));
						} else {
							msgFile.delete();
						}

					}

				}
				Thread.sleep(100);// Polling frequency
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

	private boolean isValid(File msgFile) {
		return (msgFile.isFile());
	}

	private File getFilePath(String path) throws Exception {
		File file = new File(path);
		if (!file.exists())
			throw new Exception("folder not exist!:" + path);
		if (!file.isDirectory())
			throw new Exception(path + " is not a directory.");

		return file;

	}

	private List<Map<String, String>> constructMessage(File file) throws Exception {

		//BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		List<Map<String, String>> msgList = new ArrayList<Map<String, String>>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			// br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] message = line.split(csvSplitBy);
				Map<String, String> mapMessage = new HashMap<String, String>();

				switch (message[0]) {
				case MSG_TYP_1:
					mapMessage.put(MSG_TYPE, MSG_TYP_1);
					mapMessage.put(PRODUCT_TYPE, message[1]);
					mapMessage.put(VALUE, message[2]);
					break;
				case MSG_TYP_2:
					mapMessage.put(MSG_TYPE, MSG_TYP_2);
					mapMessage.put(PRODUCT_TYPE, message[1]);
					mapMessage.put(VALUE, message[2]);
					mapMessage.put(QUANTITY, message[3]);
					break;
				case MSG_TYP_3:
					mapMessage.put(MSG_TYPE, MSG_TYP_3);
					mapMessage.put(PRODUCT_TYPE, message[1]);
					mapMessage.put(VALUE, message[2]);
					mapMessage.put(OPERATION, message[3]);
					break;
				default:
					throw new Exception("Message is not recognized!");
				}
				
				

				if (mapMessage.isEmpty() == false)
					msgList.add(mapMessage);
				
				
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msgList;

	}

}
