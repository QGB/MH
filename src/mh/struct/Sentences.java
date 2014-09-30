package mh.struct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import mh.struct.entry.Senten;
import test.swing.miglayout.AddingToGrid;


public class Sentences extends ArrayList<Senten> implements Serializable{
	public Sentences() {
		super();
	}
//	public int add(DoubleSt aDS) {
//		add(new Senten(aDS));
//		return size()-1;
//	}
	
	public void add(DoubleSt aDS,Voice av) {
		add(new Senten(aDS,av));
	}
	public void add(String astEn,String astCn,Voice av) {
		add(new DoubleSt(astEn,astCn),av);
	}
	
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(Senten sent:this){
			sb.append(sent.toString());
			sb.append("\n-----------\n");
		}
		return sb.toString();
	}
}
