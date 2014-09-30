import qgb.T;


public class Person{
	String name;
	String telephone,department;
    public Person(String astFile) {
    	String str=T.read_st(astFile);
    	int ia=0,ib=0;
    	ia=str.indexOf("N[");
    	ib=str.indexOf("]N");
    	name=str.substring(ia+2,ib);
    	T.print(name);
    	
    	ia=str.indexOf("D[");
    	ib=str.indexOf("]D");
    	department=str.substring(ia+2,ib);
    	T.print(department);
    	
    	ia=str.indexOf("T[");
    	ib=str.indexOf("]T");
    	telephone=str.substring(ia+2,ib);
    	T.print(telephone);
    }

	public Person() {
	}
	/**Only for test**/
	public Person(String astName,String astTel) {
		name=astName;
		telephone=astTel;
	}

	
	public boolean search(String astSearch) {
	
	
	T.print(name.contains(astSearch));
	return name.contains(astSearch);
	

	}

	public boolean isName(String astname) {
		return name.equals(astname);
	}
	
	public  void setName() {
		name=T.msgbox("输入姓名:");
	}
	
	public void setTelephone() {
		telephone=T.msgbox("输入电话:");
	}
	public void setDepartment(){
		department=T.msgbox("输入部门:");
	}
	public void WriteFile(String ast){
		T.write(ast,"N["+ name+"]ND["+department+"]DT["+telephone+"]T");
	}

	
	@Override
	public String toString() {
		return "Person [name=" + name + ", telephone=" + telephone
				+ ", department=" + department + "]";
	}

	public static void main(String[] args) {
		 Person p=new Person();
		 p.setTelephone();
		 p.setName();
		 p.setDepartment();
		 p.WriteFile("1");
		 p.search("1");
		 
		//Person p2=new Person("1");
	}
	
}
