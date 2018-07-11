package com.jpmorgan.data;

public class Product {
	private String productCode;
	private String productName;

	public Product() {
		super();
	}

	public Product(String productCode, String productName) {
		super();
		this.productCode = productCode;
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if (obj instanceof Product) {
			Product prod = (Product) obj;
			return this.productCode.equals(prod.getProductCode());
		}
		return false;
	}

}
