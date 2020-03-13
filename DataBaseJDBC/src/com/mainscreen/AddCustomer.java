package com.mainscreen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.services.DatabaseService;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCustomer {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomer window = new AddCustomer();
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
	public AddCustomer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 592, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(361, 82, 96, 19);
		frame.getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(361, 131, 96, 19);
		frame.getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(361, 171, 96, 19);
		frame.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(361, 220, 96, 19);
		frame.getContentPane().add(textField_3);

		JLabel lblFirstname = new JLabel("FirstName");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstname.setBounds(150, 74, 96, 32);
		frame.getContentPane().add(lblFirstname);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(150, 125, 96, 25);
		frame.getContentPane().add(lblLastName);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(150, 161, 96, 37);
		frame.getContentPane().add(lblAddress);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoneNumber.setBounds(150, 216, 128, 25);
		frame.getContentPane().add(lblPhoneNumber);

		JLabel lblAddNewCustomer = new JLabel("Add New Customer");
		lblAddNewCustomer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddNewCustomer.setBounds(218, 25, 193, 25);
		frame.getContentPane().add(lblAddNewCustomer);

		JButton btnNewButton = new JButton("Insert");

		btnNewButton.setBounds(157, 312, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setBounds(351, 312, 89, 23);
		frame.getContentPane().add(btnBack);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(22, 374, 141, 14);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")
						|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
					lblNewLabel.setText("Some Fields Are Missing");
				} else {
					if (DatabaseService.Register_Customer(textField.getText(), textField_1.getText(),
							textField_2.getText(), Integer.parseInt(textField_3.getText()))) {
						lblNewLabel.setText("Customer Inserted");
					} else {
						lblNewLabel.setText("Error with adding customer");
					}
				}
			}
		});
	}

}
