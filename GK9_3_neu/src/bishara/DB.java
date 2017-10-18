package bishara;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.FormParam;
import javax.ejb.*;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
public class DB {

	private static DB instance = null;
	private Statement stmt;
	private Connection con;
	
	public DB() {
		// init database
		
	}

	public static DB getInstance() {
		if (instance == null) {
			instance = new DB();
		}
		return instance;
	}
	
	public void createCon() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:~/dbuser", "sa", "");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void closeCon() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createTable() {
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS users ( vname varchar(50), lname varchar(50), mail varchar(50) primary key, password varchar(50))");
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registerUser(String firstname,String lastname,String mail,String pw) {
		try {
			if(firstname == null) {
				System.out.println("firstname null");
			}
			
			//stmt = con.createStatement();
			//stmt.executeUpdate( "INSERT INTO users VALUES ( '"+firstname+"','"+lastname+"','"+mail+"','"+pw+"' )" );
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Fehler bei Registrierung");
		}
	}
	
	public String loginUser(String mail,String pw) {
		String statement="<html>\r\n" + 
				"<head><meta charset=\"UTF-8\"></head>\r\n" + 
				"<body>\r\n" + 
				"<h1>Login nicht erfolgreich</h1>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
		try {
			stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery( "SELECT password FROM users WHERE mail = '"+mail+"'");
	        if(rs.next()) {
	        	String password = rs.getString("password");
	        	if(password.equals(pw)) {
	        		System.out.println("Password = Pw");
	        		statement = "<html>\r\n" + 
	        				"<head><meta charset=\"UTF-8\"></head>\r\n" + 
	        				"<body>\r\n" + 
	        				"<h1>Login erfolgreich</h1>\r\n" + 
	        				"</body>\r\n" + 
	        				"</html>\r\n" + 
	        				"";
	        	}
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return statement;
	}
}