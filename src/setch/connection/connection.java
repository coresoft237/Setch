package setch.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class connection {
	String  driver="com.mysql.cj.jdbc.Driver";
	String user="root";
	String pass="";
	String url="jdbc:mysql://127.0.0.1:3306/setch?serverTimezone=UTC";
	String url1="jdbc:mysql://127.0.0.1:3306?serverTimezone=UTC";
	Statement st;
	Statement st1;
	Connection conn;
	Connection conn1;
	public connection()
	{
		
	}
	public Statement connecter()
	{
		
		if(st==null)
		{
		try
		{
			
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, pass);			
			st=conn.createStatement();
			
			
			}
		catch(Exception e)
		{
			System.out.println("error"+e.getMessage());
			}
		finally
		{
			
		}
		}
		else
		{
			
			
		}
	
		return st;
}
	public Statement connecter1()
	{
		
		if(st1==null)
		{
		try
		{
			
			Class.forName(driver);
			conn1=DriverManager.getConnection(url1, user, pass);			
			st1=conn1.createStatement();
			
			
			}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			}
		finally
		{
			
		}
		}
		else
		{
			
			
		}
	
		return st1;
}
	public void close()
	{
		try {
			
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close1()
	{
		try {
			
			conn1.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	


}
