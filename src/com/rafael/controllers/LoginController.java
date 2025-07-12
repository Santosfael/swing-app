package com.rafael.controllers;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.rafael.model.User;
import com.rafael.model.UserDAO;
import com.rafael.views.DashboardView;
import com.rafael.views.LoginView;

public class LoginController {

	private LoginView view;
	private UserDAO model;
	
	public LoginController(LoginView view) {
		this.view = view;
		this.model = new UserDAO();
	}
	
	public void handleLogin() {
		String email = view.getEmail();
		String password = view.getPassword();
		
		if (email.isEmpty() || password.isEmpty()) {
			view.showMessage("Por favor, preencha todos os campos.",
							"Erro de login",
							JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		User user = model.validateLogin(email, password);
		
		if (user != null) {
			view.showMessage(
					"Login bem-sucedido! Bem-vindo, " + user.getName() + "!",
					"Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			view.dispose();
			
			SwingUtilities.invokeLater(() -> {
				DashboardView dashboardView  = new DashboardView();
				dashboardView.setVisible(true);
			});
		} else {
			view.showMessage("E-mail ou senha incorretos.",
					"Erro de Login",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
