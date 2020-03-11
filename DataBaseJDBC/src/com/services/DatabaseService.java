package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}
