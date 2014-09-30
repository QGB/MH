package mh.struct;

import java.io.Serializable;

import mh.struct.entry.Word;
import qgb.T;
import qgb.interfaces.GetSet;
import qgb.interfaces.NotNullObject;
//,Serializable 
public class StrNotNull implements NotNullObject<String>,Serializable{
	/************************************/
	public static void main(String[] args) {
		String str="qgb",st2=str;
		StrNotNull sn=new StrNotNull(str);
		
		T.print("[%s]%b",st2,str==st2);
		//T.print("[%s]%b",sn.get(),sn.get()==str);
		str="qgb1";
		T.print("[%s]%b",st2,str==st2);
		//T.print("[%s]%b",sn.get(),sn.get()==str);
	}
/****************End Test **************/
	private String str;
	
	public StrNotNull() {}
	
	public StrNotNull(String str) {
		this.str = str;
	}
	
	@Override
	public String get() {
		if (str == null) {
			return "";
		}else {
			return str;	
		}
	}

	@Override
	public void set(String at) {
		/**String是不可变类**/
		str =at;
	}
	
	@Override
	public String toString() {
		return get();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	public boolean equals(StrNotNull ast) {
		return this.get().equals(ast.get());
	}
	/****/
	public void set(StrNotNull sNN) {
		str=sNN.str;
	}

	@Override
	public boolean notNull() {
		return (str!=null);
	}

}
