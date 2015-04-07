package mh.net.voice;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import qgb.T;
import qgb.interfaces.QRunable;
import qgb.net.Get;
import mh.gui.results.ResultsPanel;
import mh.gui.results.WordPanel;
import mh.net.test.GetVoice;
import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Entry;
import mh.struct.entry.Phon;

public class GetPhonV implements QRunable{
	public static void main(String[] args) {
	}
	
	ResultsPanel gRP;
	final Voice gv;
	C gc;
	public GetPhonV(Voice av,ResultsPanel arp,C ac) {
		if (av==null) {
			T.argsError(av);
		}
		this.gv=av;
		gRP=arp;
		gc=ac;
		
	}
	
	@Override
	public void run() {
		long lt=System.currentTimeMillis();
		InputStream is=null;
		try {
			is=Get.urlfile(gv.stM.get());
		} catch (MalformedURLException e) {
			return;
		} catch (IOException e) {
			return;
		}
		if (gbNotStop) {
			gv.set(is);
			/**�����������gSsP.addVoice��������Ϊ
			 * gSsP.addSenten��δ��ɶ��׳�NullPointerException
			 * �������� 500��һ���ܱ�֤��ȫ�Ľ�Сֵ����������
			 * 999</br>
			 * �����޸Ķ��̵߳�һЩ���⣬�Ѿ�ȥ��sleep **/
			lt=System.currentTimeMillis()-lt;
			//T.print(lt);
			if (lt<555) {
				T.sleep(555-lt);
			}
			/***********************/
			gRP.gWP.addVoice(gv,gc);
			gRP.gPP.go(gv.stM.get());
			gbIsDone=true;
			gbNotStop=false;
		}
		
	}
	
	private boolean gbNotStop=true;
	@Override
	public void stop(){
		gbNotStop=false;
	}
	@Override
	public boolean isStop() {
		return !gbNotStop;
	}
	private boolean gbIsDone=false;
	@Override
	public boolean isDone() {
		return gbIsDone;
	}
	
}
