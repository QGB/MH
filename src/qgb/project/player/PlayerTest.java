package qgb.project.player;

import qgb.T;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerTest {
	public static void main(String[] args) throws JavaLayerException {
		Player player=new Player(
				T.read_is("D:/Program Files/KuGou/Temp/Boys、be - Stand Up!.mp3"));
		player.play();
		//player.
	}

}
