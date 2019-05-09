package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import business.PurchaseRequestLineItem;

public class PurchaseRequestLineItemDB implements DAO<PurchaseRequestLineItem> {

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/prs";
		String username = "prs_user";
		String password = "windows";
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
	}

	@Override
	public PurchaseRequestLineItem get(int code) {
		String sql = "SELECT * FROM PurchaseRequestLineItem WHERE id = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, code);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt(1);
				int purchaseRequestID = rs.getInt(2);
				int productID = rs.getInt(3);
				int quantity = rs.getInt(4);
				PurchaseRequestLineItem p = new PurchaseRequestLineItem(ID, purchaseRequestID, productID, quantity);
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
	public List<PurchaseRequestLineItem> getAll() {
		String sql = "SELECT * FROM PurchaseRequestLineItem";
		List<PurchaseRequestLineItem> PurchaseRequestLineItems = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt(1);
				int purchaseRequestID = rs.getInt(2);
				int productID = rs.getInt(3);
				int quantity = rs.getInt(4);
				PurchaseRequestLineItem p = new PurchaseRequestLineItem(ID, purchaseRequestID, productID, quantity);
				PurchaseRequestLineItems.add(p);
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return PurchaseRequestLineItems;
	}

	@Override
	public boolean add(PurchaseRequestLineItem t) {
		String sql = "INSERT INTO PurchaseRequestLineItem (purchaseRequestID, productID, quantity) VALUES (?,?,?)";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, t.getPurchaseRequestID());
			ps.setInt(2, t.getProductID());
			ps.setInt(3, t.getQuantity());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(PurchaseRequestLineItem t) {
		String sql = "DELETE FROM PurchaseRequestLineItem WHERE id = ?";
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
	public boolean update(PurchaseRequestLineItem t) {
//		String sql = "UPDATE PurchaseRequestLineItem SET Description = ?, Price = ? WHERE Code = ?";
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
