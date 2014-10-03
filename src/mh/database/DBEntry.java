package mh.database;

import static mh.database.MDT.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import qgb.jdbc.QJDBT;
import qgb.text.QText;
import mh.gui.FindPanel;
import mh.gui.MainFrame;
import mh.gui.results.ResultsPanel;
import mh.net.AbstractGetEntry;
import mh.struct.entry.Entry;

public class DBEntry extends AbstractGetEntry{
	public static void main(String[] args) {
		DBEntry dbE=new DBEntry(null);
		dbE.byWord("book");
		MainFrame.main(null);
	}
	public DBEntry(ResultsPanel aRP) {
		super(1,aRP);
	}

	@Override
	public Entry byWord(String ast) {
		if (isStop()) return null;
		Entry entry=new Entry();
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", 
					gsTableEntry,gsCWord));
			prep.setString(1,ast);
			ResultSet rs=prep.executeQuery();
			QJDBT.print(rs);
			//			if (rs.next()) {
//				for(int i=0)
//			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


}
