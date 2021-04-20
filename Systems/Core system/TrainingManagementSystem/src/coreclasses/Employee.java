package coreclasses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utility.*;

public class Employee {
    private Name name;
    private ID id;
    private LocalDate dateOfBirth;
    private LocalDate startOfEmployment;
    private EMail eMailAddress;
    private PhoneNumber mobileNumber;
    
// Links
    private Store store;
    private Set<TrainingEvent> trainingEvents;


    /**
     * Constructor for Regular retail employees
     * 
     * @param aName
     * @param anId
     * @param aDateOfBirth
     * @param aStartOfEmployment
     * @param anEMailAddress
     * @param aMobileNumber 
     * @param aStore 
     */
    public Employee(Name aName, ID anId, LocalDate aDateOfBirth, LocalDate aStartOfEmployment, 
            EMail anEMailAddress, PhoneNumber aMobileNumber, Store aStore)
    {
        name = aName;
        id = anId;
        dateOfBirth = validate(aDateOfBirth);
        startOfEmployment = validate(aStartOfEmployment);
        eMailAddress = anEMailAddress;
        mobileNumber = aMobileNumber;
        store = aStore;
        trainingEvents = new HashSet<>();
    }
    
    /**
     * Adds a training event to employee
     * 
     * @param aTrainingEvent 
     */
    public void addTrainingEvent(TrainingEvent aTrainingEvent)
    {
        trainingEvents.add(aTrainingEvent);
    }
    
    /**
     * Returns all the training events linked to employee
     * 
     * @return referenced training event objects.
     */
    public Set<TrainingEvent> getTrainingEvents()
    {
        return trainingEvents;
    }
    
    /**
     * Removes a training event from employee
     * 
     * @param aTrainingEvent 
     */
    public void removeTrainingEvent(TrainingEvent aTrainingEvent)
    {
        trainingEvents.remove(aTrainingEvent);
    }
    
    /**
     * Removes the reference to the store object.
     */
    public void removeStore()
    {
        store = null;

    }

    /**
     * Returns the name object representing the name of the employee.
     * 
     * @return name of the employee
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Returns the unique employee Id as an ID object.
     * 
     * @return id a unique ID
     */
    public ID getId()
    {
        return id;
    }
    
    /**
     * Returns the unique employee Id as a String.
     * 
     * @return id a Unique ID
     */
    public String getIdAsString()
    {
        return id.getID();
    }

    /**
     * Employee's date of birth
     * 
     * @return the date of birth of the employee
     */
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * The date the employee started their employment.
     * 
     * @return start of employment date of the employee
     */
    public LocalDate getStartOfEmployment()
    {
        return startOfEmployment;
    }

    /**
     * Employee email address
     * 
     * @return email address
     */
    public String geteMailAddress()
    {
        return eMailAddress.geteMail();
    }

    /**
     * Employee mobile phone number
     * 
     * @return mobile phone number
     */
    public PhoneNumber getMobileNumber()
    {
        return mobileNumber;
    }

    /**
     * Sets the name of the employee
     * 
     * @param aName 
     */
    public void setName(Name aName)
    {
        this.name = aName;
    }

    /**
     * Sets the date of birth of the employee
     * 
     * @param aDateOfBirth 
     */
    public void setDateOfBirth(LocalDate aDateOfBirth)
    {
        dateOfBirth = validate(aDateOfBirth);
    }

    /**
     * Sets the employment start date
     * 
     * @param aStartOfEmployment 
     */
    public void setStartOfEmployment(LocalDate aStartOfEmployment)
    {
        startOfEmployment = validate(aStartOfEmployment);
    }

    /**
     * Sets the email address of the employee
     * 
     * @param anEMailAddress 
     */
    public void seteMailAddress(EMail anEMailAddress)
    {
        eMailAddress = anEMailAddress;
    }

    /**
     * Sets the employees mobile phone number
     * 
     * @param aMobileNumber 
     */
    public void setMobileNumber(PhoneNumber aMobileNumber)
    {
        mobileNumber = aMobileNumber;
    }

    /**
     * A method to check for no input of name fields
     * 
     * @param aString 
     */
    private LocalDate validate(LocalDate aDate)
    {
        if(aDate == null)
        {
            throw new IllegalArgumentException("Please enter a date");
        }
        return aDate;
    }
    
    
    /**
     * List of employee information
     * 
     * @return 
     */
    public List<String> employeeInformation()
    {
        List<String> info = new ArrayList<>();
        info.add(name.toString());
        info.add(mobileNumber.toString());
        info.add(eMailAddress.toString());
        info.add("Start date = " + startOfEmployment.toString());
        info.add("D.O.B = " + dateOfBirth.toString());
        info.add(trainingEvents.toString());

        return info;
    }
    /**
     * Returns a string representation of the employee name.
     * 
     * @return employee name as a string
     */    
    @Override
    public String toString()
    {
        return name.toString();
    }    
 
}
