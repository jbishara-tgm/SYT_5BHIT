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
		
	}

	public static DB getInstance() {
		if (instance == null) {
			instance = new DB();
		}
		return instance;
	}
	
	public void createCon() throws Exception {
		Class.forName("org.h2.Driver");
		con = DriverManager.getConnection("jdbc:h2:~/dbuser", "sa", "");
	}
	
	public void closeCon() throws Exception {
		con.close();
	}
	
	public void createTable() throws Exception {
		stmt = con.createStatement();
		stmt.executeUpdate(
				"CREATE TABLE IF NOT EXISTS users ( vname varchar(255), lname varchar(255), mail varchar(255) primary key, password varchar(255))");
	}
	
	public String registerUser(String firstname,String lastname,String mail,String pw) {
		String statement = "<html>\r\n" + 
				"<head><meta charset=\"UTF-8\"></head>\r\n" + 
				"<body>\r\n" + 
				"<h1> Registrierung erfolgreich</h1>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
		boolean error = false;
		try {
			if((firstname.length() == 0) || (lastname.length() == 0) || (mail.length() == 0) || pw.length() == 0) {
				statement = "<html>\r\n" + 
						"<head><meta charset=\"UTF-8\"></head>\r\n" + 
						"<body>\r\n" + 
						"<h1> Alle Felder sind Pflichtfelder</h1>\r\n" + 
						"</body>\r\n" + 
						"</html>\r\n" + 	
						"";
				error = true;
			}
			
			if(error == false) {
				stmt = con.createStatement();
				stmt.executeUpdate( "INSERT INTO users VALUES ( '"+firstname+"','"+lastname+"','"+mail+"','"+pw+"' )" );
			}
			
		}catch(Exception e) {
			//e.printStackTrace();
			statement = "<html>\r\n" + 
					"<head><meta charset=\"UTF-8\"></head>\r\n" + 
					"<body>\r\n" + 
					"<h1> Registrierung nicht erfolgreich</h1>\r\n" + 
					"</body>\r\n" + 
					"</html>\r\n" + 
					"";
		}
		return statement;
	}
	
	public String loginUser(String mail,String pw) {
		String statement= "<html>\r\n" + 
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
	        		String vname = "";
	        		String lname = "";
	        		
	        		ResultSet vnameRs = stmt.executeQuery("SELECT vname FROM users WHERE mail = '"+mail+"'");
	        		if(vnameRs.next()) {
	        			vname = vnameRs.getString("vname");
	        		}
	        		ResultSet lnameRs = stmt.executeQuery("SELECT lname FROM users WHERE mail = '"+mail+"'");
	        		if(lnameRs.next()) {
	        			lname = lnameRs.getString("lname");
	        		}
	        		
	        		System.out.println("Password = Pw");
	        		statement = "<html>\r\n" + 
	        				"<head><meta charset=\\\"UTF-8\\\"></head>\r\n" + 
	        				"<body>\r\n" + 
	        				"<h1>Login erfolgreich</h1><br>\r\n" + 
	        				"Hallo <b>"+ vname +" "+ lname +" </b>!\r\n" + 
	        				"</body> \r\n" + 
	        				"</html> ";
	        	}
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return statement;
	}
}