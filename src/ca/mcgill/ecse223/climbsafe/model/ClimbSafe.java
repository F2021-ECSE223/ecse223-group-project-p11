/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;
import java.sql.Date;

// line 3 "../../../../../iteration1model.ump"
public class ClimbSafe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbSafe Attributes
  private int guideWeeklyCost;

  //ClimbSafe Associations
  private ClimbingSeason season;
  private List<Booking> bookings;
  private List<Assignment> assignments;
  private List<BookableEquipment> bookableEquipments;
  private List<Hotel> hotels;
  private List<User> users;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbSafe(int aGuideWeeklyCost, ClimbingSeason aSeason)
  {
    guideWeeklyCost = aGuideWeeklyCost;
    if (aSeason == null || aSeason.getClimbSafe() != null)
    {
      throw new RuntimeException("Unable to create ClimbSafe due to aSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    season = aSeason;
    bookings = new ArrayList<Booking>();
    assignments = new ArrayList<Assignment>();
    bookableEquipments = new ArrayList<BookableEquipment>();
    hotels = new ArrayList<Hotel>();
    users = new ArrayList<User>();
  }

  public ClimbSafe(int aGuideWeeklyCost, Date aStartDateForSeason, Date aEndDateForSeason)
  {
    guideWeeklyCost = aGuideWeeklyCost;
    season = new ClimbingSeason(aStartDateForSeason, aEndDateForSeason, this);
    bookings = new ArrayList<Booking>();
    assignments = new ArrayList<Assignment>();
    bookableEquipments = new ArrayList<BookableEquipment>();
    hotels = new ArrayList<Hotel>();
    users = new ArrayList<User>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGuideWeeklyCost(int aGuideWeeklyCost)
  {
    boolean wasSet = false;
    guideWeeklyCost = aGuideWeeklyCost;
    wasSet = true;
    return wasSet;
  }

  public int getGuideWeeklyCost()
  {
    return guideWeeklyCost;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getSeason()
  {
    return season;
  }
  /* Code from template association_GetMany */
  public Booking getBooking(int index)
  {
    Booking aBooking = bookings.get(index);
    return aBooking;
  }

  public List<Booking> getBookings()
  {
    List<Booking> newBookings = Collections.unmodifiableList(bookings);
    return newBookings;
  }

  public int numberOfBookings()
  {
    int number = bookings.size();
    return number;
  }

  public boolean hasBookings()
  {
    boolean has = bookings.size() > 0;
    return has;
  }

  public int indexOfBooking(Booking aBooking)
  {
    int index = bookings.indexOf(aBooking);
    return index;
  }
  /* Code from template association_GetMany */
  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  public List<Assignment> getAssignments()
  {
    List<Assignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(Assignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
  }
  /* Code from template association_GetMany */
  public BookableEquipment getBookableEquipment(int index)
  {
    BookableEquipment aBookableEquipment = bookableEquipments.get(index);
    return aBookableEquipment;
  }

  public List<BookableEquipment> getBookableEquipments()
  {
    List<BookableEquipment> newBookableEquipments = Collections.unmodifiableList(bookableEquipments);
    return newBookableEquipments;
  }

  public int numberOfBookableEquipments()
  {
    int number = bookableEquipments.size();
    return number;
  }

  public boolean hasBookableEquipments()
  {
    boolean has = bookableEquipments.size() > 0;
    return has;
  }

  public int indexOfBookableEquipment(BookableEquipment aBookableEquipment)
  {
    int index = bookableEquipments.indexOf(aBookableEquipment);
    return index;
  }
  /* Code from template association_GetMany */
  public Hotel getHotel(int index)
  {
    Hotel aHotel = hotels.get(index);
    return aHotel;
  }

  public List<Hotel> getHotels()
  {
    List<Hotel> newHotels = Collections.unmodifiableList(hotels);
    return newHotels;
  }

  public int numberOfHotels()
  {
    int number = hotels.size();
    return number;
  }

  public boolean hasHotels()
  {
    boolean has = hotels.size() > 0;
    return has;
  }

  public int indexOfHotel(Hotel aHotel)
  {
    int index = hotels.indexOf(aHotel);
    return index;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Booking addBooking(int aNumOfWeeks, boolean aWantsGuide, boolean aWantsHotel, int aTotalCost, Climber aClimber, Assignment aAssignment)
  {
    return new Booking(aNumOfWeeks, aWantsGuide, aWantsHotel, aTotalCost, this, aClimber, aAssignment);
  }

  public boolean addBooking(Booking aBooking)
  {
    boolean wasAdded = false;
    if (bookings.contains(aBooking)) { return false; }
    ClimbSafe existingClimbSafe = aBooking.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aBooking.setClimbSafe(this);
    }
    else
    {
      bookings.add(aBooking);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBooking(Booking aBooking)
  {
    boolean wasRemoved = false;
    //Unable to remove aBooking, as it must always have a climbSafe
    if (!this.equals(aBooking.getClimbSafe()))
    {
      bookings.remove(aBooking);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookingAt(Booking aBooking, int index)
  {  
    boolean wasAdded = false;
    if(addBooking(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookingAt(Booking aBooking, int index)
  {
    boolean wasAdded = false;
    if(bookings.contains(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookingAt(aBooking, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Assignment addAssignment(Date aReservationStartDate, Date aReservationEndDate, Booking aBooking, ClimbingWeek... allAssignedWeeks)
  {
    return new Assignment(aReservationStartDate, aReservationEndDate, aBooking, this, allAssignedWeeks);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    ClimbSafe existingClimbSafe = aAssignment.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aAssignment.setClimbSafe(this);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignment, as it must always have a climbSafe
    if (!this.equals(aAssignment.getClimbSafe()))
    {
      assignments.remove(aAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignmentAt(Assignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(Assignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookableEquipments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addBookableEquipment(BookableEquipment aBookableEquipment)
  {
    boolean wasAdded = false;
    if (bookableEquipments.contains(aBookableEquipment)) { return false; }
    ClimbSafe existingClimbSafe = aBookableEquipment.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aBookableEquipment.setClimbSafe(this);
    }
    else
    {
      bookableEquipments.add(aBookableEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBookableEquipment(BookableEquipment aBookableEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aBookableEquipment, as it must always have a climbSafe
    if (!this.equals(aBookableEquipment.getClimbSafe()))
    {
      bookableEquipments.remove(aBookableEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookableEquipmentAt(BookableEquipment aBookableEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addBookableEquipment(aBookableEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookableEquipments()) { index = numberOfBookableEquipments() - 1; }
      bookableEquipments.remove(aBookableEquipment);
      bookableEquipments.add(index, aBookableEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookableEquipmentAt(BookableEquipment aBookableEquipment, int index)
  {
    boolean wasAdded = false;
    if(bookableEquipments.contains(aBookableEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookableEquipments()) { index = numberOfBookableEquipments() - 1; }
      bookableEquipments.remove(aBookableEquipment);
      bookableEquipments.add(index, aBookableEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookableEquipmentAt(aBookableEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addHotel(String aName, String aAddress, int aRating)
  {
    return new Hotel(aName, aAddress, aRating, this);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    ClimbSafe existingClimbSafe = aHotel.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aHotel.setClimbSafe(this);
    }
    else
    {
      hotels.add(aHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotel(Hotel aHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotel, as it must always have a climbSafe
    if (!this.equals(aHotel.getClimbSafe()))
    {
      hotels.remove(aHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAt(Hotel aHotel, int index)
  {  
    boolean wasAdded = false;
    if(addHotel(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAt(Hotel aHotel, int index)
  {
    boolean wasAdded = false;
    if(hotels.contains(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAt(aHotel, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    ClimbSafe existingClimbSafe = aUser.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aUser.setClimbSafe(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a climbSafe
    if (!this.equals(aUser.getClimbSafe()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ClimbingSeason existingSeason = season;
    season = null;
    if (existingSeason != null)
    {
      existingSeason.delete();
    }
    while (bookings.size() > 0)
    {
      Booking aBooking = bookings.get(bookings.size() - 1);
      aBooking.delete();
      bookings.remove(aBooking);
    }
    
    while (assignments.size() > 0)
    {
      Assignment aAssignment = assignments.get(assignments.size() - 1);
      aAssignment.delete();
      assignments.remove(aAssignment);
    }
    
    while (bookableEquipments.size() > 0)
    {
      BookableEquipment aBookableEquipment = bookableEquipments.get(bookableEquipments.size() - 1);
      aBookableEquipment.delete();
      bookableEquipments.remove(aBookableEquipment);
    }
    
    while (hotels.size() > 0)
    {
      Hotel aHotel = hotels.get(hotels.size() - 1);
      aHotel.delete();
      hotels.remove(aHotel);
    }
    
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "guideWeeklyCost" + ":" + getGuideWeeklyCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "season = "+(getSeason()!=null?Integer.toHexString(System.identityHashCode(getSeason())):"null");
  }
}