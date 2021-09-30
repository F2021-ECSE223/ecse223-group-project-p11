/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 25 "../../../../../iteration1model.ump"
public abstract class Member extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private String name;
  private int emergencyPhoneNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aEmail, String aPassword, ClimbSafe aClimbSafe, String aName, int aEmergencyPhoneNumber)
  {
    super(aEmail, aPassword, aClimbSafe);
    name = aName;
    emergencyPhoneNumber = aEmergencyPhoneNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyPhoneNumber(int aEmergencyPhoneNumber)
  {
    boolean wasSet = false;
    emergencyPhoneNumber = aEmergencyPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getEmergencyPhoneNumber()
  {
    return emergencyPhoneNumber;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "emergencyPhoneNumber" + ":" + getEmergencyPhoneNumber()+ "]";
  }
}