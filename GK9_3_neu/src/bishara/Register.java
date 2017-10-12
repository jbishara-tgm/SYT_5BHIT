package bishara;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/register")
public class Register {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String register() throws Exception {
		
		
		return "<html>\r\n" + 
				"<head><meta charset=\"UTF-8\"></head>\r\n" + 
				"<body>\r\n" + 
				"<form method=\"post\">\r\n" + 
				"  First name:<br>\r\n" + 
				"  <input type=\"text\" name=\"firstname\"><br>\r\n" + 
				"  Last name:<br>\r\n" + 
				"  <input type=\"text\" name=\"lastname\"><br>\r\n" + 
				"   E-Mail:<br>\r\n" + 
				"  <input type=\"text\" name=\"mail\"><br>\r\n" + 
				"  Password:<br>\r\n" + 
				"  <input type=\"password\" name=\"pw\">\r\n" + 
				"  <input type=\"submit\" value=\"Submit\">\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String submit(@FormParam("firstname") String firstname,@FormParam("lastname") String lastname,@FormParam("mail") String mail,@FormParam("pw") String pw) {
		try {
		Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:~/dbuser", "sa", "" );
        Statement stmt = con.createStatement();
        //stmt.executeUpdate( "DROP TABLE table1" );
        stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS users ( vname varchar(50), lname varchar(50), mail varchar(50) primary key, password varchar(50))" );
        stmt.executeUpdate( "INSERT INTO users VALUES ( '"+firstname+"','"+lastname+"','"+mail+"','"+pw+"' )" );
        //System.out.println(stmt.executeQuery("SELECT * FROM users"));
        con.close();

		}catch(Exception e) {
			System.out.println("nix geht");
			e.printStackTrace();
		}
		return "<html>\r\n" + 
				"<head><meta charset=\"UTF-8\"></head>\r\n" + 
				"<body>\r\n" + 
				"<h1>Registrierung erfolgreich</h1>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
	}
}
