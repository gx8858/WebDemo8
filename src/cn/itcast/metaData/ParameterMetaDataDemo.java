package cn.itcast.metaData;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil2;

/**
 * ����Ԫ����
 * @author Administrator
 *
 */
public class ParameterMetaDataDemo {
	
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ��Դ
			conn = MyJdbcUtil2.getConnection();
			// ��дSQL���
			String sql = "update t_account set money = ? ,username = ? where id = ?";
			// Ԥ����
			stmt = conn.prepareStatement(sql);
			// ��ȡ����Ԫ����
			ParameterMetaData metaData  = stmt.getParameterMetaData();
			// ��ȡ�����ĸ���
			int count = metaData.getParameterCount();
			System.out.println("�����ĸ����ǣ�"+count);   // �����ĸ����ǣ�3
			
			String msg = metaData.getParameterTypeName(1);
			System.out.println("���������ͣ�"+msg);      // ���������ͣ�VARCHAR  �����ͻ�ȡ�Ĳ�׼ȷ��
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(stmt, conn);
		}
	}
	

}
