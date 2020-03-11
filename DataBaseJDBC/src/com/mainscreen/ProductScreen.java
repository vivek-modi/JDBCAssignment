package com.mainscreen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.services.DatabaseService;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ProductScreen {

	protected JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductScreen window = new ProductScreen();
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
	public ProductScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 614, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Add product");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(248, 24, 134, 23);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ItemName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(113, 108, 94, 23);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("ItemType");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(113, 160, 94, 23);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("ItemPrice");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(113, 206, 94, 23);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("ItemQuanity");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(113, 250, 94, 23);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);

		textField = new JTextField();
		textField.setBounds(363, 111, 94, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(363, 209, 94, 20);
		frame.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(363, 253, 94, 20);
		frame.getContentPane().add(textField_3);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(161, 333, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(335, 333, 89, 23);
		frame.getContentPane().add(btnAdd);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] {  "Baked Goods", "Breads", "Dairy Products", "Eggs", "Meat" }));
		comboBox.setBounds(363, 163, 94, 20);
		frame.getContentPane().add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(233, 377, 150, 14);
		frame.getContentPane().add(lblNewLabel_2);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") 
						|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
					lblNewLabel_2.setText("Some Fields Are Empty");
				} else {
					DatabaseService databaseService = new DatabaseService();
					if (databaseService.InsertItem(textField.getText(), comboBox.getSelectedItem().toString(),
							Integer.parseInt(textField_2.getText()), Integer.parseInt(textField_3.getText()))) {
						lblNewLabel_2.setText("Product Added");
					} else {
						lblNewLabel_2.setText("Error with adding products");
					}
				}
			}
		});
	}

}
