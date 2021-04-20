package coreclasses;


import java.time.LocalDate;
import utility.EMail;
import utility.ID;
import utility.Name;
import utility.PhoneNumber;


public class Manager extends Employee
{
    
    /**
     * Constructor for a manager object
     * 
     * @param aName
     * @param anId
     * @param aDateOfBirth
     * @param aStartOfEmployment
     * @param anEMailAddress
     * @param aMobileNumber
     * @param aStore 
     */
    public Manager(Name aName, ID anId, LocalDate aDateOfBirth, 
            LocalDate aStartOfEmployment, EMail anEMailAddress, 
            PhoneNumber aMobileNumber, Store aStore)
    {
        super(aName, anId, aDateOfBirth, aStartOfEmployment, 
                anEMailAddress, aMobileNumber, aStore);
    }
    
    
}
