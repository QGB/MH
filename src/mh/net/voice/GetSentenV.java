package mh.net.voice;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit.GoToMatchingBracketAction.EndAction;

import qgb.T;
import qgb.interfaces.QRunable;
import qgb.net.Get;
import mh.MainLoader;
import mh.gui.MainFrame;
import mh.gui.results.ResultsPanel;
import mh.gui.results.senten.SentencesPanel;
import mh.struct.Voice;
/**�������дΪ�̳� GetVoice ���ִ���
 * û�б�Ҫ����׷�������**/
public class GetSentenV implements QRunable{
	final Voice gv;
	final ResultsPanel gRP;
	final int gi;
	
	public GetSentenV(Voice gv, ResultsPanel aRP, int gi) {
		this.gv = gv;
		gRP=aRP;
		this.gi = gi;
	}

	@Override
	public void run() {
		end();
		if(true)return;
		long lt=System.currentTimeMillis();
		InputStream is=null;
		try {
			is=Get.urlfile(gv.stM.get());
		} catch (Exception e) {
			return;
		} 
		if (gbNotStop) {
			gv.set(is);
			/**�����������gSsP.addVoice��������Ϊ
			 * gSsP.addSenten��δ��ɶ��׳�NullPointerException
			 * �������� 500��һ���ܱ�֤��ȫ�Ľ�Сֵ����������
			 * 999 **/
			lt=System.currentTimeMillis()-lt;
			//T.print(lt);
			if (lt<999) {
				T.sleep(999-lt);
			}
			/***********************/
			gRP.gSsP.addVoice(gv,gi);
			end();
		}
		
	}
	
	private void end() {
		gRP.gPP.go(gv.stM.get());
		gbIsDone=true;
		gbNotStop=false;
	}

	private boolean gbNotStop=true;
	public void stop(){gbNotStop=false;}
	public boolean isStop() {return !gbNotStop;}
	private boolean gbIsDone=false;
	public boolean isDone() {return gbIsDone;}
	
	public static void main(String[] args) {
		MainLoader.main(null);
	}
}
