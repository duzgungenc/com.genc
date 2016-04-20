package com.genc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GetDbConnection {

	private static DataSource DBConnection=null;
	private static Context context = null;
	/**
	 * This method can open connection with default JNDI name which is (newWeb)
	 * There two overloaded method.
	 * If you want to use other JNDI name
	 * Please enter the JNDI name
	 *  
	 */
	public static DataSource createConnection()  throws Exception {
		if(DBConnection!= null){
			return DBConnection;
		}
		try{
			if(context==null){
				context = new InitialContext();
			}
			DBConnection = (DataSource) context.lookup("newWeb");
					
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Connection can not create");
		}
		return DBConnection;
	}
	
	
	/**
	 * This method can open connection with JNDI name
	 * Please enter the JNDI name
	 * </br></br>
	 *  
	 *  
	 *  @param JNDI
	 * </br>
	 * 
	 *  GetDBConnection.createConnection(String JNDI);
	 *  @return DataSource
	 *  </br></br>
	 */
	public static DataSource createConnection(String JNDI)  throws Exception {
		if(DBConnection!= null){
			return DBConnection;
		}
		try{
			if(context==null){
				context = new InitialContext();
			}
			DBConnection = (DataSource) context.lookup(JNDI);
					
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Connection can not create");
		}
		return DBConnection;
	}
	
	/**
	 * This method can create  ResultSet by tableName
	 * @param tableName
	 * 
	 * @return ResultSet
	 */
	
	public static ResultSet returnResultSet(String tableName){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement query = null;
		
		try{
			conn = GetDbConnection.createConnection().getConnection();
			query = conn.prepareStatement("select * from  " + tableName);
			rs = query.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Can not return ResultSet");
		}
		
		return rs;
		
				
	}
	
	/**
	 * This method can create  ResultSet by colunmName and tableName
	 * @param tableName
	 * @param colunmName
	 * @return ResultSet
	 */
	
	public static ResultSet returnResultSet(String colunmName,String tableName){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement query = null;
		
		try{
			conn = GetDbConnection.createConnection().getConnection();
			query = conn.prepareStatement("select "+ colunmName +" from  " + tableName);
			rs = query.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Can not return ResultSet");
		}
		
		return rs;
		
				
	}
	
	
}
