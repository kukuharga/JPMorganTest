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
			//Message Routing
			System.out.println("On Sales Message: " + message);
			if (MessageHandler.MSG_TYP_1.equalsIgnoreCase(message.get(MessageHandler.MSG_TYPE))) {
				System.out.println("Type1 Handling..");
				String productType = message.get(MessageHandler.PRODUCT_TYPE);
				double price = Double.parseDouble(message.get(MessageHandler.VALUE));
				System.out.println("====Before====");
				svc.printSalesTrxData(productType);
				svc.addSale(productType, price);
				System.out.println("====After====");
				svc.printSalesTrxData(productType);
			}else if (MessageHandler.MSG_TYP_2.equalsIgnoreCase(message.get(MessageHandler.MSG_TYPE))) {
				System.out.println("Type2 Handling..");
				String productType = message.get(MessageHandler.PRODUCT_TYPE);
				double price = Double.parseDouble(message.get(MessageHandler.VALUE));
				int qty = Integer.parseInt(message.get(MessageHandler.QUANTITY));
				System.out.println("====Before====");
				svc.printSalesTrxData(productType);
				svc.addSale(productType, price, qty);
				System.out.println("====After====");
				svc.printSalesTrxData(productType);

			}else if (MessageHandler.MSG_TYP_3.equalsIgnoreCase(message.get(MessageHandler.MSG_TYPE))) {
				System.out.println("Type3 Handling..");
				String productType = message.get(MessageHandler.PRODUCT_TYPE);
				double value = Double.parseDouble(message.get(MessageHandler.VALUE));
				String operation = message.get(MessageHandler.OPERATION);
				System.out.println("====Before====");
				svc.printSalesTrxData(productType);
				svc.modifySale(productType, value, operation);
				System.out.println("====After====");
				svc.printSalesTrxData(productType);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
