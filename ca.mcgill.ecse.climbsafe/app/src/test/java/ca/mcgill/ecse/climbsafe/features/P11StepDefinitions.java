package ca.mcgill.ecse.climbsafe.features;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
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
	  error = "";
	  errorCntr = 0;
    throw new io.cucumber.java.PendingException();
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
    throw new io.cucumber.java.PendingException();
  }

  @When("the guide with {string} attempts to update their account information to {string}, {string}, and {string} \\(p11)")
  public void the_guide_with_attempts_to_update_their_account_information_to_and_p11(String string,
      String string2, String string3, String string4) {
	  ClimbSafeFeatureSet3Controller.updateGuide(string, string2, string3, string4);    
	  throw new io.cucumber.java.PendingException();
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
	  throw new io.cucumber.java.PendingException();
  }

  @Then("the number of guides in the system is {int} \\(p11)")
  public void the_number_of_guides_in_the_system_is_p11(Integer int1) {
	  assertEquals(climbSafe.getGuides().size(), int1);
	  throw new io.cucumber.java.PendingException();
  }

  @Then("the following {string} shall be raised \\(p11)")
  public void the_following_shall_be_raised_p11(String string) {
    fail("An unexpected assignment exists in the BTMS");
    throw new io.cucumber.java.PendingException();
  }

  @Then("the guide account information will not be updated and will keep {string}, {string}, {string}, and {string} \\(p11)")
  public void the_guide_account_information_will_not_be_updated_and_will_keep_and_p11(String string,
      String string2, String string3, String string4) {
	  assertEquals("", error);
	  assertEquals(0, errorCntr);
	  Guide guide = (Guide) Guide.getWithEmail(string);
	  assertEquals(guide.getEmail(), string);
	  assertEquals(guide.getPassword(), string2);
	  assertEquals(guide.getName(), string3);
	  assertEquals(guide.getEmergencyContact(), string4);   
	  throw new io.cucumber.java.PendingException();
  }
}
