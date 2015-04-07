package test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import qgb.T;
import qgb.media.MP3player;

public class Bis{
	public static void main(String[] args) throws Exception{
		System.out.println("qgb");
		//cin("qgb");	
		BufferedInputStream bis=T.read_bis("256");
        byte[] bdata;  
        bdata=T.InputStreamToByte(bis);
        InputStream iStream=T.ByteToInputStream(bdata);
        //MP3player.play(T.ByteToInputStream(bdata));
        T.write("copy", iStream);
	}
	public static void write (String ast_filename,BufferedInputStream abis) throws IOException {
        byte[] data = new byte[1];     
        //BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(cst_test_path +ast_filename)); 
        
        while(abis.read(data)!=-1)     
            {     
                bos.write(data);     
            }     
    
            //将缓冲区中的数据全部写出     
            bos.flush();     
    
            //关闭流     
            //bufferedInputStream.close();     
            bos.close();     
    
	}
}

