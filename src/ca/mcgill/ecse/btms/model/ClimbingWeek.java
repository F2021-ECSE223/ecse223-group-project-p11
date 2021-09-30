/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;

// line 65 "../../../../../model.ump"
public class ClimbingWeek
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingWeek Associations
  private List<Assignment> assignments;
  private ClimbingSeason season;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingWeek(ClimbingSeason aSeason)
  {
    assignments = new ArrayList<Assignment>();
    boolean didAddSeason = setSeason(aSeason);
    if (!didAddSeason)
    {
      throw new RuntimeException("Unable to create week due to season. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_GetOne */
  public ClimbingSeason getSeason()
  {
    return season;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    assignments.add(aAssignment);
    if (aAssignment.indexOfAssignedWeek(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssignment.addAssignedWeek(this);
      if (!wasAdded)
      {
        assignments.remove(aAssignment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    if (!assignments.contains(aAssignment))
    {
      return wasRemoved;
    }

    int oldIndex = assignments.indexOf(aAssignment);
    assignments.remove(oldIndex);
    if (aAssignment.indexOfAssignedWeek(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssignment.removeAssignedWeek(this);
      if (!wasRemoved)
      {
        assignments.add(oldIndex,aAssignment);
      }
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setSeason(ClimbingSeason aSeason)
  {
    boolean wasSet = false;
    //Must provide season to week
    if (aSeason == null)
    {
      return wasSet;
    }

    if (season != null && season.numberOfWeeks() <= ClimbingSeason.minimumNumberOfWeeks())
    {
      return wasSet;
    }

    ClimbingSeason existingSeason = season;
    season = aSeason;
    if (existingSeason != null && !existingSeason.equals(aSeason))
    {
      boolean didRemove = existingSeason.removeWeek(this);
      if (!didRemove)
      {
        season = existingSeason;
        return wasSet;
      }
    }
    season.addWeek(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Assignment> copyOfAssignments = new ArrayList<Assignment>(assignments);
    assignments.clear();
    for(Assignment aAssignment : copyOfAssignments)
    {
      if (aAssignment.numberOfAssignedWeeks() <= Assignment.minimumNumberOfAssignedWeeks())
      {
        aAssignment.delete();
      }
      else
      {
        aAssignment.removeAssignedWeek(this);
      }
    }
    ClimbingSeason placeholderSeason = season;
    this.season = null;
    if(placeholderSeason != null)
    {
      placeholderSeason.removeWeek(this);
    }
  }

}