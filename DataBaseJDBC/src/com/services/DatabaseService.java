package com.services;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import com.database.DbConnect;
import com.utils.CustomerData;
import com.utils.EmployeeData;
import com.utils.OrderData;
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

	public static boolean Register_Customer(String firstName, String LastName, String Address, int number) {
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement(
					"insert into customers(customer_firstname,customer_lastname,customer_address,customer_number) values(?,?,?,?)");
			statement.setString(1, firstName);
			statement.setString(2, LastName);
			statement.setString(3, Address);
			statement.setInt(4, number);
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

	@SuppressWarnings("resource")
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

	public static boolean DeleteItem(int id) throws SQLException {
		CallableStatement callableStatement = DbConnect.getInstance().prepareCall("call deleteitem(?)");
		callableStatement.setInt(1, id);
		return (!callableStatement.execute()) ? true : false;
	}

	public static boolean UpdateItem(int id, String itemName, String itemType, int price, int qunatity) {
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement(
					"UPDATE items SET item_name=?,item_type=?,item_price=?,item_quantity=? where item_id=?");
			statement.setString(1, itemName);
			statement.setString(2, itemType);
			statement.setInt(3, price);
			statement.setInt(4, qunatity);
			statement.setInt(5, id);
			return (statement.executeUpdate() == 1) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<EmployeeData> ReadEmployeeData() {
		ArrayList<EmployeeData> arrayList = new ArrayList<>();
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement("SELECT * FROM employees_view");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				arrayList.add(new EmployeeData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6)));
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("resource")
	public static String ExportEmployee() throws IOException {
		ArrayList<EmployeeData> data = ReadEmployeeData();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("employee.csv")));

		if (data == null) {
			return "No Data Available to Generate";
		} else {
			for (EmployeeData employee : data) {
				writer.write("Employee ID: " + employee.getId() + "\nEmployee Name: " + employee.getFirstName() + " "
						+ employee.getLastName() + "\nEmployee Gender: " + employee.getGender()
						+ "\nEmployee Designation: " + employee.getDesignation() + "\nEmployee Department: "
						+ employee.getDepartment() + "\n\n");
			}
			writer.close();
			return "Import Successfully";
		}
	}

	public static boolean UpdateEmployee(int id, String firstname, String lastName, String gender, String des,
			String dep) {
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement(
					"UPDATE employees SET firstname=?,lastname=?,gender=?,designation=?,department=? where id=?");
			statement.setString(1, firstname);
			statement.setString(2, lastName);
			statement.setString(3, gender);
			statement.setString(4, des);
			statement.setString(5, dep);
			statement.setInt(6, id);
			return (statement.executeUpdate() == 1) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean DeleteEmployee(String firstName, String LastName) throws SQLException {
		CallableStatement callableStatement = DbConnect.getInstance().prepareCall("call deleteemployee(?,?)");
		callableStatement.setString(1, firstName);
		callableStatement.setString(2, LastName);
		return (!callableStatement.execute()) ? true : false;
	}

	public static ArrayList<CustomerData> ReadCustomerData() {
		ArrayList<CustomerData> arrayList = new ArrayList<>();
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement("SELECT * FROM customers_view");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				arrayList.add(new CustomerData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5)));
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String ExportCustomer() throws IOException {
		ArrayList<CustomerData> data = ReadCustomerData();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("customer.csv")));

		if (data == null) {
			return "No Data Available to Generate";
		} else {
			for (CustomerData customer : data) {
				writer.write("Customer ID: " + customer.getId() + "\nCustomer Name: " + customer.getFirstName() + " "
						+ customer.getLastName() + "\nCustomer Address: " + customer.getAddress()
						+ "\nCustomer Number: " + customer.getNumber() + "\n\n");
			}
			writer.close();
			return "Import Successfully";
		}
	}

	public static ArrayList<OrderData> ReadOrderData() {
		ArrayList<OrderData> arrayList = new ArrayList<>();
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement("SELECT * FROM orders");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				arrayList
						.add(new OrderData(rs.getInt(1), rs.getInt(4), rs.getInt(5), rs.getString(2), rs.getString(3)));
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String ExportOrder() throws IOException {
		ArrayList<OrderData> data = ReadOrderData();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("order.csv")));

		if (data == null) {
			return "No Data Available to Generate";
		} else {
			for (OrderData order : data) {
				writer.write("Order ID: " + order.getId() + "\nCustomer Name: " + order.getCName() + "\nOrder Name: "
						+ order.getItemName() + "\nOrder Quantity: " + order.getQuantity() + "\nOrder Amount: "
						+ order.getAmount() + "\n\n");
			}
			writer.close();
			return "Import Successfully";
		}
	}

	public static boolean InsertOrder(String CName, String itemName, int qunatity, int amount) {
		try {
			PreparedStatement statement = DbConnect.getInstance().prepareStatement(
					"insert into orders(customer_name,item_name,item_quantity,amount) values(?,?,?,?)");
			statement.setString(1, CName);
			statement.setString(2, itemName);
			statement.setInt(3, qunatity);
			statement.setInt(4, amount);
			return (statement.executeUpdate() == 1) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int getItemPrice(String x) throws SQLException {
		int value = 0;
		PreparedStatement statement = DbConnect.getInstance()
				.prepareStatement("SELECT item_price FROM items where item_name=?");
		statement.setString(1, x);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			value = rs.getInt(1);
		}
		return value;
	}
}
