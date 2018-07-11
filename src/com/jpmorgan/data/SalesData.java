package com.jpmorgan.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SalesData {
//	private Map<String, Double> salesMap = new HashMap<String, Double>();
	private Map<String,List<SalesTrx>> salesTrxMap = new HashMap<String,List<SalesTrx>>();
	
	
	public void insertSalesTrx(String productType, double price, int quantity) {	
		List<SalesTrx> sales = salesTrxMap.get(productType);
		if(sales == null) {
			 sales = new ArrayList<SalesTrx>();
			 salesTrxMap.put(productType, sales);
		}
		sales.add(new SalesTrx(productType,price,quantity));		

	}
	
//	public void insertOrUpdateSales(String productType, double price, int quantity) {
//		Double oldSalesValue = salesMap.get(productType);
//		oldSalesValue = (oldSalesValue == null) ? new Double(0) : oldSalesValue;
//		Double newSalesValue = new Double(price * quantity);
//		salesMap.put(productType, newSalesValue + oldSalesValue);
//
//	}
	
	public List<SalesTrx>getSalesTrx(String productCode) {
		return salesTrxMap.get(productCode);
	}

//	public Map<String, Double> getSalesMap() {
//		return salesMap;
//	}

//	public void setSalesMap(Map<String, Double> salesMap) {
//		this.salesMap = salesMap;
//	}

	public Map<String, List<SalesTrx>> getSalesTrxMap() {
		return salesTrxMap;
	}

	public void setSalesTrxMap(Map<String, List<SalesTrx>> salesTrxMap) {
		this.salesTrxMap = salesTrxMap;
	}
	
	

}
