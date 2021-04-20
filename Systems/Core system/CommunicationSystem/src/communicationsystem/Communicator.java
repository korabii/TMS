package communicationsystem;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;


public class Communicator
{
    private ServiceRequest service = null;
    
    public Communicator()
    {
        service = new ServiceRequest();
    }
    
    /**
     * Creates a list of employees that are overdue or have 1 month remaining to 
     * sit the Qualified Assistant test and sends a reminder to the employees.
     */
    public void sendQualifiedAssistantReminder()
    {
        //System.out.println(response.toString());
        System.out.println("\nList of employees with 1 month remaining or overdue "
                + "qualified assistant test");
        System.out.println("----------------------------------------------------"
                + "------------------------");
        try
        {
            Gson gs = new Gson();
            JsonArray ja = gs.fromJson(service.get("http://localhost/tms_rest_api/api/communicationsystem/qa.php"), JsonArray.class);
            JsonObject jo;
            for(JsonElement je: ja)
            {
                jo = je.getAsJsonObject();
                System.out.println(jo.get("title").getAsString() + ". " 
                        + jo.get("firstname").getAsString()
                        + " " + jo.get("lastname").getAsString() 
                        + " email: " + jo.get("email_address").getAsString() 
                        + " mobile: " + jo.get("mobile_number").getAsString());
            }
        }
        catch(JsonSyntaxException e)
        {
            System.out.println("Could not retrieve list of overdue employees "
                    + "for the qualified assistant test");
            System.out.println("Error: " + e.getMessage());
            
        }
    }
    
    /**
     * Creates a list of employees that are overdue or have 1 month remaining to 
     * sit the Junior Sales Assistant test and sends a reminder to the employees.
     */
    public void sendJuniorSalesAssistantReminder()
    {
        //System.out.println(response.toString());
        System.out.println("\nList of employees with 1 month remaining or overdue "
                + "junior sales assistant test");
        System.out.println("----------------------------------------------------"
                + "------------------------");
        try
        {
            Gson gs = new Gson();
            JsonArray ja = gs.fromJson(service.get("http://localhost/tms_rest_api/api/communicationsystem/js.php"), JsonArray.class);
            JsonObject jo;
            for(JsonElement je: ja)
            {
                jo = je.getAsJsonObject();
                System.out.println(jo.get("title").getAsString() + ". " 
                        + jo.get("firstname").getAsString()
                        + " " + jo.get("lastname").getAsString() 
                        + " email: " + jo.get("email_address").getAsString() 
                        + " mobile: " + jo.get("mobile_number").getAsString());
            }
        }
        catch(Exception e)
        {
            System.out.println("Could not retrieve a list of overdue employees "
                    + "for the junior sales assistant test");
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Creates a list of employees that are overdue or have 1 month remaining to 
     * sit the Sales Assistant test and sends a reminder to the employees.
     */
    public void sendSalesAssistantReminder()
    {
        //System.out.println(response.toString());
        System.out.println("\nList of employees with 1 month remaining or overdue "
                + "sales assistant test");
        System.out.println("----------------------------------------------------"
                + "------------------------");
        try
        {
            Gson gs = new Gson();
            JsonArray ja = gs.fromJson(service.get("http://localhost/tms_rest_api/api/communicationsystem/sa.php"), JsonArray.class);
            JsonObject jo;
            for(JsonElement je: ja)
            {
                jo = je.getAsJsonObject();
                System.out.println(jo.get("title").getAsString() + ". " 
                        + jo.get("firstname").getAsString()
                        + " " + jo.get("lastname").getAsString() 
                        + " email: " + jo.get("email_address").getAsString() 
                        + " mobile: " + jo.get("mobile_number").getAsString());
            }
        }
        catch(Exception e)
        {
            System.out.println("Could not retrieve a list of overdue employees "
                    + "for the sales assistant test");
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Creates a list of employees that are overdue or have 1 month remaining to 
     * sit the Senior Sales Assistant test and sends a reminder to the employees.
     */
    public void sendSeniorSalesAssistantReminder()
    {
        //System.out.println(response.toString());
        System.out.println("\nList of employees with 1 month remaining or overdue "
                + "senior sales assistant test");
        System.out.println("----------------------------------------------------"
                + "------------------------");
        try
        {
            Gson gs = new Gson();
            JsonArray ja = gs.fromJson(service.get("http://localhost/tms_rest_api/api/communicationsystem/ss.php"), JsonArray.class);
            JsonObject jo;
            for(JsonElement je: ja)
            {
                jo = je.getAsJsonObject();
                System.out.println(jo.get("title").getAsString() + ". " 
                        + jo.get("firstname").getAsString()
                        + " " + jo.get("lastname").getAsString() 
                        + " email: " + jo.get("email_address").getAsString() 
                        + " mobile: " + jo.get("mobile_number").getAsString());
            }
        }
        catch(Exception e)
        {
            System.out.println("Could not retrieve a list of overdue employees "
                    + "for the senior sales assistant test");
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Sends all the reminders for every test, Qualified Assistant, Junior Sales
     * Assistant, Sales Assistant and Senior Sales Assistant.
     */
    public void sendAll()
    {
        sendQualifiedAssistantReminder();
        sendJuniorSalesAssistantReminder();
        sendSalesAssistantReminder();
        sendSeniorSalesAssistantReminder();
    }

    /**
     * @return String representation of the object.
     */
    @Override
    public String toString()
    {
        return "This object is reponsible for sending reminders to staff about "
                + "overdue or soon to be overdue compulsory tests";
    }
    
    
            
}
