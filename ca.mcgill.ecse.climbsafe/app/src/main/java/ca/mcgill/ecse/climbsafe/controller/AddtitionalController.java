package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Administrator;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;

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
	
	public static List<String> getMemberEmails() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> list = new ArrayList<String>();
		for (Member member : cS.getMembers()) {
			list.add(member.getEmail());
		}
		return list;
	}
	//maxime
	public static List<List<Integer>>EquipmentBundleItemsQuantity() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<List<Integer>> quantities= new ArrayList<>();
		for(EquipmentBundle bI: cS.getBundles()) {
			List<Integer> quantity=new ArrayList<>();
			for(BookedItem i:bI.getBookedItems()) {
				quantity.add(i.getQuantity());
			} 
			quantities.add(quantity);
		}
		return quantities;
	}
	public static List<List<String>>EquipmentBundleItemsNames() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<List<String>> names= new ArrayList<>();
		for(EquipmentBundle bI: cS.getBundles()) {
			List<String> name=new ArrayList<>();
			for(BundleItem i:bI.getBundleItems()) {
				String x = i.getEquipment().getName();
				name.add(x);
			}
			names.add(name);
		}
		return names;
	}
	
	//done
	
	
	//oliver
	public static List<String> getAssignmentMemberEmails() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> list =new ArrayList<String>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getMember().getEmail());
		}
		return list;
	}
	public static List<String> getAssignmentMemberNames() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> list =new ArrayList<String>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getMember().getName());
		}
		return list;
	}
	public static List<String> getAssignmentGuideEmails() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> list =new ArrayList<String>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getGuide().getEmail());
		}
		return list;
	}
	public static List<String> getAssignmentGuideNames() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> list =new ArrayList<String>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getGuide().getName());
		}
		return list;
	}
	public static List<String> getAssignmentHotelNames() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> list =new ArrayList<String>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getHotel().getName());
		}
		return list;
	}
	public static List<Integer> getAssignmentStartWeeks() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<Integer> list =new ArrayList<Integer>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getStartWeek());
		}
		return list;
	}
	public static List<Integer> getAssignmentEndWeeks() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<Integer> list =new ArrayList<Integer>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getEndWeek());
		}
		return list;
	}
	public static List<Integer> getAssignmentTotalGuideCosts() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<Integer> list =new ArrayList<Integer>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getTotalGuideCost());
		}
		return list;
	}
	public static List<Integer> getAssignmentTotalEquipmentCosts() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<Integer> list =new ArrayList<Integer>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getTotalEquipmentCost());
		}
		return list;
	}
	
	public static List<String> getAssignmentStatuses() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> list =new ArrayList<String>();
		for (Assignment a : cS.getAssignments()) {
			list.add(a.getAssignmentStatusFullName());
		}
		return list;
	}
	
	
	//oliver done
	public static List<Assignment> getAllAssignments(){
		ClimbSafe cS= ClimbSafeApplication.getClimbSafe();
		return cS.getAssignments();
	}
	
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
					totalCostEquipment, a.getAssignmentStatusFullName(), a.getAuthorizationCode(), a.getRefundPercentage());
			list.add(t);
		}
		return list;
	}

	
	
	
	
	//for testing
	public static String getMemberInfo(String email){
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> list = new ArrayList<String>();
		for (Member member : cS.getMembers()) {
			if(member.getEmail().equals(email)) {
				return (String) (member.getName() + member.getPassword() + member.getEmergencyContact() + member.getBookedItem(0).toString());
			}
		}
		return "here";
	}
		
	
	
	public static void createAdmin() {
		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		if(!climbSafe.hasAdministrator()) {
			Administrator admin = new Administrator("admin@nmc.nt", "admin", climbSafe);
			climbSafe.setAdministrator(admin);
		}
	}
	
	public static List<TOEquipment> getEquipment() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
	    var equipments = new ArrayList<TOEquipment>();
	    for(var equipment : cS.getEquipment()) {
	      equipments.add(new TOEquipment(equipment.getName(),equipment.getWeight(),equipment.getPricePerWeek()));
	    }
	    return equipments;
	  }
	
	
	public static List<String> getEquipmentStrings() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> equipmentStr = new ArrayList<String>();
		for(var equipment : cS.getEquipment()) {
			String temp = equipment.getName();
			equipmentStr.add(temp);
			
		}
		return equipmentStr;
		
	}
	
	public static List<String> getBundleStrings() {
		ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
		List<String> bundleStr = new ArrayList<String>();
		for(var bundle : cS.getBundles()) {
			String temp = bundle.getName();
			bundleStr.add(temp);
			
		}
		return bundleStr;
	}
	
	public static Integer getItemCost(String name) {
		List<TOEquipment> test= getEquipment();
		for(int i = 0; i < test.size(); i++) {
			if(name.equals(test.get(i).getName())) {
				return test.get(i).getPricePerWeek();
			}
		}	
		return null;
	}
	
	public static String login(String email, String password) throws InvalidInputException {
		if (!User.hasWithEmail(email)) {
			throw new InvalidInputException("This email does not correspond to any system member");	
		} else if (User.getWithEmail(email).getPassword().equals(password)){
			throw new InvalidInputException("The password is incorrect");	
		} else if (User.getWithEmail(email) instanceof Guide) {
			return "Guide";
		} else if (User.getWithEmail(email) instanceof Member) {
			return "Member";
		} else if (User.getWithEmail(email) instanceof Administrator) {
			return "Admin";
		}
		return null;
	}

	

}
