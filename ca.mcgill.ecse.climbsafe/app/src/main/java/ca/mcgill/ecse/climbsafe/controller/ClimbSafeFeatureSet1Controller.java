package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.NamedUser;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet1Controller {
  /** This controller method validates the given input to set the climbing season start date, number of weeks in the
   * climbing season and the price of guide per week.
   * @author Lee Brickman
   * @param startDate
   * @param nrWeeks
   * @param priceOfGuidePerWeek
   * @throws InvalidInputException
   */

	public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek) throws InvalidInputException {
		String error = "";
		
		if (nrWeeks < 0 || nrWeeks == 0) {
			error = "The number of climbing weeks must be greater than or equal to zero";
		}
		if (priceOfGuidePerWeek < 0 || priceOfGuidePerWeek == 0) {
			error = "The price of guide per week must be greater than or equal to zero";
		}
		if (!error.isEmpty()) {
			throw new InvalidInputException(error);
		}
// get the instance of climbSafe, undergo operations listed above and catch any potential runtime errors.
		ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		try {
			climbSafe.setStartDate(startDate);
			climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
			climbSafe.setNrWeeks(nrWeeks);
			ClimbSafePersistence.save(climbSafe);

		} catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}

	}

/**identify the user in the system with the provided string email and delete said user if able
 * @author Lee Brickman
 * @param email
 */

	public static void deleteMember(String email) {
		
		if (!NamedUser.hasWithEmail(email) || !(NamedUser.getWithEmail(email) instanceof Member)) {
;
		} else {

				Member memberToDelete = (Member) Member.getWithEmail(email);
				memberToDelete.delete();
				ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
	
		}

	}
/**identify the guide in the system with the provided string email and delete said guide if able
 * @author Lee Brickman
 * @param email
 */

	public static void deleteGuide(String email) {
		
		if (!NamedUser.hasWithEmail(email) || !(NamedUser.getWithEmail(email) instanceof Guide)) {

		} else {
	
				Guide memberToDelete = (Guide) Guide.getWithEmail(email);
				memberToDelete.delete();
				ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
		}
	}

	// this method needs to be implemented only by teams with seven team members
	public static void deleteHotel(String name) {
	}

}
