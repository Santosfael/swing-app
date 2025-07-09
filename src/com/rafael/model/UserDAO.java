package com.rafael.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rafael.utils.DatabaseConnection;

public class UserDAO {

	public User validateLogin(String email, String password) {
		String sql = "SELECT id, name, password FROM users WHERE email = ? AND password = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			conn = DatabaseConnection.getConnection();
			if (conn == null) {
				System.err.println("Erro: Não foi possível obter a conexão com o banco de dados");
				return null;
			}
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new User(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password")
				);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao vlidar login: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConnection(conn);
			
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				System.err.println("Erro ao fechar os recursos: " + e.getMessage());
			}
		}
		
		return user;
	}
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT id, name, email FROM users ORDER BY name";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseConnection.getConnection();
			if (conn == null) {
				System.out.println("Erro: Não foi possível obter a conexão com o banco de daodos");
				return null;
			}
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				User user = new User(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						null
				);
				
				users.add(user);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar todos os usuários: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConnection(conn);
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				System.err.println("Erro ao fechar os recursos: " + e.getMessage());
			}
		}
		return users;
	}
}
