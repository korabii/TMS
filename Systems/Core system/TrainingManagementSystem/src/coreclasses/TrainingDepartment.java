package coreclasses;

import java.util.HashSet;
import java.util.Set;
import utility.*;

public class TrainingDepartment extends BusinessLocation
{
    private int capacity;
    private Set<TrainingEvent> trainingEvent;

    /**
     * Constructor for training department
     * 
     * @param anId
     * @param aLocation
     * @param aCapacity 
     */
    public TrainingDepartment(ID anId, Address aLocation, int aCapacity)
    {
        super(anId, aLocation);
        capacity = aCapacity;
        trainingEvent = new HashSet<>();
    }
    
    /**
     * Add a training event
     * 
     * @param aTrainingEvent
     */
    public void addTrainingEvent(TrainingEvent aTrainingEvent)
    {
        trainingEvent.add(aTrainingEvent);
    }

    /**
     * The maximum number of employees that can be accommodated 
     * at the event at any one time.
     * 
     * @return Training department capacity
     */
    public int getCapacity()
    {
        return capacity;
    }

    /**
     * Sets the capacity of the training department
     * 
     * @param aCapacity 
     */
    public void setCapacity(int aCapacity)
    {
        capacity = aCapacity;
    }

    /**
     * String representation of the object.
     * @return ID, Location, capacity, and training events as a string.
     */
    @Override
    public String toString()
    {
        return "TrainingDepartment: id = " + super.getId() + ", location = " 
                + super.getLocation() + " capacity = " + capacity 
                + ", trainingEvent = " + trainingEvent.size();
    }




    
    
    
    
    
}
