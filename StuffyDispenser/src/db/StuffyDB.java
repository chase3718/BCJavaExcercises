package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import business.Stuffy;

public class StuffyDB implements DAO<Stuffy> {

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/stuffy";
		String username = "stuffy_user";
		String password = "sesame";
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
	}
	
	@Override
	public Stuffy get(int code) {
		String sql = "SELECT * FROM Stuffy WHERE id = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, code);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
//				String desc = rs.getString(2);
//				double price = rs.getDouble(3);
//				Stuffy p = new Stuffy(code, desc, price);
				String type = rs.getString(2);
				String color = rs.getString(3);
				String size = rs.getString(4);
				int limbs = rs.getInt(5);
				Stuffy p = new Stuffy(code, type, color, size, limbs);
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
	public List<Stuffy> getAll() {
		String sql = "SELECT * FROM Stuffy";
		List<Stuffy> stuffies = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				String code = rs.getString(1);
//				String desc = rs.getString(2);
//				double price = rs.getDouble(3);
				int id = rs.getInt(1);
				String type = rs.getString(2);
				String color = rs.getString(3);
				String size = rs.getString(4);
				int limbs = rs.getInt(5);
				Stuffy p = new Stuffy(id, type, color, size, limbs);
				stuffies.add(p);
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return stuffies;
	}

	@Override
	public boolean add(Stuffy t) {
		String sql = "INSERT INTO Stuffy (type, color, size, limbs) VALUES (?,?,?,?)";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
//			ps.setString(1, t.getCode());
//			ps.setString(2, t.getDescription());
//			ps.setDouble(3, t.getPrice());
			ps.setString(1, t.getType());
			ps.setString(2, t.getColor());
			ps.setString(3, t.getSize());
			ps.setInt(4, t.getLimbs());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(Stuffy t) {
		String sql = "DELETE FROM stuffy WHERE id = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, t.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean update(Stuffy t) {
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
