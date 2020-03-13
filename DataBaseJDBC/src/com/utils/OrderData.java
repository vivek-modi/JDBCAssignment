package com.utils;

public class OrderData {

	private int id, quantity, amount;
	private String CName, itemName;

	public OrderData(int id, int quantity, int amount, String cName, String itemName) {
		this.id = id;
		this.quantity = quantity;
		this.amount = amount;
		CName = cName;
		this.itemName = itemName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCName() {
		return CName;
	}

	public void setCName(String cName) {
		CName = cName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
