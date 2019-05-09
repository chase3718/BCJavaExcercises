package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.Product;

public class ProductDB implements DAO<Product>{

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/prs";
		String username = "prs_user";
		String password = "windows";
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
	}
	
	@Override
	public Product get(int code) {
		String sql = "SELECT * FROM Product WHERE id = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, code);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt(1);
			    int vendorID = rs.getInt(2);
			    String partNumber = rs.getString(3);
			    String name = rs.getString(4);
			    double price = rs.getDouble(5);
			    String unit = rs.getString(6);
			    String photoPath = rs.getString(7);
				Product p = new Product(ID, vendorID, partNumber, name, price, unit, photoPath);
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
		String sql = "SELECT * FROM Product";
		List<Product> Products = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt(1);
			    int vendorID = rs.getInt(2);
			    String partNumber = rs.getString(3);
			    String name = rs.getString(4);
			    double price = rs.getDouble(5);
			    String unit = rs.getString(6);
			    String photoPath = rs.getString(7);
				Product p = new Product(ID, vendorID, partNumber, name, price, unit, photoPath);
				
				Products.add(p);
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return Products;
	}

	@Override
	public boolean add(Product t) {
		String sql = "INSERT INTO Product (vendorID, partNumber, name, price, unit, photoPath) VALUES (?,?,?,?,?,?)";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, t.getVendorID());
			ps.setString(2, t.getPartNumber());
			ps.setString(3, t.getName());
			ps.setDouble(4, t.getPrice());
			ps.setString(5, t.getUnit());
			ps.setString(6, t.getPhotoPath());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(Product t) {
		String sql = "DELETE FROM Product WHERE id = ?";
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
	public boolean update(Product t) {
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
