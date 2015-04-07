package qgb.project.ccsu.practicum2014.model;

import java.util.ArrayList;

import qgb.T;
import qgb.file.F;
import qgb.project.ccsu.practicum2014.view.Main;
import qgb.text.QText;
import static qgb.file.Serialize.gXStream;
import static qgb.project.ccsu.practicum2014.view.Utils.*;
public class DepartMents {
	public static void main(String[] args) {
		Main.main(null);
	}
///////////////////////////////////////////////////
	public static ArrayList<String> galS=new ArrayList<String>();
	private final static String gstPath="Practicum2014/DepartMents.galS.xml";
	
	static{
		if (F.isExist(gstPath)) {
			galS=(ArrayList<String>)gXStream.fromXML(T.read_st(gstPath));
		}else {
			galS.add("Default");
		}
	}
	
	public static void add() {
		try {
			String str=input("请输入新增的部门：");
			if (galS.contains(str)) {
				msgbox("部门已经存在！");
			}else{
				galS.add(str);
			}
		} catch (Exception e) {
			msgbox("新增部门失败！");
		}
	}
	
	public static void find() {
		try {
			String str=input("请输入待查找部门：");
			boolean notFind=true;
			for (int i = 0; i <galS.size(); i++) {
				if( galS.get(i).contains(str)){
					msgbox(QText.format("找到部门：No:%s %s", i,galS.get(i)) );
					notFind=false;
				}
			}
			if (notFind) {
				msgbox("不存在此部门！");
				return;
			}
		} catch (Exception e) {
			msgbox("查找部门失败！");
		}
	}
	
	public static void modify() {
		try {
			final int index=Integer.valueOf(input("请输入待修改的部门编号："));
			if (index<1||index>=galS.size()) {//0为默认部门
				msgbox("不存在此部门！");
				return;
			}
			galS.set(index, input("请输入新部门名字,原部门为："+galS.get(index))) ;
		} catch (Exception e) {
			msgbox("修改部门失败！");
		}
	}
	
	public static void save() {
		T.write(gstPath,gXStream.toXML(galS));
	}

	public static int getIndexByName(String st) {
		for (int i = 0; i < galS.size(); i++) {
			if (st.equals(galS.get(i))) {
				return i;
			}
		}
		T.argsError(st);
		return -1;
	}

}
