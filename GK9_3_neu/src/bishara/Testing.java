package bishara;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class Testing {

	/*@Test
	public void testRegisterHTML() throws Exception {
		Register reg = new Register();
		reg.register();
		
	}*/
	
	
	@Test
	public void testIfConnectionNotNull() {
		DB db = DB.getInstance();
	    assertNotNull(db.getInstance().createCon());
	}

	@Test
	public void testIfTableExists() {
		DB db = DB.getInstance();
		assertNotNull(db.getInstance().createTable(););
	}
}
