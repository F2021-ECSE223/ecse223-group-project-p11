package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
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

}
