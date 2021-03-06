/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.mcgill.ecse.climbsafe.application;

import java.sql.Date;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.EventQueue;

import ca.mcgill.ecse.climbsafe.model.Administrator;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.view.*;

public class ClimbSafeApplication {
	private static ClimbSafe climbSafe;

	public String getGreeting() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// Use regular Swing theme if FlatLaf is unavailable
			e.printStackTrace();
		}

		EventQueue.invokeLater(InitialHomePage::new);
	}

	public static ClimbSafe getClimbSafe() {
		if (climbSafe == null) {
			// these attributes are default, you should set them later with the setters

			climbSafe = ClimbSafePersistence.load();

		}

		return climbSafe;
	}
}
