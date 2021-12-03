
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
  
  /*
   * method returning a list of guides transfer objects
   * @author Anaelle Drai-Laguens
   */
  
  public static List<TOGuide> getGuides() {
    ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
    List<TOGuide> list = new ArrayList<TOGuide>();
    for (Guide guide : cS.getGuides()) {
      TOGuide g = new TOGuide(guide.getName(), guide.getEmergencyContact(), guide.getEmail(), guide.getPassword());
      list.add(g);
    }
    return list;
  }
  
  /*
   * method returning a list of all the registered guide emails
   * @author Can Akin
   * 
   */
  public static List<String> getGuideEmails() {
    ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
    List<String> list = new ArrayList<String>();
    for (Guide guide : cS.getGuides()) {
      list.add(guide.getEmail());
    }
    return list;
  }
  
  /*
   * updates the items in the bundle
   * void method 
   * @author Lee Brickman
   * 
   */
  public static void updateBundleItem(String BundleName, String itemName, Integer newNum){
    ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
 
    for(EquipmentBundle e: cS.getBundles()) {
      if(e.getName().equals(BundleName)) {
        for(BundleItem bI: e.getBundleItems()) {
          if(bI.getEquipment().getName().equals(itemName)) {
            bI.setQuantity(newNum);
            break;
          }
        }
      }
      }
  }
  /* 
   * returns a list of all the bundle items name in bundle found with its name 
   * @param bundle Name 
   * @author Oliver Cafferty 
   */
  public static List<String> getBundleItemNames(String bName){
	  ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
	  List<String> bINames= new ArrayList<>();
	  for(EquipmentBundle e:cS.getBundles()) {
		  if(e.getName().equals(bName)) {
			  for(BundleItem bI: e.getBundleItems()) {
				  bINames.add(bI.getEquipment().getName());
			  }
			  break;
		  }
	  }
	  return bINames;
  }
  public static List<Integer> getBundleItemQuantities(String bName){
	  ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
	  List<Integer> bIQ= new ArrayList<>();
	  for(EquipmentBundle e:cS.getBundles()) {
		  if(e.getName().equals(bName)) {
			  for(BundleItem bI: e.getBundleItems()) {
				  bIQ.add(bI.getQuantity());
			  }
			  break;
		  }
	  }
	  return bIQ;
  }

  
  /* 
   * returns a list of all the equipment objects available in the climbsafe app
   * @author Oliver Cafferty 
   */
  public static List<Equipment> getAllEquipment(){
    ClimbSafe cS= ClimbSafeApplication.getClimbSafe();
    return  cS.getEquipment();
  }

  /*
   * returns a list of all the emails for registered members
   * @author Can Akin
   */
  public static List<String> getMemberEmails() {
    ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
    List<String> list = new ArrayList<String>();
    for (Member member : cS.getMembers()) {
      list.add(member.getEmail());
    }
    return list;
  }
  /*
   * method returning a 2d list with all the item quantites for each item per bundle 
   * @author Maxime Drouin
   */
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
  /*
   * method returning a 2d list of all the item names per bundle 
   * @author Oliver Cafferty
   */
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
  
  
  /*
   * method returning all of the assignments in the climbsafe application
   * @author Anaelle Drai-Laguens
   */
  
  public static List<Assignment> getAllAssignments(){
    ClimbSafe cS= ClimbSafeApplication.getClimbSafe();
    return cS.getAssignments();
  }
  

  
  
  
  
  /*
   * method returning all of the member info, for testing purposes
   * @param email
   * @author Anaelle Drai-Laguens
   */
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
    
  /*
   * method creating the admin for the climbsafe application
   * @author Sam Snodgrass
   */
  
  public static void createAdmin() {
    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    if(!climbSafe.hasAdministrator()) {
      Administrator admin = new Administrator("admin@nmc.nt", "admin", climbSafe);
      climbSafe.setAdministrator(admin);
    }
  }
  /*
   * method returning a list of equipment transfer objects that are in the application
   * @author Oliver Cafferty 
   */
  
  public static List<TOEquipment> getEquipment() {
    ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
      var equipments = new ArrayList<TOEquipment>();
      for(var equipment : cS.getEquipment()) {
        equipments.add(new TOEquipment(equipment.getName(),equipment.getWeight(),equipment.getPricePerWeek()));
      }
      return equipments;
    }
  
  /*
   * method returning a list of all of hte names of the equipments
   * @author Lee Brickman
   */
  
  public static List<String> getEquipmentStrings() {
    ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
    List<String> equipmentStr = new ArrayList<String>();
    for(var equipment : cS.getEquipment()) {
      String temp = equipment.getName();
      equipmentStr.add(temp);
      
    }
    return equipmentStr;
    
  }
  /*
   * method returning a list of all of the bundle names
   * @author Maxime Drouin
   */
  public static List<String> getBundleStrings() {
    ClimbSafe cS = ClimbSafeApplication.getClimbSafe();
    List<String> bundleStr = new ArrayList<String>();
    for(var bundle : cS.getBundles()) {
      String temp = bundle.getName();
      bundleStr.add(temp);
      
    }
    return bundleStr;
  }
  /*
   * method returning the list of equipment in a bundle
   * @param name of equipment
   * @author 
   */

  /*
   * method returning the cost of a specific item 
   * @param name 
   * @author Sam Snodgrass
   */
  public static Integer getItemCost(String name) {
    List<TOEquipment> test= getEquipment();
    for(int i = 0; i < test.size(); i++) {
      if(name.equals(test.get(i).getName())) {
        return test.get(i).getPricePerWeek();
      }
    } 
    return null;
  }
  /*
   * method allowing a user to log into the climbsafe application
   * @param email
   * @param password
   * @author Sam Snodgrass
   */
  
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
