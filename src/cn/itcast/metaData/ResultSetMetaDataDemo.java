package cn.itcast.metaData;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil2;

/**
 * 结果集元数据
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
			// 获取资源
			conn = MyJdbcUtil2.getConnection();
			// 编写sql语句
			String sql = "select * from t_account where id = ?";
			// 预编译sql
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			rs = stmt.executeQuery();
			// 获取结果集的元数据
			ResultSetMetaData metaData = rs.getMetaData();
			// 获取列数
			int count = metaData.getColumnCount();
			System.out.println(count);
			for (int i = 1; i <= count; i++) {
				// 获取列的名称
				System.out.println(metaData.getColumnName(i));
				
				// 获取列的类型
				System.out.println(metaData.getColumnTypeName(i));
			}
		
			/**
			 * 结果：
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
