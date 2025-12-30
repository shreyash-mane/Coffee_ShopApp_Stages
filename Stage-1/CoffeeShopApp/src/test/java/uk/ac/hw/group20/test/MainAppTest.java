package uk.ac.hw.group20.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import uk.ac.hw.group20.main.HomeGui;
import uk.ac.hw.group20.main.MainApp;


public class MainAppTest {
	MainApp app = new MainApp();
	
	@Test
    void testMainAppLaunch() {
		
		app.main(new String[]{});
		HomeGui frame = new HomeGui("CS01");
        frame.setVisible(true);

        assertNotNull(app, "Frame should not be null");
        assertTrue(frame.isShowing(), "Frame should be visible");
        
        frame.dispose();
    }
	
	@Test
    void testMainMethodRunsWithoutException() {
        assertDoesNotThrow(() -> MainApp.main(new String[]{}), 
            "Main method should run without throwing an exception");
    }


}
