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
 */
public class DbutilDemo1 {
	
	/**
	 * 把一条记录封装到JavaBean对象中
	 * @throws SQLException
	 * 结果为：
	 * Account [id=2, username=美美, money=1000.0]
	 */
	@Test
	public void run1() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		Account ac = runner.query("select * from t_account where id = ?", new BeanHandler<Account>(Account.class), 2);
		System.out.println(ac);
	}
	
	/**
	 * 把JavaBean保存到List集合中
	 * @throws SQLException
	 * 结果为：
	 * Account [id=1, username=聪聪, money=18000.0]
		Account [id=2, username=美美, money=1000.0]
		Account [id=3, username=小凤, money=10000.0]
		Account [id=4, username=赵刘, money=10000.0]
		Account [id=5, username=天启, money=10000.0]
	 */
	@Test
	public void run2() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		List<Account> list = runner.query("select * from t_account", new BeanListHandler<Account>(Account.class));
		for (Account account : list) {
			System.out.println(account);
		}
	}
	
	
	/**
	 * 把一条记录封装到数组中
	 * @throws SQLException
	 * 结果为：
	 * [2, 美美, 1000.0]
	 */
	@Test
	public void run3() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		Object [] obj = runner.query("select * from t_account where id = ?", new ArrayHandler(),2);
		System.out.println(Arrays.toString(obj));
	}
	
	
	/**
	 * 一条记录封装到数组中，把数组存放在集合中
	 * @throws SQLException
	 * 结果为：
	 * [1, 聪聪, 18000.0]
		[2, 美美, 1000.0]
		[3, 小凤, 10000.0]
		[4, 赵刘, 10000.0]
		[5, 天启, 10000.0]
	 */
	@Test
	public void run4() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		List<Object []> list = runner.query("select * from t_account", new ArrayListHandler());
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
	}
	
	/**
	 * 一条记录封装到Map集合
	 * @throws SQLException
	 * 结果为：
	 * {id=2, username=美美, money=1000.0}
	 */
	@Test
	public void run5() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		Map<String, Object> map = runner.query("select * from t_account where id = ?", new MapHandler(),2);
		System.out.println(map);
	}
	
	
	/**
	 * 一条记录封装到Map集合，把Map集合存放到集合中
	 * @throws SQLException
	 * 结果为：
	 * [
	 *  {id=1, username=聪聪, money=18000.0}, 
	 *  {id=2, username=美美, money=1000.0}, 
	 *  {id=3, username=小凤, money=10000.0}, 
	 *  {id=4, username=赵刘, money=10000.0}, 
	 *  {id=5, username=天启, money=10000.0}
	 * ]
	 */
	@Test
	public void run6() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		List<Map<String, Object>> list = runner.query("select * from t_account", new MapListHandler());
		System.out.println(list);
	}
	
	
	/**
	 * 封装count(*) 单行单列数据
	 * @throws SQLException
	 * 结果为：5
	 */
	@Test
	public void run7() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		long obj = (Long) runner.query("select count(*) from t_account", new ScalarHandler());
		System.out.println(obj);
	}
	
	
	/**
	 * 查询是一列数据，把一列数据封装到集合中。
	 * new ColumnListHandler()可以有参数也可没有，自己定
	 * @throws SQLException
	 * 结果为：
	 * 18000.0
		1000.0
		10000.0
		10000.0
		10000.0
	 */
	@Test
	public void run8() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		List<Object> list = runner.query("select username,money from t_account", new ColumnListHandler("money"));
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	
	/**
	 * 把一条记录封装到一个map集合，把该map集合又存放在另一个map集合中。
	 * new KeyedHandler()可以有参数也可没有，自己定
	 * @throws SQLException
	 * 结果为：
	 * { 
	 * 	赵刘={id=4, username=赵刘, money=10000.0}, 
	 * 	天启={id=5, username=天启, money=10000.0}, 
	 * 	小凤={id=3, username=小凤, money=10000.0}, 
	 * 	聪聪={id=1, username=聪聪, money=18000.0}, 
	 * 	美美={id=2, username=美美, money=1000.0}
	 * }
	 */
	@Test
	public void run9() throws SQLException{
		QueryRunner runner = new QueryRunner(MyJdbcUtil2.getDataSource());
		Map<Object, Map<String, Object>> map = runner.query("select * from t_account", new KeyedHandler("username"));
		System.out.println(map);
	}
	
}






