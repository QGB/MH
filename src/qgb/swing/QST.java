package qgb.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import qgb.T;

public class QST {
	public static void main(String[] args) {

	}
	/**************Test Code End*****************/
	public final static Toolkit gT=Toolkit.getDefaultToolkit();
	public final static int giWinTaskBarHight=27;
	
	/*******************************/
	/**��Ҫ�� �� EDT�е���**/
	public static void refreshAll(Component ac) {
		refresh(ac);
		if (Container.class.isAssignableFrom(ac.getClass())) {
			Container cont= (Container) ac;
			if (cont != null) {
				Component[] yc=cont.getComponents();
				if (yc.length>0) {
					for (int i = 0; i < yc.length; i++) {
						refreshAll(yc[i]);
					}
				}
			}
		}
	}
	/**��Ҫ�� �� EDT�е���**/
	public static void refresh(Component ac){
			if (ac != null) {
				Boolean b=ac.isVisible();
				ac.setVisible(!b);
				ac.setVisible(b);
			}
	}
	/*******************************/
	public static void setAllForeground(Object ao_instance,Color acolor) {
		setForeground(ao_instance, acolor);
		if (Container.class.isAssignableFrom(ao_instance.getClass())) {
			Container cont= (Container) ao_instance;
			if (cont != null) {
				Component[] yc=cont.getComponents();
				if (yc.length>0) {
					for (int i = 0; i < yc.length; i++) {
						setAllForeground(yc[i], acolor);
					}
				}
			}
		}
	}
	
	public static void setForeground(Object ao_instance,Color acolor){
		if (Component.class.isAssignableFrom(ao_instance.getClass())) {
			Component comp= (Component) ao_instance;
			if (comp != null) {
				comp.setForeground(acolor);
			}

		}
	}
	/*******************************/
	public static void setAllBackground(Object ao_instance,Color acolor) {
		setBackground(ao_instance, acolor);
		if (Container.class.isAssignableFrom(ao_instance.getClass())) {
			Container cont= (Container) ao_instance;
			if (cont != null) {
				Component[] yc=cont.getComponents();
				if (yc.length>0) {
					for (int i = 0; i < yc.length; i++) {
						setAllBackground(yc[i], acolor);
					}
				}
			}
		}
	}
	
	public static void setBackground(Object ao_instance,Color acolor){
		if (Component.class.isAssignableFrom(ao_instance.getClass())) {
			Component comp= (Component) ao_instance;
			if (comp != null) {
				comp.setBackground(acolor);
			}

		}
	}
	/********************************************/
	public static ImageIcon getScaledIcon(String astPath,int width, int height){
		Image image=new ImageIcon(astPath).getImage().
				getScaledInstance(width,height,Image.SCALE_SMOOTH);
		return new ImageIcon(image);
		
	}
	/*******************************/
	public static Font getFont(String astPath,float size) {
		return getFont(astPath, size, Font.PLAIN);
	}
	public static Font getFont(String astPath,float size, int style) {
		Font font = null;
		try {
			font=Font.createFont(Font.TRUETYPE_FONT,
					T.read_bis(astPath));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return font.deriveFont(size).deriveFont(style);
	}
	/*******************************/
	public static Rectangle getMidScreen(int aiWidth, int aiHeight) {
		Dimension dScreen=QST.gT.getScreenSize();
		return new Rectangle((dScreen.width-aiWidth)/2,(dScreen.height-aiHeight)/2, aiWidth,aiHeight);
	}
	public static Point getBRLocation(Dimension size) {
		return getBRLocation(size.width, size.height);
	}
	public static Point getBRLocation(int aiWidth, int aiHeight) {
		Dimension dS=QST.gT.getScreenSize();
		return new Point(dS.width-aiWidth,dS.height-aiHeight-giWinTaskBarHight);
	}
}
