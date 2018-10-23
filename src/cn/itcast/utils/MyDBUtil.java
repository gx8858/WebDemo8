package cn.itcast.utils;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.itcast.vo.Account;

/**
 * ��װͨ�õķ���
 * @author Administrator
 *
 */
public class MyDBUtil {
	
	/**
	 * ͨ�ò�ѯ����
	 * @param sql
	 * @param rsh
	 * @param objs
	 * @return
	 */
	public <T> T query(String sql,ResultSetHandler<T> rsh,Object... objs){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = MyJdbcUtil2.getConnection();
			// ���ñ�дsql
			stmt = conn.prepareStatement(sql);
			// ���ò���
			ParameterMetaData metaData = stmt.getParameterMetaData();
			// ��ȡ�����ĸ�����?�ĸ�����
			int count = metaData.getParameterCount();
			// ����ֵ
			for (int i = 1; i <= count; i++) {
				// ѭ������ֵ;
				stmt.setObject(i, objs[i - 1]);
			}
			// ִ��
			rs = stmt.executeQuery();
			// ���÷�װ�Ľ��
			T t = rsh.handler(rs);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(rs, stmt, conn);
		}
		return null;
	}
	
	
	/**
	 * ��װ����ɾ�ĵķ���
	 * @param sql
	 * @param objs
	 */
	public void update(String sql,Object... objs){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = MyJdbcUtil2.getConnection();
			// ��дsql�����ڴ��������
			// Ԥ����sql
			stmt = conn.prepareStatement(sql);
			// ���ò���  insert into t_user values (?,?,?,?,?);
			// �����м�������������ʲô���͵İ���
			// ��ȡ?�ĸ���
			ParameterMetaData metaData = stmt.getParameterMetaData();
			// ��ȡ�����ĸ���
			int count = metaData.getParameterCount();
			// ����ֵ
			for (int i = 1; i <= count; i++) {
				// ѭ������ֵ
				stmt.setObject(i, objs[i - 1]);
			}
			// ִ��
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(stmt, conn);
		}
	}
	
	
	
	@Test
	public void run(){
		// ��ӵĲ���
		// update("insert into t_account values (null,?,?)","����",10000);
		// �����޸�
		// update("update t_account set username = ? , money = ? where id = ?","����",100000,4);
		// ɾ��
		update("delete from t_account where id = ?",4);
	}
	
	@Test
	public void testFind(){
		/*Account ac = query("select * from t_account where id = ?",new BeanObj(),1);
		System.out.println(ac);*/
		
		List<Account> list = query("select * from t_account",new BeanList());
		for (Account account : list) {
			System.out.println(account);
		}
	}
}


/**
 * �û�ʵ�ָýӿڣ��Լ��ṩ��װ�ķ�ʽ
 * @author Administrator
 *
 */
class BeanObj implements ResultSetHandler<Account>{
	/**
	 * �����ݷ�װ��Account
	 * @throws SQLException 
	 */
	public Account handler(ResultSet rs) throws SQLException {
		Account a = new Account();
		if(rs.next()){
			a.setId(rs.getInt("id"));
			a.setUsername(rs.getString("username"));
			a.setMoney(rs.getDouble("money"));
		}
		return a;
	}
}


class BeanList implements ResultSetHandler<List<Account>>{
	/**
	 * �����ݷ�װ��Account
	 * @throws SQLException 
	 */
	public List<Account> handler(ResultSet rs) throws SQLException {
		List<Account> list = new ArrayList<Account>();
		while(rs.next()){
			Account a = new Account();
			a.setId(rs.getInt("id"));
			a.setUsername(rs.getString("username"));
			a.setMoney(rs.getDouble("money"));
			list.add(a);
		}
		return list;
	}
}















