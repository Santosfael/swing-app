package com.rafael.controllers;

import java.util.List;

import javax.swing.JOptionPane;

import com.rafael.model.User;
import com.rafael.model.UserDAO;
import com.rafael.views.DashboardView;

public class DashboardController {

	private DashboardView view;
	private UserDAO model;
	
	public DashboardController(DashboardView view) {
		this.view = view;
		this.model = new UserDAO();
	}
	
	public void loadUsers() {
		List<User> users = model.getAllUsers();
		
		if(users != null && !users.isEmpty()) {
			view.displayUser(users);
		} else {
			view.displayUser(new java.util.ArrayList<User>());
			view.showMessage("Nenhum usuário encontrado ou erro ao carregar usuários.",
					"Informação",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
