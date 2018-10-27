package cn.itcast.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import cn.itcast.dao.AccountDao;
import cn.itcast.utils.MyJdbcUtil3;

public class AccountService {
	
	@Test
	public void run(){
		
		payMoney("�ϴ�","����",1000);
		
	}
	
	
	/**
	 * ��˭��˭ת����Ǯ��������Ҫ����service��ʾ����
	 * @param from
	 * @param to
	 * @param money
	 */
	public void payMoney(String from,String to,double money){
		Connection conn = null;
		try {
			// �������ĬĬ�Ĵ�����һ�����ӣ����뵽�߳����ˣ������Ѿ������ˣ�
			conn = MyJdbcUtil3.startTransaction();
//			MyJdbcUtil3.startTransaction();
			AccountDao dao = new AccountDao();
			// �ȿ۳�����Ǯ
			dao.update(-money, from);
			
			// int i = 10/0; ���Գ��쳣����ع�
			
			// ����һ���˼Ӷ���Ǯ
			dao.update(money, to);
			
			// �ύ����
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
