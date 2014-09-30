package qgb.text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import mh.struct.StrNotNull;
import qgb.CharsetName;
import qgb.T;
/**modified at 2014-09-22 21:21:51**/
public class QText {
	static int in = 0;

	public static void main(String[] args) throws IOException {
		T.print("[%s]",lineWrap("0123456789Abcdefghijk",3));
		//T.print(pad("123", 9, "AB"));
		// T.print(used_chars(T.read_st("D:/jass.txt")));
		// T.print(formatNum(1, "00"));
		// String str ="1234";
		// T.print(str.indexOf("2"));
		// T.print(str.substring(0,str.indexOf("3") ));
	}

	// /////////////////////////////////////////////
	// public static String[](String astext,String ast_)
	// {
	// astext.
	// return df.format(ao);
	// }

	// /////////////////////////////////////////////
	/**format Decimal
	 * 2014-7---**/
	public static String formatNum(Object ao, String ast) throws IOException {
		DecimalFormat df = new DecimalFormat(ast);
		// T.print( df.format(ao));
		return df.format(ao);
	}

	// /////////////////////////////////////////////////
	public static String repeat(int itimes, String ast_text) {
		if (itimes < 1) {
			return "";
		}
		String string = "";
		for (int i = 0; i < itimes; i++) {
			string = string + ast_text;
		}
		return string;
	}

	public static String used_chars(String ast_text) {
		String ta = "";
		String tb = "";
		for (int i = 0; i < ast_text.length(); i++) {
			tb = ast_text.substring(i, i + 1);
			if (ta.contains(tb) == false) {
				ta = ta + tb;
			}
		}
		return ta;
	}

	public static String padHead(Object aBePaded, int ai, String astPad) {
		int ia = ai - aBePaded.toString().length();
		if (ia < 0) {
			T.notify("aBePaded too large!");
			return aBePaded.toString();
		}
		int im = ia / astPad.length();
		int ir = ia % astPad.length();

		return (repeat(ir, " ") + repeat(im, astPad) + aBePaded.toString());
	}

	public static String pad(Object aBePaded, int ai, String astPad) {
		int ia = ai - aBePaded.toString().length();
		if (ia < 0) {
			T.notify("aBePaded too large!");
			return aBePaded.toString();
		}
		int im = ia / astPad.length();
		int ir = ia % astPad.length();

		// System.out.printf("ai=%d\nia=%d\nim=%d\nast.l=%d\nir=%d\n",
		// ai,ia,im,astPad.length(),ir);

		return (aBePaded.toString() + repeat(im, astPad) + repeat(ir, " "));
	}

	public static String pad(Object aBePaded, int ai) {

		int ia = ai - aBePaded.toString().length();
		if (ia < 0) {
			T.notify("aBePaded too large!");
			return aBePaded.toString();
		}

		// System.out.printf("ai=%d\nia=%d\nim=%d\nast.l=%d\nir=%d\n",
		// ai,ia,im,astPad.length(),ir);

		return (aBePaded.toString() + repeat(ia, " "));
	}

	public static String getBegins(String ast, int ai) {
		return (String) ast.subSequence(0, T.min(ast.length(),ai));
	}
	/**可用于参数化SQL语句；
	 * 2014-09-21 00:03:01**/
	public static String format(String format, Object... args) {
		ByteArrayOutputStream byos=new ByteArrayOutputStream();
		PrintStream ps=new PrintStream(byos,true);
		ps.format(format, args);
		try {
			return byos.toString(CharsetName.GST_DEF);
		} catch (UnsupportedEncodingException e) {e.printStackTrace();}
		return "";
	}
	/**eclipse 76,96**/
	public static String lineWrap(StringBuilder asb,int aiColumns) {
		if(asb==null)return null;
		if(aiColumns<1||asb.length()<=aiColumns)return asb.toString();
		int ir=asb.length(),ic=aiColumns;//,irows=imax/aiColumns,ir=imax%aiColumns+irows;
		//if((ir)>aiColumns)irows+=ir/aiColumns
		while (ir>aiColumns) {
			asb.insert(ic,'\n');
			ic+=aiColumns+1;
			ir-=(aiColumns);
			//T.print("r=%d,c=%d",ir,ic);
		}
		//if(ir>0)asb.insert(ic,'\n');
		return asb.toString();
	}
	public static String lineWrap(String ast,int aiColumns) {
		return lineWrap(new StringBuilder(ast), aiColumns);
	}
}
