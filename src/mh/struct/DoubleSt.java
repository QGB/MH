package mh.struct;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import mh.struct.entry.Word;
import qgb.T;

public class DoubleSt implements Serializable {
	public StrNotNull st1 = new StrNotNull();
	public StrNotNull st2 = new StrNotNull();

	public DoubleSt(String ast1, String ast2) {
		st1.set(ast1);
		st2.set(ast2);
	}

	public void set(DoubleSt ast){
		if (ast==null) {
//			st1.set(null);
//			st2.set(null);
			return;
		}else {
			st1.set(ast.st1.get());
			st2.set(ast.st2.get());
		}

	}

	@Override
	public String toString() {
//		Word w=new Word();
//		w.stW.set("test");
//		T.print(w.stW);

		return st1 + "\n" + st2;
	}

	public static void main(String[] args) throws Exception {
		ObjectOutput out;
		String StF = T.autoPath("obj.bin");
		out = new ObjectOutputStream(new FileOutputStream(StF));
		out.writeObject(new DoubleSt(null, "s2"));

		ObjectInputStream in = new ObjectInputStream(T.read_bis(StF));
		Object obj = in.readObject();
		T.print(obj.getClass());
		DoubleSt ds = (DoubleSt) obj;
		T.print("[%s]", ds.st1);
		
	}
}