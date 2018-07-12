package com.jpmorgan.data;

public class SalesAdjustment {
	private String operation;
	private String productType;
	private double value;

	public SalesAdjustment() {
		super();
	}

	public SalesAdjustment(String operation, String productType, double value) {
		super();
		this.operation = operation;
		this.productType = productType;
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SalesAdjustment [operation=" + operation + ", value=" + value + "]";
	}
	
	

}
