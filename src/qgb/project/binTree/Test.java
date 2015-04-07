package qgb.project.binTree;

import java.util.Arrays;

import qgb.T;


public class Test {
    
    public static void main(String[] args){
    	//T.print(Arrays.copyOfRange("ABCDEF".toCharArray(), 0, 3)); 
    	//T.print(Arrays.copyOfRange("ABCDEF".toCharArray(), 3+1, 4+1));
    	//T.exit();
    	TextNode T;
    	char[] DLR,LDR;
//        T = new TextNode("A");
//        T.setL("B");          
//        T.setR("C");        
//        T.L.setR("D");     
//        T.R.setL("E");  
//        T.R.L.setR("G");
//        T.R.setR("F");
//        T.R.R.setL("H");
//        T.R.R.setR("I");
//        System.out.println(TreePrinter.TreeString(T));
//        
//        DLR="ABCDEF".toCharArray();LDR="CBAEDF".toCharArray();
//        T=cTree(DLR, LDR);
//        System.out.println(TreePrinter.TreeString(T));
//        
        DLR="ABCDEFGHIJ".toCharArray();LDR="BCDAFEHJIG".toCharArray();
        T=cTree(DLR, LDR);
        System.out.println(TreePrinter.TreeString(T));
    }
    
    public static TextNode cTree(char[] aDLR,char[] aLDR) {
		if (aDLR.length!=aLDR.length||aDLR.length==0) {
			return null;
		}
    	if (aDLR.length==1) {
			return new TextNode(aDLR[0]);
		}else if (aDLR.length==2) {
			TextNode T=new TextNode(aDLR[0]);
			T.setL(aDLR[1]);
			return T;
		}
		
		TextNode T=new TextNode(aDLR[0]);
		int i=getIndex(aLDR,aDLR[0]);
		char[] LDR1=Arrays.copyOfRange(aLDR, 0, i);
		
		T.L=cTree(Arrays.copyOfRange(aDLR, 1, i+1),LDR1);
		
		char[] LDR2=Arrays.copyOfRange(aLDR, i+1, aLDR.length);
		
		T.R=cTree(Arrays.copyOfRange(aDLR,i+1,aDLR.length),LDR2);
		
		return  T;
		//T
		
		//T.setL(aLDR[]);
	}

	private static int getIndex(char[] aLDR, char c) {
		for (int i = 0; i < aLDR.length; i++) {
			if (aLDR[i]==c) {
				return i;
			}
		}
		T.argsError(aLDR,c);
		return -1;
	}
}
