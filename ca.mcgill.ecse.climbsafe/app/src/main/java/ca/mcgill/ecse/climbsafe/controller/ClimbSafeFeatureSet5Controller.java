package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet5Controller {

  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) throws InvalidInputException {
	  
	  String error = "";
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  /*
	  try {
		  EquipmentBundle a = climbSafe.addBundle(name, discount);
		  Equipment b = climbSafe.
		  
		  for(int i = 0 ; i < equipmentNames.size(); i++) {
			  
			  
			  climbSafe.addBundleItem(equipmentQuantities.get(i).intValue(), a, equipmentNames.);
			  
			  
		  }
		  
		  
		  climbSafe.addBundle(name, discount);
		  
	  }catch (RuntimeException e) {
			error = e.getMessage();
			if (error.equals(
					"")) {
				error = "";
			}
			throw new InvalidInputException(error);
		}
	  
	  
	  */
	  
	  
	  
  }

  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {}

}
