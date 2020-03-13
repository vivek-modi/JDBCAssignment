package com.mainscreen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.database.DbConnect;
import com.services.DatabaseService;
import com.utils.CustomerData;
import com.utils.ProductData;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AddOrder {

	public JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOrder window = new AddOrder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddOrder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 608, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Add order");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(272, 37, 113, 22);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(158, 116, 113, 22);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Item Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(158, 181, 113, 22);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(158, 249, 113, 22);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(393, 119, 127, 20);
		frame.getContentPane().add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(393, 184, 127, 20);
		frame.getContentPane().add(comboBox_1);

		textField = new JTextField();
		textField.setBounds(393, 252, 127, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Add Order");
		btnNewButton.setBounds(183, 333, 106, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(371, 333, 89, 23);
		frame.getContentPane().add(btnBack);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(34, 357, 127, 14);
		frame.getContentPane().add(lblNewLabel_2);

		ArrayList<CustomerData> customerData = DatabaseService.ReadCustomerData();
		if (customerData != null) {
			ArrayList<String> name = new ArrayList<>();
			for (CustomerData customerData2 : customerData) {
				name.add(customerData2.getFirstName());
			}
			String[] type = new String[name.size()];
			comboBox.setModel(new DefaultComboBoxModel<>(name.toArray(type)));

		} else {
			lblNewLabel_2.setText("No Customer Available");
		}

		ArrayList<ProductData> orderData = DatabaseService.ReadProductData();
		if (orderData != null) {
			ArrayList<String> name = new ArrayList<>();
			for (ProductData productData : orderData) {
				name.add(productData.getItem_name());
			}
			String[] type = new String[name.size()];
			comboBox_1.setModel(new DefaultComboBoxModel<>(name.toArray(type)));

		} else {
			lblNewLabel_2.setText("No Order name Available");
		}

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerScreen().frame.setVisible(true);
				frame.dispose();
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().equals("")) {
					lblNewLabel_2.setText("Fields are Empty");
				} else {

					try {
						int itemPrice = DatabaseService.getItemPrice(comboBox_1.getSelectedItem().toString());
						int quantity = Integer.parseInt(textField.getText());
						lblNewLabel_2.setText(DatabaseService.InsertOrder(comboBox.getSelectedItem().toString(),
								comboBox_1.getSelectedItem().toString(), quantity, itemPrice * quantity) ? "Order Added"
										: "Error with Adding Order");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}

}
