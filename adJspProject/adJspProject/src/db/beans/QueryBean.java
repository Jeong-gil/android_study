package db.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryBean {
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	public QueryBean() {
		conn = null;
		stmt = null;
		rs = null;
	}
	
	public void getConnection() {
		
		try {
			conn = DBconnection.getConnection();
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList getUserInfo() throws Exception {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT ");
		sb.append(" 	U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME ");
		sb.append(" FROM ");
		sb.append(" 	USER_INFO_SAMPLE ");
		sb.append(" ORDER BY ");
		sb.append(" 	WRITE_TIME ");
		
		rs = stmt.executeQuery(sb.toString());
		
		ArrayList res = new ArrayList();
		
		while (rs.next()) {
			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
		}
		
		System.out.println(sb.toString());
		return res;
	}
	
	public ArrayList getUserInfo(String strUser) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT ");
		sb.append(" 	U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME ");
		sb.append(" FROM ");
		sb.append(" 	USER_INFO_SAMPLE ");
		sb.append(" WHERE ");
		sb.append(" 	U_iD LIKE '%" + strUser + "%' ");
		sb.append(" ORDER BY ");
		sb.append(" 	WRITE_TIME ");
		sb.append(" DESC ");
		
		rs = stmt.executeQuery(sb.toString());
		
		ArrayList res = new ArrayList();
		while (rs.next()) {
			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
		}
		
		System.out.println(sb.toString());
		return res;
	}
	
	public int setUserInfo(String id, String name, String phone, String grade) {
		
		int result = 0;
		
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		
		sb.append(" INSERT INTO ");
		sb.append(" 	USER_INFO_SAMPLE (U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME) ");
		sb.append(" VALUES ");
		sb.append(" 	('"+ id +"', '"+ name +"', '"+ phone +"', '"+ grade +"', NOW())");
		
		System.out.println(sb.toString());
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public int deleteUserInfo(String id) {
		
		int result = 0;
		
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		
		sb.append(" DELETE FROM ");
		sb.append(" 	USER_INFO_SAMPLE ");
		sb.append(" WHERE ");
		sb.append(" 	U_ID = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.clearParameters();
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public int updateUserInfo(String id, String name, String phone, String grade) {
		
		int result = 0;
		
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		
		sb.append(" UPDATE USER_INFO_SAMPLE ");
		sb.append(" SET ");
		sb.append(" 	U_NAME = ?, U_PHONE = ?, U_GRADE = ?, WRITE_TIME = NOW() ");
		sb.append(" WHERE ");
		sb.append(" 	U_ID = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.clearParameters();
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, grade);
			pstmt.setString(4, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	
	public ArrayList searchUserInfo(String id) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT * ");
		sb.append(" FROM USER_INFO_SAMPLE ");
		sb.append(" WHERE ");
		sb.append(" U_NAME ");
		sb.append(" LIKE '%" +id+ "%' ");
		
		rs = stmt.executeQuery(sb.toString());
		
		ArrayList res = new ArrayList();
		while (rs.next()) {
			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
		}
		System.out.println(sb.toString());
		return res;
	}

}
