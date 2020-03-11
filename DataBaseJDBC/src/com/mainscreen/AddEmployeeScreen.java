package com.mainscreen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.services.DatabaseService;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEmployeeScreen {

	protected JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployeeScreen window = new AddEmployeeScreen();
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
	public AddEmployeeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 586, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Add Employee");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(231, 27, 168, 38);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("FirstName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(141, 117, 73, 20);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("LastName");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(141, 167, 73, 20);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Gender");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(141, 213, 73, 20);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Designation");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(141, 260, 88, 20);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Department");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1.setBounds(141, 306, 88, 20);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);

		textField = new JTextField();
		textField.setBounds(348, 119, 102, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(348, 169, 102, 20);
		frame.getContentPane().add(textField_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female" }));
		comboBox.setBounds(348, 215, 102, 20);
		frame.getContentPane().add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Executive Chef", "Bartender" }));
		comboBox_1.setBounds(348, 262, 102, 20);
		frame.getContentPane().add(comboBox_1);

		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1
				.setModel(new DefaultComboBoxModel(new String[] { "Management", "Chef", "Inventory", "Maintenance" }));
		comboBox_1_1.setBounds(348, 308, 102, 20);
		frame.getContentPane().add(comboBox_1_1);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(141, 366, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnAddEmployee = new JButton("Add Employee");

		btnAddEmployee.setBounds(322, 366, 120, 23);
		frame.getContentPane().add(btnAddEmployee);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(231, 407, 120, 14);
		frame.getContentPane().add(lblNewLabel_2);

		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					lblNewLabel_2.setText("Some Fields Are Missing");
				} else {
					DatabaseService databaseService = new DatabaseService();
					if (databaseService.InsertEmployee(textField.getText(), textField_1.getText(),
							comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(),
							comboBox_1_1.getSelectedItem().toString())) {
						lblNewLabel_2.setText("Employee Inserted");
					} else {
						lblNewLabel_2.setText("Error with adding employess");
					}
				}

			}
		});
	}
}
