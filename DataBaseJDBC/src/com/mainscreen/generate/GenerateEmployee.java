package com.mainscreen.generate;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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

public class GenerateEmployee {

	public JFrame frame;
	private static JTable table;
	private static ArrayList<EmployeeData> tabledata = new ArrayList<EmployeeData>();
	private static JLabel lblNewLabel;
	private static DefaultTableModel model;

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
					lblNewLabel.setText(DatabaseService.ExportProduct());
				} catch (IOException e1) {
					e1.printStackTrace();
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
							employeeData.getDesignation() });
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
