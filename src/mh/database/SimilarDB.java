package mh.database;

import static mh.database.MDT.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import qgb.text.QText;

public class SimilarDB {
	public static void main(String[] args) {

	}
	/******************* Test End ***********************/
	public static Collection<String> getSimWords(String astW) {
		try {
			PreparedStatement prep = gConn.prepareStatement(QText.format(
					"Select * From %s where %s=?;", 
					gsTableEntry,gsCWord));
			prep.setString(1,astW);
			ResultSet rs=prep.executeQuery();
			while (rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
