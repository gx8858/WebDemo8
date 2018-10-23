package cn.itcast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil2;
import cn.itcast.utils.MyJdbcUtil3;
import cn.itcast.vo.Account;

public class AccountDao {
	
	/**
	 * ����޸ĵĲ���
	 * @param ac
	 */
	public void update(Account ac){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = MyJdbcUtil2.getConnection();
			// ��дSQL���
			String sql = "update t_account set usename = ? , money = ? where id = ?";
			// Ԥ����
			stmt = conn.prepareStatement(sql);
			// ���ò���
			stmt.setString(1, ac.getUsername());
			stmt.setDouble(2, ac.getMoney());
			stmt.setInt(3, ac.getId());
			// ִ��
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(stmt, conn);
		}
	}
	
	@Test
	public void run() {
		update(1000,"����");
	}
	
	/**
	 * ת�˵�dao
	 * @param money
	 * @param username
	 */
	public void update(double money,String username){
		// ��ȡ����
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ���ӣ����߳��л�ȡ��
			conn = MyJdbcUtil3.getConnection();
			String sql = "update t_account set money = money - ? where username = ?";
			// Ԥ����sql
			stmt = conn.prepareStatement(sql);
			//����ֵ
			stmt.setDouble(1, money);
			stmt.setString(2, username);
			// ִ��
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
//			 MyJdbcUtil3.release(stmt, conn);  ����������д��仰�����д����service��һִ�����update�ͻᱻ�ͷŵ�
		}
	}

	
	
	/**
	 * ��ӵĲ���
	 * @param ac
	 */
	public void save(Account ac){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = MyJdbcUtil2.getConnection();
			// ��дSQL���
			String sql = "insert into t_account values (null,?,?)";
			// Ԥ����
			stmt = conn.prepareStatement(sql);
			// ���ò���
			stmt.setString(1, ac.getUsername());
			stmt.setDouble(2, ac.getMoney());
			// ִ��
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(stmt, conn);
		}
	}
	
	
	/**
	 * ͨ��id��ѯ����JavaBean
	 * @param id
	 * @return
	 */
	public Account findById(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = MyJdbcUtil2.getConnection();
			String sql = "select * from t_account where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs  =stmt.executeQuery();
			Account a = new Account();
			if(rs.next()){
				a.setId(rs.getInt("id"));
				a.setUsername(rs.getString("username"));
				a.setMoney(rs.getDouble("money"));
			}
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(rs, stmt, conn);
		}
		return null;
	}
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	public List<Account> findAll(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = MyJdbcUtil2.getConnection();
			String sql = "select * from t_account";
			stmt = conn.prepareStatement(sql);
			rs  =stmt.executeQuery();
			List<Account> list = new ArrayList<Account>();
			if(rs.next()){
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setUsername(rs.getString("username"));
				a.setMoney(rs.getDouble("money"));
				list.add(a);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(rs, stmt, conn);
		}
		return null;
	}
	
	
	
}
