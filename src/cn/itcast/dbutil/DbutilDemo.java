package cn.itcast.dbutil;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil2;
import cn.itcast.vo.Account;

/**
 * DButil测试
 * @author Administrator
 *
 */
public class DbutilDemo {
	
	@Test
	public void run(){
		// 创建QueryRunner类
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		// 删除的操作
		try {
			runner.update("delete from t_account where id = ?", 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void run2(){
		// 创建QueryRunner类
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		// 删除的操作
		try {
			Account ac = runner.query("select * from t_account where id = ?", new BeanHandler<Account>(Account.class), 2);
			System.out.println(ac);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void run3(){
		// 创建QueryRunner类
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		// 删除的操作
		try {
			Account ac = runner.query("select * from t_account where id = ?", new BeanAccount() , 3);
			System.out.println(ac);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 自己实现方法
 * @author Administrator
 *
 */
class BeanAccount implements ResultSetHandler<Account>{
	/**
	 * 自己封装
	 */
	public Account handle(ResultSet rs) throws SQLException {
		Account ac = new Account();
		if(rs.next()){
			ac.setId(rs.getInt("id"));
			ac.setUsername(rs.getString("username"));
			ac.setMoney(rs.getDouble("money"));
		}
		return ac;
	}
	
}







