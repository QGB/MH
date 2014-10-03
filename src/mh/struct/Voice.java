package mh.struct;

import java.io.InputStream;
import java.util.Arrays;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import qgb.T;
import qgb.interfaces.GetSet;
import qgb.interfaces.NotNullObject;
import qgb.media.MP3player;
import qgb.text.QText;
import test.t2;

public class Voice implements NotNullObject<InputStream> {
	/**string of voice URL**/
	public final StrNotNull stM = new StrNotNull();
	private volatile byte[] bdata;

	public Voice() {
		this("", null);
	}
	public Voice(String astUrl) {
		this(astUrl, null);
	}
	public Voice(String astMark,InputStream ais) {
		stM.set(astMark);
		set(ais);
	}

	@Override
	public InputStream get() {
		return T.BytesToInputStream(bdata);
	}

	@Override
	public void set(InputStream ais) {
		bdata = T.InputStreamToBytes(ais);
	}
	public void set(String astMark) {
		stM.set(astMark);
	}
	public void set(String astMark,InputStream ais) {
		set(astMark);
		set(ais);
	}
	public void set(Voice av) {
		if (av.bdata!=null&&av.stM.get()!="") {
			bdata=av.bdata;
			stM.set(av.stM);;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	public boolean equals(Voice av) {
		return av.stM.equals(this.stM)&&Arrays.equals(av.bdata, this.bdata);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(super.toString());
		if (stM.get().length()>20) {
			sb.append("[stM="+QText.getBegins(stM.get(),20)+"...");
		}else {
			sb.append("[stM="+stM.get());
		}
		sb.append(",notNull()="+notNull()+"]");
		return sb.toString();
	}
	public void play() {
		if (notNull()) {
			Play play=new Play(get());
			Thread thread=new Thread(play);
			thread.start();
		}
	}
	/*************  Play  ***************/
	public static Player gPlayer;
	public static void stopPlay() {
		if (gPlayer==null) {
			return;
		}
		gPlayer.close();
		//T.print("stop at "+T.Thread());
	}
	private class Play implements Runnable{
		InputStream gis;
		
		public Play(InputStream gis) {
			if(gis==null){
				throw new NullPointerException();
			}
			this.gis = gis;
		}

		@Override
		public void run() {
			stopPlay();
			try {
				gPlayer=new Player(gis);
				gPlayer.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
			//T.print("Play END");
		}
	}
	/*************  Play End  ***********/
	@Override
	public boolean notNull() {
		if (bdata==null) {
			return false;
		}
		if (bdata.length==0) {
			return false;
		}
		return true;
	}
	
}


// ///////////////////////////////////////