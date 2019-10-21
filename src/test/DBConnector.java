package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	public static Connection dbConn;
	public static Statement stmt;
	public static String identity;
	public static String ID;
	public static String role;
	public static String user;
	public static String privilege;
	public static String function;
	
	public DBConnector() {
		try{
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=hw","sa","1234567890");
    		System.out.println("连接数据库成功");
    		stmt = dbConn.createStatement();
    		}catch(Exception e){
    		e.printStackTrace();
    		System.out.print("连接失败");
    		}
	}
	public boolean login(String usr, String pwd) throws SQLException {
		String safe_sql = null;
		if (usr.startsWith("2")) {
			safe_sql = "select pwd,id from student where school_ID =" + usr;
		}
		else if (usr.startsWith("3")){
			safe_sql = "select email,id from teacher where school_ID =" + usr;
		}
		else {
			
		}
		

		//stmt.executeQuery(db_sql);
		ResultSet rs = stmt.executeQuery(safe_sql);
		System.out.println(safe_sql);
		boolean isSafe = false;
		while(rs.next()) {
			System.out.println(rs.getString(1).toString().trim());
			if (rs.getString(1).toString().trim().equals(pwd.trim())) {
				isSafe = true;
				System.out.println("登陆成功");
				ID = usr;
				user = rs.getString(2).toString().trim();
				myPrint(user);
				getIdentity(user);
				return true;
			}
			else {
				System.out.println("密码错误");
				return false;
			}
		}
		return false;
	}
	public static void myPrint(String x) {
		System.out.println(x);
	}
	public static void getIdentity(String num) throws SQLException {
		String identity_sql = "select type, id from myuser where id = '"+num+"'";
		ResultSet rs = stmt.executeQuery(identity_sql);
		while(rs.next()) {
			identity = rs.getString(1).toString().trim();
			user = rs.getString(2).trim();
			myPrint(identity);
		}

		
		
	}
	public static ResultSet getClassInfo() throws SQLException {
		ResultSet rs = null;
		if (identity.equals("student")) {
			String stuInfo_sql = "select * from grade where school_id = " + ID;
			myPrint(stuInfo_sql);
			rs = stmt.executeQuery(stuInfo_sql);
			return rs;
		}
		if (identity.equals("teacher")) {
			String teacherInfo_sql = "select * from student ";
			myPrint(teacherInfo_sql);
			rs = stmt.executeQuery(teacherInfo_sql);
			return rs;
		}
		return null;
		
	}
	public static ResultSet getGrade() throws SQLException {
		
		ResultSet rs = null;
		if (identity.equals("student")) {
			String stuInfo_sql = "select * from grade where school_id = " + ID;
			myPrint(stuInfo_sql);
			rs = stmt.executeQuery(stuInfo_sql);
			return rs;
		}
		if (identity.equals("teacher")) {
			String teacherInfo_sql = "select * from grade ";
			myPrint(teacherInfo_sql);
			rs = stmt.executeQuery(teacherInfo_sql);
			return rs;
		}
		return null;
		
	}
	public static void changeGrade() {
		
	}
	public static int changeInfo(
			String name,
			String gender,
			String age,
			String grade,
			String iclass,
			String province,
			String pwd) throws Throwable {

		String change_sql = String.format("update student set name = '%s', gender = '%s' , age = '%s' , grade = '%s', class = '%s', province = '%s', pwd = '%s' from student where school_ID = %s", name,gender,age,grade,iclass,province,pwd,ID);
		myPrint(change_sql);
		return stmt.executeUpdate(change_sql);
		
		
	}
	public void getRole() throws SQLException {
		ResultSet rs = null;
		String role_sql = "select * from user_role where user_id ='" + user + "'";
		myPrint(role_sql);
		rs = stmt.executeQuery(role_sql);
		while (rs.next()) {
			role = rs.getString(2).toString().trim();
			break;
		}
	}
	public void getPrivilege() throws SQLException {
		ResultSet rs = null;
		String privilege_sql = "select * from role_privilege where role_ID ='" + role + "'";
		myPrint(privilege_sql);
		rs = stmt.executeQuery(privilege_sql);
		while (rs.next()) {
			privilege = rs.getString(3).toString().trim();
			break;
		}
	}
	public void getFunction() throws SQLException {
		ResultSet rs = null;
		String function_sql = "select * from privilege_function where privilege_ID ='" + privilege + "'";
		myPrint(function_sql);
		rs = stmt.executeQuery(function_sql);
		while (rs.next()) {
			function = rs.getString(3).toString().trim();
			break;
			
		}

	}
	public boolean isLeagalFunction(String button) throws SQLException {
		
		getRole();
		getPrivilege();
		getFunction();

		ResultSet rs = null;
		String isLeagal_sql = "select * from myfunction where id ='" + function + "'";
		myPrint(isLeagal_sql);
		rs = stmt.executeQuery(isLeagal_sql);
		while (rs.next()) {
			String functionName = rs.getString(3).toString().trim();
			if (functionName.equals(button.trim())) {
				return true;
			}
			
		}
		return false;
	}
		

	public ResultSet getInfo() throws SQLException {
		ResultSet rs = null;
		if (identity.equals("student")) {
			String stuInfo_sql = "select * from student where school_ID = " + ID;
			myPrint(stuInfo_sql);
			rs = stmt.executeQuery(stuInfo_sql);
			return rs;
		}
		if (identity.equals("teacher")) {
			String teacherInfo_sql = "select * from teacher where school_ID=" + ID;
			myPrint(teacherInfo_sql);
			rs = stmt.executeQuery(teacherInfo_sql);
		}
		return null;
	}
	public static void connect_to_sql_server(String usr, String pwd){

    	try{
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		Connection dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=hw","sa","1234567890");
    		System.out.println("连接数据库成功");
    		String t_sql = "select * from grade where school_ID =" + usr;
    		String safe_sql = "select pwd from student where school_ID =" + usr;
    		String db_sql = "use hw";
    		Statement stmt = dbConn.createStatement();
    		//stmt.executeQuery(db_sql);
    		ResultSet rs = stmt.executeQuery(safe_sql);
    		System.out.println(safe_sql);
    		while(rs.next()) {
    			System.out.println(rs.getString(1).toString().trim());
    			if (rs.getString(1).toString().trim().equals(pwd.trim())) {
    				System.out.println("登陆成功");
    				rs = stmt.executeQuery(t_sql);
    				while(rs.next()) {
    					System.out.println(rs.getString(1) + "  "+ rs.getString(2) + "  " + rs.getString(3));
    				}
    				break;
    			}
    			else {
    				System.out.println("密码错误");
    				break;
    			}
    		}

    		
    		}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("连接失败");
    		}
    	}
}
