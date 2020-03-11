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

//	public static String[] getItemTypes() throws SQLException {
//
//		PreparedStatement statement = DbConnect.getInstance().prepareStatement("SELECT DISTINCT item_type FROM items");
//		ResultSet rs = statement.executeQuery();
//		ArrayList<String> ItemTypes = new ArrayList<String>();
//		while (rs.next()) {
//			ItemTypes.add(rs.getString(1));
//		}
//		String[] type = new String[ItemTypes.size()];
//		return type = ItemTypes.toArray(type);
//	}

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
