package mh.net;

import qgb.interfaces.StopTask;
import mh.struct.entry.Entry;
/**@see qgb.interfaces.StopTask**/
public interface GetEntry extends StopTask{
	Entry byWord(String ast);// throws IOException, IllegalArgumentException;
	//Word getWord();
}
