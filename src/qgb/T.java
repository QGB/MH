package qgb;

import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.swing.JOptionPane;

import qgb.file.F;
import qgb.text.QText;
import test.swing.component.JTextArea2;
/**QGB's java basic method**/
public final class T {
	public static String cst = "T";
	public static String cst_test_path = "D:/test/";
	/****/
	private T() {throw new Error("Don't let anyone instantiate this class!");}
	// /////////////////////////////////////////////////////////
	public static void main(String[] args) {
		T.print("java.awt.EventDispatchThread.pumpEvents(java.awt.event.ActionEvent[ACTION_PE".length());
		//getSource(aClass)
		//T.print(getCurrentMethod());
		//T.write("t.bin", "ast_text", CharsetName.GST_GBK);
		//getSource(T.class);
		//print("java.awt.EventDispatchThread.pumpEvents(java.awt.event.ActionEvent[ACTION_PERFORMED,cmd=,when=14".length());
	}

	// ///////////////////////////////////////////////////////
	/**Current class
	 * fixed at 2014-09-24 19:36:50**/
	public static Class<?> getCurrentClass() {
		StackTraceElement[] yste = Thread.currentThread().getStackTrace();
		if (yste.length<2) {
			return null;
		}
		/**getCurrentClass**/
		String str="";
		for (int i = 0; i < yste.length; i++) {
			if(yste[i].getMethodName().equals("getCurrentClass")){
				try {
					return Class.forName(yste[i+1].getClassName());
				} catch (ClassNotFoundException e) {	e.printStackTrace();}
			}
		}
		return null;
	}
//	public static String getCurrentClassName() {
//		return getCurrentClass().getName();
//	}
	/**TODO:无法区分重载方法**/
	public static Method getCurrentMethod() {
		StackTraceElement[] yste = Thread.currentThread().getStackTrace();
		if (yste.length<2) {
			return null;
		}
		/**getMethodName**/
		String str="";
		for (int i = 0; i < yste.length; i++) {
			if(yste[i].getMethodName().equals("getCurrentMethod")){
				Class<?> cC=null;
				try {
					cC=Class.forName(yste[i+1].getClassName());
				} catch (ClassNotFoundException e) {	e.printStackTrace();}
				Method[] ym=cC.getMethods();
				str= yste[i+1].toString();
				str=str.substring(0, str.lastIndexOf('('));
//				try {
//					T.print(cC.getMethod(str, null));
//				} catch (Exception e) {	e.printStackTrace();	}
				//T.print(str);
				for (int j = 0; j < ym.length; j++) {
					if (str.endsWith(ym[j].getName())){
						return ym[j];
						//T.msgbox(str);
					}
					//T.print(ym[j].getName());
					//T.print(ym[i].);
				}
			}
		}
		return null;
	}
	/**
	 * Thread safe</p>
	 * **/
	public static synchronized String argsError(Object... args)
			throws IllegalArgumentException {
		StackTraceElement[] yste=Thread.currentThread().getStackTrace();
		if (yste.length<2) {
			return null;
		}
		/**倒数第二项**/
		String str = yste[yste.length - 1-1].toString();
		//print(yste);
		StringBuilder sb = new StringBuilder("\n");
		sb.append(str.substring(0, str.lastIndexOf('(') + 1));
		int im = args.length;
		if (im > 0) {
			for (int i = 0; i < im - 1; i++) {
				sb.append(args[i] + ",");
			}
			sb.append(args[im - 1]);
		}

		sb.append(")|"
				+ str.substring(str.lastIndexOf('(') + 1, str.lastIndexOf(')')));
		// T.write("ste.txt", str);
		// T.print();
		throw new IllegalArgumentException(QText.lineWrap(sb, 76));
	}

	// //////////////////////////////////////////////////////
	public static void sleep(long alms) {
		try {
			java.lang.Thread.sleep(alms);
		} catch (InterruptedException e) {
			error(e, "T.sleep(" + alms + ") Interrupted!");
		}

	}

	// /////////////////////////////////////////////////////
	public static void printProperties() {
		Properties pes = System.getProperties();
		Set<String> set = pes.stringPropertyNames();
		for (String str : set) {
			System.out.printf("%s=%s\n", QText.pad(str, 29, " "),
					pes.getProperty(str));
		}
	}

	public static void printEclipse() {
		print("Max console line char length=96");
	}
	// /////////////////////////////////////////////////////////
	public static String getCurrentThreadName() {
		return Thread.currentThread().getName();
	}

	public static void thread(int ai) {
		T.print("%d:%s", ai, Thread.currentThread().getName());
	}

	// ///////////////////////////////////////////////////////
	public static String time() {
		return qgb.time.TimeConvert.toString(System.currentTimeMillis());
	}

	// ///////////////////////////////////////////////////////
	public static Process run(String ast) {
		Runtime rt = Runtime.getRuntime();
		try {
			return rt.exec(ast);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	// /////////////////////////////////////////////////////////
	public static String msgbox(Object message, Object initialSelectionValue)
			throws HeadlessException {
		return JOptionPane.showInputDialog(message, initialSelectionValue);
	}

	/**
	 * Shows a question-message dialog requesting input from the user. The
	 * dialog uses the default frame, which usually means it is centered on the
	 * screen.
	 * 
	 * @param message
	 *            the Object to display
	 * @return the <code>String</code> property
	 * @see java.awt.GraphicsEnvironment.isHeadless
	 */
	public static String msgbox(Object message){
		return JOptionPane.showInputDialog(message);
	}

	// /////////////////////////////////
	public static void notify(String ast_text) {
		Throwable taa = new Throwable();
		StackTraceElement[] yste = taa.getStackTrace();
		for (int i = 0; i < yste.length - 1; i++) {
			T.print((i + 1) + ":" + yste[yste.length - 1 - i]);
		}
		T.print(ast_text + "|" + getCurrentTime());
	}

	// ////////////////////////////////////////////////////////
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		// System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		return df.format(new Date());
	}

	/////////// Print /////////////////////////////
	/**Print Thread,Class,Time**/
	public static void print() {
		T.print(getCurrentThreadName());
		T.print(getCurrentClass().getName());
		T.print(getCurrentTime());
	}
	public static void print(Object aoa) {
		System.out.println(aoa);
	}
	public static void print(Collection<?> aoa) {
		for (Object o:aoa) {
			System.out.println(o.toString());
		}
	}
	public static void print(int[] ayi) {
		if (ayi == null) {
			return;
		}
		if (ayi.length > 0) {
			T.print("%s[%d]", "int", ayi.length);
		}
		String stFormat = "[%-" + get_intDigit(ayi.length) + "s]=%s";
		for (int i = 0; i < ayi.length; i++) {
			print(stFormat, i, ayi[i]);
		}
	}
	/**
	 * modified at 2014-07-31 17:35:23
	 * **/
	public static void print(Object ayo[]) {
		if (ayo == null) {
			return;
		}
		if (ayo.length > 0) {
			T.print("%s[%d]", ayo[0].getClass().getName(), ayo.length);
		}
		String stFormat = "[%-" + get_intDigit(ayo.length) + "s]=%s";
		for (int i = 0; i < ayo.length; i++) {
			print(stFormat, i, ayo[i]);
		}
	}
	/**
	 * Fixed at 2014-08-12 20:02:29**/
	public static void print(String ast_text, int ai_size) {
		if (ast_text.contains("%")) {
			/**void qgb.T.print(String format, Object... args)**/
			T.print(ast_text+"%s", ai_size,"");
			//T.msgbox();
			return;
		}
		if (ai_size>ast_text.length()) {
			ai_size=ast_text.length();
		}
		System.out.println(ast_text.subSequence(0, ai_size));
	}
	/**
	 * fixed at 2014-07-12 22:06:13|auto new line
	 * **/
	public static void print(String format, Object... args) {
		System.out.printf(format + "\n", args);
	}
	///////////// Print End //////////////////////
	
	public static int get_intDigit(int ai) {
		return String.valueOf(ai).length();
	}

	// ////////////////////////////////////////////////////
	public static String cin(String ast_parentComponent, String ast_message) {
		return (JOptionPane.showInputDialog(ast_parentComponent, ast_message));
	}

	public static String cin(String ast_message) {
		return (JOptionPane
				.showInputDialog("Pleaese input text :", ast_message));
	}

	// ///////////////////////////////////////////////////

	/**
	 * writes ast_text to (cst_test_path +ast_filename)
	 * 
	 * @see java.io.FileWriter
	 * @see qgb.T.autoPath
	 */
	public static void write(String ast_filename, String ast_text) {
		FileWriter fw;
		try {
			fw = new FileWriter(autoPath(ast_filename));
			fw.write(ast_text);
			fw.flush();
			// tprint(ast_text);
		} catch (IOException e) {

			e.printStackTrace();
		}
		// print(cst + "|" + cst_test_path + ast_filename);
	}

	/**如果ast_filename以 .开头，则为当前路径
	 * </p> modified at 2014-07-23 02:08:51 modified at 2014-07-31 03:00:19
	 **/
	public static String autoPath(String ast_filename) {
		if (ast_filename.startsWith(".")) {
			return ast_filename;
		}
		if (isFullPath(ast_filename)) {// 测试文件名是否为全路径

			make_dirs(ast_filename);
			return ast_filename;
		} else {
			make_dirs(cst_test_path + ast_filename);
			return (cst_test_path + ast_filename);
		}
	}

	/**
	 * Encodes this {@code String} into a sequence of bytes using the named
	 * charset, storing the result into (cst_test_path +ast_filename).
	 * 
	 * @param ast_filename
	 *            .
	 * @param ast_text
	 *            .
	 * @param ast_bytes
	 *            . The name of a supported
	 *            {@linkplain java.nio.charset.Charset charset} ,"UTF-8" etc.
	 * @see java.nio.charset.Charset
	 * @see qgb.T#write(String, InputStream)
	 * @see java.lang.String#getBytes()
	 */
	public static void write(String ast_filename, String ast_text,
			String CharsetName) {
		InputStream isa;
		try {
			isa = BytesToInputStream(ast_text.getBytes(CharsetName));
			write(ast_filename, isa);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		T.print(getCurrentMethod());
	}

	/**
	 * 2014-2-7 23:00:06
	 * 
	 * @version 1.1 fixed
	 */
	public static void write(String ast_filename, InputStream abis) {
		byte[] data = new byte[1];
		String sFileName = autoPath(ast_filename);
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(sFileName));
			while (abis.read(data) != -1) {
				bos.write(data);
				// T.notify("T.write(" + cst_test_path + ast_filename
				// + ",InputStream)");
			}

			// 将缓冲区中的数据全部写出
			bos.flush();

			// 关闭流
			// bufferedInputStream.close();
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void write(String ast_filename, byte[] ayb) {
		write(ast_filename, BytesToInputStream(ayb));

	}

	// /////////////////////////////////////////////////////////////
	public static void make_dirs(String fileName) {
		File f = new File(fileName);
		// T.print("%s isD %b",fileName,F.isDirectory(fileName));
		/** 不能使用File.isDirectory()判断一个不存在的文件是否为目录 **/
		if (F.isDirectory(fileName) == false) {
			f = f.getParentFile();
			if (f == null) {
				return;
			}
		}
		// T.print(f.getPath());
		if (f.exists()) {
			// notify("illegal fileName="+fileName);
			return;
		}
		f.mkdirs();
		if (f.exists() == false) {
			notify("illegal fileName=" + fileName);
			return;
		}
	}

	// ////////////////////////////////////////////
	/**
	 * Automatic judgment on the file path
	 * <p>
	 * default charset is GBK
	 * 
	 * @version 1.1
	 * @see qgb.T#read_st(String, String)
	 */
	public static String read_st(String ast_filename) {
		BufferedInputStream bis;
		bis = read_bis(autoPath(ast_filename));
		InputStreamReader isr = new InputStreamReader(bis);

		// print("qgb"+isr.getEncoding());
		try {
			String str = new String(InputStreamToBytes(bis), isr.getEncoding());
			bis.close();
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Automatic judgment on the file path
	 * <p>
	 * decodes this file into a sequence of {@code String} using the named
	 * charset.
	 * 
	 * @param ast_filename
	 * 
	 * @param ast_bytes
	 *            , The name of a supported
	 *            {@linkplain java.nio.charset.Charset charset} ,"UTF-8" etc.
	 * @see java.nio.charset.Charset
	 * @see qgb.T#read_bis(String)
	 * @see java.lang.String#getBytes()
	 */
	public static String read_st(String fileName, String ast_CharsetName) {
		try {
			return new String(InputStreamToBytes(read_bis(fileName)),
					ast_CharsetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Automatic judgment on the file path
	 * 
	 * @see java.io.File
	 */
	public static BufferedInputStream read_bis(String fileName) {
		File file = new File(autoPath(fileName));

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		// MP3player.play(bis);//test
		return bis;
	}

	public static boolean isFullPath(String fileName) {
		return (fileName.contains(":"));
	}

	// ////////////////////////////////////////////////////////////////
	public static void exit(boolean... abnotify) {
		if (abnotify.length < 1) {
			System.exit(0);
		}
		notify("Exit!");
		System.exit(0);
	}

	public static void exit() {
		System.exit(0);
	}

	// //////////////////////////////////////////////////////////////
	public static String repeat(int itimes, String ast_text) {
		if (itimes < 1) {
			return null;
		}
		String string = "";
		for (int i = 0; i < itimes; i++) {
			string = string + ast_text;
		}
		return string;
	}

	// //////////////////////////////////////////////////////////////////////
	public static String[] yObjectToYStr(Object[] ayo) {
		String[] yst = new String[ayo.length];
		for (int i = 0; i < ayo.length; i++) {
			yst[i] = ayo[i].toString();
		}
		return yst;

	}

	// //////////////////////////////////////////////////////////////////
	/**
	 * Tested 2014-3-24 0:46:50
	 * 
	 * @param abis
	 * @param ast_charSet
	 * @return
	 * @throws IOException
	 */
	public static String InputStreamToString(InputStream abis,
			String ast_charSet) {
		if (abis == null) {
			T.notify("InputStream null!");
			return null;
		}
		String str = "";
		try {
			InputStreamReader isr = new InputStreamReader(abis, ast_charSet);
			int ich;
			while ((ich = isr.read()) != -1) {
				str = str + (char) ich;
			}

			isr.close();
		} catch (UnsupportedEncodingException e) {
			T.error(e, "UnsupportedEncodingException");
		} catch (IOException e) {
			T.error(e, "IOException");
		}
		// T.print(str);
		return str;
	}

	// //////////////////////////////////////////////////////////////////
	/**if parameter is null then return null**/
	public static byte[] InputStreamToBytes(InputStream ais) {
		if (ais == null) {
			return null;
		}
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		byte imgdata[] = null;
		try {
			while ((ch = ais.read()) != -1) {
				bytestream.write(ch);
			}
			imgdata = bytestream.toByteArray();
			bytestream.close();
			ais.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return imgdata;
	}

	// ////////////////////////////////////////////////////////////////
	/**if parameter is null then return null**/
	public static InputStream BytesToInputStream(byte[] b) {
		if (b==null) {
			return null;
		}else {
			return new ByteArrayInputStream(b);
		}
	}

	// //////////////////////////////////////////////////////////////////
	public static void error(Exception e) {
		e.printStackTrace();

	}

	public static void error(Exception e, String ast) {
		e.printStackTrace();
		T.print(ast + "|" + time());

	}

	public static byte[] read_byteArray(String fileName) {
		return InputStreamToBytes(read_bis(fileName));
	}

	public static void delFile(String string) {
		File file = new File(string);
		if (file.exists()) {
			if (file.delete() == false) {
				T.notify("T.delFile error！ file is not  deleted!");
			}
		} else {
			T.notify("T.delFile error！ file is not found!");
		}

	}

	public static void setProxy(String asIP, String asPort) {
		// ,这个是用JAVA自带的代理方法设置上网,用htmlunit的情况下不生效，可以自己试试
		System.getProperties().setProperty("proxySet", "true");
		// 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
		System.getProperties().setProperty("http.proxyHost", asIP);
		System.getProperties().setProperty("http.proxyPort", asPort);
	}

	/*********** setOutStream *************************/
	public static void setOutStream(String ast) {
		try {
			PrintStream ps = new PrintStream(autoPath(ast),
					CharsetName.GST_UTF8);
			System.setOut(ps);
		} catch (FileNotFoundException e) {
			T.error(e, ast + " Not Found");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void resetOutStream() {
		resetOutStream(true, CharsetName.GST_UTF8);
	}
	public static void resetOutStream(String asCharsetName) {
		resetOutStream(true, asCharsetName);
	}
	public static void resetOutStream(boolean bFlush,String asCharsetName) {
		try {
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out),
			bFlush,asCharsetName));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/*********** setOutStream End ********************/
	public static void msgbox() {
		msgbox("");
	}
	/**The method max(int, int) in the type T is not applicable for the arguments (int, null)**/
	public static int max(int aia, int aib) {
		if (aia > aib) {
			return aia;
		}
		return aib;
	}
	public static int min(int aia, int aib) {
		if (aia < aib) {
			return aia;
		}
		return aib;
	}
	
	public static String getSource(Class<?> aClass) {
		String stp=JTextArea2.class.getProtectionDomain().getCodeSource().getLocation().toString();
		if (stp.endsWith("/bin/")==false)return "Not Eclipse!";
		stp=stp.substring(6, stp.length()-4)+"src/";
		stp=stp+aClass.getName().replace('.','/')+".java";
		//print(stp);
		return T.read_st(stp);
	}


}
