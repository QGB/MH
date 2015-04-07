package mh.database;

import static mh.database.MDT.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import qgb.T;
import qgb.swing.QST;
import qgb.text.QText;
import mh.gui.MainFrame;
import mh.gui.results.ResultsPanel;
import mh.net.AbstractGetEntry;
import mh.struct.entry.C;
import mh.struct.entry.Entry;
import mh.struct.entry.Phon;
import mh.struct.entry.Senten;

public class DBEntry extends AbstractGetEntry{
	public static void main(String[] args) {
//		DBEntry dbE=new DBEntry(null);
//		dbE.byWord("book");
		T.print(T.getSource(DBEntry.class));
		//MainLoader.main(null);
	}
	public DBEntry(ResultsPanel aRP) {
		super(1,aRP);
	}

	@Override
	public Entry byWord(String ast) {
		if (isStop()) return null;
		ast=MDT.redirect(ast);
		Entry entry=new Entry();
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", 
					gsTableEntry,gsCWord));
			prep.setString(1,ast);
			ResultSet rs=prep.executeQuery();
			//QJDBT.print(rs);
			if (rs.next()) {
				entry.word.stW.set(rs.getString(gsCWord));
				entry.trans.add(rs.getString(gsCTrans));
			}
			prep.close();
			
			
			gRP.gTP.showTrans(entry.trans);
			
			prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", 
					gsTablePhon,gsCWord));
			prep.setString(1,ast);
			rs=prep.executeQuery();
			//QJDBT.print(rs);
			while(rs.next()) {
				Phon p=new Phon(rs.getString(gsCPhon), rs.getString(gsCUrl));
				p.v.set(T.BytesToInputStream(rs.getBytes(gsCVoiceByte)));
				entry.word.ps.put(C.valueOf(rs.getString(gsCountry)), p);
			}
			prep.close();
			gRP.gWP.showWord(entry.word);
			
			prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", 
					gsTableSenten,gsCWord));
			prep.setString(1,ast);
			rs=prep.executeQuery();
			//QJDBT.print(rs);
			while (rs.next()) {
				Senten s=new Senten(rs.getString(gsCEn), rs.getString(gsCn));
				s.v.set(T.BytesToInputStream(rs.getBytes(gsCVoiceByte)));
				entry.sentces.add(s);
			}
			prep.close();
			gRP.gSsP.showSentences(entry.sentces);
			QST.refreshAll(gRP);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//entry.print("ey");
		entry.setting.gbIsFromDB=true;
		return entry;
	}


}
