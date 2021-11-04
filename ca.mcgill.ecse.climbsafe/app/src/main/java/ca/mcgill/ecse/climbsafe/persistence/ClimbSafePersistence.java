package ca.mcgill.ecse.climbsafe.persistence;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class ClimbSafePersistence {

  private static String filename = "data.climbsafe";

  public static void setFilename(String filename) {
    ClimbSafePersistence.filename = filename;
  }

  public static void save() {
    ClimbSafePersistence.setFilename(filename);
    save(ClimbSafeApplication.getClimbSafe());
  }

  public static void save(ClimbSafe climbsafe) {
    PersistenceObjectStream.setFilename(filename);
    PersistenceObjectStream.serialize(climbsafe);
  }

  public static ClimbSafe load() {
    PersistenceObjectStream.setFilename(filename);
    var climbsafe = (ClimbSafe) PersistenceObjectStream.deserialize();
    // model cannot be loaded - create empty BTMS
    if (climbsafe == null) {
      climbsafe = new ClimbSafe(); 
    } else {
      climbsafe.reinitialize();
    }
    return climbsafe;
  }

}