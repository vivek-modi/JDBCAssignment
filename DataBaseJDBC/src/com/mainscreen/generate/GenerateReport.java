package com.mainscreen.generate;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.services.DatabaseService;
import com.utils.ProductData;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GenerateReport {

	protected JFrame frame;
	private JTable table;
	private DefaultTableModel model;

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

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(26, 469, 150, 14);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Export");
		btnNewButton.setBounds(769, 101, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete");

		btnNewButton_1.setBounds(769, 183, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		ArrayList<ProductData> data = DatabaseService.ReadProductData();

		if (data == null) {
			lblNewLabel.setText("No Product Found");
		} else {
			model = (DefaultTableModel) table.getModel();
			for (ProductData productData : data) {
				model.addRow(new Object[] { productData.getItem_id(), productData.getItem_name(),
						productData.getItem_type(), productData.getItem_price(), productData.getItem_quantity() });
			}
		}

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

			}
		});

	}
}
