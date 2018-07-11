package com.jpmorgan.data;

public class SalesTrx {
	private String productType;
	private Double price;
	private Integer qty;
	
	
	public SalesTrx() {
		super();
		
	}
	public SalesTrx(String productType, Double price, Integer qty) {
		super();
		this.productType = productType;
		this.price = price;
		this.qty = qty;
	}
	
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append(this.productType);
		sbf.append("|");
		sbf.append(this.price);
		sbf.append("|");
		sbf.append(this.qty);
		return sbf.toString();
	}
	
	
	
	
	
	
}
