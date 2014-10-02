package mh.database;

import static mh.database.MDT.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import qgb.text.QText;
import mh.gui.FindPanel;
import mh.gui.results.ResultsPanel;
import mh.net.AbstractGetEntry;
import mh.struct.entry.Entry;

public class DBEntry extends AbstractGetEntry{
	public static void main(String[] args) {

	}
	public DBEntry(ResultsPanel aRP) {
		super(1,aRP);
	}

	@Override
	public Entry byWord(String ast) {
		if (isStop()) return null;
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
		
		return null;
	}


}
