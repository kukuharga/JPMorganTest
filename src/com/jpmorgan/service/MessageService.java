package com.jpmorgan.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.jpmorgan.data.SalesData;
import com.jpmorgan.data.SalesTrx;

public class MessageService {
	private SalesData salesData = new SalesData();

	public void addSale(String productType, double price) {
		salesData.insertSalesTrx(productType, price, 1);
	}

	public void addSale(String productType, double price, int qty) {
		salesData.insertSalesTrx(productType, price, qty);
	}

	public void modifySale(String productType, double value, String operation) throws Exception {

		switch (operation.toLowerCase()) {
		case "add":
			modifyAdd(productType, salesData.getSalesTrxList(), value);
			break;
		case "subtract":
			modifyAdd(productType, salesData.getSalesTrxList(), value * -1);
			break;
		case "multiply":
			modifyMultiply(productType, salesData.getSalesTrxList(), value * 1);
			break;
		default:
			throw new Exception("Operation not permitted: "+operation);
		}
		
		salesData.addAdjustment(productType, operation.toLowerCase(), value);

	}

	private double getSalesValue(String productType) {
		return salesData.getSalesTrxList().stream().filter(n -> productType.equalsIgnoreCase(n.getProductType()))
				.map(n -> n.getPrice() * n.getQty()).collect(Collectors.summingDouble(m -> m));
	}

	public void printSalesTrxData(String productType) {
		List<SalesTrx> salesTrxList = salesData.getSalesTrx(productType);
		if (salesTrxList == null) {
			System.out.println("===No data found===");
			return;
		}

		salesTrxList.stream().forEach(trx -> System.out.println(trx));
		System.out.println("total|" + getSalesValue(productType));
	}

	public void printProductSales() {
		Map<String, Double> productSales = salesData.getSalesTrxList().stream().collect(Collectors
				.groupingBy(SalesTrx::getProductType, Collectors.summingDouble(n -> n.getQty() * n.getPrice())));

		System.out.println(productSales);
	}

	public void printAdjustment() {
		Map<String, Double> productSales = salesData.getSalesTrxList().stream().collect(Collectors
				.groupingBy(SalesTrx::getProductType, Collectors.summingDouble(n -> n.getQty() * n.getPrice())));

		System.out.println(productSales);
	}

	private void modifyAdd(String productType, List<SalesTrx> salesTrxList, double value) {

		salesTrxList.stream().filter(n -> productType.equalsIgnoreCase(n.getProductType()))
				.forEach(trx -> trx.setPrice(trx.getPrice() + value));
	}

	private void modifyMultiply(String productType, List<SalesTrx> salesTrxList, double value) {
		salesTrxList.stream().filter(n -> productType.equalsIgnoreCase(n.getProductType()))
				.forEach(trx -> trx.setPrice(trx.getPrice() * value));
	}

}
