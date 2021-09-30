/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.sql.Date;
import java.util.*;

// line 52 "../../../../../model.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private Date reservationEndDate;

  //Assignment Associations
  private MountainGuide assignedGuide;
  private List<ClimbingWeek> assignedWeeks;
  private Booking booking;
  private ClimbSafe climbSafe;
  private Hotel assignedHotel;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(Date aReservationEndDate, Booking aBooking, ClimbSafe aClimbSafe, ClimbingWeek... allAssignedWeeks)
  {
    reservationEndDate = aReservationEndDate;
    assignedWeeks = new ArrayList<ClimbingWeek>();
    boolean didAddAssignedWeeks = setAssignedWeeks(allAssignedWeeks);
    if (!didAddAssignedWeeks)
    {
      throw new RuntimeException("Unable to create Assignment, must have at least 1 assignedWeeks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aBooking == null || aBooking.getAssignment() != null)
    {
      throw new RuntimeException("Unable to create Assignment due to aBooking. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    booking = aBooking;
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Assignment(Date aReservationEndDate, int aNumOfWeeksForBooking, boolean aWantsGuideForBooking, boolean aWantsHotelForBooking, int aTotalCostForBooking, ClimbSafe aClimbSafeForBooking, Climber aClimberForBooking, ClimbSafe aClimbSafe, ClimbingWeek... allAssignedWeeks)
  {
    reservationEndDate = aReservationEndDate;
    assignedWeeks = new ArrayList<ClimbingWeek>();
    boolean didAddAssignedWeeks = setAssignedWeeks(allAssignedWeeks);
    if (!didAddAssignedWeeks)
    {
      throw new RuntimeException("Unable to create Assignment, must have at least 1 assignedWeeks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    booking = new Booking(aNumOfWeeksForBooking, aWantsGuideForBooking, aWantsHotelForBooking, aTotalCostForBooking, aClimbSafeForBooking, aClimberForBooking, this);
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReservationEndDate(Date aReservationEndDate)
  {
    boolean wasSet = false;
    reservationEndDate = aReservationEndDate;
    wasSet = true;
    return wasSet;
  }

  public Date getReservationEndDate()
  {
    return reservationEndDate;
  }
  /* Code from template association_GetOne */
  public MountainGuide getAssignedGuide()
  {
    return assignedGuide;
  }

  public boolean hasAssignedGuide()
  {
    boolean has = assignedGuide != null;
    return has;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getAssignedWeek(int index)
  {
    ClimbingWeek aAssignedWeek = assignedWeeks.get(index);
    return aAssignedWeek;
  }

  public List<ClimbingWeek> getAssignedWeeks()
  {
    List<ClimbingWeek> newAssignedWeeks = Collections.unmodifiableList(assignedWeeks);
    return newAssignedWeeks;
  }

  public int numberOfAssignedWeeks()
  {
    int number = assignedWeeks.size();
    return number;
  }

  public boolean hasAssignedWeeks()
  {
    boolean has = assignedWeeks.size() > 0;
    return has;
  }

  public int indexOfAssignedWeek(ClimbingWeek aAssignedWeek)
  {
    int index = assignedWeeks.indexOf(aAssignedWeek);
    return index;
  }
  /* Code from template association_GetOne */
  public Booking getBooking()
  {
    return booking;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public Hotel getAssignedHotel()
  {
    return assignedHotel;
  }

  public boolean hasAssignedHotel()
  {
    boolean has = assignedHotel != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setAssignedGuide(MountainGuide aAssignedGuide)
  {
    boolean wasSet = false;
    MountainGuide existingAssignedGuide = assignedGuide;
    assignedGuide = aAssignedGuide;
    if (existingAssignedGuide != null && !existingAssignedGuide.equals(aAssignedGuide))
    {
      existingAssignedGuide.removeAssignment(this);
    }
    if (aAssignedGuide != null)
    {
      aAssignedGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfAssignedWeeksValid()
  {
    boolean isValid = numberOfAssignedWeeks() >= minimumNumberOfAssignedWeeks();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignedWeeks()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAssignedWeek(ClimbingWeek aAssignedWeek)
  {
    boolean wasAdded = false;
    if (assignedWeeks.contains(aAssignedWeek)) { return false; }
    assignedWeeks.add(aAssignedWeek);
    if (aAssignedWeek.indexOfAssignment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssignedWeek.addAssignment(this);
      if (!wasAdded)
      {
        assignedWeeks.remove(aAssignedWeek);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeAssignedWeek(ClimbingWeek aAssignedWeek)
  {
    boolean wasRemoved = false;
    if (!assignedWeeks.contains(aAssignedWeek))
    {
      return wasRemoved;
    }

    if (numberOfAssignedWeeks() <= minimumNumberOfAssignedWeeks())
    {
      return wasRemoved;
    }

    int oldIndex = assignedWeeks.indexOf(aAssignedWeek);
    assignedWeeks.remove(oldIndex);
    if (aAssignedWeek.indexOfAssignment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssignedWeek.removeAssignment(this);
      if (!wasRemoved)
      {
        assignedWeeks.add(oldIndex,aAssignedWeek);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setAssignedWeeks(ClimbingWeek... newAssignedWeeks)
  {
    boolean wasSet = false;
    ArrayList<ClimbingWeek> verifiedAssignedWeeks = new ArrayList<ClimbingWeek>();
    for (ClimbingWeek aAssignedWeek : newAssignedWeeks)
    {
      if (verifiedAssignedWeeks.contains(aAssignedWeek))
      {
        continue;
      }
      verifiedAssignedWeeks.add(aAssignedWeek);
    }

    if (verifiedAssignedWeeks.size() != newAssignedWeeks.length || verifiedAssignedWeeks.size() < minimumNumberOfAssignedWeeks())
    {
      return wasSet;
    }

    ArrayList<ClimbingWeek> oldAssignedWeeks = new ArrayList<ClimbingWeek>(assignedWeeks);
    assignedWeeks.clear();
    for (ClimbingWeek aNewAssignedWeek : verifiedAssignedWeeks)
    {
      assignedWeeks.add(aNewAssignedWeek);
      if (oldAssignedWeeks.contains(aNewAssignedWeek))
      {
        oldAssignedWeeks.remove(aNewAssignedWeek);
      }
      else
      {
        aNewAssignedWeek.addAssignment(this);
      }
    }

    for (ClimbingWeek anOldAssignedWeek : oldAssignedWeeks)
    {
      anOldAssignedWeek.removeAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignedWeekAt(ClimbingWeek aAssignedWeek, int index)
  {  
    boolean wasAdded = false;
    if(addAssignedWeek(aAssignedWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedWeeks()) { index = numberOfAssignedWeeks() - 1; }
      assignedWeeks.remove(aAssignedWeek);
      assignedWeeks.add(index, aAssignedWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignedWeekAt(ClimbingWeek aAssignedWeek, int index)
  {
    boolean wasAdded = false;
    if(assignedWeeks.contains(aAssignedWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedWeeks()) { index = numberOfAssignedWeeks() - 1; }
      assignedWeeks.remove(aAssignedWeek);
      assignedWeeks.add(index, aAssignedWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignedWeekAt(aAssignedWeek, index);
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
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setAssignedHotel(Hotel aAssignedHotel)
  {
    boolean wasSet = false;
    Hotel existingAssignedHotel = assignedHotel;
    assignedHotel = aAssignedHotel;
    if (existingAssignedHotel != null && !existingAssignedHotel.equals(aAssignedHotel))
    {
      existingAssignedHotel.removeAssignment(this);
    }
    if (aAssignedHotel != null)
    {
      aAssignedHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (assignedGuide != null)
    {
      MountainGuide placeholderAssignedGuide = assignedGuide;
      this.assignedGuide = null;
      placeholderAssignedGuide.removeAssignment(this);
    }
    ArrayList<ClimbingWeek> copyOfAssignedWeeks = new ArrayList<ClimbingWeek>(assignedWeeks);
    assignedWeeks.clear();
    for(ClimbingWeek aAssignedWeek : copyOfAssignedWeeks)
    {
      aAssignedWeek.removeAssignment(this);
    }
    Booking existingBooking = booking;
    booking = null;
    if (existingBooking != null)
    {
      existingBooking.delete();
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
    if (assignedHotel != null)
    {
      Hotel placeholderAssignedHotel = assignedHotel;
      this.assignedHotel = null;
      placeholderAssignedHotel.removeAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservationEndDate" + "=" + (getReservationEndDate() != null ? !getReservationEndDate().equals(this)  ? getReservationEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignedGuide = "+(getAssignedGuide()!=null?Integer.toHexString(System.identityHashCode(getAssignedGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "booking = "+(getBooking()!=null?Integer.toHexString(System.identityHashCode(getBooking())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignedHotel = "+(getAssignedHotel()!=null?Integer.toHexString(System.identityHashCode(getAssignedHotel())):"null");
  }
}