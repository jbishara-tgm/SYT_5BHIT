package bishara;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/register")
public class Register {
	DB db = DB.getInstance();
	
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
	public String submit(@FormParam("firstname") String firstname,@FormParam("lastname") String lastname,@FormParam("mail") String mail,@FormParam("pw") String pw) throws Exception {
		String response = "";

			db.getInstance().createCon();
			db.getInstance().createTable();
			response = db.getInstance().registerUser(firstname, lastname, mail, pw);
			db.getInstance().closeCon();

		return response;
	}
}
