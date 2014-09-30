package test.thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

class tt implements ActionListener {
	ttt t;
	Timer time;

	public tt(ttt t) {
		this.t = t;
		time = new Timer(1000, this);
		time.setRepeats(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		time.stop();
	}

	

}

class ttt extends Thread {
	tt temp;
	int i = 0;

	public ttt() {
		temp = new tt(this);
	}

	public void run() {
		temp.time.start();
		while (i < 10000) {
			System.out.print(i + " ");
			i++;
			if (!temp.time.isRunning()) {
				System.out.println("End");
				return;
			}
		}
	}
}

public class SwingTimerTest {
	public static void main(String args[]) {
		ttt tttt = new ttt();
		tttt.start();
	}
}