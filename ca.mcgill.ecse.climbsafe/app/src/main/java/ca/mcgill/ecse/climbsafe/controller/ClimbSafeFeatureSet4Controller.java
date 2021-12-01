package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet4Controller {

  
  /*** This controller method validates the given input to add an equipment by passing all the tests.
   * 
   * @author Can Akin
   * @param name
   * @param weight
   * @param pricePerWeek
   * @throws InvalidInputException
   */
  
  
  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {
    
    String error = "";  //initialize error 
    
    
    //Check all invalid input data cases, and update the error string if the data is invalid
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
    
    if (BookableItem.hasWithName(name) && BookableItem.getWithName(name) instanceof Equipment) {
        error = "The piece of equipment already exists";
    } 
    
    if (BookableItem.hasWithName(name) && BookableItem.getWithName(name) instanceof EquipmentBundle) {
        error = "The equipment bundle already exists";
    } 
    
    // if error occurred in the previous input data cases, throw an exception 
    if(!error.isEmpty()) {
      throw new InvalidInputException(error);
    }
    try {
      climbSafeApp.addEquipment(name, weight, pricePerWeek); // add an equipment
      ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
    } catch (RuntimeException c) { // catch possible errors
      error = c.getMessage();
      throw new InvalidInputException(error);
    }
    
  }
  /*** This controller method validates the given input to update an equipment by passing all the tests.
   * @author Can Akin
   * 
   * @param oldName
   * @param newName
   * @param newWeight
   * @param newPricePerWeek
   * @throws InvalidInputException
   */

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
  
    if((EquipmentBundle.getWithName(newName) instanceof EquipmentBundle)) {
      error = "An equipment bundle with the same name already exists";
    }
    
    
    if(Equipment.getWithName(newName) instanceof Equipment && !newName.equals(oldName)) {
        error = "The piece of equipment already exists";
      }
      
    
    if(!error.isEmpty()) {
      throw new InvalidInputException(error);
    }
    
    try {
      //update the equipment 
      Equipment equipmentToUpdate = (Equipment) Equipment.getWithName(oldName);
      equipmentToUpdate.setName(newName);
      equipmentToUpdate.setWeight(newWeight);
      equipmentToUpdate.setPricePerWeek(newPricePerWeek);
      ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
      
    } catch (RuntimeException c) {
      throw new InvalidInputException(c.getMessage());
    }
    
  }

}