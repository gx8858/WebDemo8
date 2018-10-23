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
	 * 完成修改的操作
	 * @param ac
	 */
	public void update(Account ac){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = MyJdbcUtil2.getConnection();
			// 编写SQL语句
			String sql = "update t_account set usename = ? , money = ? where id = ?";
			// 预编译
			stmt = conn.prepareStatement(sql);
			// 设置参数
			stmt.setString(1, ac.getUsername());
			stmt.setDouble(2, ac.getMoney());
			stmt.setInt(3, ac.getId());
			// 执行
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(stmt, conn);
		}
	}
	
	@Test
	public void run() {
		update(1000,"美美");
	}
	
	/**
	 * 转账的dao
	 * @param money
	 * @param username
	 */
	public void update(double money,String username){
		// 获取链接
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取链接（从线程中获取）
			conn = MyJdbcUtil3.getConnection();
			String sql = "update t_account set money = money - ? where username = ?";
			// 预编译sql
			stmt = conn.prepareStatement(sql);
			//设置值
			stmt.setDouble(1, money);
			stmt.setString(2, username);
			// 执行
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
//			 MyJdbcUtil3.release(stmt, conn);  不能在这里写这句话，如果写了在service中一执行这个update就会被释放掉
		}
	}

	
	
	/**
	 * 添加的操作
	 * @param ac
	 */
	public void save(Account ac){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = MyJdbcUtil2.getConnection();
			// 编写SQL语句
			String sql = "insert into t_account values (null,?,?)";
			// 预编译
			stmt = conn.prepareStatement(sql);
			// 设置参数
			stmt.setString(1, ac.getUsername());
			stmt.setDouble(2, ac.getMoney());
			// 执行
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(stmt, conn);
		}
	}
	
	
	/**
	 * 通过id查询单个JavaBean
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
	 * 查询所有内容
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
