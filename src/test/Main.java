package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
	public static void main(String [] args){

	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection dbConn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=master","tfr","tfr");
		System.out.println("�������ݿ�ɹ�");
		}catch(Exception e){
		e.printStackTrace();
		System.out.print("����ʧ��");
		}
	}

}
