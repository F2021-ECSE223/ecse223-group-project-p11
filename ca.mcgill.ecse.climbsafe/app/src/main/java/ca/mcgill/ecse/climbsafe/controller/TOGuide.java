/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 19 "../../../../../ClimbSafeTransferObjects.ump"
public class TOGuide
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGuide Attributes
  private String name;
  private String emergencyContact;
  private String email;
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGuide(String aName, String aEmergencyContact, String aEmail, String aPassword)
  {
    name = aName;
    emergencyContact = aEmergencyContact;
    email = aEmail;
    password = aPassword;
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

  public boolean setEmergencyContact(String aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getEmergencyContact()
  {
    return emergencyContact;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}