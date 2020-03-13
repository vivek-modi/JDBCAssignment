package com.mainscreen.generate;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mainscreen.EmployeeScreen;
import com.services.DatabaseService;
import com.utils.EmployeeData;
import com.utils.ProductData;
import javax.swing.JComboBox;

public class GenerateEmployee {

	public JFrame frame;
	private static JTable table;
	private static ArrayList<EmployeeData> tabledata = new ArrayList<EmployeeData>();
	private static JLabel lblNewLabel;
	private static DefaultTableModel model;
	private JTextField textField;
	private JTextField textField_1;
	private int rowId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateEmployee window = new GenerateEmployee();
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
	public GenerateEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 879, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBounds(705, 204, 102, 29);
		frame.getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 25, 631, 215);
		frame.getContentPane().add(scrollPane);
		table = new JTable(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Firstname", "Lastname", "Gender", "Designation", "Department" }));
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(36, 269, 197, 14);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton_1_1 = new JButton("Export");
		btnNewButton_1_1.setBounds(705, 126, 102, 29);
		frame.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("Back");
		btnNewButton_1_2.setBounds(705, 51, 102, 29);
		frame.getContentPane().add(btnNewButton_1_2);

		JLabel lblNewLabel_1 = new JLabel("FirstName");
		lblNewLabel_1.setBounds(70, 320, 78, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(179, 317, 117, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("LastName");
		lblNewLabel_1_1.setBounds(352, 320, 78, 14);
		frame.getContentPane().add(lblNewLabel_1_1);

		textField_1 = new JTextField();
		textField_1.setBounds(496, 317, 118, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Gender");
		lblNewLabel_1_1_1.setBounds(70, 381, 78, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JComboBox comboBox = new JComboBox(new DefaultComboBoxModel(new String[] { "Male", "Female" }));
		comboBox.setBounds(178, 378, 118, 20);
		frame.getContentPane().add(comboBox);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Designation");
		lblNewLabel_1_1_1_1.setBounds(352, 381, 78, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);

		JComboBox comboBox_1 = new JComboBox(new DefaultComboBoxModel(new String[] { "Executive Chef", "Bartender" }));
		comboBox_1.setBounds(495, 378, 119, 20);
		frame.getContentPane().add(comboBox_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Department");
		lblNewLabel_1_1_1_1_1.setBounds(70, 444, 78, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);

		JComboBox comboBox_1_1 = new JComboBox(
				new DefaultComboBoxModel(new String[] { "Management", "Chef", "Inventory", "Maintenance" }));
		comboBox_1_1.setBounds(178, 441, 118, 20);
		frame.getContentPane().add(comboBox_1_1);

		JButton btnNewButton_1_3 = new JButton("Update");
		btnNewButton_1_3.setBounds(705, 348, 102, 29);
		frame.getContentPane().add(btnNewButton_1_3);

		ViewData();
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeScreen().frame.setVisible(true);
				frame.dispose();
			}
		});

		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblNewLabel.setText(DatabaseService.ExportEmployee());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				if (row >= 0) {
					rowId = Integer.parseInt(model.getValueAt(row, 0).toString());
					textField.setText(model.getValueAt(row, 1).toString());
					textField_1.setText(model.getValueAt(row, 2).toString());
					comboBox.setSelectedItem(model.getValueAt(row, 3).toString());
					comboBox_1.setSelectedItem(model.getValueAt(row, 4).toString());
					comboBox_1_1.setSelectedItem(model.getValueAt(row, 5).toString());
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (table.getSelectedRow() > -1) {
						lblNewLabel.setText((DatabaseService.DeleteEmployee(
								table.getModel().getValueAt(table.getSelectedRow(), 1).toString(),
								table.getModel().getValueAt(table.getSelectedRow(), 2).toString()))
										? "Data Delete Successfully"
										: "Error with Deleting Data");
						ViewData();
						textField.setText("");
						textField_1.setText("");
					} else {
						lblNewLabel.setText("Please Select Row From Tabel");
					}
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					lblNewLabel.setText("Please Select Row From Tabel");
				} else {
					lblNewLabel
							.setText((DatabaseService.UpdateEmployee(rowId, textField.getText(), textField_1.getText(),
									comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(),
									comboBox_1_1.getSelectedItem().toString())) ? "Update Successfully"
											: "Not Successfully");
					ViewData();
				}
			}
		});
	}

	public static void ViewData() {
		if (tabledata == null) {
			lblNewLabel.setText("No Product Found");
		} else {
			model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			tabledata = DatabaseService.ReadEmployeeData();
			try {
				for (EmployeeData employeeData : tabledata) {
					model.addRow(new Object[] { employeeData.getId(), employeeData.getFirstName(),
							employeeData.getLastName(), employeeData.getGender(), employeeData.getDesignation(),
							employeeData.getDepartment() });
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
