package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

import java.util.ArrayList;
import java.util.List;

public class ClimbSafeFeatureSet6Controller {

	/*
	 * This method deletes the equipment in the system if it is found via its name.
	 * If it is not found then it throws a InvalidInputException
	 * 
	 * @author Oliver Cafferty
	 * 
	 * @param name of equipment to delete
	 * 
	 * @throws InvalidInputExcpetion
	 */

	public static void deleteEquipment(String name) throws InvalidInputException {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();

		if (!BookableItem.hasWithName(name) || !(BookableItem.getWithName(name) instanceof Equipment)) {
		} else {
			Equipment found = (Equipment) BookableItem.getWithName(name);
			if (found.hasBundleItems()) {
				throw new InvalidInputException("The piece of equipment is in a bundle and cannot be deleted");
			}
			found.delete();
		}
	}

	/*
	 * This method deletes the equipment bundle in the system if it is found via its
	 * name. If it is not found then it throws a InvalidInputException
	 * 
	 * @author Oliver Cafferty
	 * 
	 * @param name of equipment bundle to delete
	 */

	// this method does not need to be implemented by a team with five team members

	public static void deleteEquipmentBundle(String name) {
		if (!BookableItem.hasWithName(name) || !(BookableItem.getWithName(name) instanceof EquipmentBundle)) {
		} else {
			EquipmentBundle equipmentBundle = (EquipmentBundle) BookableItem.getWithName(name);
			equipmentBundle.delete();
		}

	}

	/*
	 * This function returns a list of the assignments found in the system and
	 * prints them out.
	 * 
	 * @author Oliver Cafferty
	 */

	public static List<TOAssignment> getAssignments() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<TOAssignment> list = new ArrayList<TOAssignment>();
		for (Assignment a : cS.getAssignments()) {
			Member member = a.getMember();
			int totalCostEquipment = 0;
			// for each booked item 
			for (BookedItem item : member.getBookedItems()) {
				// if it is a bundle, compute bundle cost
				if (item.getItem() instanceof EquipmentBundle) {
					double bundleCost = 0;
					EquipmentBundle equipmentBundle = (EquipmentBundle) item.getItem();
					for (BundleItem bI : equipmentBundle.getBundleItems()) {
						Equipment e = bI.getEquipment();
						bundleCost += e.getPricePerWeek() * bI.getQuantity();
					}
					if (member.getGuideRequired()) {
						bundleCost = bundleCost * (100.0 - equipmentBundle.getDiscount()) /100.0;
					}
					totalCostEquipment += bundleCost * item.getQuantity();

				} else if (item.getItem() instanceof Equipment) {
					Equipment e = (Equipment) item.getItem();
					totalCostEquipment += e.getPricePerWeek() * item.getQuantity();
				}
			}
			totalCostEquipment *= member.getNrWeeks();
			Guide guide = a.getGuide();
			Hotel hotel = a.getHotel();
			String hotelName;
			String guideName;
			String guideEmail;
			int guidePrice;
			if (hotel == null) {
				hotelName = null;
			} else {
				hotelName = hotel.getName();
			}
			if (guide == null) {
				guideName = null;
				guidePrice = 0;
			} else {
				guideName = guide.getName();
				guidePrice = cS.getPriceOfGuidePerWeek() * member.getNrWeeks();
			}
			if (guide == null) {
				guideEmail = null;
			} else {
				guideEmail = guide.getEmail();
			}
			TOAssignment t = new TOAssignment(member.getEmail(), member.getName(), guideEmail, guideName, hotelName,
					a.getStartWeek(), a.getEndWeek(), guidePrice,
					totalCostEquipment);
			list.add(t);
		}
		return list;
	}

}