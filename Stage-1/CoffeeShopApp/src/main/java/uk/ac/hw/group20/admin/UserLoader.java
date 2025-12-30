package uk.ac.hw.group20.admin;

import java.io.*;
import java.util.*;

public class UserLoader {
    private static final String FILE_NAME = "data/users.csv";
    private static final String FILE_NAME_LOGS = "data/users_log.csv";

    public static List<User> loadUsersFromCSV() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            throw new RuntimeException("users.csv not found in data folder.");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    users.add(new User(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim()));
                } else {
                    System.out.println("Invalid length of user details: " + data.length);
                    throw new RuntimeException("Invalid length of user details: " + data.length);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load users from file.", e);
        }

        return users;
    }
    
    public static void addUserToCSV(User user) {
        File file = new File(FILE_NAME_LOGS);

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Append user details in CSV format
            out.println(user.getUserId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getRoleId());
            System.out.println("User Logs added successfully!");

        } catch (IOException e) {
            throw new RuntimeException("Failed to write user to file.", e);
        }
    }
    
    public static User getUserById(String userId) {
    	List<User> users = UserLoader.loadUsersFromCSV();
    	
    	if(userId == null) {
    		return null;
    	}
		
		for(User user: users) {
			if(userId.equals(user.getUserId())) {
				return user;
			}
		}
		
		return null;
    }
    
    public static List<User> getUserByRoleId(String roleId) {
    	List<User> users = UserLoader.loadUsersFromCSV();
    	List<User> userWitRole = new ArrayList<>();
    	
    	if(roleId == null) {
    		return null;
    	}
		
		for(User user: users) {
			if(roleId.equals(user.getRoleId())) {
				userWitRole.add(user);
			}
		}
		
		return userWitRole;
    }
}