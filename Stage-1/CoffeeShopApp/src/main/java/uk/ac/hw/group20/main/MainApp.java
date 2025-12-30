package uk.ac.hw.group20.main;

import javax.swing.JFrame;

import uk.ac.hw.group20.admin.LoginGui;

public class MainApp extends JFrame {
	public MainApp() {
	}

	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginGui frame = new LoginGui();
			frame.setVisible(true);
		} catch (Exception e) {
			throw new RuntimeException("Failed to start the application");
		}
	}


}
