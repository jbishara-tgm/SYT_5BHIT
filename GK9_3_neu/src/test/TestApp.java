package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bishara.DB;
import bishara.Login;
import bishara.Register;


public class TestApp {
	int i = 0;
	
	@Test
	public void testInstance() {
		DB db = new DB();
		
		db.getInstance();
	}
	
	@Test
	public void testInstance2() {
		DB db = new DB().getInstance();
	}
	
	@Test
	public void testCon() {
		DB db = new DB();
		
		try {
			db.createCon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCloseCon() {
		DB db = new DB();
		
		try {
			db.createCon();
			db.closeCon();
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testCreateTable() {
		DB db = new DB();
		
		try {
			db.createCon();
			db.createTable();
			db.closeCon();
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void testRegister() {
		DB db = new DB();
		
		try {
			db.createCon();
			db.createTable();
			
			db.registerUser("Günther", "Jauch", "ichbineinmillionär"+ i +"@mail.de", "einemillion");
			i++;
			db.closeCon();
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void testRegister2() {
		DB db = new DB();
		
		try {
			db.createCon();
			db.createTable();
			
			db.registerUser("", "Jauch", "ichbineinmillionär"+ i +"@mail.de", "einemillion");
			i++;
			db.closeCon();
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void testRegister3() {
		DB db = new DB();
		
		try {
			db.createCon();
			db.createTable();
			
			db.registerUser("Günther", "", "ichbineinmillionär"+ i +"@mail.de", "einemillion");
			i++;
			db.closeCon();
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void testRegister4() {
		DB db = new DB();
		
		try {
			db.createCon();
			db.createTable();
			
			db.registerUser("Günther", "Jauch", "", "einemillion");
			i++;
			db.closeCon();
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void testRegister5() {
		DB db = new DB();
		
		try {
			db.createCon();
			db.createTable();
			
			db.registerUser("Günther", "Jauch", "ichbineinmillionär"+ i +"@mail.de", "");
			i++;
			db.closeCon();
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void testLoginUser() {
		DB db = new DB();
		
		try {
			db.createCon();
			db.createTable();
			
			db.loginUser("h.saft@mail.at", "1234");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testLoginClass() {
		Login log = new Login();
	}
	
	@Test
	public void testLoginMeth() {
		Login log = new Login();
		
		try {
			log.login();
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testLoginSubmit() {
		Login log = new Login();
		
		try {
			log.login();
			
			log.submit("h.saft@mail.at", "1234");
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void testLoginSubmit2() {
		Login log = new Login();
		
		try {
			log.login();
			
			log.submit("h.saft@mail.at", "123456789");
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void testLoginSubmit3() {
		Login log = new Login();
		
		try {
			log.login();
			
			log.submit("h.saftsack@mail.at", "123456789");
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void testRegisterClass() {
		Register reg = new Register();
	}
	
	@Test
	public void testRegisterMeth() throws Exception {
		Register reg = new Register();
		
		reg.register();
	}
	
	@Test
	public void testRegisterSubmit() throws Exception {
		Register reg = new Register();
		
		reg.register();
		
		reg.submit("ich", "bin", "ein@berliner.de", "3");
	}
}
