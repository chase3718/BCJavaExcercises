package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import business.Product;

public class ProductDB implements DAO<Product> {

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/mma";
		String username = "mma_user";
		String password = "sesame";
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
	}

	@Override
	public Product get(String code) {
		String sql = "SELECT Code, Description, Price FROM Product WHERE Code = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, code);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String desc = rs.getString(2);
				double price = rs.getDouble(3);
				Product p = new Product(code, desc, price);
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
	public List<Product> getAll() {
		String sql = "SELECT Code, Description, Price FROM Product";
		List<Product> products = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String code = rs.getString(1);
				String desc = rs.getString(2);
				double price = rs.getDouble(3);

				Product p = new Product(code, desc, price);
				products.add(p);
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return products;
	}

	@Override
	public boolean add(Product t) {
		String sql = "INSERT INTO Product (Code, Description, Price) VALUES (?,?,?)";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, t.getCode());
			ps.setString(2, t.getDescription());
			ps.setDouble(3, t.getPrice());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(Product t) {
		String sql = "DELETE FROM Product WHERE Code = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, t.getCode());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean update(Product t) {
		String sql = "UPDATE Product SET Description = ?, Price = ? WHERE Code = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, t.getDescription());
			ps.setDouble(2, t.getPrice());
			ps.setString(3, t.getCode());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

}
