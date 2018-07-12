package com.jpmorgan.messaging;

import java.util.Map;
import com.jpmorgan.service.MessageService;

public class SalesMessageListener implements MessageListener {
	MessageService svc;

	public SalesMessageListener() {
		super();
		svc = new MessageService();
	}

	@Override
	public void onMessage(Map<String, String> message) {

		try {
			// Message Routing
			System.out.println("On Sales Message: " + message);
			String productType = message.get(MessageHandler.PRODUCT_TYPE);
			String msgType = message.get(MessageHandler.MSG_TYPE);
			boolean isPaused = "TRUE".equalsIgnoreCase(message.get(MessageHandler.PAUSE_FLAG));
			boolean isReport = "TRUE".equalsIgnoreCase(message.get(MessageHandler.REPORT_FLAG));
			double price = -1000;
			switch (msgType) {
			case MessageHandler.MSG_TYP_1:
				System.out.println("Type1 Handling..");
				System.out.println("====Before====");
				svc.printProductSales();
				price = Double.parseDouble(message.get(MessageHandler.VALUE));
				svc.addSale(productType, price);
				System.out.println("====After====");
				svc.printProductSales();
				break;
			case MessageHandler.MSG_TYP_2:
				System.out.println("Type2 Handling..");
				price = Double.parseDouble(message.get(MessageHandler.VALUE));
				int qty = Integer.parseInt(message.get(MessageHandler.QUANTITY));
				System.out.println("====Before====");
				svc.printProductSales();
				svc.addSale(productType, price, qty);
				System.out.println("====After====");
				svc.printProductSales();
				break;
			case MessageHandler.MSG_TYP_3:
				System.out.println("Type3 Handling..");
				double value = Double.parseDouble(message.get(MessageHandler.VALUE));
				String operation = message.get(MessageHandler.OPERATION);
				System.out.println("====Before====");
				svc.printProductSales();
				svc.modifySale(productType, value, operation);
				System.out.println("====After====");
				svc.printProductSales();
				break;

			}
			
			if(isPaused)  reportAdjustment();
			
			
			if(isReport) reportSales();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	private void reportSales() {
		System.out.println("====Print Product Sales====");
		svc.printProductSales();
		
	}

	private void reportAdjustment() throws InterruptedException {
		System.out.println("====System Paused====");
		Thread.sleep(20000);
	}

}
