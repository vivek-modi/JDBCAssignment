package com.mainscreen.generate;

import java.awt.EventQueue;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.database.DbConnect;
import com.mainscreen.EmployeeScreen;
import com.services.DatabaseService;
import com.utils.ProductData;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GenerateProduct {

	public JFrame frame;
	private static JTable table;
	private static DefaultTableModel model;
	private static ArrayList<ProductData> tabledata = new ArrayList<ProductData>();
	private static JLabel lblNewLabel;
	private JButton btnNewButton_1_2;
	private JLabel lblNewLabel_1;
	private JTextField name;
	private JTextField type;
	private JTextField price;
	private JTextField quantity;
	private JButton btnNewButton;
	private int rowId;

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
		table = new JTable(
				new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name", "Type", "Price", "Qunatity" }));
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(36, 269, 197, 14);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton_1_1 = new JButton("Export");
		btnNewButton_1_1.setBounds(705, 126, 102, 29);
		frame.getContentPane().add(btnNewButton_1_1);

		btnNewButton_1_2 = new JButton("Back");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1_2.setBounds(705, 51, 102, 29);
		frame.getContentPane().add(btnNewButton_1_2);

		lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(52, 331, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		name = new JTextField();
		name.setBounds(147, 328, 86, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Type");
		lblNewLabel_1_1.setBounds(355, 331, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1);

		type = new JTextField();
		type.setColumns(10);
		type.setBounds(449, 328, 86, 20);
		frame.getContentPane().add(type);

		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setBounds(52, 393, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		price = new JTextField();
		price.setColumns(10);
		price.setBounds(147, 390, 86, 20);
		frame.getContentPane().add(price);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Qunatity");
		lblNewLabel_1_1_1_1.setBounds(355, 393, 61, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);

		quantity = new JTextField();
		quantity.setColumns(10);
		quantity.setBounds(449, 390, 86, 20);
		frame.getContentPane().add(quantity);

		btnNewButton = new JButton("Update");

		btnNewButton.setBounds(705, 349, 102, 29);
		frame.getContentPane().add(btnNewButton);

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
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				if (row >= 0) {
					rowId = Integer.parseInt(model.getValueAt(row, 0).toString());
					name.setText(model.getValueAt(row, 1).toString());
					type.setText(model.getValueAt(row, 2).toString());
					price.setText(model.getValueAt(row, 3).toString());
					quantity.setText(model.getValueAt(row, 4).toString());
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (name.getText().equals("") || price.getText().equals("") || quantity.getText().equals("")
						|| type.getText().equals("")) {
					lblNewLabel.setText("Please Select Row From Tabel");
				} else {
					lblNewLabel.setText((DatabaseService.UpdateItem(rowId, name.getText(), type.getText(),
							Integer.parseInt(price.getText()), Integer.parseInt(quantity.getText()))) ? "Update Successfully" : "Not Successfully");
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
