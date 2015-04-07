package qgb.project;
//<pre name="code" class="java">package chp07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import qgb.T;


public class JDBC_Test {
	// 创建静态全局变量
	static Connection conn;
	static Statement st;
	static String sql;
	public static void main(String[] args){
		getConnection();	// 首先要获取连接，即连接到数据库
		sql="INSERT INTO lib (inum,st_name,st_id,st_sex,st_addr,st_zip,st_emai,st_tele,st_offi,st_good) VALUES (2006111111,'夏万力','43072619870408183x','女','','','','','06土木1','');";
		insert(sql);
		//createTable();
		//deleteTable();
		
		//insert();	//插入添加记录
		//update();	//更新记录数据
		//delete();	//删除记录
		//query();	//查询记录并显示
	}
	
	/* 获取数据库连接的函数*/
	public static void getConnection() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动
			
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ccsu", "root", "123456");// 创建数据连接
			st = (Statement) conn.createStatement();	
		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
			
		}
		//return con;	//返回所建立的数据库连接
	}
	
	private static void deleteTable() {
		sql="DROP TABLE lib;";
		SQL_execute(sql);
	}

	public static void createTable() {
		sql ="CREATE TABLE lib (inum integer NOT NULL,"
				+ "st_name varchar(20) NOT NULL,"
				+ "st_id varchar(20) NOT NULL,"
				+ "st_sex varchar(2) NOT NULL,"
				+ "st_addr varchar(50),"
				+ "st_zip varchar(10),"
				+ "st_emai varchar(50),"
				+ "st_tele varchar(50),"
				+ "st_offi varchar(50),"
				+ "st_good varchar(50),"
				+ "PRIMARY KEY (inum));";
		SQL_execute(sql);
		
	}

	public static void SQL_execute(String sql) {
		try {
			st.execute(sql);
		} catch (NullPointerException e) {
			T.error(e,"可能未连接数据库！");
		} catch (SQLException e) {
			T.error(e,sql+"| execute error ！");
		}	
	}

	/* 插入数据记录，并输出插入的数据记录数*/
	public static void insert(String asql) {
		
		SQL_execute(asql);
	}
	
	/* 更新符合要求的记录，并返回更新的记录数目*/
	public static void update() {
		try {
			String sql = "update staff set wage='2200' where name = 'lucy'";// 更新数据的sql语句
			
			int count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数
			
			System.out.println("staff表中更新 " + count + " 条数据");		//输出更新操作的处理结果
			
			conn.close();	//关闭数据库连接
			
		} catch (SQLException e) {
			System.out.println("更新数据失败");
		}
	}

	/* 查询数据库，输出符合要求的记录的情况*/
	public static void query(String sql) {
		try {
			//String sql = "select * from lib where inum>2011000001 and inum<2012123456;";		// 查询数据的sql语句
			
			ResultSet rs = st.executeQuery(sql);	//执行sql查询语句，返回查询数据的结果集
			System.out.println("最后的查询结果为：");
			while (rs.next()) {	// 判断是否还有下一个数据
				
				// 根据字段名获取相应的值
				//CountryCode | Language | IsOfficial | Percentage
				String name =String.valueOf(rs.getInt("inum"));
				//int age = rs.getInt("age");
				String sex = rs.getString("st_name");
				String address = rs.getString("st_id");
				String depart = rs.getString("st_sex");
				//String worklen = rs.getString("worklen");
				//String wage = rs.getString("wage");
				
				//输出查到的记录的各个字段的值
				System.out.println(name + " "  + " " + sex + " " + address
						+ " " + depart );
			
			}
			conn.close();	//关闭数据库连接
			
		} catch (SQLException e) {
			System.out.println("查询数据失败");
		}
	}

	/* 删除符合要求的记录，输出情况*/
	public static void delete() {
		try {
			String sql = "delete from staff  where name = 'lili'";// 删除数据的sql语句
			
			int count = st.executeUpdate(sql);// 执行sql删除语句，返回删除数据的数量
			
			System.out.println("staff表中删除 " + count + " 条数据\n");	//输出删除操作的处理结果
			
			conn.close();	//关闭数据库连接
			
		} catch (SQLException e) {
			System.out.println("删除数据失败");
		}
		
	}
	

}
//</pre><br>
//<pre></pre>
//<p></p>
//<p><strong>项目部署到服务器，然后运行结果：</strong></p>
//<p></p>
//<p><a href="http://hi.csdn.net/attachment/201110/12/0_1318394342H718.gif" target="_blank"><img src="http://hi.csdn.net/attachment/201110/12/0_1318394342H718.gif" width="800" height="150" alt=""></a></p>
//<pre></pre>
//<pre></pre>
//<pre></pre>

