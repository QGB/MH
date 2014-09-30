//������������ַ�Լ�pingָ��ؽ��
/*
 * �������� 2006-2-17
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package qgb.project;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import qgb.Array;
import qgb.CharsetName;
import qgb.T;
import qgb.text.Regex;
import qgb.text.Text;
/**
 * @author liubo
 *
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class Cmd {
    
    
    public Cmd(){
        
    }
    
    public static String getMACAddress() {
        String address = "";
        String os = System.getProperty("os.name");
        System.out.println(os);
        if (os != null && os.startsWith("Windows")) {
            try {
                String command = "cmd.exe /c ipconfig /all";
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.indexOf("Physical Address") > 0) {                        
                        int index = line.indexOf(":");
                        index += 2;
                        address = line.substring(index);
                        break;
                    }
                }
                br.close();
                return address.trim();
            } catch (IOException e) {}
            
        }
        return address;
    }
    
    public static String getPing(String ast_cmd) {
        String ping = "";
        String os = System.getProperty("os.name");
        //System.out.println(os);
        if (os != null && os.startsWith("Windows")) {
            try {
                String command = "cmd.exe /c "+ast_cmd;
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader br =
                        new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    //T.print(line);
                	ping = ping + line +"\n";
                }
                br.close();
                return ping.trim();
            } catch (IOException e) {}
            
        }
        return ping;
    }
 
 public static void main(String[] args) throws Exception {
     //System.out.println(""+Cmd.getMACAddress());//��������ַ
	 JDBC_Test.getConnection();
	 JDBC_Test.SQL_execute("CREATE TABLE ccsu_ip3 (st_sld char(20),st_ip char(15))");
	 String st_input=T.read_st("ysld.txt");
	 //T.print(st_input);
	 String[] yst=st_input.split("\n");
	 Array.del_muti(yst);
	 //T.print(yst);
	 //T.exit();
     for (int i = 0; i < yst.length; i++) {
    	 String str= Cmd.getPing("ping -n 1 "+yst[i]); //���ping���
         String stre_ip="\\b(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\b";
         String st_ip= Regex.get_first_text(stre_ip, str);
         T.print(st_ip);
         JDBC_Test.SQL_execute("INSERT INTO ccsu_ip3 (st_sld,st_ip) "+ "VALUES ('"+ yst[i]+ "','"+  st_ip+"');");
         
	}

 
 }
}
