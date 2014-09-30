package mh.net;

import qgb.interfaces.QRunable;
import mh.struct.entry.Entry;

public interface GetEntry extends QRunable{
	Entry byWord(String ast);// throws IOException, IllegalArgumentException;
	//Word getWord();
}
