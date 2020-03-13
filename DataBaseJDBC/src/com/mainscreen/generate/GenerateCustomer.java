package com.mainscreen.generate;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mainscreen.EmployeeScreen;
import com.services.DatabaseService;
import com.utils.CustomerData;
import com.utils.ProductData;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GenerateCustomer {

	public JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;
	private static ArrayList<CustomerData> tabledata = new ArrayList<CustomerData>();
	private static JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnGenerateOrder;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateCustomer window = new GenerateCustomer();
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
	public GenerateCustomer() {
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 25, 631, 215);
		frame.getContentPane().add(scrollPane);
		table = new JTable(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "FirstName", "LastName", "Address", "Number" }));
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(42, 262, 233, 14);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("Export  Customer");
		btnNewButton.setBounds(42, 390, 145, 23);
		frame.getContentPane().add(btnNewButton);

		btnGenerateOrder = new JButton("Export Order");

		btnGenerateOrder.setBounds(254, 390, 145, 23);
		frame.getContentPane().add(btnGenerateOrder);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setBounds(42, 312, 78, 23);
		frame.getContentPane().add(btnBack);
		ViewData();

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblNewLabel.setText(DatabaseService.ExportCustomer());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnGenerateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblNewLabel.setText(DatabaseService.ExportOrder());
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
			tabledata = DatabaseService.ReadCustomerData();
			try {
				for (CustomerData customerData : tabledata) {
					model.addRow(new Object[] { customerData.getId(), customerData.getFirstName(),
							customerData.getLastName(), customerData.getAddress(), customerData.getNumber() });
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
