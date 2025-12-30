package uk.ac.hw.group20.test.order;

import org.junit.jupiter.api.*;

import uk.ac.hw.group20.admin.ValidateUser;

public class ValidateUserTest {
	
	String username;
	String password;
	ValidateUser validateUser;
	
	
	@BeforeEach
	void setupVariables() {
		this.username = "IMA";
		this.password = "IMA";
		this.validateUser = new ValidateUser();
	}
	
	@Test
	void testValidateUsername() {
		this.username = null;
		
		String response = this.validateUser.validateUser(this.username, this.password);
		
		Assertions.assertEquals("Username cannot be blank", response);
		
	}
	
	@Test
	void testValidatePassword() {
		this.password = null;
		
		String response = this.validateUser.validateUser(this.username, this.password);
		
		Assertions.assertEquals("Password cannot be blank", response);
		
	}
	
	@Test
	void testValidateWrongCredentials() {
		
		this.password = "ima";
		
		String response = this.validateUser.validateUser(this.username, this.password);
		
		Assertions.assertEquals("Invalid username and/or password", response);
		
	}
	
	@Test
	void testValidateCorrectCredentials() {
		
		String response = this.validateUser.validateUser(this.username, this.password);
		
		Assertions.assertTrue(response.contains("Success"));
		
	}

}
