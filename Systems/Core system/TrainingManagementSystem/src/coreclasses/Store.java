package coreclasses;

import utility.*;


public class Store extends BusinessLocation
{

    /**
     * Constructor for a store
     * 
     * @param anId
     * @param aLocation 
     */
    public Store(ID anId, Address aLocation)
    {
        super(anId, aLocation);
    }
    
    /**
     * Constructor for a store
     * 
     * @param aStore 
     */
    public Store(Store aStore)
    {
        super(aStore.getId(), aStore.getLocation());
    }
    
    /**
     * Remove employee from store
     * 
     * @param anEmployee 
     */
    public void removeEmployee(Employee anEmployee)
    {
        anEmployee.removeStore();
    }

    
    
}
