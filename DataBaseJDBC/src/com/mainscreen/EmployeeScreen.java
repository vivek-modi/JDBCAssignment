package com.mainscreen;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.mainscreen.generate.GenerateCustomer;
import com.mainscreen.generate.GenerateEmployee;
import com.mainscreen.generate.GenerateProduct;

import javax.swing.JButton;

public class EmployeeScreen {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeScreen window = new EmployeeScreen();
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
	public EmployeeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuBar.setBounds(0, 0, 91, 26);
		frame.getContentPane().add(menuBar);

		JMenu mnOptions = new JMenu("Options");
		mnOptions.setForeground(Color.BLACK);
		mnOptions.setFont(new Font("Arial Black", Font.BOLD, 18));
		menuBar.add(mnOptions);

		JMenuItem menu_reset = new JMenuItem("Logout");
		menu_reset.setFont(new Font("Arial", Font.BOLD, 11));
		mnOptions.add(menu_reset);
		menu_reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginScreen().frame.setVisible(true);
				frame.dispose();
			}
		});

		JMenuItem menu_exit = new JMenuItem("EXIT");
		menu_exit.setFont(new Font("Arial", Font.BOLD, 11));
		mnOptions.add(menu_exit);

		JButton btnNewButton = new JButton("Product");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProductScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(62, 93, 139, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnEmployee = new JButton("Employee");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEmployeeScreen().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnEmployee.setBounds(248, 93, 148, 23);
		frame.getContentPane().add(btnEmployee);

		JButton btnNewButton_1 = new JButton("Generate product");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GenerateProduct().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(62, 164, 139, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Generate Employee");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GenerateEmployee().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1_1.setBounds(248, 164, 148, 23);
		frame.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_1_1 = new JButton("Generate Customer");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GenerateCustomer().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1_1_1.setBounds(147, 236, 161, 23);
		frame.getContentPane().add(btnNewButton_1_1_1);
		menu_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

}
