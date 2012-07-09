package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author Jiaming.Zhang  E-mail:eJMingZhang@gmail.com
 * @version 创建时间：2012-3-31 下午03:56:32
 * 类说明
 */

public class PostgreJDBC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		postgresql();
	}
	
	public static void postgresql(){
		String username ="travelhub";
		String password ="travelhub";
		String jdbcDriver = "org.postgresql.Driver"; 
		String url = "jdbc:postgresql://172.24.2.168:5432/travelhub";
		Connection conn =null;
		//org.postgresql.Driver
		try 
		{ 
		//Class.forName(jdbcDriver); 
		// new org.postgresql.Driver(); 
			String sql =" select \"CITYCODE\" from \"IH_CITY\" ";
		conn = DriverManager.getConnection(url, username, password); 
		PreparedStatement stmt = conn.prepareStatement(sql); 
		// stmt.setInt(1, 21); 
		// stmt.setString(1, new String(area.getBytes(), "GBK")); 
		stmt.executeQuery();
		System.out.println( conn);
		} catch(Exception e){
			System.out.println( );
			e.printStackTrace();
		}

		
	}

}
