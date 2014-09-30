package qgb.jdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import qgb.T;


public class QJDBT {

	public static void main(String[] args) {

	}

	public static void printType(ResultSet ars) {
		Field[] yf=ResultSet.class.getFields();
		for(Field f:yf){
				try {
					if (f.getInt(null)==ars.getType()) {
						T.print(".type=%s.%s(%s)",f.getClass().getName(),f.getName(),f.getInt(null));	
					}
				} 
				catch (IllegalArgumentException e) {e.printStackTrace();} 
				catch (IllegalAccessException e) {e.printStackTrace();}
				catch (SQLException e) {
					e.printStackTrace();
				} 
	
		}
	}
	public static void print(ResultSet ars) {
		T.print(ars.toString()+" Info:");
		try {
			T.print(".size=%d",ars.getFetchSize());
		} catch (SQLException e) {e.printStackTrace();}
		printType(ars);
	}
}
