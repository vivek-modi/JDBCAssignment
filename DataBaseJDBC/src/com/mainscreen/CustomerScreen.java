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
		frame.setBounds(100, 100, 579, 468);
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
		btnNewButton.setBounds(100, 151, 128, 37);
		frame.getContentPane().add(btnNewButton);
	}

}
