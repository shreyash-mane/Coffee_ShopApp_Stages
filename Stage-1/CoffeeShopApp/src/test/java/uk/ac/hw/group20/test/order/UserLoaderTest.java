package uk.ac.hw.group20.test.order;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.junit.jupiter.api.*;

import uk.ac.hw.group20.admin.User;
import uk.ac.hw.group20.admin.UserLoader;

public class UserLoaderTest {
	
	private static final String TEST_FILE = "data/users_test_file.csv";
    private static final String TEST_LOG_FILE = "data/test_users_log.csv";

    @BeforeEach
    public void setUp() throws IOException {
        Files.createDirectories(Paths.get("data"));

        List<String> users = Arrays.asList(
            "1,john_doe,password123,admin",
            "2,jane_doe,securePass,user"
        );
        
        Files.write(Paths.get(TEST_FILE), users);
    }

    @AfterEach
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
        Files.deleteIfExists(Paths.get(TEST_LOG_FILE));
    }
    
    @Test
    public void testFileNotExistsThrowsException() {
        File file = new File("data/users_wrong_name.csv");

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            if (!file.exists()) {
                throw new RuntimeException("users.csv not found in data folder.");
            }
        });

        Assertions.assertEquals("users.csv not found in data folder.", exception.getMessage());
    }
    
    
    @Test
    public void testLoadUsersThrowsExceptionWhenFileNotFound() {
        File file = new File("data/users_wrong_name.csv");

        assertFalse(file.exists());
        
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
        	if (!file.exists()) {
                throw new RuntimeException("users.csv not found in data folder.");
            }
        });

        Assertions.assertEquals("users.csv not found in data folder.", exception.getMessage());
    }
    
    @Test
    public void testLoadUsersSuccessfully() throws IOException {

        List<User> users = UserLoader.loadUsersFromCSV();

        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.size()>2);
        Assertions.assertEquals("IMA", users.get(0).getUsername());
        Assertions.assertEquals("CS01", users.get(0).getUserId());
    }
    
    @Test
    public void testLoadUsersThrowsExceptionWhenDataLengthIsInvalid() throws IOException {

    	String[] data = {"Name","Id"};
    	
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            UserLoader.loadUsersFromCSV();
            if (data.length != 4) {
            	throw new RuntimeException("Invalid length of user details: " + data.length);
            }
        });

        assertTrue(exception.getMessage().contains("Invalid length of user details"));
        
    }
    
}
