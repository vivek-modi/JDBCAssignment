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
import javax.swing.JButton;
import javax.swing.JLabel;

public class CustomerScreen {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerScreen window = new CustomerScreen();
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
	public CustomerScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 579, 349);
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

		JButton btnNewButton = new JButton("Add Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddCustomer().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(101, 189, 128, 37);
		frame.getContentPane().add(btnNewButton);

		JButton btnAddOrder = new JButton("Add Order");
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddOrder().frame.setVisible(true);
				frame.dispose();
			}
		});
		btnAddOrder.setBounds(354, 189, 128, 37);
		frame.getContentPane().add(btnAddOrder);

		JLabel lblNewLabel = new JLabel("Welcome To Stock Management");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(169, 51, 249, 37);
		frame.getContentPane().add(lblNewLabel);
	}

}
