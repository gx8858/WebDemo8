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
				System.out.println(rs.getString("TABLE_CAT"));   // ����𣨿�Ϊ null��
				System.out.println(rs.getString("TABLE_SCHEM")); // ��ģʽ����Ϊ null��
				System.out.println(rs.getString("TABLE_NAME"));  // ������
				System.out.println(rs.getString("COLUMN_NAME")); // ������
				System.out.println(rs.getString("KEY_SEQ"));     // �����е����кţ�ֵ 1 ��ʾ�����еĵ�һ�У�ֵ 2 ��ʾ�����еĵڶ��У��� 
				System.out.println(rs.getString("PK_NAME"));     // ���������ƣ���Ϊ null��
				
				/**
				 * �����
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





