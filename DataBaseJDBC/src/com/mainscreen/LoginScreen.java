package com.mainscreen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;

import com.services.DatabaseService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
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
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Stock Management");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(194, 26, 235, 31);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(156, 135, 80, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(343, 134, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(156, 210, 80, 14);
		frame.getContentPane().add(lblNewLabel_1_1);

		textField_1 = new JPasswordField();
		textField_1.setBounds(343, 209, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(350, 309, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(143, 309, 89, 23);
		frame.getContentPane().add(btnRegister);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(248, 373, 142, 14);
		frame.getContentPane().add(lblNewLabel_2);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					lblNewLabel_2.setText("Some Fields Are Missing");
				} else {
					DatabaseService databaseService = new DatabaseService();
					if (databaseService.Login(textField.getText(), textField_1.getText()) == 1) {
						new CustomerScreen().frame.setVisible(true);
					} else if (databaseService.Login(textField.getText(), textField_1.getText()) == 0) {
						new EmployeeScreen().frame.setVisible(true);
					} else {
						lblNewLabel_2.setText("Error Login");
					}
				}
			}
		});

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
	}
}
