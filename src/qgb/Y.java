package qgb;

import java.util.ArrayList;
import java.util.List;
public class Y {  
  
    public static void main(String [] args) {  
    	String[] yst={"1", "2", "3", "4" ,"5", "6", "7", "8"};
    	ArrayList<Object> al=ArrayToArrayList(yst);
    	al=addArrayToArrayList(al, yst);
    	T.print(al.toArray());
    	int[] yi={1,2,34,5};
    	T.print(yi);
    }  
    /********************************************/
    public static ArrayList<Object> addArrayToArrayList(ArrayList<Object> aal,Object[] ayo) {
    	for (int i = 0; i < ayo.length; i++) {
			aal.add(ayo[i]);
		}
		return aal;
	}
    public static ArrayList<Object> ArrayToArrayList(Object[] ayo) {
		return addArrayToArrayList(new ArrayList<Object>(), ayo);
		
	}
    /***************************************/
    //ȥ���������ظ���ֵ  
    public static String[] del_muti(String[] str) {  
        List<String> list = new ArrayList<String>();  
        for (int i=0; i<str.length; i++) {  
            if(!list.contains(str[i])) {  
                list.add(str[i]);  
            }  
        }  

        String[] newStr =  list.toArray(new String[1]); //����һ���������ж����ָ�����͵�����   
        return newStr;
    }  
  
    public static String[] del_one_element(String[] ayst,int aidel) {  
        /*String [] str = {"Java", "C++", "Php", "C#", "Python"};  
        for (String elementA:ayst ) {  
            System.out.print(elementA + " ");  
        }  */
        //ɾ��php  
        List<String> list = new ArrayList<String>();  
        for (int i=0; i<ayst.length; i++) {  
            list.add(ayst[i]);  
        }  
        list.remove(aidel); //list.remove("Php")   
        //System.out.println();  
        return( list.toArray(new String[1]));
        /*String[] newStr =  list.toArray(new String[1]); //����һ���������ж����ָ�����͵�����   
        for (String elementB:newStr ) {  
            System.out.print(elementB + " ");  
        }     
        System.out.println();  */
    }  
  

	public static String ArrayToStr(String[] ayst, String ast_separator) {
		String str="";
		//T.print("["+ast_separator+"]");
		for (int i = 0; i < ayst.length; i++) {
			str=str+ayst[i]+ast_separator;
		}
		return str;
	}
	/**
	 * Windows ���з� \r\n**/
	public static String[] StrToArray(String ast,String ast_separator) {
		
		return ast.split(ast_separator);	
	}  
}  