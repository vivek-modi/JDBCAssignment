package com.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.database.DbConnect;
import com.utils.ProductData;

public class DatabaseService {

	public static boolean Register_user(String username, String password, int type) {
		try {
			PreparedStatement statement = DbConnect.getInstance()
					.prepareStatement("insert into login(username,password,type) values(?,?,?)");
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setInt(3, type);
			return (statement.executeUpdate() > 0) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int Login(String username, String password) {
		int num = 2;
		try {
			PreparedStatement statement = DbConnect.getInstance()
					.prepareStatement("SELECT * FROM login where username=? AND password=?");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				if (set.getString(1).equals(username) && set.getString(2).equals(password)) {
					num = (Integer.parseInt(set.getString(3)) == 1) ? 1 : 0;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	public static boolean InsertEmployee(String fname, String lname, String gender, String designation,
			String department) {
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement(
					"insert into employees(firstname,lastname,gender,designation,department) values(?,?,?,?,?)");
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setString(3, gender);
			statement.setString(4, designation);
			statement.setString(5, department);
			return (statement.executeUpdate() == 1) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean InsertItem(String itemName, String itemType, int price, int qunatity) {
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement(
					"insert into items(item_name,item_type,item_price,item_quantity) values(?,?,?,?)");
			statement.setString(1, itemName);
			statement.setString(2, itemType);
			statement.setInt(3, price);
			statement.setInt(4, qunatity);
			return (statement.executeUpdate() == 1) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<ProductData> ReadProductData() {
		ArrayList<ProductData> arrayList = new ArrayList<>();
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement("SELECT * FROM items");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				arrayList.add(
						new ProductData(rs.getInt(1), rs.getInt(4), rs.getInt(5), rs.getString(2), rs.getString(3)));
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String ExportProduct() throws IOException {
		ArrayList<ProductData> data = ReadProductData();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("items.csv")));

		if (data == null) {
			return "No Data Available to Generate";
		} else {
			for (ProductData productData : data) {
				writer.write("Item ID: " + productData.getItem_id() + "\nItem Name: " + productData.getItem_name()
						+ "\nItem Type: " + productData.getItem_type() + "\nItem Price: " + productData.getItem_price()
						+ "\nItem Quantity: " + productData.getItem_quantity() + "\n\n");

			}
			writer.close();
			return "Import Successfully";
		}

	}
}
