package com.rafael.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql//location:3306/db_swing_app?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "1234567";
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao conectar ao banco de dados: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Driver JDBC não encontrado: " + e.getMessage());
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Conexão com o banco de dados fechada!");
			} catch (SQLException e) {
				System.err.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		}
	}
}
