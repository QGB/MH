package mh.database;

import static mh.database.MDT.*;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import qgb.T;
import qgb.text.QText;
import qgb.thread.TaskQueue;
import mh.MainLoader;
import mh.struct.entry.C;
import mh.struct.entry.Entry;
import mh.struct.entry.Phon;
import mh.struct.entry.Phons;
import mh.struct.entry.Senten;
import mh.struct.entry.Translations;
import mh.struct.entry.Word;

/**
 * import static mh.database.MDT.*;</br> import static
 * mh.database.MDT.gConn;</br> import static mh.database.MDT.gStat;</br> import
 * static mh.database.MDT.gsDbName;</br> import static
 * mh.database.MDT.gsTableFH;</br> import static mh.database.MDT.gsCWord;</br>
 * import static mh.database.MDT.gsCTime;</br> import static
 * mh.database.MDT.gsTableEntry;</br> import static
 * mh.database.MDT.gsCTrans;</br> import static
 * mh.database.MDT.gsTableSenten;</br> import static mh.database.MDT.gsCEn;</br>
 * import static mh.database.MDT.gsCn;</br> import static
 * mh.database.MDT.gsCUrl;</br> import static mh.database.MDT.gsCVoiceByte;</br>
 * import static mh.database.MDT.gsTablePhon;</br> import static
 * mh.database.MDT.gsCPhon;</br> import static mh.database.MDT.gsCountry;</br>
 **/
public class MDT {
	public static void main(String[] args) throws SQLException {
		MainLoader.db();
		MainLoader.main(null);
		//T.sleep(99);
		//T.print(gConn);
		//createTable();
		//T.print(EntryExists("t"));
		// Entry.print(ae, astName)
		// T.print(yf);
//		Entry e=new Entry();
//		e.word.stW.set("t");
//		e.trans.add("n");
//		MDT.save(e);
	}

	private static void testSqlite() {
		PreparedStatement prep = null;
		String sql = QText.format("INSERT INTO %s(%s,%s) VALUES(?,?)",
				gsTableEntry, gsCWord, gsCTrans);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, "");
			prep.setString(2, "");
			prep.execute();
		} catch (SQLException e) {
			T.setOutStream("sql.txt");
			// T.print("%s\n[%s][%s]",sql,ae.word.stW.get(),ae.trans.toString());
			e.printStackTrace();
		}
	}

	private static void printField() {
		Class<MDT> cMDT = MDT.class;
		Field[] yf = cMDT.getDeclaredFields();

		for (int i = 0; i < yf.length; i++) {
			String str = yf[i].toString();
			int ia = str.indexOf("mh.database");
			str = QText.format("* import static %s;</br>",
					str.substring(ia, str.length()));
			T.print(str);
		}
	}

	/**************** Test End ************************/
	public static Connection gConn;
	public static Statement gStat;
	/**TODO:DB Test*/
	final private static String gsDbName =T.autoPath("mh.db"); //"./mh.db";
	/** gsCWord, gsCTime **/
	final static String gsTableFH = "FindHistory";
	final static String gsCWord = "stWord";
	final static String gsCTime = "stTime";
	/** gsCWord, gsCTrans **/
	final static String gsTableEntry = "Entry";
	final static String gsCTrans = "stTrans";
	/** gsCWord, gsCEn, gsCn, gsCUrl,gsCVoiceByte(blob) **/
	final static String gsTableSenten = "Sentences";
	final static String gsCEn = "stEn";
	final static String gsCn = "stCn";
	final static String gsCUrl = "stUrl";
	final static String gsCVoiceByte = "Bytes";
	/** gsCWord, gsCPhon, gsCountry, gsCUrl,gsCVoiceByte(blob) **/
	final static String gsTablePhon = "Phons";
	final static String gsCPhon = "stPhon";
	final static String gsCountry = "stCountry";
	private final static TaskQueue gDBThread = new TaskQueue();
	static {
		Runnable run = new Runnable() {
			@Override
			public void run() {
				try {
					Class.forName("org.sqlite.JDBC");
					// 建立连接
					gConn = DriverManager.getConnection("jdbc:sqlite:"
							+ gsDbName, "", "");
					gConn.setAutoCommit(true);
					gStat = gConn.createStatement();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						gConn.rollback();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				// T.print();
			}
		};
		gDBThread.invoke(run);
	}

	public static void commit() {
		if (true)
			return;
		Runnable run = new Runnable() {
			@Override
			public void run() {
				try {
					gConn.commit();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						gConn.rollback();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		gDBThread.invoke(run);
	}

	// Statement
	public static boolean EntryExists(String ast) {
		ast=redirect(ast);
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", gsTableEntry, gsCWord));
			prep.setString(1, ast);
			ResultSet rs = prep.executeQuery();
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
		if (EntryExists(ae.word.stW.get())) {
			return;
		}
		if (ae.word.stRedirectFrom!=null) {
			setRedirect(ae.word.stRedirectFrom, ae.word.stW.get());
		}
		// ae.print();//test
		PreparedStatement prep = null;
		String sql = QText.format("INSERT INTO %s(%s,%s) VALUES(?,?)",
				gsTableEntry, gsCWord, gsCTrans);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, ae.word.stW.get());
			prep.setString(2, ae.trans.toString());
			prep.execute();
		} catch (SQLException e) {
			//T.print("[%s][%s]",ae.word.stW.get(),ae.trans.toString());
			// T.msgbox( ae.word.stW.get());
			// T.write("SQL-error", ae.word.stW.get().getBytes());
			e.printStackTrace();
		}
		try {
			for (Phons.Entry<C, Phon> e : ae.word.ps.entrySet()) {
				sql=QText.format(
						"INSERT INTO %s(%s,%s,%s,%s,%s) VALUES(?,?,?,?,?)",
						gsTablePhon, gsCWord, gsCPhon, gsCountry, gsCUrl,
						gsCVoiceByte);
				prep = gConn.prepareStatement(sql);
				prep.setString(1, ae.word.stW.get());
				prep.setString(2, e.getValue().stP.get());
				prep.setString(3, e.getKey().toString());
				prep.setString(4, e.getValue().v.stM.get());
				if (e.getValue().v.notNull()) {
					prep.setBytes(5, T.InputStreamToBytes(e.getValue().v.get()));
				}
				prep.execute();
			}
			for (Senten s : ae.sentces) {
				prep = gConn.prepareStatement(QText.format(
						"INSERT INTO %s(%s,%s,%s,%s,%s) VALUES(?,?,?,?,?)",
						gsTableSenten, gsCWord, gsCEn, gsCn, gsCUrl,
						gsCVoiceByte));
				prep.setString(1, ae.word.stW.get());
				prep.setString(2, s.ds.st1.get());
				prep.setString(3, s.ds.st2.get());
				prep.setString(4, s.v.stM.get());
				if (s.v.notNull()) {
					prep.setBytes(5, T.InputStreamToBytes(s.v.get()));
				}
				prep.execute();
			}
			commit();
			prep.close();
		} catch (SQLException e) {
			MDT.error(e,sql,ae.word.stW.get());
		}
	}

	private static void error(SQLException e, PreparedStatement prep) {
		try {
			T.print(prep.getParameterMetaData().toString());
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private static void error(SQLException e, String ... yst) {
		T.print(yst);
		e.printStackTrace();
	}

	public static Phons getPhons(String astw) {
		astw=redirect(astw);
		Phons ps=new Phons();
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", 
					gsTablePhon,gsCWord));
			prep.setString(1,astw);
			ResultSet rs=prep.executeQuery();
			//QJDBT.print(rs);
			while(rs.next()) {
				Phon p=new Phon(rs.getString(gsCPhon), rs.getString(gsCUrl));
				p.v.set(T.BytesToInputStream(rs.getBytes(gsCVoiceByte)));
				ps.put(C.valueOf(rs.getString(gsCountry)), p);
			}
			prep.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}

	public static Translations getTrans(String astw) {
		astw=redirect(astw);
		
		Translations trans = new Translations();
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", gsTableEntry, gsCWord));
			prep.setString(1, astw);
			ResultSet rs = prep.executeQuery();
			// QJDBT.print(rs);
			if (rs.next()) {
				//T.msgbox(rs.getString(gsCTrans));
				trans.add(rs.getString(gsCTrans));
			}
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trans;
	}

	/** 在没有数据库时才建表 **/
	private static void createTable() {
		if (DBexists())
			return;
		try {
			// 在没有数据库时才做建表和插数据
			String sql = QText.format("create table %s("
					+ "%s char(50) NOT NULL," + "%s char(255),"
					+ "PRIMARY KEY (%s));", gsTableEntry, gsCWord, gsCTrans,
					gsCWord);
			gStat.executeUpdate(sql);
			/** sql二进制数据 blob **/
			sql = QText.format("create table %s(" + "%s char(50) NOT NULL,"
					+ "%s char(99)NOT NULL," + "%s char(99)," + "%s char(100),"
					+ "%s blob);", gsTableSenten, gsCWord, gsCEn, gsCn, gsCUrl,
					gsCVoiceByte);
			gStat.executeUpdate(sql);

			sql = QText.format("create table %s(" + "%s char(50) NOT NULL,"
					+ "%s char(25)NOT NULL," + "%s char(2)," + "%s char(100),"
					+ "%s blob);", gsTablePhon, gsCWord, gsCPhon, gsCountry,
					gsCUrl, gsCVoiceByte);
			gStat.executeUpdate(sql);

			sql = QText
					.format("create table %s(%s char(50) NOT NULL,%s char(13) NOT NULL);",
							gsTableFH, gsCWord, gsCTime);
			gStat.executeUpdate(sql);

			sql = QText.format("create table %s("
					+ "%s char(50) NOT NULL," + "%s char(50),"
					+ "PRIMARY KEY (%s));", gsTableRedirect, gsCWord, gsCRedirect,
					gsCWord);
			gStat.executeUpdate(sql);
			
			commit();
		} catch (Exception e2) {
			try {
				gConn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e2.printStackTrace();
		}
	}

	public static boolean DBexists() {
		return new File(T.autoPath(gsDbName)).exists();
	}
	////////////// Redirect////////////////////////
	/** gsCWord, gsCRedirect **/
	final static String gsTableRedirect = "Redirect";
	final static String gsCRedirect = "stRedirect";
	/**没有重定向则返回 astw自身*/
	public static String redirect(String astw) {
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", gsTableRedirect, gsCWord));
			prep.setString(1, astw);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				return rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return astw;
	}
	
	private static boolean setRedirect(String astw,String astRed) {
		PreparedStatement prep = null;
		String sql = QText.format("INSERT INTO %s(%s,%s) VALUES(?,?)",
				gsTableRedirect, gsCWord, gsCRedirect);
		try {
			prep = gConn.prepareStatement(sql);
			prep.setString(1, astw);
			prep.setString(2, astRed);
			prep.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
