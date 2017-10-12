package bishara;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/login")
public class Login {

	@GET
	@Produces("application/html")
	public String einloggen() throws Exception {
		
		
		return "<html>\r\n" + 
				"<head><meta charset=\"UTF-8\"></head>\r\n" + 
				"<body>\r\n" + 
				"<form>\r\n" + 
				"  First name:<br>\r\n" + 
				"  <input type=\"text\" name=\"firstname\"><br>\r\n" + 
				"  Last name:<br>\r\n" + 
				"  <input type=\"text\" name=\"lastname\">\r\n" + 
				"  Password:<br>\r\n" + 
				"  <input type=\"password\" name=\"pw\">\r\n" + 
				"  <input type=\"submit\" value=\"Submit\">\r\n" + 
				"</form>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
	}

	@Path("{f}")
	@GET
	@Produces("application/json")
	public Response convertFtoCfromInput(@PathParam("f") float f) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		float celsius;
		celsius = (f - 32) * 5 / 9;
		jsonObject.put("F Value", f);
		jsonObject.put("C Value", celsius);

		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	}
}
