package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.User;

public class UserDB implements DAO<User>{

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/prs";
		String username = "prs_user";
		String password = "windows";
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
	}
	
	@Override
	public User get(int code) {
		String sql = "SELECT * FROM User WHERE id = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, code);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String phoneNumber = rs.getString(6);
				String email = rs.getString(7);
				boolean isReviewer = rs.getBoolean(8);
				boolean isAdmin = rs.getBoolean(9);
				User p = new User(id, userName, password, firstName, lastName, phoneNumber, email, isReviewer, isAdmin);
				rs.close();
				return p;
			} else {
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public List<User> getAll() {
		String sql = "SELECT * FROM User";
		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String phoneNumber = rs.getString(6);
				String email = rs.getString(7);
				boolean isReviewer = rs.getBoolean(8);
				boolean isAdmin = rs.getBoolean(9);
				User p = new User(id, userName, password, firstName, lastName, phoneNumber, email, isReviewer, isAdmin);
				
				users.add(p);
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return users;
	}

	@Override
	public boolean add(User t) {
		String sql = "INSERT INTO User (username, password, firstname, lastname, phonenumber, email, isReviewer, isAdmin) VALUES (?,?,?,?,?,?,?,?)";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getPhoneNumber());
			ps.setString(6, t.getEmail());
			ps.setBoolean(7, t.isReviewer());
			ps.setBoolean(8, t.isAdmin());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(User t) {
		String sql = "DELETE FROM User WHERE id = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, t.getID());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean update(User t) {
//		String sql = "UPDATE Product SET Description = ?, Price = ? WHERE Code = ?";
//		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
//			ps.setString(1, t.getDescription());
//			ps.setDouble(2, t.getPrice());
//			ps.setString(3, t.getCode());
//			ps.executeUpdate();
//			return true;
//		} catch (SQLException e) {
//			System.err.println(e);
//			return false;
//		}
		return false;
	}
	
}
