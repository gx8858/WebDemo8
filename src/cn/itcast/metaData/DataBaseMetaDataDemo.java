package cn.itcast.metaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil2;

/**
 * 数据库元数据
 * @author Administrator
 *
 */
public class DataBaseMetaDataDemo {
	
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 获取资源
			conn = MyJdbcUtil2.getConnection();
			// 获取数据库元数据
			DatabaseMetaData metaData = conn.getMetaData();
			// 获取url  链接的基本信息
			System.out.println(metaData.getURL());
			System.out.println(metaData.getUserName());
			System.out.println(metaData.getDriverName());
			
			// 获取主键的信息
			rs = metaData.getPrimaryKeys(null, null, "t_account");
			if(rs.next()){
				// 获取
				System.out.println(rs.getString("TABLE_NAME"));
				System.out.println(rs.getString("COLUMN_NAME"));
				System.out.println(rs.getString("PK_NAME"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(rs, stmt, conn);
		}
	}

}





