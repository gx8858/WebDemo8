package cn.itcast.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import cn.itcast.dao.AccountDao;
import cn.itcast.utils.MyJdbcUtil3;

public class AccountService {
	
	@Test
	public void run(){
		
		payMoney("聪聪","美美",1000);
		
	}
	
	
	/**
	 * 从谁给谁转多少钱（事物需要加在service层示例）
	 * @param from
	 * @param to
	 * @param money
	 */
	public void payMoney(String from,String to,double money){
		Connection conn = null;
		try {
			// 开启事物（默默的创建了一个链接，放入到线程中了，事物已经开启了）
			conn = MyJdbcUtil3.startTransaction();
//			MyJdbcUtil3.startTransaction();
			AccountDao dao = new AccountDao();
			// 先扣除多少钱
			dao.update(-money, from);
			
			// int i = 10/0; 测试出异常情况回滚
			
			// 给另一个人加多少钱
			dao.update(money, to);
			
			// 提交事物
			MyJdbcUtil3.commitTransaction();
			
		} catch (SQLException e) {
			try {
				MyJdbcUtil3.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			MyJdbcUtil3.release(null, conn);
		}
	}

}
