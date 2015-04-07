package qgb.media;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import qgb.T;
import qgb.net.Get;//test


public class MP3player {
	
	public static void play(InputStream ais) {
			Player player;
			try {
				player = new Player(ais);
				player.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
	}

	public static void main(String[] args) throws Exception {
		//MP3player mp3 = new MP3player("D:/test/book.mp3");
		//mp3.play();
		play(Get.urlfile("http://dict.cn/mp3.php?q=5thzT"));
	}


 
}
