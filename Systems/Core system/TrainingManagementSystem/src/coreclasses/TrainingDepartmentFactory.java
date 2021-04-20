package coreclasses;

import com.google.gson.*;
import com.google.gson.JsonArray;
import java.util.HashSet;
import java.util.Set;
import utility.Address;
import utility.ID;
import utility.IDClass;
import utility.ServiceRequest;

/**
 * Responsible for creating TrainingDepartment objects from the database and 
 * initialising the system on start up with the other factory classes.
 */
public class TrainingDepartmentFactory
{
    private Set<TrainingDepartment> trainingDepartments;
    
    /**
     * 
     */
    public TrainingDepartmentFactory()
    {
        trainingDepartments = new HashSet<>();
        initialise();
    }
    
    /**
     * Gets all the trainingdepartment objects in the entire system.
     */
    private void initialise()
    {      
        
        String url = "http://localhost/tms_rest_api/api/trainingdepartments/find_all_departments.php?";

        Gson gs = new Gson();
        JsonArray ja = gs.fromJson(new ServiceRequest().get(url), JsonArray.class);
        JsonObject jo = null;
       
        String code;
        TrainingDepartment td = null;
        for(JsonElement je: ja)
        {
            jo = je.getAsJsonObject();

            code = je.getAsJsonObject().get("td_id").getAsString().substring(2);
            td = new TrainingDepartment
            (
                new ID(IDClass.TD, code),
                new Address
                (
                    jo.get("number").getAsInt(), 
                    jo.get("streetname").getAsString(), 
                    jo.get("city").getAsString(), 
                    jo.get("postcode").getAsString().toUpperCase()
                ), 
                jo.get("capacity").getAsInt()
            );
            
            trainingDepartments.add(td);
        }                            
        System.out.println(trainingDepartments);        
    }

    /**
     * Gets all the linked training departments.
     * 
     * @return trainingDepartments
     */
    public Set<TrainingDepartment> getTrainingDepartments()
    {
        return trainingDepartments;
    }

    /**
     * Returns a string representing the linked training departments
     * 
     * @return trainingDepartments
     */
    @Override
    public String toString()
    {
        return "TrainingDepartmentFactory{" + "trainingDepartments=" + trainingDepartments + '}';
    }
    
    
    
}
