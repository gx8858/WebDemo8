package cn.itcast.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 需要用户实现的接口
 * @author Administrator
 *
 */
public interface ResultSetHandler<T> {
	
	// 用户重写方法，提供封装的方式
	public T handler(ResultSet rs) throws SQLException;
	
}
