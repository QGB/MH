package test.thread;

import qgb.T;
import qgb.text.Text;

public class ThreadState {
	static Runnable ra=new Runnable() {
		public void run() {
			for (int i = 0; i < 9; i++) {
				T.sleep(555);
				for (int j = 0; j < Integer.MAX_VALUE; j++) {
					i=i+1;
					i=i-1;
				}
			}
		}
	};
	static Thread ta=new Thread(ra);
	
	static Runnable rb=new Runnable() {
		@Override
		public void run() {
			for (int i = 0; i < 99999; i++) {
				T.print(Text.pad(i, 5)+":"+ ta.getState());
				T.sleep(1);
			}
			T.exit();
		}
	};
	
	static Thread tb=new Thread(rb);
	
	public static void main(String[] args) {
		T.print(ThreadState.class);
		tb.start();
		T.sleep(3);
		ta.start();
	}

}
