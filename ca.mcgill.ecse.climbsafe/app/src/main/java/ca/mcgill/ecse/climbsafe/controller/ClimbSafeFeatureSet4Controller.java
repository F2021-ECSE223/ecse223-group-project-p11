package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet4Controller {

  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {
    
    String error = "";
    
    if(name.equals("") || name == null || name.isEmpty()) {
      error = "The name must not be empty";
    }
    
    if(weight <= 0) {
      error = "The weight must be greater than 0";
    }
    
    if(pricePerWeek < 0) {
      error = "The price per week must be greater than or equal to 0";
    }
    ClimbSafe climbSafeApp = ClimbSafeApplication.getClimbSafe();
    
    List<Equipment> currentEquipment = climbSafeApp.getEquipment();
    
    for(Equipment e: currentEquipment) {
      
      if(e.hasWithName(name)) {
        error = "The piece of equipment already exists";
      }   
    }
    
    if((EquipmentBundle.getWithName(name) instanceof EquipmentBundle)) {
      error = "The equipment bundle already exists";
    }
    
     
    if(!error.isEmpty()) {
      throw new InvalidInputException(error);
    }
    try {
      climbSafeApp.addEquipment(name, weight, pricePerWeek);
    } catch (RuntimeException c) {
      error = c.getMessage();
      throw new InvalidInputException(error);
    }
    
    
  }

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {
    
    String error = "";
   
    if(newWeight <= 0) {
      error = "The weight must be greater than 0";
      
    }
    if(newPricePerWeek < 0) {
      error = "The price per week must be greater than or equal to 0";
    }
    
    if(newName == null || newName.equals("")) {
      error = "The name must not be empty";
    }
   
    if(Equipment.getWithName(oldName)==null) {
      error = "The piece of equipment does not exist";
    }
    
    //error = The piece of equipment already exists";
    /*
    ClimbSafe climbSafeApp = ClimbSafeApplication.getClimbSafe();
    
    List<Equipment> currentEquipment = climbSafeApp.getEquipment();
    
    for(Equipment e: currentEquipment) {
      
      if(e.hasWithName(name)) {
        error = "The piece of equipment already exists";
      }   
    }*/
      
    if((EquipmentBundle.getWithName(newName) instanceof EquipmentBundle)) {
      error = "An equipment bundle with the same name already exists";
    }
    
    if(!error.isEmpty()) {
      throw new InvalidInputException(error);
    }
    
    try {
      
      Equipment equipmentToUpdate = (Equipment) Equipment.getWithName(oldName);
      equipmentToUpdate.setName(newName);
      equipmentToUpdate.setWeight(newWeight);
      equipmentToUpdate.setPricePerWeek(newPricePerWeek);
      
    } catch (RuntimeException c) {
      throw new InvalidInputException(c.getMessage());
    }
    
  }

}
