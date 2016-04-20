package com.genc.dao;
import java.sql.*;
public class UpdateProduct {

	
public int updateCars(int productid, String title) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			/*
			 * If this was a real application, you should do data validation here
			 * before updating data.
			 */
			
			conn = GetDbConnection.createConnection().getConnection();
			query = conn.prepareStatement("update car " +
					"set title = ? " +
					"where productid = ? ");

			query.setString(1, title);
			query.setInt(2, productid);
			query.executeUpdate();
			
			} catch(Exception e) {
			e.printStackTrace();
			return 500;
			}
			finally {
			if (conn != null) conn.close();
			}
			
			return 200;
	}
}

