package com.rafael.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.rafael.controllers.DashboardController;
import com.rafael.model.User;

public class DashboardView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTable usersTable;
	private DefaultTableModel tableModel;
	private DashboardController controller;
	
	public DashboardView() {
		this.controller = new DashboardController(this);
		sceneDashboardView();
	}
	
	private void sceneDashboardView() {
		setTitle("Dashboard - List de usuários");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		String[] columnsName = {"ID", "Nome", "E-mail"};
		
		tableModel = new DefaultTableModel(columnsName, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		usersTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(usersTable);
		add(scrollPane, BorderLayout.CENTER);
		
		JButton refreshUserButton = new JButton("Atualizar Usuários");
		refreshUserButton.setPreferredSize(new Dimension(100, 45));
		
		refreshUserButton.addActionListener(e -> controller.loadUsers());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(refreshUserButton);
		add(buttonPanel, BorderLayout.NORTH);
		
		controller.loadUsers();
	}
	
	public void displayUser(List<User> users) {
		tableModel.setRowCount(0);
		
		for (User user : users) {
			Object[] row = {user.getId(), user.getName(), user.getEmail()};
			tableModel.addRow(row);
		}
	}
	
	public void showMessage(String message, String title, int messageType) {
		JOptionPane.showMessageDialog(this, message, title, messageType);
	}
}
