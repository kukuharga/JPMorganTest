package com.jpmorgan.service;

import java.util.List;

import com.jpmorgan.data.SalesData;
import com.jpmorgan.data.SalesTrx;
import com.jpmorgan.exception.DataNotFoundException;

public class MessageService {
	private SalesData salesData = new SalesData();

	public void addSale(String productType, double price) {
		salesData.insertSalesTrx(productType, price, 1);
	}

	public void addSale(String productType, double price, int qty) {
		salesData.insertSalesTrx(productType, price, qty);
	}

	public void modifySale(String productType, double value, String operation) throws DataNotFoundException {

		List<SalesTrx> salesTrxList = salesData.getSalesTrx(productType);
		if (salesTrxList == null)
			throw new DataNotFoundException("Product Type " + productType + " not found!");
		
		if ("add".equals(operation.toLowerCase())) {
			modifyAdd(salesTrxList, value);
		} else if ("substract".equalsIgnoreCase(operation)) {
			modifyAdd(salesTrxList, value * -1);
		} else if ("multiply".equalsIgnoreCase(operation)) {
			modifyMultiply(salesTrxList, value * -1);
		}

		

	}

	private double getSalesValue(String productType) {
		double salesVal = 0;
		List<SalesTrx> salesTrxList = salesData.getSalesTrx(productType);
		for (SalesTrx trx : salesTrxList) {
			salesVal += (trx.getPrice() * trx.getQty());
		}
		return salesVal;
	}

	public void printSalesTrxData(String productType) {
		List<SalesTrx> salesTrxList = salesData.getSalesTrx(productType);
		if(salesTrxList == null) {
			System.out.println("No data found.");
			return;
		}
		for (SalesTrx trx : salesTrxList) {
			System.out.println(trx);
		}
		
		System.out.println("total|"+getSalesValue(productType));
	}

	private void modifyAdd(List<SalesTrx> salesTrxList, double value) {
		for (SalesTrx trx : salesTrxList) {
			trx.setPrice(trx.getPrice() + value);
		}
	}

	private void modifyMultiply(List<SalesTrx> salesTrxList, double value) {
		for (SalesTrx trx : salesTrxList) {
			trx.setPrice(trx.getPrice() * value);
		}
	}

}
