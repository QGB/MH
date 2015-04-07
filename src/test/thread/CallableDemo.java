package test.thread;

//: concurrency/CallableDemo.java
/**
 *  java传值or传引用 测试；
 *  Callable 测试；
 * **/

import java.util.concurrent.*;
import java.util.concurrent.TimeoutException;
import java.util.*;

import qgb.T;

class TaskWithResult implements Callable<String> {
	private int id;
	private Parm gp;

	public TaskWithResult(Parm ap) {
		this.gp = ap;
		
	}

	public String call() {
		long la = (long) Integer.MAX_VALUE - Integer.MIN_VALUE;
		la = la / 10;
		T.sleep(3000);
		//T.print(la);
		T.print(T.Thread()+gp.get());
		gp.set(gp.get()+" 2");
		int ia = 0, ib = 0;
		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
			ia++;
			if (ia == la) {
//				if () {
//					
//				}
				ia = 0;
				T.print(++ib);
				if (ib==2) {
					gp.print();
					//Thread.currentThread();
					//return "";
				}
			}

		}
		//
		T.Thread(id + 1);
		return "result of TaskWithResult " + id;
	}
}

public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Parm parm=new Parm("qgb call test!");
		
		//Parm gsp
		TaskWithResult call_t=new TaskWithResult(parm);
		
		parm.set(parm.get()+" 1");
		T.print(T.Thread()+parm.get());
		Future<String> fs = exec.submit(call_t);
		String str = null;
		try {
			// get() blocks until completion:
			str=fs.get(1, TimeUnit.SECONDS);
			// System.out.println(fs.get());
		} catch (InterruptedException e) {
			e.printStackTrace();

		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			fs.cancel(true);
			e.printStackTrace();
			//T.exit();
			
		} finally {
			fs.cancel(true);
			exec.shutdown();
			exec.shutdownNow();
		}
		
		T.print("fs.isCancelled()=%b\nfs.isDone()=%b", 
				fs.isCancelled(), fs.isDone());
		T.print("exec.isShutdown()=%b\nexec.isTerminated()=%b", 
				exec.isShutdown(), exec.isTerminated());
		
		T.print(T.Thread()+parm.get());
		T.print(str);
	}
}

class Parm{
	private String gst="";
	public static Parm gsp;
	
	public Parm(String ast) {
		this.gst = ast;
	}

	public String get() {
		return gst;
	}
	
	public void set(String ast){
		gst=ast;
	}
	
	public void print(){
		T.msgbox(gst);
	}
}