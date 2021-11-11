package ca.mcgill.ecse.climbsafe.features;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.function.Executable;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Assignment.AssignmentStatus;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.Member.BanStatus;
import ca.mcgill.ecse.climbsafe.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignmentFeatureStepDefinitions {
	private ClimbSafe climbSafe;
	private String error = "";
	private int errorcount;
/**
 * 
 * @param dataTable
 * @author Anaëlle Drai-Laguens
 * setting start date, number of weeks, price of guide per week
 */
	@Given("the following ClimbSafe system exists:")
	public void the_following_climb_safe_system_exists(io.cucumber.datatable.DataTable dataTable) {

		climbSafe = ClimbSafeApplication.getClimbSafe();
		List<Map<String, String>> rows = dataTable.asMaps();
		for (Map<String, String> row : rows) { // set the attributes of the climbSafe system
			climbSafe.setStartDate(Date.valueOf(row.get("startDate")));
			climbSafe.setNrWeeks(Integer.parseInt(row.get("nrWeeks")));
			climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(row.get("priceOfGuidePerWeek")));
		}
	}
/**
 * @author Anaëlle Drai-Laguens
 * @param dataTable
 * adding equipment to instance of climbsafe
 */
	@Given("the following pieces of equipment exist in the system:") // copied from p1
	public void the_following_pieces_of_equipment_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		var rows = dataTable.asMaps();

		for (var row : rows) {
			climbSafe.addEquipment(row.get("name"), Integer.parseInt(row.get("weight")),
					Integer.parseInt(row.get("pricePerWeek")));
		}
	}
/**
 * @author Anaëlle Drai-Laguens
 * @param dataTable
 * creating available bundles
 */
	@Given("the following equipment bundles exist in the system:") // copied from p2
	public void the_following_equipment_bundles_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps();
		for (var row : rows) {
			EquipmentBundle bundle = climbSafe.addBundle(row.get("name"), Integer.parseInt(row.get("discount")));
			String items = row.get("items");
			String quantities = row.get("quantity");
			List<String> itemList = Arrays.asList(items.split(","));
			List<String> quantityList = Arrays.asList(quantities.split(","));
			for (int i = 0; i < itemList.size(); i++) {
				if (Equipment.hasWithName(itemList.get(i))
						&& Equipment.getWithName(itemList.get(i)) instanceof Equipment) {
					BundleItem bundleItem = bundle.addBundleItem(Integer.parseInt(quantityList.get(i)), climbSafe,
							(Equipment) Equipment.getWithName(itemList.get(i)));
					bundle.addBundleItem(bundleItem);
				}
			}
		}
	}
/**
 * 
 * @param dataTable
 * @author Anaëlle Drai-Laguens
 * instance of a guide and guide's data in climbsafe
 */
	@Given("the following guides exist in the system:") // copied from p3
	public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> guideList = dataTable.asMaps();
		// Traverse through list of guides
		for (int i = 0; i < guideList.size(); i++) {
			// Retrieve information
			String guideEmail = guideList.get(i).get("email");
			String guidePassword = guideList.get(i).get("password");
			String guideName = guideList.get(i).get("name");
			String guideEmergencyContact = guideList.get(i).get("emergencyContact");
			// Add guide with given information
			climbSafe.addGuide(guideEmail, guidePassword, guideName, guideEmergencyContact);
		}
	}
/**
 * 
 * @param dataTable
 * @author Can Akin
 * instance of a member in climbsafe
 */
	@Given("the following members exist in the system:") // copied from p3
	public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> memberList = dataTable.asMaps();
		for (int i = 0; i < memberList.size(); i++) {
			climbSafe.addMember(memberList.get(i).get("email"), memberList.get(i).get("password"),
					memberList.get(i).get("name"), memberList.get(i).get("emergencyContact"),
					Integer.parseInt(memberList.get(i).get("nrWeeks")),
					Boolean.parseBoolean(memberList.get(i).get("guideRequired")),
					Boolean.parseBoolean(memberList.get(i).get("hotelRequired")));
		}
	}
/**
 * @author Can Akin
 * The admin initiates the assignment process via controller
 */
	@When("the administrator attempts to initiate the assignment process") 
	public void the_administrator_attempts_to_initiate_the_assignment_process() {
		callController(() -> AssignmentController.initiateAssignment());
	}
/**
 * 
 * @param dataTable
 * @author Can Akin
 * successful initialization of assignment and associated assignment data
 * 
 */
	@Then("the following assignments shall exist in the system:") // copied from p5
	public void the_following_assignments_shall_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> cucumberData = dataTable.asMaps();

		for (Map<String, String> assignmentData : cucumberData) {
			var assignmentMember = (Member) User.getWithEmail(assignmentData.get("memberEmail"));
			var assignmentGuide = (Guide) User.getWithEmail(assignmentData.get("guideEmail"));
			var assignmentHotel = Hotel.getWithName(assignmentData.get("hotelName"));
			int startWeek = Integer.valueOf(assignmentData.get("startWeek"));
			int endWeek = Integer.valueOf(assignmentData.get("endWeek"));

			
				
			assertEquals(assignmentMember, assignmentMember.getAssignment().getMember());
			assertEquals(assignmentGuide, assignmentMember.getAssignment().getGuide());
			assertEquals(startWeek, assignmentMember.getAssignment().getStartWeek());
			assertEquals(endWeek, assignmentMember.getAssignment().getEndWeek());
			
			
		}
	}

/**
 * 
 * @param email
 * @param AssignmentStatus
 * @author Maxime Drouin
 * a member is given an assignment status
 */
	@Then("the assignment for {string} shall be marked as {string}")
	public void the_assignment_for_shall_be_marked_as(String email, String AssignmentStatus) {
		Member m = (Member) User.getWithEmail(email);
		Assignment a = m.getAssignment();
		assertEquals(AssignmentStatus, a.getAssignmentStatusFullName());
	}
/**
 * 
 * @param numOfAssignments
 * @author Maxime Drouin
 * sets number pof assignments currently in the climbsafe system
 */
	@Then("the number of assignments in the system shall be {string}")
	public void the_number_of_assignments_in_the_system_shall_be(String numOfAssignments) {
		assertEquals(Integer.parseInt(numOfAssignments), climbSafe.getAssignments().size());
	}
/**
 * 
 * @param errorMessage
 * @author Maxime Drouin
 * check error message is correct
 */
	@Then("the system shall raise the error {string}")
	public void the_system_shall_raise_the_error(String errorMessage) {
		assertTrue(error.contains(errorMessage));
	}
/**
 * 
 * @param dataTable
 * @author Oliver Cafferty
 * adds name,weight and price of equipment to climbsafe
 */
	@Given("the following equipment exists in the system:") // copied from p2
	public void the_following_equipment_exists_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps();
		for (var row : rows) {
			String name = row.get("name");
			int weight = Integer.parseInt(row.get("weight"));
			int pricePerWeek = Integer.parseInt(row.get("pricePerWeek"));
			climbSafe.addEquipment(name, weight, pricePerWeek);
		}
	}
/**
 * 
 * @param dataTable
 * @author Oliver Cafferty
 * creating new assignment, setting hotel and guide for assignment
 */
	@Given("the following assignments exist in the system:") // copied from p5
	public void the_following_assignments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> cucumberData = dataTable.asMaps();

		for (Map<String, String> assignmentData : cucumberData) {
			var assignmentMember = (Member) User.getWithEmail(assignmentData.get("memberEmail"));
			var assignmentGuide = (Guide) User.getWithEmail(assignmentData.get("guideEmail"));
			var assignmentHotel = Hotel.getWithName(assignmentData.get("hotelName"));
			int startWeek = Integer.valueOf(assignmentData.get("startWeek"));
			int endWeek = Integer.valueOf(assignmentData.get("endWeek"));

			Assignment newAssignment = climbSafe.addAssignment(startWeek, endWeek, assignmentMember);
			newAssignment.setGuide(assignmentGuide);
			newAssignment.setHotel(assignmentHotel);
		}
	}
/**
 * 
 * @param email
 * @param authorizationCode
 * @author Oliver Cafferty
 * admin confirms payment via controller call
 */
	@When("the administrator attempts to confirm payment for {string} using authorization code {string}") // todo
	public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(String email,
			String authorizationCode) {
		callController(() -> AssignmentController.payTrip(email, authorizationCode));

	}
/**
 * 
 * @param email
 * @param authorizationCode
 * @author Sam Snodgrass
 * checks that the inputed authorization code matches that of the assignment
 */
	@Then("the assignment for {string} shall record the authorization code {string}")
	public void the_assignment_for_shall_record_the_authorization_code(String email, String authorizationCode) {
		Member member = (Member) User.getWithEmail(email);
		Assignment assignment = member.getAssignment();
		assertEquals(authorizationCode, assignment.getAuthorizationCode());
	}
/**
 * 
 * @param email
 * @author Sam Snodgrass
 * confirms member does not actually exists with this email
 */
	@Then("the member account with the email {string} does not exist") 
	public void the_member_account_with_the_email_does_not_exist(String email) {
		assertEquals(null, (Member) User.getWithEmail(email));
	}
/**
 * 
 * @param numOfMembers
 * @author Sam Snodgrass
 * throws assertion error if ammount oif members are not equal
 * 
 */
	@Then("there are {string} members in the system")
	public void there_are_members_in_the_system(String numOfMembers) {
		assertEquals(Integer.parseInt(numOfMembers), climbSafe.getMembers().size());
	}
/**
 * 
 * @param errorMessage
 * @author Sam Snodgrass
 * check if proper error is raised
 */
	@Then("the error {string} shall be raised")
	public void the_error_shall_be_raised(String errorMessage) {
		assertTrue(error.contains(errorMessage));
	}
/**
 * 
 * @param email
 * @author Lee Brickman
 * admin attempts to cancel trip via controller call
 */
	@When("the administrator attempts to cancel the trip for {string}") 
	public void the_administrator_attempts_to_cancel_the_trip_for(String email) {
		callController(() -> AssignmentController.cancelTrip(email));
	}
/**
 * 
 * @param email
 * @author Lee Brickman
 * user with input email pays for trip
 */
	@Given("the member with {string} has paid for their trip") 
	public void the_member_with_has_paid_for_their_trip(String email) {
		Member member = (Member) User.getWithEmail(email);
		member.getAssignment().payAssignment("23313");
	}
/**
 * 
 * @param email
 * @param refundPercentage
 * @author Lee Brickman
 * confirms refund percentage matches refund of member
 */
	@Then("the member with email address {string} shall receive a refund of {string} percent") 
	public void the_member_with_email_address_shall_receive_a_refund_of_percent(String email, String refundPercentage) {
		Member member = (Member) User.getWithEmail(email);
		assertEquals(Integer.parseInt(refundPercentage), member.getAssignment().getRefundPercentage());
	}
/**
 * 
 * @param email
 * @author Lee Brickman
 * member has payed assignment and started their trip
 */
	@Given("the member with {string} has started their trip") //
	public void the_member_with_has_started_their_trip(String email) {
		Member m = (Member) User.getWithEmail(email);
		m.getAssignment().payAssignment("23234");
		m.getAssignment().startAssignment();
	}
/**
 * @param email
 * @author Lee Brickman 
 * admin tries to finish trip via controller call
 */
	@When("the administrator attempts to finish the trip for the member with email {string}") // todo
	public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(String email) {
		callController(() -> AssignmentController.finishTrip(email));
	}
/**
 * 
 * @param email
 * @author Maxime Drouin
 * set the member with string email as banned
 */
	@Given("the member with {string} is banned") 
	public void the_member_with_is_banned(String email) {
		Member member = (Member) User.getWithEmail(email);
		member.setBanStatus(BanStatus.Banned);
	}
/**
 * 
 * @param email
 * @param status
 * @author Maxime Drouin
 * confirms member is indeed banned
 */
	@Then("the member with email {string} shall be {string}") // todo
	public void the_member_with_email_shall_be(String email, String status) {
		Member member = (Member) User.getWithEmail(email);
		assertEquals(member.getBanStatus().toString(), status);
	}
/**
 * 
 * @param weekNumber
 * @author Sam Snodgrass
 * admin starts climbing trips for given week
 */
	@When("the administrator attempts to start the trips for week {string}") // todo
	public void the_administrator_attempts_to_start_the_trips_for_week(String weekNumber) {
		callController(() -> AssignmentController.startTrips(Integer.parseInt(weekNumber)));
	}
/**
 * 
 * @param email
 * @author Sam Snodgrass
 * the member has actually cancelled their trip
 */
	@Given("the member with {string} has cancelled their trip") // todo
	public void the_member_with_has_cancelled_their_trip(String email) {
		Member member = (Member) User.getWithEmail(email);
		member.getAssignment().cancelAssignment();
	}
/**
 * 
 * @param email
 * @author Sam Snodgrass
 * set the member's assignment status as finished
 */
	@Given("the member with {string} has finished their trip") // not sure
	public void the_member_with_has_finished_their_trip(String email) {
		Member member = (Member) User.getWithEmail(email);
		//member.getAssignment().payAssignment("jsh");
		//member.getAssignment().startAssignment();
		//member.getAssignment().finishAssignment();
		Assignment a =member.getAssignment();
		a.setAssignmentState(AssignmentStatus.Finished);
		
		//a.setAssignmentStatus(AssignmentStatus.Finished);
	}
/**
 * 
 * @param email
 * @author Sam Snodgrass
 * Ban the member
 */
	@Then("the member with email {string} shall be banned")
	public void the_member_with_email_shall_be_banned(String email) {
		Member member = (Member) User.getWithEmail(email);
		assertEquals(member.getBanStatus(), BanStatus.Banned);
	}

	private void callController(Executable executable) { // from the btms step definitions in tutorial
		// 6
		try { // try executing the function
			executable.execute();
		} catch (InvalidInputException e) { // in case an error occurs, store the message and increment
			// error count
			error += e.getMessage();
			errorcount += 1;
		} catch (Throwable t) {
			fail();
		}
	}
}