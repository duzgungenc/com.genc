package com.genc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;

import com.genc.util.ConvertToJSON;

public class CrudDb {
	
	
public int deleteCar(int pk) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			/*
			 * If this was a real application, you should do data validation here
			 * before deleting data.
			 */
			
			conn = GetDbConnection.createConnection().getConnection();
			query = conn.prepareStatement("delete from car " +
											"where id = ? ");
			
			query.setInt(1, pk);
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
	
	
	public int insertNewModel(String title,String body_style) throws Exception{
		PreparedStatement query = null;
		Connection conn = null;		
		try{
						
			conn = GetDbConnection.createConnection().getConnection();
			query = conn.prepareStatement("insert into car (title,body_style) VALUES (?,?) ");
			
			query.setString(1, title);
			query.setString(2, body_style);
			
			query.executeUpdate();
			
			query.close();
			
		}catch(SQLException sqlError){
			sqlError.printStackTrace();
			System.out.println("ERROR");
			return 500;
		}catch(Exception e){
			e.printStackTrace();
			return 500;
		}
		
		return 200;
	}
	
	
	
	
	
	public int insertNewModel(String title,String body_style,String transmission,String exterior,String interior,String miles,
			String doors,String passengers,String stock,String vin,String fuel_mileage,String fuel_type,String price) throws Exception{
		PreparedStatement query = null;
		Connection conn = null;		
		try{
						
			conn = GetDbConnection.createConnection().getConnection();
			query = conn.prepareStatement("insert into car (title,body_style,transmission,exterior,interior,miles,doors,passengers,stock,vin,fuel_mileage,fuel_type,price) VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			
			query.setString(1, title);
			query.setString(2, body_style);
			query.setString(3, transmission);
			query.setString(4, exterior);
			query.setString(5, interior);
			query.setString(6, miles);
			query.setString(7, doors);
			query.setString(8, passengers);
			
			query.setString(9, stock);
			query.setString(10, vin);
			query.setString(11, fuel_mileage);
			query.setString(12, fuel_type);
			query.setString(13, price);
			
			query.executeUpdate();
			
			query.close();
			
		}catch(SQLException sqlError){
			sqlError.printStackTrace();
			System.out.println("ERROR");
			return 500;
		}catch(Exception e){
			e.printStackTrace();
			return 500;
		}
		
		return 200;
	}

	
	public JSONArray searchProducts(int keyId, String brand) throws Exception{
		PreparedStatement query = null;
		Connection conn = null;
		
		ConvertToJSON converter = new ConvertToJSON();
		JSONArray json = new JSONArray();
		
		try{
			
			
			conn = GetDbConnection.createConnection().getConnection();
			query = conn.prepareStatement("select * from cardb where keyid = ? and make = ?");
			query.setInt(1, keyId);
			query.setString(2, brand);
			ResultSet rs =  query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();
			
		}catch(SQLException sqlError){
			sqlError.printStackTrace();
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return json;
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return json;
	}
	
	public JSONArray searchProducts(int keyId) throws Exception{
		PreparedStatement query = null;
		Connection conn = null;
		
		ConvertToJSON converter = new ConvertToJSON();
		JSONArray json = new JSONArray();
		
		try{
			
			
			conn = GetDbConnection.createConnection().getConnection();
			query = conn.prepareStatement("select * from cardb where keyid = ? ");
			query.setInt(1, keyId);
			
			ResultSet rs =  query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();
			
		}catch(SQLException sqlError){
			sqlError.printStackTrace();
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return json;
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return json;
	}
}
