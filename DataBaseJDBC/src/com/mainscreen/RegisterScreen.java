package com.mainscreen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.services.DatabaseService;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterScreen {

	protected JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen window = new RegisterScreen();
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
	public RegisterScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 577, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Register ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(240, 21, 152, 40);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(139, 99, 93, 22);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(346, 103, 99, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(139, 157, 93, 22);
		frame.getContentPane().add(lblNewLabel_1_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(346, 161, 99, 20);
		frame.getContentPane().add(passwordField);

		JLabel lblNewLabel_1_1_1 = new JLabel("Type");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(139, 220, 93, 22);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Employee", "Customer" }));
		comboBox.setBounds(346, 224, 99, 20);
		frame.getContentPane().add(comboBox);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(143, 306, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(346, 306, 89, 23);
		frame.getContentPane().add(btnRegister);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(223, 370, 158, 14);
		frame.getContentPane().add(lblNewLabel_2);

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().equals("") || passwordField.getText().equals("")) {
					lblNewLabel_2.setText("Some Fields Are Missing");
				} else {
					DatabaseService service = new DatabaseService();
					service.Register_user(textField.getText(), passwordField.getText(), comboBox.getSelectedIndex());
					new LoginScreen().frame.setVisible(true);
					frame.dispose();
				}
			}
		});
	}
}
