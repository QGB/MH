package qgb.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.io.IOException;

import qgb.T;

public class QField {
	public static final int I_TXT_HEIGHT=30;
	
	public static final Dimension D_BTN_SMALL = new Dimension(20, 20);

	public static final Insets INSETS_BTN = new Insets(0, 0, 0, 0);
	
	public static void main(String[] args) {
		Font font = null;
		try {
			font=Font.createFont(Font.TRUETYPE_FONT,
					T.read_bis("./res/FZMWFont.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		T.print(font);
	}
}
