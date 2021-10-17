package ca.mcgill.ecse.climbsafe.features;

import java.sql.Date;
import java.util.List;
import java.util.Map;


import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class P11StepDefinitions {
	private ClimbSafe climbSafe;
	private String error;
	private int errorCntr;
	
  @Given("the following ClimbSafe system exists: \\(p11)")
  public void the_following_climb_safe_system_exists_p11(
      io.cucumber.datatable.DataTable dataTable) {
	  climbSafe = ClimbSafeApplication.getClimbSafe();
	  
	  List<Map<String, String>> rows = dataTable.asMaps(); 
	  for (Map<String, String> row : rows) { //probably a better way to do this
		  climbSafe.setStartDate(Date.valueOf(row.get("startDate")));
		  climbSafe.setNrWeeks(Integer.parseInt(row.get("nrWeeks")));
		  climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(row.get("priceOfGuidePerWeek")));
	  }
	  
	  error = "";
	  errorCntr = 0;
	 
    //throw new io.cucumber.java.PendingException();
  }

  @Given("the following guides exist in the system: \\(p11)")
  public void the_following_guides_exist_in_the_system_p11(
      io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> rows = dataTable.asMaps(); //dw
	  for (Map<String, String> row : rows) {
	      String email = row.get("email");
	      String password = row.get("password");
	      String name = row.get("name");
	      String emergencyContact = row.get("emergencyContact");
	      climbSafe.addGuide(email, password, name, emergencyContact);
	 
	      
	    }
    //throw new io.cucumber.java.PendingException();
	  
  }

  @When("the guide with {string} attempts to update their account information to {string}, {string}, and {string} \\(p11)")
  public void the_guide_with_attempts_to_update_their_account_information_to_and_p11(String string,
      String string2, String string3, String string4) {
	  callController(() -> ClimbSafeFeatureSet3Controller.updateGuide(string, string2, string3, string4));    
	 // throw new io.cucumber.java.PendingException();
  }

  @Then("their guide account information will be updated and is now {string}, {string}, {string}, and {string} \\(p11)")
  public void their_guide_account_information_will_be_updated_and_is_now_and_p11(String string,
      String string2, String string3, String string4) {
	  assertEquals("", error);
	  assertEquals(0, errorCntr);
	  Guide guide = (Guide) Guide.getWithEmail(string);
	  assertEquals(guide.getEmail(), string);
	  assertEquals(guide.getPassword(), string2);
	  assertEquals(guide.getName(), string3);
	  assertEquals(guide.getEmergencyContact(), string4);    
	  
	  //throw new io.cucumber.java.PendingException();
  }

  @Then("the number of guides in the system is {int} \\(p11)")
  public void the_number_of_guides_in_the_system_is_p11(Integer int1) {
	  assertEquals(climbSafe.getGuides().size(), int1);
	  //throw new io.cucumber.java.PendingException();
  }

  
  @Then("the following {string} shall be raised \\(p11)")
  public void the_following_shall_be_raised_p11(String string) {
	  assertTrue(error.contains(string));
    //throw new io.cucumber.java.PendingException();
  }

  @Then("the guide account information will not be updated and will keep {string}, {string}, {string}, and {string} \\(p11)")
  public void the_guide_account_information_will_not_be_updated_and_will_keep_and_p11(String string,
      String string2, String string3, String string4) {
	  //assertEquals("", error); //assert no equal ?? case where there is an error
	  //assertEquals(0, errorCntr);
	  Guide guide = (Guide) Guide.getWithEmail(string);
	  assertEquals(guide.getEmail(), string);
	  assertEquals(guide.getPassword(), string2);
	  assertEquals(guide.getName(), string3);
	  assertEquals(guide.getEmergencyContact(), string4);   
	  //throw new io.cucumber.java.PendingException();
	  
  }
  
  @After
  public void tearDown() {
	  climbSafe.delete();
	}
  

private void callController(Executable executable) { //from the btms step definitions in the tutorial, for @When
    try {
      executable.execute();
    } catch (InvalidInputException e) {
      error += e.getMessage();
      errorCntr += 1;
    } catch (Throwable t) {
      fail();
    }
  }
}