/**
 * 
 */
package qgb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import qgb.T;

public class Mysql {
	// ������̬ȫ�ֱ���
	static Connection conn;
	static Statement st;
	static String sql;
	

	public static void main(String[] args) {
		connect();
		String sql="select * from lib where inum>2011000001 and inum<2012123456;";
		ResultSet rs=null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		T.print(RS_toArray(rs, "st_id")); 
	}

	public static String[] RS_toArray(ResultSet ars, String ast) {
		String str = "";
		String[] yst={""};
		ArrayList<String> al = new ArrayList<String>();
		try {
			ars.first();
			while (ars.next()) {
				str = ars.getString(ast);
				al.add(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al.toArray(yst);

	}
	
	
	
	
	/* ��ȡ���ݿ����ӵĺ���*/
	public static void connect() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");// ����Mysql��������
			
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "123456");// ������������
			st = (Statement) conn.createStatement();	
		} catch (Exception e) {
			System.out.println("���ݿ�����ʧ��" + e.getMessage());
			
		}
		//return con;	//���������������ݿ�����
	}
	
	/**
	 * @param url
	 * @param user
	 * @param password
	 */
	public static void connect(String url,
	        String user, String password) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");// ����Mysql��������
			
			conn = DriverManager.getConnection(
					url,user,password);// ������������
			st = (Statement) conn.createStatement();	
		} catch (Exception e) {
			System.out.println("���ݿ�����ʧ��" + e.getMessage());
			
		}
		//return con;	//���������������ݿ�����
	}


	/**
	 * @param ast_sql 
	 * ast_sql is not affected by the inner spaces.
	 */
	public static void SQL_execute(String ast_sql) {
		try {
			st.execute(ast_sql);
		} catch (NullPointerException e) {
			T.error(e,"����δ�������ݿ⣡");
		} catch (SQLException e) {
			T.error(e,sql+"| execute error ��");
		}	
	}
	
}
