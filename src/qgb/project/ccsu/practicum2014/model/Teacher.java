package qgb.project.ccsu.practicum2014.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import mh.database.MDT;
import qgb.T;
import qgb.project.ccsu.practicum2014.view.Main;
import qgb.text.QText;
import static qgb.project.ccsu.practicum2014.view.Utils.*;

public class Teacher implements Serializable {
	public static final String gsFDM = "giDM";

	public static void main(String[] args) throws ClassNotFoundException {
		Main.main(null);
		T.print(getFields());
		T.print(T.getCurrentMethod());
	}

	public static String[] getFields() {
		Class<Teacher> cT = Teacher.class;
		Field[] yf = cT.getDeclaredFields();
		ArrayList<String> als = new ArrayList<String>();
		for (int i = 0; i < yf.length; i++) {
			/**getModifiers() 返回字段属性的组合值*/
			if (yf[i].getModifiers()> Modifier.STATIC) {
				continue;
			}
			//T.print("%s-%s",yf[i].getModifiers(),yf[i].getName());
			als.add(yf[i].getName());
		}
		return als.toArray(new String[1]);
	}

	// ///////////////////////////////////
	private int giNum = -1;
	private String gsname;
	private int giDM = 0;// 默认部门
	private int giWork = 0;//
	private String gsDegree="教授";
	private int giDate=19780605;
	public String getGsDegree() {
		return gsDegree;
	}

	public void setGsDegree(String gsDegree) {
		this.gsDegree = gsDegree;
	}

	public int getGiDate() {
		return giDate;
	}

	public void setGiDate(int giDate) {
		this.giDate = giDate;
	}
	
	public int getGiWork() {
		return giWork;
	}

	public void setGiWork(int giWork) {
		this.giWork = giWork;
	}

	public int getGiDM() {
		return giDM;
	}

	public void setGiDM(int giDM) {
		this.giDM = giDM;
	}

	public int getGiNum() {
		return giNum;
	}

	public void setGiNum(int giNum) {
		if (giNum < 0
				|| Teachers.innerFind(QText.format("Num:%s\n", giNum)) != -1) {
			msgbox("编号出错：" + giNum);
			T.argsError(giNum);
		}
		this.giNum = giNum;
	}

	public String getGsname() {
		return gsname;
	}

	public void setGsname(String gsname) {
		if (gsname == null || gsname.length() < 1) {
			T.argsError(gsname);
		}
		this.gsname = gsname;
	}

	public Teacher() throws Exception {
		modify();
	}

	public Teacher(int giNum, String gsname) {
		setGiNum(giNum);
		setGsname(gsname);
	}

	public boolean contains(String ast) {
		return toString().contains(ast);
	}

	@Override
	public String toString() {
		return QText.format("Num:%s\n" + "Name:%s\n", giNum, gsname);
	}

	public void modify() throws Exception {
		setGiNum(Integer.valueOf(input("请输入教师编号",
				giNum == -1 ? Teachers.galT.size() + 1 : giNum)));
		setGsname(input("请输入教师名字", "名字"));
	}

}
