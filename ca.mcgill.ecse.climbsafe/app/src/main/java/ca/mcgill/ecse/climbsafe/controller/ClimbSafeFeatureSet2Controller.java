package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.NamedUser;

public class ClimbSafeFeatureSet2Controller {

	/**
	 * This function registers a guide in the ClimbSafe system
	 * 
	 * @author Maxime Drouin
	 * 
	 * @param email
	 * @param password
	 * @param name
	 * @param emergencyContact
	 * @param nrWeeks
	 * @param guideRequired
	 * @param hotelRequired
	 * @param itemNames
	 * @param itemQuantities
	 * @throws InvalidInputException
	 */
	public static void registerMember(String email, String password, String name, String emergencyContact, int nrWeeks,
			boolean guideRequired, boolean hotelRequired, List<String> itemNames, List<Integer> itemQuantities)
			throws InvalidInputException {

		ClimbSafe cs = ClimbSafeApplication.getClimbSafe();

		String e = "";

		if (!(email.indexOf("@") > 0)) {
			e = "Invalid email";
		}
		if (email.contains("@") == false) {
			e = "Invalid email";
		}
		if (!(email.indexOf("@") == email.lastIndexOf("@"))) {
			e = "Invalid email";
		}
		if (!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
			e = "Invalid email";
		}
		if (!(email.lastIndexOf(".") < email.length() - 1)) {
			e = "Invalid email";
		}
		if (email.contains(" ") == true) {
			e = "The email must not contain any spaces";
		}
		// condition for which the email already exists

		if (password == null) {
			e = "The password cannot be empty";
		}
		if (password.equals("")) {
			e = "The password cannot be empty";
		}
		if (name.equals("") || name == null) {
			e = "The name cannot be empty";
		}
		// check if emergency contact

		if (NamedUser.hasWithEmail(email) && NamedUser.getWithEmail(email) instanceof Guide) {
			e = "A guide with this email already exists";
		}
		if (NamedUser.hasWithEmail(email) && NamedUser.getWithEmail(email) instanceof Member) {
			e = "A member with this email already exists";
		}
		if (emergencyContact.equals("")) {
			e = "The emergency contact cannot be empty";
		}

		if (email.equals("admin@nmc.nt")) {
			e = "The email entered is not allowed for members";
		}

		if (nrWeeks <= 0) {
			e = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
		}
		if (nrWeeks > cs.getNrWeeks()) {
			e = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
		}

		for (Integer i : itemQuantities) {
			if (i < 1) {
				e = "If item is added it must be greater than 0";
			}
		}
		for (String s : itemNames) {
			if (BookableItem.getWithName(s) == null) {
				e = "Requested item not found";
			}

		}
		if (!e.equals(""))
			throw new InvalidInputException(e);
		try {
			Member member = cs.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired,
					hotelRequired);
			for (int i = 0; i < itemQuantities.size(); i++) {
				BookableItem item = BookableItem.getWithName(itemNames.get(i));
				member.addBookedItem(itemQuantities.get(i), cs, item);
			}

		} catch (RuntimeException e1) {
			throw new InvalidInputException(e1.getMessage());
		}
	}

	/** This function updates a member information in the system.
     * 
     * 
     * @author Maxime Drouin
     * 
     * @param email
     * @param newPassword
     * @param newName
     * @param newEmergencyContact
     * @param newNrWeeks
     * @param newGuideRequired
     * @param newHotelRequired
     * @param newItemNames
     * @param newItemQuantities
     * @throws InvalidInputException
     */
    @SuppressWarnings("finally")
    public static void updateMember(String email, String newPassword, String newName, String newEmergencyContact,
            int newNrWeeks, boolean newGuideRequired, boolean newHotelRequired, List<String> newItemNames,
            List<Integer> newItemQuantities) throws InvalidInputException {

        ClimbSafe cs = ClimbSafeApplication.getClimbSafe();

        String e = "";

        if (!(NamedUser.getWithEmail(email) == null)) { {
            e = "Member not found";
        }
        
        if (!(NamedUser.getWithEmail(email) instanceof Member)) {
        	e = "This is not a member";
        }
        // condition for which the email already exists

        if (newPassword == null) {
            e = "The password cannot be empty";
        }
        if (newPassword.equals("")) {
            e = "The password cannot be empty";
        }
        if (newName.equals("") || newName == null) {
            e = "The name cannot be empty";
        }
        // check if emergency contact

        if (newEmergencyContact.equals("")) {
            e = "The emergency contact cannot be empty";
        }

        if (newNrWeeks <= 0) {
            e = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
        }
        if (newNrWeeks > cs.getNrWeeks()) {
            e = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
        }

        for (Integer i : newItemQuantities) {
            if (i < 1) {
                e = "If item is added it must be greater than 0";
            }
        }
        for (String s : newItemNames) {
            if (BookableItem.getWithName(s) == null) {
                e = "Requested item not found";
            }
        }

        if (!e.isEmpty())
            throw new InvalidInputException(e);
//        
//        try {
        Member uMem = (Member) Member.getWithEmail(email);
        uMem.setPassword(newPassword);
        uMem.setName(newName);
        uMem.setEmergencyContact(newEmergencyContact);
        uMem.setNrWeeks(newNrWeeks);
        uMem.setGuideRequired(newGuideRequired);
        uMem.setHotelRequired(newHotelRequired);
        for (BookedItem item : uMem.getBookedItems()) {
            uMem.removeBookedItem(item);
        }
        for (int i = 0; i < newItemQuantities.size(); i++) {
            BookableItem item = BookableItem.getWithName(newItemNames.get(i));
            uMem.addBookedItem(newItemQuantities.get(i), cs, item);
        }
//        } catch (Exception e1) {
//            throw new InvalidInputException(e1.getMessage());
//        }

    }
}