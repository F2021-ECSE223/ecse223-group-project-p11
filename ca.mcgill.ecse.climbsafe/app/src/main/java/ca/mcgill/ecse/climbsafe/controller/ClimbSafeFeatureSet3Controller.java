package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;

public class ClimbSafeFeatureSet3Controller {

	public static void registerGuide(String email, String password, String name, String emergencyContact)
			throws InvalidInputException {
		String error = "";
		if(password.equals("")){
			error = "Password cannot be empty";
		}
		
		if(name.equals("")) {
			error = "Name cannot be empty";	
		}
		
		if(emergencyContact.equals("")) {
			error = "Emergency contact cannot be empty";
		}
	
		if(email.equals("")) {
			error = "Email cannot be empty";
		}
		
		if (!error.isEmpty()) {
		      throw new InvalidInputException(error);
	    }
		
		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		try {
			climbSafe.addGuide(email, password, name, emergencyContact);
		} catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals(
					"Cannot create due to duplicate number. See http://manual.umple.org?RE003ViolationofUniqueness.html")) {
				error = "An account with this email already exists. Please use a different email.";
			}
			throw new InvalidInputException(error);
		}
	}

	public static void updateGuide(String email, String newPassword, String newName, String newEmergencyContact)
			throws InvalidInputException {
		String error = "";
		
		if(newPassword.equals("")){
			error += "Password cannot be empty";
		}
		
		if(newName.equals("")) {
			error += "Name cannot be empty";	
		}
		
		if(newEmergencyContact.equals("")) {
			error += "Emergency contact cannot be empty";
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