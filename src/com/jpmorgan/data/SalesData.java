package com.jpmorgan.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalesData {
	List<SalesTrx> sales = new ArrayList<SalesTrx>();
	List<SalesAdjustment> salesAdjustments = new ArrayList<SalesAdjustment>();

	public void insertSalesTrx(String productType, double price, int quantity) {
		sales.add(new SalesTrx(productType, price, quantity));

	}

	public List<SalesTrx> getSalesTrxList() {

		return sales;
	}

	public List<SalesTrx> getSalesTrx(String productType) {
		return this.sales.stream().filter(n -> productType.equalsIgnoreCase(n.getProductType())).collect(Collectors.toList());
	}
	
	public void addAdjustment(String productType,String operationType,double adjustmentValue) {
		this.salesAdjustments.add(new SalesAdjustment(operationType,productType,adjustmentValue));
	}

}
