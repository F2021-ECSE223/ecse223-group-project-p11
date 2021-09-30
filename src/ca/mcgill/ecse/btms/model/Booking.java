/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;
import java.sql.Date;

// line 42 "../../../../../model.ump"
public class Booking
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Booking Attributes
  private int numOfWeeks;
  private boolean wantsGuide;
  private boolean wantsHotel;
  private int totalCost;

  //Booking Associations
  private List<BookableEquipment> bookedEquipments;
  private ClimbSafe climbSafe;
  private Climber climber;
  private Assignment assignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Booking(int aNumOfWeeks, boolean aWantsGuide, boolean aWantsHotel, int aTotalCost, ClimbSafe aClimbSafe, Climber aClimber, Assignment aAssignment)
  {
    numOfWeeks = aNumOfWeeks;
    wantsGuide = aWantsGuide;
    wantsHotel = aWantsHotel;
    totalCost = aTotalCost;
    bookedEquipments = new ArrayList<BookableEquipment>();
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create booking due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aClimber == null || aClimber.getBooking() != null)
    {
      throw new RuntimeException("Unable to create Booking due to aClimber. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climber = aClimber;
    if (aAssignment == null || aAssignment.getBooking() != null)
    {
      throw new RuntimeException("Unable to create Booking due to aAssignment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assignment = aAssignment;
  }

  public Booking(int aNumOfWeeks, boolean aWantsGuide, boolean aWantsHotel, int aTotalCost, ClimbSafe aClimbSafe, String aEmailForClimber, String aPasswordForClimber, ClimbSafe aClimbSafeForClimber, String aNameForClimber, int aEmergencyPhoneNumberForClimber, Date aReservationEndDateForAssignment, ClimbSafe aClimbSafeForAssignment, ClimbingWeek... allAssignedWeeksForAssignment)
  {
    numOfWeeks = aNumOfWeeks;
    wantsGuide = aWantsGuide;
    wantsHotel = aWantsHotel;
    totalCost = aTotalCost;
    bookedEquipments = new ArrayList<BookableEquipment>();
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create booking due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climber = new Climber(aEmailForClimber, aPasswordForClimber, aClimbSafeForClimber, aNameForClimber, aEmergencyPhoneNumberForClimber, this);
    assignment = new Assignment(aReservationEndDateForAssignment, this, aClimbSafeForAssignment, allAssignedWeeksForAssignment);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumOfWeeks(int aNumOfWeeks)
  {
    boolean wasSet = false;
    numOfWeeks = aNumOfWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setWantsGuide(boolean aWantsGuide)
  {
    boolean wasSet = false;
    wantsGuide = aWantsGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean setWantsHotel(boolean aWantsHotel)
  {
    boolean wasSet = false;
    wantsHotel = aWantsHotel;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalCost(int aTotalCost)
  {
    boolean wasSet = false;
    totalCost = aTotalCost;
    wasSet = true;
    return wasSet;
  }

  public int getNumOfWeeks()
  {
    return numOfWeeks;
  }

  public boolean getWantsGuide()
  {
    return wantsGuide;
  }

  public boolean getWantsHotel()
  {
    return wantsHotel;
  }

  /**
   * details of computation are written in the project report
   */
  public int getTotalCost()
  {
    return totalCost;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isWantsGuide()
  {
    return wantsGuide;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isWantsHotel()
  {
    return wantsHotel;
  }
  /* Code from template association_GetMany */
  public BookableEquipment getBookedEquipment(int index)
  {
    BookableEquipment aBookedEquipment = bookedEquipments.get(index);
    return aBookedEquipment;
  }

  public List<BookableEquipment> getBookedEquipments()
  {
    List<BookableEquipment> newBookedEquipments = Collections.unmodifiableList(bookedEquipments);
    return newBookedEquipments;
  }

  public int numberOfBookedEquipments()
  {
    int number = bookedEquipments.size();
    return number;
  }

  public boolean hasBookedEquipments()
  {
    boolean has = bookedEquipments.size() > 0;
    return has;
  }

  public int indexOfBookedEquipment(BookableEquipment aBookedEquipment)
  {
    int index = bookedEquipments.indexOf(aBookedEquipment);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public Climber getClimber()
  {
    return climber;
  }
  /* Code from template association_GetOne */
  public Assignment getAssignment()
  {
    return assignment;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookedEquipments()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBookedEquipment(BookableEquipment aBookedEquipment)
  {
    boolean wasAdded = false;
    if (bookedEquipments.contains(aBookedEquipment)) { return false; }
    bookedEquipments.add(aBookedEquipment);
    if (aBookedEquipment.indexOfBooking(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBookedEquipment.addBooking(this);
      if (!wasAdded)
      {
        bookedEquipments.remove(aBookedEquipment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBookedEquipment(BookableEquipment aBookedEquipment)
  {
    boolean wasRemoved = false;
    if (!bookedEquipments.contains(aBookedEquipment))
    {
      return wasRemoved;
    }

    int oldIndex = bookedEquipments.indexOf(aBookedEquipment);
    bookedEquipments.remove(oldIndex);
    if (aBookedEquipment.indexOfBooking(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBookedEquipment.removeBooking(this);
      if (!wasRemoved)
      {
        bookedEquipments.add(oldIndex,aBookedEquipment);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookedEquipmentAt(BookableEquipment aBookedEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addBookedEquipment(aBookedEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookedEquipments()) { index = numberOfBookedEquipments() - 1; }
      bookedEquipments.remove(aBookedEquipment);
      bookedEquipments.add(index, aBookedEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookedEquipmentAt(BookableEquipment aBookedEquipment, int index)
  {
    boolean wasAdded = false;
    if(bookedEquipments.contains(aBookedEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookedEquipments()) { index = numberOfBookedEquipments() - 1; }
      bookedEquipments.remove(aBookedEquipment);
      bookedEquipments.add(index, aBookedEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookedEquipmentAt(aBookedEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeBooking(this);
    }
    climbSafe.addBooking(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<BookableEquipment> copyOfBookedEquipments = new ArrayList<BookableEquipment>(bookedEquipments);
    bookedEquipments.clear();
    for(BookableEquipment aBookedEquipment : copyOfBookedEquipments)
    {
      aBookedEquipment.removeBooking(this);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeBooking(this);
    }
    Climber existingClimber = climber;
    climber = null;
    if (existingClimber != null)
    {
      existingClimber.delete();
    }
    Assignment existingAssignment = assignment;
    assignment = null;
    if (existingAssignment != null)
    {
      existingAssignment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numOfWeeks" + ":" + getNumOfWeeks()+ "," +
            "wantsGuide" + ":" + getWantsGuide()+ "," +
            "wantsHotel" + ":" + getWantsHotel()+ "," +
            "totalCost" + ":" + getTotalCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climber = "+(getClimber()!=null?Integer.toHexString(System.identityHashCode(getClimber())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null");
  }
}