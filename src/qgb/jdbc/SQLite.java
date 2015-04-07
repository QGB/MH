/*
 * 代号：(Phenix)凤凰
 * @author 刘虻
 * 2009-5-7
 * V 3.0
 */
package qgb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.sqlite.JDBC;

import qgb.T;

/**
 * 测试Sqlite数据库
 * db.TestA
 * @author 刘虻 
 * 2009-5-7  下午02:27:29
 * 
 * page size 65536 bytes
 */
public class SQLite {
	
	public static void main(String[] args) {
		
		//数据库连接
		Connection conn = null;
		Statement st = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	      
	    	//建立连接
	    	conn = DriverManager.getConnection("jdbc:sqlite:mh_t.db","","");
	    	conn.setAutoCommit(false);

	    	st = conn.createStatement();
	    	
	    	String st_t=".æspə'reiʃən";
	    	
	    	try {
	    		//在没有数据库时才做建表和插数据
	    		st.executeUpdate("create table ccsu ( "
	    				+ "st_num char(10) primary key NOT NULL , "
	    				+ "st_id char(18) NOT NULL "
	    				+ "); " );
		    	
	    	}catch(Exception e2) {e2.printStackTrace();}
	    	st.executeUpdate( "INSERT INTO ccsu(st_num,st_id) VALUES('PX0','测试')" );
	    	//st_t="INSERT INTO entry(word,translations,phonetic) VALUES('aspiration','测试','"+ st_t+"')" ;
	    	//T.print(st_t);
	    	//T.print("INSERT INTO TMP_DEMO(ID,ANAME) VALUES('PX000000000000000000','测试')");
    		//st.executeUpdate( st_t);	
	    	
	    	String str="";
	    	ResultSet rs = st.executeQuery( "Select * From ccsu" );
	    	while (rs.next()) {
	    		//str=rs.getString(3).trim();
//	    		if (str.length()>0) {
//	    			st_t="INSERT INTO ccsu(st_num,st_id) VALUES('"
//		    				+ rs0.getString(1)+"','"
//		    				+str + "')";
//		    		st.executeUpdate(st_t);
//		    		
		    		System.out.println( "num=["+rs.getString(1)+"] id:["+rs.getString(2)+"]");
		    		
//				}
	    		
	    	}
	    	rs.close();
	    	st.close();
	    	conn.commit();
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      try {
	    	  conn.rollback();
	      }catch (Exception e2) {}
	    } finally {
	    	try {
	    		conn.close();
	    	}catch(Exception e2) {}
	    }
	}
	
	
	public static ResultSet get_RS() {
		
		//数据库连接
		Connection conn = null;
		Statement st = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	      
	    	//建立连接
	    	conn = DriverManager.getConnection("jdbc:sqlite:mh_t.db","","");
	    	conn.setAutoCommit(false);
	    	st = conn.createStatement();	
	    	ResultSet rs = st.executeQuery( "Select * From ccsu" );
	    	return rs;
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      try {
	    	  conn.rollback();
	      }catch (Exception e2) {}
	    }
		return null;

	}
}
