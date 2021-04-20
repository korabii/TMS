package coreclasses;

import java.util.HashMap;
import java.util.Map;
import utility.Address;
import utility.ID;

public abstract class BusinessLocation
{
    private ID id;
    private Address location;
    private Map<ID, Employee> employees;

    /**
     * Initialises a new BusinessLocation object with the given values.
     * 
     * @param anId BusinessLocation ID
     * @param aLocation Address of BusinessLocation
     */
    public BusinessLocation(ID anId, Address aLocation)
    {
        id = anId;
        location = aLocation;
        employees = new HashMap<>();
    }

    /**
     * Returns a Map of ID and Employee objects. Each ID object in the key value 
     * pair (ID, Employee) corresponds to the employees ID in the pair.
     *
     * @return all associated employees
     */
    public Map<ID, Employee> getEmployees()
    {
        return employees;
    }

    /**
     * employees references the provided employeList
     * 
     * @param employeeList 
     */
    public void setEmployees(Map<ID, Employee> employeeList)
    {
        employees = employeeList;
    }

    /**
     * Adds an employee reference to the employees Map
     * 
     * @param anEmployee an employee to add.
     */
    public void addEmployees(Employee anEmployee)
    {
        employees.put(anEmployee.getId(), anEmployee);
    }
      
    /**
     * Returns a unique ID that identifies a real world premises 
     * belonging to the company.
     * 
     * @return Unique ID 
     */
    public ID getId()
    {
        return id;
    }
    
     /**
     * Returns a unique ID that identifies a real world premises 
     * belonging to the company.
     * 
     * @return Unique ID as a String
     */
    public String getIDAsString()
    {
        return id.getID();
    }

    /**
     * Returns the location of the physical premises corresponding to the object.
     * 
     * @return location, a real world UK address.
     */
    public Address getLocation()
    {
        return location;
    }

    /**
     * Records a reference to the provided Address object, i.e. setting a new address.
     * 
     * @param aLocation an address for the premises
     */
    public void setLocation(Address aLocation)
    {
        location = aLocation;
    }

    /**
     * @return a string representation of id, location and employees.
     */
    @Override
    public String toString()
    {
        return "BusinessLocation: id = " + id + ", location = " 
                + location + ", employees = " + employees;
    }
    
}
