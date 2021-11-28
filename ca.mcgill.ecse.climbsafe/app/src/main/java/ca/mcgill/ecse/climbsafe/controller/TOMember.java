/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.controller;

// line 25 "../../../../../ClimbSafeTransferObjects.ump"
public class TOMember
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOMember Attributes
  private String name;
  private String emergencyContact;
  private String email;
  private String password;
  private int nrWeeks;
  private boolean guideRequired;
  private boolean hotelRequired;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOMember(String aName, String aEmergencyContact, String aEmail, String aPassword, int aNrWeeks, boolean aGuideRequired, boolean aHotelRequired)
  {
    name = aName;
    emergencyContact = aEmergencyContact;
    email = aEmail;
    password = aPassword;
    nrWeeks = aNrWeeks;
    guideRequired = aGuideRequired;
    hotelRequired = aHotelRequired;
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

  public boolean setNrWeeks(int aNrWeeks)
  {
    boolean wasSet = false;
    nrWeeks = aNrWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuideRequired(boolean aGuideRequired)
  {
    boolean wasSet = false;
    guideRequired = aGuideRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setHotelRequired(boolean aHotelRequired)
  {
    boolean wasSet = false;
    hotelRequired = aHotelRequired;
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

  public int getNrWeeks()
  {
    return nrWeeks;
  }

  public boolean getGuideRequired()
  {
    return guideRequired;
  }

  public boolean getHotelRequired()
  {
    return hotelRequired;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isGuideRequired()
  {
    return guideRequired;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isHotelRequired()
  {
    return hotelRequired;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "nrWeeks" + ":" + getNrWeeks()+ "," +
            "guideRequired" + ":" + getGuideRequired()+ "," +
            "hotelRequired" + ":" + getHotelRequired()+ "]";
  }
}