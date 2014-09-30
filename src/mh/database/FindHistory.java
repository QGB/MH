package mh.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListSet;

import qgb.T;
import qgb.jdbc.QJDBT;
import qgb.text.QText;
import static mh.database.MDT.*;
//import static mh.database.MDT.gConn;
//import static mh.database.MDT.gStat;

public class FindHistory {

	public static void main(String[] args) {
		T.print(T.getCurrentMethod());
		T.print(tableName());
		//dele/e();
		//getLastWords(50);
	}	
	
	public static long addWord(String ast) {
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"INSERT INTO %s(%s,%s) VALUES(?,?)",
					tableName(),gsCWord,gsCTime));
			prep.setString(1, ast);
			prep.setLong(2, System.currentTimeMillis());
			prep.execute();
			gConn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**返回 不重复的单词 单词数<=aiMax**/
	public static Collection<String> getLastWords(int aiMax) {
		try {
			ResultSet rs= gStat.executeQuery(QText.format(
					"Select * From %s order by %s desc limit %d;",
					tableName(),gsCTime,aiMax));
			//ConcurrentSkipListSet<String> cSLSet=new ConcurrentSkipListSet<String>();
			ArrayList<String> als=new ArrayList<String>();
			//int i=0;
			while (rs.next()) {
				//T.print(rs.getString(1));
				if (als.contains(rs.getString(1))==false) {
					als.add(rs.getString(1));
				}
			 }
			//T.print("-----------\n"+als);
			return als;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static void delete(){
		try {
			gConn.setAutoCommit(true);
			gStat.execute(QText.format("delete From %s where %s<1411102396000;",
					tableName(),gsCTime));
			T.print(gStat.getWarnings());
			//QJDBT.printType(rs);
			
			int i=0;
//			while (rs.next()) {
//				 T.print((++i)+"["+rs.getString(1)+"],["+rs.getString(2)+"]");
//			}
			//gConn.commit();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private static void dropTable() {
		try {
			gStat.executeUpdate("drop table "+
		tableName()+ ";");
			
			//st.executeUpdate("drop table History;");
			gConn.commit();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	private static String tableName() {
		return FindHistory.class.getSimpleName();
	}

	public static ResultSet get_RS() {

		// 数据库连接
		Connection gConn = null;
		Statement st = null;
		try {
			Class.forName("org.sqlite.JDBC");

			// 建立连接
			gConn = DriverManager.getConnection("jdbc:sqlite:mh_t.db", "", "");
			gConn.setAutoCommit(false);
			st = gConn.createStatement();
			ResultSet rs = st.executeQuery("Select * From ccsu");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				gConn.rollback();
			} catch (Exception e2) {
			}
		}
		return null;

	}

}
