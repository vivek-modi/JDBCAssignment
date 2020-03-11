package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.database.DbConnect;

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
			String sql = "";
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

}
