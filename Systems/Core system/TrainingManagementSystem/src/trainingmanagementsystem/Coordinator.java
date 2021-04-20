package trainingmanagementsystem;

import coreclasses.*;
import java.util.Set;

public class Coordinator
{

    private static Coordinator aCoordinator;
    
    private Coordinator(){}
    
    public static Coordinator getInstance()
    {
        if(aCoordinator == null)
        {
            aCoordinator = new Coordinator();
        }
        return aCoordinator;
    }
    

    
    public void makeBooking(TrainingEvent aTrainingEvent, Employee anEmployee)
    {
        try
        {
            aTrainingEvent.addEmployee(anEmployee);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void cancelBooking(TrainingEvent aTrainingEvent, Employee anEmployee)
    {
        aTrainingEvent.removeEmployee(anEmployee);
    }
    
    public Set<TrainingEvent> viewTraining(Employee anEmployee)
    {
        return anEmployee.getTrainingEvents();
    }
}
