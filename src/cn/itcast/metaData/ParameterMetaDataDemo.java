package cn.itcast.metaData;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil2;

/**
 * 参数元数据
 * @author Administrator
 *
 */
public class ParameterMetaDataDemo {
	
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取资源
			conn = MyJdbcUtil2.getConnection();
			// 编写SQL语句
			String sql = "update t_account set money = ? ,username = ? where id = ?";
			// 预编译
			stmt = conn.prepareStatement(sql);
			// 获取参数元数据
			ParameterMetaData metaData  = stmt.getParameterMetaData();
			// 获取参数的个数
			int count = metaData.getParameterCount();
			System.out.println("参数的个数是："+count);   // 参数的个数是：3
			
			String msg = metaData.getParameterTypeName(1);
			System.out.println("参数的类型："+msg);      // 参数的类型：VARCHAR  （类型获取的不准确）
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(stmt, conn);
		}
	}
	

}
