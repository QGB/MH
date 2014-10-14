package mh.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import qgb.T;
import qgb.text.QText;
import static mh.database.MDT.*;

public class FindHistory {
	public static void main(String[] args) {
		T.print(T.getCurrentMethod());
		T.print(gsTableFH);
		//dele/e();
		//getLastWords(50);
	}	
	
	public static long addWord(String ast) {
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"INSERT INTO %s(%s,%s) VALUES(?,?)",
					gsTableFH,gsCWord,gsCTime));
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
					gsTableFH,gsCTime,aiMax));
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
					gsTableFH,gsCTime));
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
		gsTableFH+ ";");
			
			//st.executeUpdate("drop table History;");
			gConn.commit();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
