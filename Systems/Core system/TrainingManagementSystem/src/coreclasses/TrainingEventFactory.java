package coreclasses;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import utility.ID;
import utility.ServiceRequest;
import utility.TrainingConverterHelper;

/**
 * A class that manages training event objects
 */
public class TrainingEventFactory
{
    
    /**
     * 
     */
    public TrainingEventFactory()
    {    
    }   
    
    /**
     * Given a type for the training event this method returns all training 
     * events corresponding to the type.
     * 
     * @param aType
     * @param trainingDepartments
     * @return set of training departments corresponding to the given type.
     */
    public Set<TrainingEvent> findTrainingEvents(Type aType, Set<TrainingDepartment> trainingDepartments)
    {
        Set<TrainingEvent> trainingEvents = null;
        try
        {
        /* clear items as multiple choices in the GUI will poppulate the 
            trainingEvents set with a combination of different types of events 
            also happens if no override method is created for equals*/
        //trainingEvents.clear();
        trainingEvents = new HashSet<>();
        String type = new TrainingConverterHelper().toString(aType);
        
        String url = "http://localhost/tms_rest_api/api/trainingevents/find_training.php?";
        String param = "type=" + type;
        
        Gson gs = new Gson();
        JsonArray ja = gs.fromJson(new ServiceRequest().get(url, param), JsonArray.class);      
        JsonObject jo = null;
        
        TrainingDepartment trainingDepartment = null;
        TrainingEvent trainingEvent = null;
        ID id = null;
        //System.out.println(ja.get(0).getAsJsonObject().get("date_time").getAsString());
               
        for(JsonElement je: ja)
        {
            jo = je.getAsJsonObject();
            
            id = new ID(jo.get("td_id").getAsString());
            
            /* find the corresponding training department according to 
            id so that it can be linked to the training event. */           
            for(TrainingDepartment td: trainingDepartments)
            {
                if(td.getId().equals(id))
                {                    
                    trainingDepartment = td;
                }
            }
            
            trainingEvent = new TrainingEvent(
                new TrainingConverterHelper().toType(jo.get("type").getAsString()),                   
                trainingDepartment,
                LocalTime.parse(jo.get("date_time").getAsString().substring(11), 
                        DateTimeFormatter.ISO_TIME),
                LocalDate.parse(jo.get("date_time").getAsString().substring(0, 10), 
                        DateTimeFormatter.ISO_DATE),
                jo.get("te_id").getAsInt()
            );
            

            trainingEvents.add(trainingEvent);
            //System.out.println(jo.get("date_time").getAsString());
        }
        
        System.out.println("training events --- \n" + trainingEvents);
        }
        catch(Exception e)
        {
            System.out.println("No training events found");
        }
        return trainingEvents;
    }
    
    /**
     * Makes a booking for anEmployee to anEvent.
     * 
     * @param anEmployee the employee to book an event for.
     * @param anEvent the event to book the employee on.
     * @return 
     */
    public boolean makeBooking(Employee anEmployee, TrainingEvent anEvent)
    {
        String message = null;
        boolean result = false;
        try
        {
            String USER_AGENT = "Mozilla/5.0";
            String employeeId = anEmployee.getId().getID().toLowerCase();
            String eventID = String.valueOf(anEvent.getId());

            String url = "http://localhost/tms_rest_api/api/training/make_booking.php";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //set to JSON
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.addRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");

            con.setDoOutput(true);

            //Information sent to the API
            String urlParameters = "{\"employee_id\":\""+ employeeId + "\"," 
                                + "\"training_event_id\":\""+ eventID + "\"}";


            try(OutputStream os = con.getOutputStream())
            {
                byte[] input = urlParameters.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("response code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            //Response from the API
            while((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();

            message = response.toString();
            
            // not a good idea to couple the output of the API with the core system
            // later iterations should get and read the message as a JSON object.
            if(message.substring(0, 27).equals("{\"message\":\"training booked")) result = true;
            
            // create the booking on the running instance.
            anEvent.addEmployee(anEmployee);
            
            System.out.println(message);
        }
        catch(Exception e)
        {
            message = "Booking could not be created";
            result = false;
            System.out.println(message);
        }      
        return result;
    }   
    
    /**
     * Cancels a booking for anEmployee on anEvent.
     * 
     * @param anEmployee
     * @param anEvent
     * @return result, a Boolean value indicating the success of the operation.
     */
    public boolean cancelBooking(Employee anEmployee, TrainingEvent anEvent)
    {
        String message = null;
        boolean result = true;
        try
        {
            String USER_AGENT = "Mozilla/5.0";
            String employeeId = anEmployee.getId().getID().toLowerCase();
            String eventID = String.valueOf(anEvent.getId());

            String url = "http://localhost/tms_rest_api/api/training/cancel_booking.php";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //set to JSON
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.addRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");

            con.setDoOutput(true);

            //Information sent to the API
            String urlParameters = "{\"employee_id\":\""+ employeeId + "\"," 
                                + "\"training_event_id\":\""+ eventID + "\"}";


            try(OutputStream os = con.getOutputStream())
            {
                byte[] input = urlParameters.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            System.out.println("response code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            //Response from the API
            while((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();

            message = response.toString();
            
            // checks whether the event was cancelled in the db
            result = (message.equals("{\"message\":\"training event cancelled\"}"));
            
            // cancel event in the running instance
            anEvent.removeEmployee(anEmployee);
            
            System.out.println(response.toString());
        }
        catch(IOException e)
        {
            message = "Booking could not be cancelled";
            result = false;
            System.out.println(message);
        }      
        return result;
    }

    /**information on the object.
     * 
     * 
     * @return a string message describing the object.
     */
    @Override
    public String toString()
    {
        return "TrainingEventFactory, is a singelton which is responsible for "
                + "the creation and mangement of training event objects on the"
                + " running instance.";
    }
    

    
    
}
