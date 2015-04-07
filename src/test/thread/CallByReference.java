package test.thread;

import qgb.T;

public class CallByReference {
	
	public static void main(String[] args) {
		Parm parm=new Parm(" p main");
		T.print(T.Thread()+parm.get());
		
		RunDemo rd=new RunDemo(parm);
		parm.set(parm.get()+" mc0");
		
		Thread tRun=new Thread(rd);
		tRun.start();
		T.sleep(9);
		T.print(T.Thread()+parm.get());
		parm.set(parm.get()+" mc1");
		T.print(T.Thread()+parm.get());
	}

}

class RunDemo implements Runnable{
	Parm gp;
	
	public RunDemo(Parm ap) {
		this.gp = ap;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		T.print(T.Thread()+gp.get());
		gp.set(" p run");
		//gp.print();
		T.print(T.Thread()+gp.get());
		T.sleep(19);
		T.print(T.Thread()+gp.get());
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