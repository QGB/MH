package mh.database;

import static mh.database.MDT.gStat;
import static mh.database.MDT.gsCTime;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import qgb.T;
import qgb.swing.QST;
import qgb.text.QText;
import mh.struct.Sentences;
import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Entry;
import mh.struct.entry.Phon;
import mh.struct.entry.Phons;
import mh.struct.entry.Senten;

/**
* import static mh.database.MDT.*;</br>
* import static mh.database.MDT.gConn;</br>
* import static mh.database.MDT.gStat;</br>
* import static mh.database.MDT.gsDbName;</br>
* import static mh.database.MDT.gsTableFH;</br>
* import static mh.database.MDT.gsCWord;</br>
* import static mh.database.MDT.gsCTime;</br>
* import static mh.database.MDT.gsTableEntry;</br>
* import static mh.database.MDT.gsCTrans;</br>
* import static mh.database.MDT.gsTableSenten;</br>
* import static mh.database.MDT.gsCEn;</br>
* import static mh.database.MDT.gsCn;</br>
* import static mh.database.MDT.gsCUrl;</br>
* import static mh.database.MDT.gsCVoiceByte;</br>
* import static mh.database.MDT.gsTablePhon;</br>
* import static mh.database.MDT.gsCPhon;</br>
* import static mh.database.MDT.gsCountry;</br>
 **/
public class MDT {
	public static void main(String[] args) throws SQLException {
		//createTable();
//		String sql = QText
//				.format("create table %s("
//						+ "%s char(50),"
//						+ "%s blob);",
//						"BlobTest", gsCWord,gsCVoiceByte);
//		gStat.executeUpdate(sql);
//		PreparedStatement prep = gConn.prepareStatement(QText.format(
//				"INSERT INTO %s(%s,%s) VALUES(?,?)", "BlobTest",
//				gsCWord,gsCVoiceByte));
//		prep.setString(1, System.currentTimeMillis()+"A");
//		InputStream is=T.read_is("1.doc");
//		prep.setBytes(2, T.InputStreamToBytes(is));
//		prep.execute();
		PreparedStatement prep = gConn.prepareStatement(QText.format(
				"Select * From %s;", 
				"BlobTest"));
		ResultSet rs=prep.executeQuery();
		while (rs.next()) {
			T.print(rs.getString(1));
			T.write("Blob",rs.getBytes(2));
			T.msgbox();
		}
		gConn.commit();
		if (true) {
			return;
		}
		T.print(EntryExists("zuo"));
		Class<MDT> cMDT=MDT.class;
		Field[] yf=cMDT.getDeclaredFields();

		for (int i = 0; i < yf.length; i++) {
			String str=yf[i].toString();
			int ia=str.indexOf("mh.database");
			str=QText.format("* import static %s;</br>", 
					str.substring(ia,str.length()));
			T.print(str);
		}
		//Entry.print(ae, astName)
		//T.print(yf);
	}
/****************Test End************************/
	public static Connection gConn;
	public static Statement gStat;
	final private static String gsDbName="./mh.db";
	/**gsCWord, gsCTime**/
	final static String gsTableFH = "FindHistory";
	final static String gsCWord = "stWord";
	final static String gsCTime = "stTime";
	/**gsCWord, gsCTrans**/
	final static String gsTableEntry = "Entry";
	final static String gsCTrans = "stTrans";
	/**gsCWord, gsCEn, gsCn, gsCUrl,gsCVoiceByte(blob)**/
	final static String gsTableSenten = "Sentences";
	final static String gsCEn = "stEn";
	final static String gsCn = "stCn";
	final static String gsCUrl = "stUrl";
	final static String gsCVoiceByte = "Bytes";
	/**gsCWord, gsCPhon, gsCountry, gsCUrl,gsCVoiceByte(blob)**/
	final static String gsTablePhon = "Phons";
	final static String gsCPhon = "stPhon";
	final static String gsCountry = "stCountry";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
			// 建立连接
			gConn = DriverManager.getConnection("jdbc:sqlite:"+gsDbName, "", "");
			gConn.setAutoCommit(false);
			gStat = gConn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				gConn.rollback();
			} catch (Exception e2) {
			}
		}

	}

	public static boolean EntryExists(String ast){
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", 
					gsTableEntry,gsCWord));
			prep.setString(1,ast);
			ResultSet rs=prep.executeQuery();
			while (rs.next()) {
				return rs.getString(1).equals(ast);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void save(Entry ae) {
		if (ae == null || ae.word.stW.get() == "")
			return;
		ae.print();//test
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"INSERT INTO %s(%s,%s) VALUES(?,?)", gsTableEntry, 
					gsCWord,	gsCTrans));
			prep.setString(1, ae.word.stW.get());
			prep.setString(2, ae.trans.toString());
			prep.execute();

			for (Phons.Entry<C, Phon> e : ae.word.ps.entrySet()) {
				prep = gConn.prepareStatement(QText.format(
						"INSERT INTO %s(%s,%s,%s,%s,%s) VALUES(?,?,?,?,?)", gsTablePhon,
						gsCWord, gsCPhon,gsCountry,gsCUrl,gsCVoiceByte));
				prep.setString(1, ae.word.stW.get());
				prep.setString(2, e.getValue().stP.get());
				prep.setString(3, e.getKey().toString());
				prep.setString(4, e.getValue().v.stM.get());
				if(e.getValue().v.notNull()){
					prep.setBlob(5, e.getValue().v.get());
				}
				prep.execute();
			}

			for(Senten s:ae.sentces){
				prep = gConn.prepareStatement(QText.format(
						"INSERT INTO %s(%s,%s,%s,%s,%s) VALUES(?,?,?,?,?)", gsTableSenten,
						gsCWord, gsCEn, gsCn, gsCUrl,gsCVoiceByte));
				prep.setString(1, ae.word.stW.get());
				prep.setString(2, s.ds.st1.get());
				prep.setString(3, s.ds.st2.get());
				prep.setString(4, s.v.stM.get());
				if(s.v.notNull()){
					prep.setBlob(5,s.v.get());
				}
				prep.execute();
			}
			
			gConn.commit();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 在没有数据库时才建表 **/
	private static void createTable() {
		if (DBexists())return;
		try {
			// 在没有数据库时才做建表和插数据
			String sql = QText
					.format("create table %s("
							+ "%s char(50) NOT NULL,"
							+ "%s char(255),"
							+ "PRIMARY KEY (%s));",
							gsTableEntry, gsCWord, gsCTrans, gsCWord);
			gStat.executeUpdate(sql);
			/**sql二进制数据 blob**/
			sql = QText.format(
							"create table %s("
							+ "%s char(50) NOT NULL,"
							+ "%s char(99)NOT NULL,"
							+ "%s char(99),"
							+ "%s char(100),"
							+ "%s blob);",
							gsTableSenten, gsCWord, gsCEn, gsCn, gsCUrl,
							gsCVoiceByte);
			gStat.executeUpdate(sql);

			sql = QText
					.format("create table %s("
							+ "%s char(50) NOT NULL,"
							+ "%s char(25)NOT NULL,"
							+ "%s char(2),"
							+ "%s char(100),"
							+ "%s blob);",
							gsTablePhon, gsCWord, gsCPhon, gsCountry, gsCUrl,
							gsCVoiceByte);
			gStat.executeUpdate(sql);

			sql = QText
					.format("create table %s(%s char(50) NOT NULL,%s char(13) NOT NULL);",
							gsTableFH, gsCWord, gsCTime);
			gStat.executeUpdate(sql);

			gConn.commit();
		} catch (Exception e2) {
			try {
				gConn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e2.printStackTrace();
		}
	}
	public static boolean DBexists(){
		return new File(T.autoPath(gsDbName)).exists();
	}
}
