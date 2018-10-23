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
 * ����JDBC������
 */
public class MyJdbcUtil {
	
	public static final String DRIVERCLASS;
	public static final String URL;
	public static final String USERNAME;
	public static final String PASSWORD;
	
	// ���������ֵ
	static{
		// ����db.properties�ļ�
		// Properties�����࣬�����ļ�
		Properties pro = new Properties();
		// ��ȡ������
		InputStream in = MyJdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ͨ��Properties��ȡ����
		DRIVERCLASS = pro.getProperty("driverClass");
		URL = pro.getProperty("url");
		USERNAME = pro.getProperty("username");
		PASSWORD = pro.getProperty("password");
	}
	
	/**
	 * ��������
	 */
	public static void loadDriver(){
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public static Connection getConnection(){
		// ��������
		loadDriver();
		// ��ȡ���Ӷ���
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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









