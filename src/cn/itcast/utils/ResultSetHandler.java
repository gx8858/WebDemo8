package cn.itcast.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ��Ҫ�û�ʵ�ֵĽӿ�
 * @author Administrator
 *
 */
public interface ResultSetHandler<T> {
	
	// �û���д�������ṩ��װ�ķ�ʽ
	public T handler(ResultSet rs) throws SQLException;
	
}
