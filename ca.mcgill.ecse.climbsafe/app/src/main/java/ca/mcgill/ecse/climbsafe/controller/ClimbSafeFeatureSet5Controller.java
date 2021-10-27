package ca.mcgill.ecse.climbsafe.controller;

import java.util.*;

import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;


public class ClimbSafeFeatureSet5Controller {

  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) throws InvalidInputException {
	  
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  List<Equipment> systemEquipment = climbSafe.getEquipment();
	  
	  String q = systemEquipment.toString();
	  for(int x = 0; x < systemEquipment.size(); x++) {
		  String a = systemEquipment.get(x).toString();
		  
		  int j = 0;
		  int matchNr = 0;
		  for(String i : equipmentNames) {
			  if(a.contains(i)) {
				  error = "";
				  matchNr ++;
			  }
			  if(name.equals(i)) {
				  error = "A bookable item called " + i + " already exists";
			  }
			  if(!q.contains(i)) {
				  error = "Equipment " + i +  " does not exist";
					}
			  
			  if (equipmentQuantities.get(j)<= 0 ) {
				  error = "Each bundle item must have quantity greater than or equal to 1";
				  
			  }
			  j++;
		  }
		  if(matchNr >= 2) {
			  error = "Equipment bundle must contain at least two distinct types of equipment";
			  
		  } 
	  }
	  
	  if(equipmentNames.size() < 2 || equipmentQuantities.size() < 2) {
		  error = "Equipment bundle must contain at least two distinct types of equipment";
	  }
	  
	  if(discount < 0) {
		  error = "Discount must be at least 0";
	  }
	  if(discount > 100) {
		  error = "Discount must be no more than 100";
	  }
	  if(name == "") {
		  error = "Equipment bundle name cannot be empty";
	  }
	  // check for duplicates in the equipment names
	  String b = climbSafe.getBundles().toString();
	  if(b.contains(name)&& b.length()> 5) {
		  error ="A bookable item called " + name + " already exists";
		  
	  }
	  
	  if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}
	  
	  try {
		  
		  EquipmentBundle newBundle = new EquipmentBundle(name, discount, climbSafe);
		  for(int i = 0, j = 0 ; i < equipmentQuantities.size(); i++, j++) {
			  newBundle.addBundleItem(equipmentQuantities.get(i).intValue(), climbSafe, (Equipment) BookableItem.getWithName(equipmentNames.get(j)));
		  }
		  
	  }catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		  
	  }}
	  
	 

  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  String error = "";
	  List<Equipment> systemEquipment = climbSafe.getEquipment();
	  
	  
	  String q = systemEquipment.toString(); //q has equipment names
	  String b = climbSafe.getBundles().toString(); //b has bundle names
	  
	  if(newEquipmentNames.size() < 2 || newEquipmentQuantities.size() < 2) {
		  error = "Equipment bundle must contain at least two distinct types of equipment";
	  }
	  
	  if(newDiscount < 0) {
		  error = "Discount must be at least 0";
	  }
	  if(newDiscount > 100) {
		  error = "Discount must be no more than 100";
	  }
	  
	  
	  if(!newName.equals(oldName) && b.contains("name:" + newName)) {
		  error = "A bookable item called " + newName + " already exists";
	  }

	  if(!b.contains("name:" + oldName))  {
		  error = "Equipment bundle " + oldName + " does not exist";
		  }
	  
	  if(newName.equals("")) {
		  error = "Equipment bundle name cannot be empty";
	  }
	  
	  
	  
	  if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}
	  
	  
	  for(int x = 0; x < systemEquipment.size(); x++) {
		  String a = systemEquipment.get(x).toString();
		  
		  int j = 0;
		  int matchNr = 0;
		  for(String i : newEquipmentNames) {
			  if(a.contains(i)) {
				  error = "";
				  matchNr ++;
			  }
			  if(!q.contains(i)) {
				  error = "Equipment " + i + " does not exist";
			  }
			  if(q.contains(newName)) {
				  error = "A bookable item called " + newName + " already exists";
			  }
			  
			  if (newEquipmentQuantities.get(j)<= 0 ) {
				  error = "Each bundle item must have quantity greater than or equal to 1";
				  
			  }
			  j++;
		  }
		  if(matchNr >= 2) {
			  error = "Equipment bundle must contain at least two distinct types of equipment";
			  
		  } 
	  }
	  
	  if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}
	  
	  try {
	for(EquipmentBundle d: climbSafe.getBundles() ) {
		  
		  if(oldName.equals(d.getName()) ) {
			List<String> temp= new ArrayList<>();
			for(int k =0; k< d.getBundleItems().size(); k++) {
				String g = d.getBundleItem(k).getEquipment().toString();
			
				temp.add(g);
				
			}
			String bundleAsString = temp.toString();
			
			d.setDiscount(newDiscount);
			//for loop of newEquipmentNames check if all equipment names match, if not, add the one that doesnt
			for(int h= 0; h<newEquipmentNames.size();h++) {
				if(!bundleAsString.contains(newEquipmentNames.get(h))) {
					d.addBundleItem(newEquipmentQuantities.get(h), climbSafe, (Equipment) BookableItem.getWithName(newEquipmentNames.get(h)));
					}
				}
			
			for(int j =0; j< newEquipmentNames.size(); j++) {
				
				for(int i =0; i< newEquipmentNames.size(); i++) {
					if(d.getBundleItem(i).toString().contains(newEquipmentNames.get(j))) {
						d.getBundleItem(i).setQuantity(newEquipmentQuantities.get(j));
					  }
					  }
				 }
			}
		  if(!newName.equals(oldName)&& oldName.equals(d.getName())) {
			  d.setName(newName);
			  
		  }
		  }
		  }catch (RuntimeException e) {
						throw new InvalidInputException(e.getMessage());
						  }
	  }
		  }
	 
