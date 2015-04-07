package test.thread;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import qgb.CharsetName;
import qgb.T;
/**
 * 结论：  String qgb.net.Get.html(String ast_url) 
 * 				throws FailingHttpStatusCodeException
 *  HtmlUnit 支持多线程
 * **/
public class HtmlUnitTest {

	public static void main(String[] args) {
		T.cst_test_path = T.cst_test_path + "HUT/";
		T.print(T.cst_test_path);
		PrintStream psErr;
		try {
			psErr = new PrintStream(T.cst_test_path + "psErr.txt",
					CharsetName.GST_UTF8);
			System.setErr(psErr);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ArrayList<Future<String>> results = new ArrayList<Future<String>>();

		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 9; i++)
			results.add(exec.submit(new HUT(i + "")));

		for (int i = 0; i < 9; i++) {
			Future<String> fs = results.get(i);
			try {
				T.write(i + "_gfs.html", fs.get(), CharsetName.GST_UTF8);
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
			} finally {
				exec.shutdown();
			}
		}
	}

}

class HUT implements Callable<String> {
	String gstr;

	public HUT(String gstr) {
		this.gstr = gstr;
	}

	@Override
	public String call() throws Exception {
		String str = qgb.net.Get.html("http://www.gfsoso.com/?q=" + gstr);
		return str;
	}
}