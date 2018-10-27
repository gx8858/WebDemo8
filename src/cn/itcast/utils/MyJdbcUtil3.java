package cn.itcast.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ����JDBC
 * @author Administrator
 */
public class MyJdbcUtil3 {
	
//	public static ComboPooledDataSource dataSource = new ComboPooledDataSource("myoracle");
	public static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	public static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	/**
	 * ��ȡ����
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		// return dataSource.getConnection();
		// �ȴӵ�ǰ���߳���ȡ����
		Connection conn = tl.get();
		if(conn == null){
			// ����һ��conn�������ȥ
			conn = dataSource.getConnection();
			// �����Ӵ�����뵽�߳���
			tl.set(conn);
		}
		// ��conn����
		return conn;
	}
	
	
	// ��������ķ���
	public static Connection startTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			// ����һ��conn�������ȥ
			conn = dataSource.getConnection();
			// �����Ӵ�����뵽�߳���
			tl.set(conn);
		}
		// ��������
		conn.setAutoCommit(false);
		return conn;
	}
	
	
	// ��������ķ���
	public static void commitTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			// ����һ��conn�������ȥ
			conn = dataSource.getConnection();
			// �����Ӵ�����뵽�߳���
			tl.set(conn);
		}
		// �ύ
		conn.commit();
		// ���߳����Ƴ�
		tl.remove();
	}
	
	// �ع�
	public static void rollbackTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			// ����һ��conn�������ȥ
			conn = dataSource.getConnection();
			// �����Ӵ�����뵽�߳���
			tl.set(conn);
		}
		// �ع�
		conn.rollback();
		// ���߳����Ƴ�
		tl.remove();
	}
	
	/**
	 * �ͷ���Դ
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
	 * �ͷ���Դ�ķ���
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
				// �黹�ķ���
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
}









