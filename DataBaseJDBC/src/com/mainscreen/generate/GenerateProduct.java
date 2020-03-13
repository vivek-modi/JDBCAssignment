package com.mainscreen.generate;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mainscreen.EmployeeScreen;
import com.services.DatabaseService;
import com.utils.ProductData;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GenerateProduct {

	public JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;
	private static ArrayList<ProductData> tabledata = new ArrayList<ProductData>();
	private static JLabel lblNewLabel;
	private JButton btnNewButton_1_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateProduct window = new GenerateProduct();
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
	public GenerateProduct() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 879, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBounds(723, 283, 102, 29);
		frame.getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 38, 646, 322);
		frame.getContentPane().add(scrollPane);
		table = new JTable(
				new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name", "Type", "Price", "Qunatity" }));
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(37, 415, 197, 14);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton_1_1 = new JButton("Export");
		btnNewButton_1_1.setBounds(723, 181, 102, 29);
		frame.getContentPane().add(btnNewButton_1_1);

		btnNewButton_1_2 = new JButton("Back");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1_2.setBounds(723, 85, 102, 29);
		frame.getContentPane().add(btnNewButton_1_2);

		ViewData();

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() > -1) {
						lblNewLabel.setText((DatabaseService.DeleteItem(
								Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString())))
										? "Data Delete Successfully"
										: "Error with Deleting Data");
						ViewData();
					} else {
						lblNewLabel.setText("Please Select Row From Tabel");
					}
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
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
			tabledata = DatabaseService.ReadProductData();
			try {
				for (ProductData productData : tabledata) {
					model.addRow(new Object[] { productData.getItem_id(), productData.getItem_name(),
							productData.getItem_type(), productData.getItem_price(), productData.getItem_quantity() });
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
