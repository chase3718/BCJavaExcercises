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

import business.PurchaseRequest;

public class PurchaseRequestDB implements DAO<PurchaseRequest> {

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/prs";
		String username = "prs_user";
		String password = "windows";
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
	}

	@Override
	public PurchaseRequest get(int code) {
		String sql = "SELECT * FROM PurchaseRequest WHERE id = ?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, code);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt(1);
				int userID = rs.getInt(2);
				String description = rs.getString(3);
				String justification = rs.getString(4);
				LocalDate dateNeeded = rs.getDate(5).toLocalDate();
				String deliveryMode = rs.getString(6);
				String status = rs.getString(7);
				double total = rs.getDouble(8);
				LocalDate submittedDate = rs.getDate(9).toLocalDate();
				String reasonForRejection = rs.getString(10);
				PurchaseRequest p = new PurchaseRequest(ID, userID, description, justification, dateNeeded,
						deliveryMode, status, total, submittedDate, reasonForRejection);
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
	public List<PurchaseRequest> getAll() {
		String sql = "SELECT * FROM PurchaseRequest";
		List<PurchaseRequest> PurchaseRequests = new ArrayList<>();
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt(1);
				int userID = rs.getInt(2);
				String description = rs.getString(3);
				String justification = rs.getString(4);
				LocalDate dateNeeded = rs.getDate(5).toLocalDate();
				String deliveryMode = rs.getString(6);
				String status = rs.getString(7);
				double total = rs.getDouble(8);
				LocalDate submittedDate = rs.getDate(9).toLocalDate();
				String reasonForRejection = rs.getString(10);
				PurchaseRequest p = new PurchaseRequest(ID, userID, description, justification, dateNeeded,
						deliveryMode, status, total, submittedDate, reasonForRejection);
				PurchaseRequests.add(p);
			}
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		}
		return PurchaseRequests;
	}

	@Override
	public boolean add(PurchaseRequest t) {
		String sql = "INSERT INTO PurchaseRequest (userID, description, justification, dateNeeded, deliveryMode, status, total, submittedDate, reasonForRejection) VALUES (?,?,?,?,?,?,?,?,?)";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, t.getUserID());
			ps.setString(2, t.getDescription());
			ps.setString(3, t.getJustification());
			ps.setDate(4, Date.valueOf(t.getDateNeeded()));
			ps.setString(5, t.getDeliveryMode());
			ps.setString(6, t.getStatus());
			ps.setDouble(7, t.getTotal());
			ps.setDate(8, Date.valueOf(t.getSubmittedDate()));
			ps.setString(9, t.getReasonForRejection());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(PurchaseRequest t) {
		String sql = "DELETE FROM PurchaseRequest WHERE id = ?";
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
	public boolean update(PurchaseRequest t) {
//		String sql = "UPDATE PurchaseRequest SET Description = ?, Price = ? WHERE Code = ?";
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
