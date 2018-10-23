package cn.itcast.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 操作JDBC工具类
 */
public class MyJdbcUtil {
	
	public static final String DRIVERCLASS;
	public static final String URL;
	public static final String USERNAME;
	public static final String PASSWORD;
	
	// 想给常量赋值
	static{
		// 解析db.properties文件
		// Properties工具类，加载文件
		Properties pro = new Properties();
		// 获取输入流
		InputStream in = MyJdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 通过Properties获取内容
		DRIVERCLASS = pro.getProperty("driverClass");
		URL = pro.getProperty("url");
		USERNAME = pro.getProperty("username");
		PASSWORD = pro.getProperty("password");
	}
	
	/**
	 * 加载驱动
	 */
	public static void loadDriver(){
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取链接
	 * @return
	 */
	public static Connection getConnection(){
		// 加载驱动
		loadDriver();
		// 获取链接对象
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 释放资源
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	/**
	 * 释放资源的方法
	 * @param stmt
	 * @param conn
	 */
	public static void release(Statement stmt,Connection conn){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn != null){
			try {
				// 归还的方法
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
}









