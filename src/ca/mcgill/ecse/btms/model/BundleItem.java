/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.btms.model;

// line 81 "../../../../../model.ump"
public class BundleItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BundleItem Associations
  private Equipment equipment;
  private EquipmentBundle bundle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BundleItem(Equipment aEquipment, EquipmentBundle aBundle)
  {
    boolean didAddEquipment = setEquipment(aEquipment);
    if (!didAddEquipment)
    {
      throw new RuntimeException("Unable to create bundleItem due to equipment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBundle = setBundle(aBundle);
    if (!didAddBundle)
    {
      throw new RuntimeException("Unable to create item due to bundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Equipment getEquipment()
  {
    return equipment;
  }
  /* Code from template association_GetOne */
  public EquipmentBundle getBundle()
  {
    return bundle;
  }
  /* Code from template association_SetOneToMany */
  public boolean setEquipment(Equipment aEquipment)
  {
    boolean wasSet = false;
    if (aEquipment == null)
    {
      return wasSet;
    }

    Equipment existingEquipment = equipment;
    equipment = aEquipment;
    if (existingEquipment != null && !existingEquipment.equals(aEquipment))
    {
      existingEquipment.removeBundleItem(this);
    }
    equipment.addBundleItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setBundle(EquipmentBundle aBundle)
  {
    boolean wasSet = false;
    //Must provide bundle to item
    if (aBundle == null)
    {
      return wasSet;
    }

    if (bundle != null && bundle.numberOfItems() <= EquipmentBundle.minimumNumberOfItems())
    {
      return wasSet;
    }

    EquipmentBundle existingBundle = bundle;
    bundle = aBundle;
    if (existingBundle != null && !existingBundle.equals(aBundle))
    {
      boolean didRemove = existingBundle.removeItem(this);
      if (!didRemove)
      {
        bundle = existingBundle;
        return wasSet;
      }
    }
    bundle.addItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Equipment placeholderEquipment = equipment;
    this.equipment = null;
    if(placeholderEquipment != null)
    {
      placeholderEquipment.removeBundleItem(this);
    }
    EquipmentBundle placeholderBundle = bundle;
    this.bundle = null;
    if(placeholderBundle != null)
    {
      placeholderBundle.removeItem(this);
    }
  }

}