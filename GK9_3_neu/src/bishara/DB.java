package bishara;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.FormParam;

public class DB {

	private static DB instance = null;
	private Statement stmt;
	private Connection con;
	
	private DB() {
		// init database
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:~/dbuser;AUTO_SERVER=TRUE", "sa", "");
			
			
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static DB getInstance() {
		if (instance == null) {
			instance = new DB();
		}
		return instance;
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
			stmt = con.createStatement();
			stmt.executeUpdate( "INSERT INTO users VALUES ( '"+firstname+"','"+lastname+"','"+mail+"','"+pw+"' )" );
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String loginUser(String mail,String pw) {
		String statement="";
		try {
			stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery( "SELECT password FROM users WHERE mail = '"+mail+"'");
	        if(rs.next()) {
	        	String password = rs.getString("pw");
	        	if(password.equals(pw)) {
	        		statement = "<html>\r\n" + 
	        				"<head><meta charset=\"UTF-8\"></head>\r\n" + 
	        				"<body>\r\n" + 
	        				"<h1>Login erfolgreich</h1>\r\n" + 
	        				"</body>\r\n" + 
	        				"</html>\r\n" + 
	        				"";
	        	}else {
	        		statement =  "<html>\r\n" + 
	        				"<head><meta charset=\"UTF-8\"></head>\r\n" + 
	        				"<body>\r\n" + 
	        				"<h1>Login nicht erfolgreich</h1>\r\n" + 
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