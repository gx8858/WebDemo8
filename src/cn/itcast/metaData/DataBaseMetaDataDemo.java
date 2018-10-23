package cn.itcast.metaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil2;

/**
 * ���ݿ�Ԫ����
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
			// ��ȡ��Դ
			conn = MyJdbcUtil2.getConnection();
			// ��ȡ���ݿ�Ԫ����
			DatabaseMetaData metaData = conn.getMetaData();
			// ��ȡurl  ���ӵĻ�����Ϣ
			System.out.println(metaData.getURL());
			System.out.println(metaData.getUserName());
			System.out.println(metaData.getDriverName());
			
			// ��ȡ��������Ϣ
			rs = metaData.getPrimaryKeys(null, null, "t_account");
			if(rs.next()){
				// ��ȡ
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





