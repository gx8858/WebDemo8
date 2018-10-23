package cn.itcast.dbutil;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil2;
import cn.itcast.vo.Account;


/**
 * DButil测试
 * @author Administrator
 *
 */
public class DbutilDemo1 {
	
	
	@Test
	public void run1() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		Account ac = runner.query("select * from t_account where id = ?", new BeanHandler<Account>(Account.class), 2);
		System.out.println(ac);
	}
	
	/**
	 * 把JavaBean保存到List集合中
	 * @throws SQLException
	 */
	@Test
	public void run2() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		List<Account> list = runner.query("select * from t_account", new BeanListHandler<Account>(Account.class));
		for (Account account : list) {
			System.out.println(account);
		}
	}
	
	
	
	@Test
	public void run3() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		Object [] obj = runner.query("select * from t_account where id = ?", new ArrayHandler(),2);
		System.out.println(Arrays.toString(obj));
	}
	
	
	@Test
	public void run4() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		List<Object []> list = runner.query("select * from t_account", new ArrayListHandler());
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
	}
	
	
	@Test
	public void run5() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		Map<String, Object> map = runner.query("select * from t_account where id = ?", new MapHandler(),2);
		System.out.println(map);
	}
	
	@Test
	public void run6() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		List<Map<String, Object>> list = runner.query("select * from t_account", new MapListHandler());
		System.out.println(list);
	}
	
	
	@Test
	public void run7() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		long obj = (Long) runner.query("select count(*) from t_account", new ScalarHandler());
		System.out.println(obj);
	}
	
	@Test
	public void run8() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		List<Object> list = runner.query("select username,money from t_account", new ColumnListHandler("money"));
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	
	@Test
	public void run9() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		Map<Object, Map<String, Object>> map = runner.query("select * from t_account", new KeyedHandler("username"));
		System.out.println(map);
	}
	
}






