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
 * 封装通用的方法
 * @author Administrator
 *
 */
public class MyDBUtil {
	
	/**
	 * 通用查询方法
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
			// 不用编写sql
			stmt = conn.prepareStatement(sql);
			// 设置参数
			ParameterMetaData metaData = stmt.getParameterMetaData();
			// 获取参数的个数（?的个数）
			int count = metaData.getParameterCount();
			// 设置值
			for (int i = 1; i <= count; i++) {
				// 循环设置值;
				stmt.setObject(i, objs[i - 1]);
			}
			// 执行
			rs = stmt.executeQuery();
			// 调用封装的结果
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
	 * 封装了增删改的方法
	 * @param sql
	 * @param objs
	 */
	public void update(String sql,Object... objs){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = MyJdbcUtil2.getConnection();
			// 编写sql，现在传入进来了
			// 预编译sql
			stmt = conn.prepareStatement(sql);
			// 设置参数  insert into t_user values (?,?,?,?,?);
			// 参数有几个啊？参数是什么类型的啊？
			// 获取?的个数
			ParameterMetaData metaData = stmt.getParameterMetaData();
			// 获取参数的个数
			int count = metaData.getParameterCount();
			// 设置值
			for (int i = 1; i <= count; i++) {
				// 循环设置值
				stmt.setObject(i, objs[i - 1]);
			}
			// 执行
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil2.release(stmt, conn);
		}
	}
	
	
	
	@Test
	public void run(){
		// 添加的操作
		// update("insert into t_account values (null,?,?)","冠西",10000);
		// 测试修改
		// update("update t_account set username = ? , money = ? where id = ?","阿娇",100000,4);
		// 删除
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
 * 用户实现该接口，自己提供封装的方式
 * @author Administrator
 *
 */
class BeanObj implements ResultSetHandler<Account>{
	/**
	 * 把数据封装到Account
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
	 * 把数据封装到Account
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















