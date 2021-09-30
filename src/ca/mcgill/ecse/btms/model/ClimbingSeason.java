/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.sql.Date;
import java.util.*;

// line 59 "../../../../../model.ump"
public class ClimbingSeason
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingSeason Attributes
  private Date startDate;
  private Date endDate;

  //ClimbingSeason Associations
  private List<ClimbingWeek> weeks;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingSeason(Date aStartDate, Date aEndDate, ClimbSafe aClimbSafe)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    weeks = new ArrayList<ClimbingWeek>();
    if (aClimbSafe == null || aClimbSafe.getSeason() != null)
    {
      throw new RuntimeException("Unable to create ClimbingSeason due to aClimbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbSafe = aClimbSafe;
  }

  public ClimbingSeason(Date aStartDate, Date aEndDate, int aGuideWeeklyCostForClimbSafe)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    weeks = new ArrayList<ClimbingWeek>();
    climbSafe = new ClimbSafe(aGuideWeeklyCostForClimbSafe, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getWeek(int index)
  {
    ClimbingWeek aWeek = weeks.get(index);
    return aWeek;
  }

  public List<ClimbingWeek> getWeeks()
  {
    List<ClimbingWeek> newWeeks = Collections.unmodifiableList(weeks);
    return newWeeks;
  }

  public int numberOfWeeks()
  {
    int number = weeks.size();
    return number;
  }

  public boolean hasWeeks()
  {
    boolean has = weeks.size() > 0;
    return has;
  }

  public int indexOfWeek(ClimbingWeek aWeek)
  {
    int index = weeks.indexOf(aWeek);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfWeeksValid()
  {
    boolean isValid = numberOfWeeks() >= minimumNumberOfWeeks();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeeks()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public ClimbingWeek addWeek()
  {
    ClimbingWeek aNewWeek = new ClimbingWeek(this);
    return aNewWeek;
  }

  public boolean addWeek(ClimbingWeek aWeek)
  {
    boolean wasAdded = false;
    if (weeks.contains(aWeek)) { return false; }
    ClimbingSeason existingSeason = aWeek.getSeason();
    boolean isNewSeason = existingSeason != null && !this.equals(existingSeason);

    if (isNewSeason && existingSeason.numberOfWeeks() <= minimumNumberOfWeeks())
    {
      return wasAdded;
    }
    if (isNewSeason)
    {
      aWeek.setSeason(this);
    }
    else
    {
      weeks.add(aWeek);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWeek(ClimbingWeek aWeek)
  {
    boolean wasRemoved = false;
    //Unable to remove aWeek, as it must always have a season
    if (this.equals(aWeek.getSeason()))
    {
      return wasRemoved;
    }

    //season already at minimum (1)
    if (numberOfWeeks() <= minimumNumberOfWeeks())
    {
      return wasRemoved;
    }

    weeks.remove(aWeek);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWeekAt(ClimbingWeek aWeek, int index)
  {  
    boolean wasAdded = false;
    if(addWeek(aWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeks()) { index = numberOfWeeks() - 1; }
      weeks.remove(aWeek);
      weeks.add(index, aWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWeekAt(ClimbingWeek aWeek, int index)
  {
    boolean wasAdded = false;
    if(weeks.contains(aWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeks()) { index = numberOfWeeks() - 1; }
      weeks.remove(aWeek);
      weeks.add(index, aWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWeekAt(aWeek, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (weeks.size() > 0)
    {
      ClimbingWeek aWeek = weeks.get(weeks.size() - 1);
      aWeek.delete();
      weeks.remove(aWeek);
    }
    
    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = null;
    if (existingClimbSafe != null)
    {
      existingClimbSafe.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }
}