package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.Vendor;

public class VendorDB implements DAO<Vendor>{

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/prs";
		String username = "prs_user";
		String password = "windows";
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
	}
	
	@Override
	public Vendor get(int code) {
		String sql = "SELECT * FROM Vendor WHERE id = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, code);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String vCode = rs.getString(2);
				String name = rs.getString(3);
				String address = rs.getString(4);
				String city = rs.getString(5);
				String state = rs.getString(6);
				String zip = rs.getString(7);
				String phoneNumber = rs.getString(8);
				String email = rs.getString(9);
				boolean isPreApproved = rs.getBoolean(10);
				Vendor p = new Vendor(id, vCode, name, address, city, state, zip, phoneNumber, email, isPreApproved);
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
	public List<Vendor> getAll() {
		String sql = "SELECT * FROM Vendor";
		List<Vendor> Vendors = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String vCode = rs.getString(2);
				String name = rs.getString(3);
				String address = rs.getString(4);
				String city = rs.getString(5);
				String state = rs.getString(6);
				String zip = rs.getString(7);
				String phoneNumber = rs.getString(8);
				String email = rs.getString(9);
				boolean isPreApproved = rs.getBoolean(10);
				Vendor p = new Vendor(id, vCode, name, address, city, state, zip, phoneNumber, email, isPreApproved);
				
				Vendors.add(p);
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return Vendors;
	}

	@Override
	public boolean add(Vendor t) {
		String sql = "INSERT INTO Vendor (code, name, address, city, state, zip, phoneNumber, email, isPreApproved) VALUES (?,?,?,?,?,?,?,?,?)";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, t.getCode());
			ps.setString(2, t.getName());
			ps.setString(3, t.getAddress());
			ps.setString(4, t.getCity());
			ps.setString(5, t.getState());
			ps.setString(6, t.getZip());
			ps.setString(7, t.getPhoneNumber());
			ps.setString(8, t.getEmail());
			ps.setBoolean(9, t.isPreApproved());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(Vendor t) {
		String sql = "DELETE FROM Vendor WHERE id = ?";
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
	public boolean update(Vendor t) {
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
