package qgb.project.math;


import qgb.T;
import qgb.jdbc.Mysql;
import qgb.math.M;

/**
 * qgb.project.math.Processing_data
 * @author Administrator
 *
 */
public class Processing_data {
	static int[] cyi=new int[150]; 
	
	public static void main(String[] args) {
		onlyForTest();
		Mysql.connect();
		createTable();
		T.msgbox("c t");
		cyi= generate150();
		//T.print(cyi);
		grade(cyi);
		//kandall(cyi);

	}

	private static void grade(int[] yi) {
		int[] yij=new int[10];
		String sql="";
		for (int i = 0; i < yi.length; i++) {
			for (int j = 1; j < 10; j++) {
				switch (j) {
				case 1:
					yij[1]=random(yi[i]+20,-20,20);
					
				case 2:
					yij[2]=random(yi[i]-20,-20,20);
						
				case 3:
					yij[3]=random(yi[i],-5,5);
		
				default:
					yij[j]=random(yi[i],-20,20);
					break;
				}
			}
			sql = "INSERT INTO math150 (isource,ig1,ig2,ig3,ig4,ig5,ig6,ig7,ig8,ig9) "
					+ "VALUES ("
					+ yi[i]
					+ ","
					+yij[1]
					+ ","
					+yij[2]
					+ ","	
					+yij[3]
					+ ","	
					+yij[4]
					+ ","	
					+ yij[5]
					+ ","
					+yij[6]
					+ ","	
					+yij[7]
					+ ","	
					+yij[8]
					+ ","
					+yij[9]
					+ ");";
			//T.print(sql);
			//T.msgbox("");
			Mysql.SQL_execute(sql);
			
		}
	}


	private static int random(int aibase, int min, int max) {
		int ia=aibase+M.ri(min,max);
		if (ia<0) {
			return 0;
		}else if (ia>100) {
			return 100;
		}
		return ia;
	}

	private static void kandall(int[] yi) {
		
		
	}

	private static void onlyForTest() {
		T.cst_test_path="D:/math/data/";
	}

	private static int[] generate150() {
		String stin= T.read_st("s.txt");
		//T.print(stin);
		String[] yst=stin.split("\n");
		int ia=0,in=0;
		int[] yi=new int[150];
		for (int i = 0; i < yst.length; i++) {
			yst[i]=yst[i].substring(0, yst[i].length()-1);
			ia=Integer.valueOf(yst[i]);
			if (ia>=0&ia<=100) {
				yi[in]=ia;
				in=in+1;
				//T.print(in);
				if (in>149) {
					return yi;
				}
				
			}

		}
		//T.print(System.getProperty(PropertyKey.user_dir));
		return null;
		
	}

	private static void createTable() {
		String sql = "CREATE TABLE math150 ("
				+ "isource integer  NOT NULL,"
				+ "ig1 integer       ,"
				+ "ig2 integer       ,"
				+ "ig3 integer       ,"
				+ "ig4 integer       ,"
				+ "ig5 integer       ,"
				+ "ig6 integer       ,"
				+ "ig7 integer       ,"
				+ "ig8 integer       ,"
				+ "ig9 integer       "
				+ ");";//

		Mysql.SQL_execute(sql);

	}

	
}