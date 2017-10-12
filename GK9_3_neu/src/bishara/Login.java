package bishara;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class Login {
	DB db = DB.getInstance();
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String login() throws Exception {
		
		
		return "<html>\r\n" + 
				"<head><meta charset=\"UTF-8\"></head>\r\n" + 
				"<body>\r\n" + 
				"<form method=\"post\">\r\n" + 
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
	public void submit(@FormParam("mail") String mail,@FormParam("pw") String pw) {
		try {
		DB.getInstance().createTable();
        DB.getInstance().loginUser(mail, pw);
        //con.close();

		}catch(Exception e) {
			System.out.println("nix geht");
			e.printStackTrace();
		}
	}
}
