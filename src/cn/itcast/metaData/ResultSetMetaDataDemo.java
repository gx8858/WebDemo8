package cn.itcast.metaData;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil2;

/**
 * �����Ԫ����
 * @author Administrator
 *
 */
public class ResultSetMetaDataDemo {
	
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// ��ȡ��Դ
			conn = MyJdbcUtil2.getConnection();
			// ��дsql���
			String sql = "select * from t_account where id = ?";
			// Ԥ����sql
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			rs = stmt.executeQuery();
			// ��ȡ�������Ԫ����
			ResultSetMetaData metaData = rs.getMetaData();
			// ��ȡ����
			int count = metaData.getColumnCount();
			System.out.println(count);
			for (int i = 1; i <= count; i++) {
				// ��ȡ�е�����
				System.out.println(metaData.getColumnName(i));
				
				// ��ȡ�е�����
				System.out.println(metaData.getColumnTypeName(i));
			}
		
			/**
			 * �����
			 * 3
				id
				INT
				username
				VARCHAR
				money
				DOUBLE
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(rs, stmt, conn);
		}
	}
	

}
