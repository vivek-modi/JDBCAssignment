package com.mainscreen.generate;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.mainscreen.EmployeeScreen;
import com.services.DatabaseService;
import com.utils.ProductData;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GenerateReport {

	public JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;
	private static ArrayList<ProductData> data;
	private static JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateReport window = new GenerateReport();
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
	public GenerateReport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 908, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 36, 690, 247);
		frame.getContentPane().add(scrollPane);

		table = new JTable(25, 25);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name", "Type", "Price", "Qunatity" }));
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(26, 469, 150, 14);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Export");
		btnNewButton.setBounds(769, 158, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete");

		btnNewButton_1.setBounds(769, 260, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(769, 72, 89, 23);
		frame.getContentPane().add(btnNewButton_2);

		data = DatabaseService.ReadProductData();
		DisplayData();

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblNewLabel.setText(DatabaseService.ExportProduct());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (table.getSelectedRow() > -1) {
						lblNewLabel.setText((DatabaseService.DeleteItem(
								Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString())))
										? "Data Delete Successfully"
										: "Error with Deleting Data");
						DisplayData();
					} else {
						lblNewLabel.setText("Please Select Row From Tabel");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

	}

	public void DisplayData() {
		if (data == null) {
			lblNewLabel.setText("No Product Found");
		} else {
			model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for (ProductData productData : data) {
				Object[] data = { productData.getItem_id(), productData.getItem_name(), productData.getItem_type(),
						productData.getItem_price(), productData.getItem_quantity() };
				model.addRow(data);
			}
		}
	}
}
