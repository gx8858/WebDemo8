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
				System.out.println(rs.getString("TABLE_CAT"));   // 表类别（可为 null）
				System.out.println(rs.getString("TABLE_SCHEM")); // 表模式（可为 null）
				System.out.println(rs.getString("TABLE_NAME"));  // 表名称
				System.out.println(rs.getString("COLUMN_NAME")); // 列名称
				System.out.println(rs.getString("KEY_SEQ"));     // 主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列）。 
				System.out.println(rs.getString("PK_NAME"));     // 主键的名称（可为 null）
				
				/**
				 * 结果：
				 * jdbc:mysql:///day18?generateSimpleParameterMetadata=true&useUnicode=true&characterEncoding=UTF-8
					root@localhost
					MySQL-AB JDBC Driver
					
					day18
					null
					t_account
					id
					1
					PRIMARY
				 */
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(rs, stmt, conn);
		}
	}

}





