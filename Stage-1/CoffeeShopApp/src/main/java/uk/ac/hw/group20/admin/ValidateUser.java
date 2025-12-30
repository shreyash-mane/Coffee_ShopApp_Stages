package uk.ac.hw.group20.admin;

import java.util.List;

import uk.ac.hw.group20.utils.Password;

public class ValidateUser {
	
	private List<User> users = UserLoader.loadUsersFromCSV();
	
	public String validateUser(String username, String password) {
		
		String responseMessage = "";
		
		if (username == null || username.trim().isEmpty()) {
		    responseMessage = "Username cannot be blank";
		} else if (password == null || password.trim().isEmpty()) {
		    responseMessage = "Password cannot be blank";
		} else {
			String passwordMd5 = Password.hashPassword(password);
			for (User user : users) {
				if (user.getUsername().equals(username) && user.getPassword().equals(passwordMd5)) {
	                return "Success, " + user.getUserId();
	            } else {
			        responseMessage = "Invalid username and/or password";
			    }
	        }
		}
		
		return responseMessage;
	}

}
