package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Member;

public class AddtitionalController {
	
	public static List<TOGuide> getGuides() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<TOGuide> list = new ArrayList<TOGuide>();
		for (Guide guide : cS.getGuides()) {
			TOGuide g = new TOGuide(guide.getName(), guide.getEmergencyContact(), guide.getEmail(), guide.getPassword());
			list.add(g);
		}
		return list;
	}

}
