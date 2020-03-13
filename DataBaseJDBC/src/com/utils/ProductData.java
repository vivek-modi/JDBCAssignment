package com.utils;

public class ProductData {

	private int item_id, item_price, item_quantity;
	private String item_name, item_type;

	public ProductData(int item_id, int item_price, int item_quantity, String item_name, String item_type) {
		this.item_id = item_id;
		this.item_price = item_price;
		this.item_quantity = item_quantity;
		this.item_name = item_name;
		this.item_type = item_type;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public int getItem_quantity() {
		return item_quantity;
	}

	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

}
