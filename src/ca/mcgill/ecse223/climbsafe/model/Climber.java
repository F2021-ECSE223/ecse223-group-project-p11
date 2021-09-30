/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 36 "../../../../../iteration1model.ump"
public class Climber extends Member
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Climber Associations
  private Booking booking;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Climber(String aEmail, String aPassword, ClimbSafe aClimbSafe, String aName, int aEmergencyPhoneNumber, Booking aBooking)
  {
    super(aEmail, aPassword, aClimbSafe, aName, aEmergencyPhoneNumber);
    if (aBooking == null || aBooking.getClimber() != null)
    {
      throw new RuntimeException("Unable to create Climber due to aBooking. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    booking = aBooking;
  }

  public Climber(String aEmail, String aPassword, ClimbSafe aClimbSafe, String aName, int aEmergencyPhoneNumber, int aNumOfWeeksForBooking, boolean aWantsGuideForBooking, boolean aWantsHotelForBooking, int aTotalCostForBooking, ClimbSafe aClimbSafeForBooking, Assignment aAssignmentForBooking)
  {
    super(aEmail, aPassword, aClimbSafe, aName, aEmergencyPhoneNumber);
    booking = new Booking(aNumOfWeeksForBooking, aWantsGuideForBooking, aWantsHotelForBooking, aTotalCostForBooking, aClimbSafeForBooking, this, aAssignmentForBooking);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Booking getBooking()
  {
    return booking;
  }

  public void delete()
  {
    Booking existingBooking = booking;
    booking = null;
    if (existingBooking != null)
    {
      existingBooking.delete();
    }
    super.delete();
  }

}