package coreclasses;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


public class TrainingEvent
{
    private int id;
    private Type trainingType;
    private LocalDate date;
    private LocalTime time;
    
// links
    private TrainingDepartment trainingDepartment;
    private Set<Employee> employees;
    
    /**
     * Constructor for training event
     * 
     * @param aTrainingType
     * @param aTrainingDepartment
     * @param aTime
     * @param aDate 
     */
    public TrainingEvent(Type aTrainingType, TrainingDepartment aTrainingDepartment, 
            LocalTime aTime, LocalDate aDate)
    {
        trainingType = aTrainingType;
        trainingDepartment = aTrainingDepartment;
        time = aTime;
        date = aDate;
        employees = new HashSet<>();
    }
    
    /**
     * Constructor for training event with an ID
     * 
     * @param aTrainingType
     * @param aTrainingDepartment
     * @param aTime
     * @param aDate
     * @param ID 
     */
    public TrainingEvent(Type aTrainingType, TrainingDepartment aTrainingDepartment, 
            LocalTime aTime, LocalDate aDate, int anID)
    {
        trainingType = aTrainingType;
        trainingDepartment = aTrainingDepartment;
        time = aTime;
        date = aDate;
        id = anID;
        employees = new HashSet<>();
    }
    
    /**
     * Adds an employee to the training event
     * 
     * @param anEmployee
     * @throws Exception 
     */
    public void addEmployee(Employee anEmployee) throws Exception
    {
        if(getPlacesRemaining() > 0)
        {
            employees.add(anEmployee);
            anEmployee.addTrainingEvent(this);
        }
        else
        {
            throw new Exception("No more spaces left for this event");
        }
    }
        
    /**
     * Removes the provided employee object from the Set containing 
     * the employees attending the event
     * 
     * @param anEmployee 
     */
    public void removeEmployee(Employee anEmployee)
    {
        employees.remove(anEmployee);
        anEmployee.removeTrainingEvent(this);
    }
    
    /**
     * How many places are left for the event.
     * 
     * @return places remaining
     */
    public int getPlacesRemaining()
    {
        return 1;//trainingDepartment.getCapacity() - employees.size();
    }

    /**
     * Type of training event
     * 
     * @return training type
     */
    public Type getTrainingType()
    {
        return trainingType;
    }

    /**
     * Date of the training event
     * 
     * @return date
     */
    public LocalDate getDate()
    {
        return date;
    }

    /**
     * Time of the training event
     * 
     * @return time
     */
    public LocalTime getTime()
    {
        return time;
    }

    /**
     * Get the training event id assigned by the database.
     * 
     * @return training event id
     */
    public int getId()
    {
        return id;
    }

    
    /**
     * Get all employees attending the event.
     * 
     * @return employees linked to event
     */
    public Set<Employee> getEmployees()
    {
        return employees;
    }
    
    

//    @Override 
//    public boolean equals(Object obj) {
//            TrainingEvent anotherEvent = (TrainingEvent)obj; 
//            return getDate().equals(anotherEvent.getDate()) 
//                    && getTime() == anotherEvent.getTime();
//    }
//    
//    @Override
//    public int hashCode() { 
//        return getTime().getHour() + getDate().getDayOfYear(); 
//    }
    
    /**
     * State of the object
     * 
     * @return training type, date, time, and the training department location.
     */
    @Override
    public String toString()
    {
        return trainingType + ", \ndate = " + date + ", time = " + time 
                + ", \nTrainingDepartment " + trainingDepartment.getLocation() + "\n\n"; 
//                + ", \nattendees = " + employees.size() + ", \nremaining spaces = " 
//                + String.valueOf(getPlacesRemaining()) + "\n\n";
    }

    
    
    
    
    
}
