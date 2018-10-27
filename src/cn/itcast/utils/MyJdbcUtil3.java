package cn.itcast.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 操作JDBC
 * @author Administrator
 */
public class MyJdbcUtil3 {
	
//	public static ComboPooledDataSource dataSource = new ComboPooledDataSource("myoracle");
	public static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	public static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	/**
	 * 获取链接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		// return dataSource.getConnection();
		// 先从当前的线程中取链接
		Connection conn = tl.get();
		if(conn == null){
			// 创建一个conn，存入进去
			conn = dataSource.getConnection();
			// 把链接存入存入到线程中
			tl.set(conn);
		}
		// 把conn返回
		return conn;
	}
	
	
	// 开启事物的方法
	public static Connection startTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			// 创建一个conn，存入进去
			conn = dataSource.getConnection();
			// 把链接存入存入到线程中
			tl.set(conn);
		}
		// 开启事物
		conn.setAutoCommit(false);
		return conn;
	}
	
	
	// 开启事物的方法
	public static void commitTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			// 创建一个conn，存入进去
			conn = dataSource.getConnection();
			// 把链接存入存入到线程中
			tl.set(conn);
		}
		// 提交
		conn.commit();
		// 从线程中移除
		tl.remove();
	}
	
	// 回滚
	public static void rollbackTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			// 创建一个conn，存入进去
			conn = dataSource.getConnection();
			// 把链接存入存入到线程中
			tl.set(conn);
		}
		// 回滚
		conn.rollback();
		// 从线程中移除
		tl.remove();
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









