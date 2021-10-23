package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;

public class ClimbSafeFeatureSet3Controller {

	/*** This controller method validates the given input to register a guide, and registers
	 * a guide if the input passes all the tests.
	 * 
	 * @author Anaëlle Drai Laguéns
	 * @param email
	 * @param password
	 * @param name
	 * @param emergencyContact
	 * @throws InvalidInputException
	 */
	public static void registerGuide(String email, String password, String name, String emergencyContact)
			throws InvalidInputException {
		String error = ""; // initialize error string
		
		// Check all invalid input data cases, and update the error string if the data is invalid
		if (password == null) {
			error = "Password cannot be null";
		}
		
		if (password.equals("")) {
			error = "Password cannot be empty";
		}
		
		if (password.contains(" ")) {
			error = "Password must not contain any spaces";
		}

		if (name.equals("") || name == null) {
			error = "Name cannot be empty";
		}

		if (emergencyContact.equals("")) {
			error = "Emergency contact cannot be empty";
		}

		if (Guide.getWithEmail(email) instanceof Guide) {
			error = "Email already linked to a guide account";
		}

		if (Member.getWithEmail(email) instanceof Member) {
			error = "Email already linked to a member account";
		}
		
		if (!(email.indexOf("@") > 0) || !(email.indexOf("@") == email.lastIndexOf("@")) || !(email.indexOf("@") < email.lastIndexOf(".")-1) || !(email.lastIndexOf(".") < email.length()-1)) {
			error = "Invalid email";
		}
	
		if (email == null) {
			error = "Email cannot be null";
		}
		
		if (email.equals("")) {
			error = "Email cannot be empty";
		}
		if (email.contains(" ")) {
			error = "Email must not contain any spaces";
		}

		if (email.equals("admin@nmc.nt")) {
			error = "Email cannot be admin@nmc.nt";
		}
		
		// if the error string was updated, meaning there is an error, throw an error with the error string
		if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}

		// Retrieve the climbsafe instance
		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		
		// Add a guide to the climbsafe system and catch any potential error
		try {
			climbSafe.addGuide(email, password, name, emergencyContact);
		} catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
	}

	/***This controller method validates the given input to update a guide's information, 
	 * and updates a guide's information if the input passes all the tests.
	 * 
	 * @author Anaëlle Drai Laguéns
	 * @param email
	 * @param newPassword
	 * @param newName
	 * @param newEmergencyContact
	 * @throws InvalidInputException
	 */
	public static void updateGuide(String email, String newPassword, String newName, String newEmergencyContact)
			throws InvalidInputException {
		String error = ""; // initialize error string

		// Check all invalid input data cases, and update the error string if the data is invalid
		if (newPassword == null) {
			error = "Password cannot be null";
		}
		
		if (newPassword.equals("")) {
			error = "Password cannot be empty";
		}
		
		if (newPassword.contains(" ")) {
			error = "Password must not contain any spaces";
		}
		
		if (newName == null) {
			error = "Name cannot be null";
		}
		
		if (newName.equals("")) {
			error = "Name cannot be empty";
		}
		
		if (newEmergencyContact.equals("")) {
			error = "Emergency contact cannot be empty";
		}

		if (Guide.getWithEmail(email) == null) {
			error = "The Guide does not exist in the system";
		}
		if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}

		try {
			Guide guideToUpdate = (Guide) Guide.getWithEmail(email);
			guideToUpdate.setPassword(newPassword);
			guideToUpdate.setName(newName);
			guideToUpdate.setEmergencyContact(newEmergencyContact);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
}