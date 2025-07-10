package com.rafael.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rafael.controllers.LoginController;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField emailField;
	private JPasswordField passwordField;
	private JButton loginButton;
	
	private LoginController controller;
	
	public LoginView() {
		setTitle("Login do Usuário");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.controller = new LoginController(this);
		SceneLogin();
	}
	
	private void SceneLogin() {
		setLayout(new BorderLayout(10, 10));
		JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
		
		inputPanel.add(new JLabel("E-mail"));
		emailField = new JTextField(20);
		inputPanel.add(emailField);
		
		inputPanel.add(new JLabel("Senha:"));
		passwordField = new JPasswordField(20);
		inputPanel.add(passwordField);
		
		add(inputPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		loginButton = new JButton("Login");
		buttonPanel.add(loginButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleLogin();
				
			}
		});
	}
	
	public String getEmail() {
		return emailField.getText();
	}
	
	public String getPassword() {
		return new String(passwordField.getPassword());
	}
	
	public void showMessage(String message, String title, int messageType) {
		JOptionPane.showMessageDialog(this, message, title, messageType);
	}
}
