/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.btms.model;
import java.util.*;

// line 19 "../../../../../model.ump"
public class Administrator extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Administrator Attributes
  private String email;
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator(String aEmail, String aPassword, ClimbSafe aClimbSafe)
  {
    super(aEmail, aPassword, aClimbSafe);
    email = "admin@nmc.nt";
    password = "admin";
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  /**
   * should be a singleton, only one instance of admin;
   */
  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}