package com.rafael;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.rafael.views.LoginView;

public class Application {

	public static void main(String[] args) {
		try {
			SwingUtilities.invokeAndWait(() -> {
				LoginView loginView = new LoginView();
				loginView.setVisible(true);
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
