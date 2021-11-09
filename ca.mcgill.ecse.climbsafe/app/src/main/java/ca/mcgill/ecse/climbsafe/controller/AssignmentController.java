package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

public class AssignmentController {

	public static void startTrips(int weekNumber) throws InvalidInputException {

		String error = "";
		if (weekNumber < 1) {
			error = "Invalid week number";
		}

		if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}

		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		for (Assignment assignment : climbSafe.getAssignments()) {
			if (assignment.getStartWeek() == weekNumber) {
				try {
					assignment.startAssignment();
				} catch (RuntimeException c) {
					throw new InvalidInputException(c.getMessage());
				}
			}
		}
		ClimbSafePersistence.save(climbSafe);
	}

	public static void finishTrip(String email) throws InvalidInputException {
		String error = "";
		if (!(User.getWithEmail(email) instanceof Member)) {
			error = "The user with this email is not a member";
		}
		if (User.getWithEmail(email) == null) {
			error = "Member with email address " + email + " does not exist";
		}

		if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}
		Member member = (Member) User.getWithEmail(email);
		Assignment assignment = member.getAssignment();
		try {
			assignment.finishAssignment();
		} catch (RuntimeException c) {
			throw new InvalidInputException(c.getMessage());
		}
		ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
	}

	public static void cancelTrip(String email) throws InvalidInputException {
		String error = "";
		if (!(User.getWithEmail(email) instanceof Member)) {
			error = "The user with this email is not a member";
		}
		if (User.getWithEmail(email) == null) {
			error = "Member with email address " + email + " does not exist";
		}

		if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}

		Member member = (Member) User.getWithEmail(email);
		Assignment assignment = member.getAssignment();
		try {
			assignment.cancelAssignment();
		} catch (RuntimeException c) {
			throw new InvalidInputException(c.getMessage());
		}
		ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
	}

	public static void payTrip(String email, String authorizationCode) throws InvalidInputException {
		String error = "";
		if (!(User.getWithEmail(email) instanceof Member)) {
			error = "The user with this email is not a member";
		}
		if (User.getWithEmail(email) == null) {
			error = "Member with email address " + email + " does not exist";
		}

		if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}

		Member member = (Member) User.getWithEmail(email);
		Assignment assignment = member.getAssignment();
		try {
			assignment.payAssignment(authorizationCode);
		} catch (RuntimeException c) {
			throw new InvalidInputException(c.getMessage());
		}
		ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
	}

	public static void initiateAssignment() throws InvalidInputException {

		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

		for (Guide guide : climbSafe.getGuides()) {
			int currentWeek = 1;
			for (Member member : climbSafe.getMembers()) {

				if (!member.hasAssignment() && member.getNrWeeks() <= climbSafe.getNrWeeks()) {
					if (!member.isGuideRequired()) {
						climbSafe.addAssignment(1, member.getNrWeeks(), member);

					} else {

						if (!guide.hasAssignments()) {

							Assignment newAssignment = climbSafe.addAssignment(1, member.getNrWeeks(), member);
							newAssignment.setGuide(guide);
							currentWeek = newAssignment.getEndWeek();
						}

						else if (currentWeek + member.getNrWeeks() <= climbSafe.getNrWeeks()) {
							Assignment newAssignment = climbSafe.addAssignment(currentWeek + 1,
									currentWeek + member.getNrWeeks(), member);
							newAssignment.setGuide(guide);
							currentWeek = newAssignment.getEndWeek();
						}
					}
				}
			}
		}
		if (climbSafe.getAssignments().size() < climbSafe.getMembers().size()) {
			 throw new InvalidInputException("Assignments could not be completed for all members");
		}
		ClimbSafePersistence.save(climbSafe);
	}
}
