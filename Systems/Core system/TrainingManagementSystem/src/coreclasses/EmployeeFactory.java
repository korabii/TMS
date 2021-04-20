package coreclasses;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import utility.EMail;
import utility.ID;
import utility.Name;
import utility.PhoneNumber;
import utility.ServiceRequest;
import utility.TrainingConverterHelper;


/**
 * A class that gets all employees for the store from the database and makes
 * them available to the system (Persistence).
 */
public class EmployeeFactory
{
    private Map<ID, Employee> employees;

       
    /**
     * Retrieves all employees, their training events and linked training 
     * departments for aStore and links them to aStore
     * 
     * @param aStore
     * @param trainingDepartments 
     */
    public EmployeeFactory(Store aStore, Set<TrainingDepartment> trainingDepartments)
    {
        employees = new HashMap<>();
        initialise(aStore);
        linkTrainingToEmployee(trainingDepartments);
    }
    

    /**
     * Retrieves all employees for aStore and links them to aStore.
     * 
     * @param aStore the object referencing the real world store the instance 
     * is running in.
     */
    private void initialise(Store aStore)
    {    
        String url = "http://localhost/tms_rest_api/api/employees/read.php?";
        String param = "store_id=" + aStore.getIDAsString();
        
        Gson gs = new Gson();
        JsonArray ja = gs.fromJson(new ServiceRequest().get(url, param), JsonArray.class);
        JsonObject jo = null;
        
        Employee emp;
        ID id = null;
        for(JsonElement je: ja)
        {
            jo = je.getAsJsonObject();

            id = new ID(jo.get("employee_id").getAsString());
            emp = new Employee         
            (
                new Name(
                    jo.get("title").getAsString(),
                    jo.get("firstname").getAsString(),
                    jo.get("lastname").getAsString()
                ), 
                id, 
                LocalDate.parse(jo.get("date_of_birth").getAsString(), DateTimeFormatter.ISO_DATE), 
                LocalDate.parse(jo.get("start_of_employment").getAsString(), DateTimeFormatter.ISO_DATE), 
                new EMail(jo.get("email_address").getAsString()), 
                new PhoneNumber(jo.get("mobile_number").getAsString()),
                aStore
            );
            
            employees.put(id, emp);
        } 
        
        System.out.println(ja);
        System.out.println(employees);
    }
    
    private void linkTrainingToEmployeeHelper(Set<TrainingDepartment> trainingDepartments, Employee employee)
    {
        String url = "http://localhost/tms_rest_api/api/trainingevents/find_employee_training.php?";
        String param = "employee_id=" + employee.getId().getID();
        
        Gson gs = new Gson();
        JsonArray ja;
        
            try{
                ja = gs.fromJson(new ServiceRequest().get(url, param), JsonArray.class);
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
                        LocalTime.parse(jo.get("date_time").getAsString().substring(11), DateTimeFormatter.ISO_TIME),
                        LocalDate.parse(jo.get("date_time").getAsString().substring(0, 10), DateTimeFormatter.ISO_DATE),
                        jo.get("te_id").getAsInt()                           
                    );


                    employee.addTrainingEvent(trainingEvent);
                    trainingEvent.addEmployee(employee);
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
   
    }
    
    /**
     * For the given employee finds all the training events the employee is booked 
     * on and records this reference in the employee object, also links the training 
     * department of the training event to the training event.
     * 
     * @param trainingDepartments 
     */
    private void linkTrainingToEmployee(Set<TrainingDepartment> trainingDepartments)
    {
        employees.values().forEach((employee) ->
        {
            linkTrainingToEmployeeHelper(trainingDepartments, employee);
        });
    }
    
    /**
     * Adds the provided employee to the store in this instance and the Database system.
     * 
     * @param anEmployee
     * @param aStore 
     */
    public void addEmployee(Employee anEmployee, Store aStore) throws MalformedURLException, IOException
    {
        String USER_AGENT = "Mozilla/5.0";
        
        String employee_id = anEmployee.getId().getID().toLowerCase();
        String title = anEmployee.getName().getTitle().toString().toLowerCase();
        String firstname = anEmployee.getName().getFirstName();
        String lastname = anEmployee.getName().getLastName();
        String dob = anEmployee.getDateOfBirth().format(DateTimeFormatter.ISO_DATE);
        String startOfEmployment = anEmployee.getStartOfEmployment().format(DateTimeFormatter.ISO_DATE);
        String email = anEmployee.geteMailAddress();
        String mobile = anEmployee.getMobileNumber().getMobileNumber();
        String storeId = aStore.getIDAsString().toLowerCase();
                
        String url = "http://localhost/tms_rest_api/api/employees/create.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //set to JSON
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.addRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        //Information sent to the API
        String urlParameters = "{\"employee_id\":\""+ employee_id + "\"," 
                            + "\"title\":\""+ title+ "\","
                            + "\"firstname\":\""+ firstname + "\"," 
                            + "\"lastname\":\""+ lastname + "\","
                            + "\"date_of_birth\":\""+ dob + "\","
                            + "\"start_of_employment\":\""+ startOfEmployment + "\","
                            + "\"email_address\":\""+ email + "\","
                            + "\"mobile_number\":\""+ mobile + "\","
                            + "\"training_level\":\""+ "na" + "\","
                            + "\"last_qa\":\""+ startOfEmployment + "\","
                            + "\"store_id\":\""+ storeId + "\"}";


        try(OutputStream os = con.getOutputStream())
        {
            byte[] input = urlParameters.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        System.out.println("response code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        //Response from the API
        while((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
        // add employee to system
        aStore.addEmployees(anEmployee);

        

    }
    
    /**
     * anEmployee is updated with new information provided from the user interface.
     * first in the database than the running instance.
     * 
     * @param anEmployee
     * @throws IOException 
     */
    public void updateEmployee(Employee anEmployee) throws IOException
    {
        String USER_AGENT = "Mozilla/5.0";
        
        String employee_id = anEmployee.getId().getID().toLowerCase();
        String title = anEmployee.getName().getTitle().toString().toLowerCase();
        String firstname = anEmployee.getName().getFirstName();
        String lastname = anEmployee.getName().getLastName();
        String dob = anEmployee.getDateOfBirth().format(DateTimeFormatter.ISO_DATE);
        String startOfEmployment = anEmployee.getStartOfEmployment().format(DateTimeFormatter.ISO_DATE);
        String email = anEmployee.geteMailAddress();
        String mobile = anEmployee.getMobileNumber().getMobileNumber();
        
        
        String url = "http://localhost/tms_rest_api/api/employees/update.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        //set to JSON
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.addRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        
        con.setDoOutput(true);
        
        //Information sent to the API
        String urlParameters = "{\"employee_id\":\""+ employee_id + "\"," 
                            + "\"title\":\""+ title+ "\","
                            + "\"firstname\":\""+ firstname + "\"," 
                            + "\"lastname\":\""+ lastname + "\","
                            + "\"date_of_birth\":\""+ dob + "\","
                            + "\"start_of_employment\":\""+ startOfEmployment + "\","
                            + "\"email_address\":\""+ email + "\","
                            + "\"mobile_number\":\""+ mobile + "\"}";
        
        
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
        
        System.out.println(response.toString());
        

    }
    
    /**
     * Creates a Map of employee id's of employees that are overdue on training
     * and the training type as a string
     * 
     * @param aStore
     * @return a Map of employee id's training type
     */
    public Map<ID, String> trainingProgress(Store aStore)
    {
        String url = "http://localhost/tms_rest_api/api/employees/training_progress.php?";
        String param = "store_id=" + aStore.getIDAsString();
        
        Map<ID, String> employeeTraining = new HashMap<>();
        
        Gson gs = new Gson();
        JsonArray ja;
        
        LocalDate today = LocalDate.now();
        
        try{
            ja = gs.fromJson(new ServiceRequest().get(url, param), JsonArray.class);
            JsonObject jo = null;
            ID id;

            for(JsonElement je: ja)
            {                      
                jo = je.getAsJsonObject();
                id = new ID(jo.get("employee_id").getAsString());
                Employee employee = employees.get(id);
                String trainingLevel = jo.get("training_level").getAsString();
                LocalDate qaDate = LocalDate.parse(jo.get("last_qa").getAsString(), DateTimeFormatter.ISO_DATE);
                
//                String[] trainingList = {"Junior Sales Assistant", "Sales Assistant", 
//                "Qualified Sales Assistant", "Senior Sales Assistant", "na", "js", "sa", "qa"};
//                int[] monthList = {3, 6, 12, 18};
//
//                LocalDate cutOffDate;
//                for(int i = 0; i<trainingList.length / 2; i++)
//                {
//                    cutOffDate = employee.getStartOfEmployment().plusMonths(monthList[i]);
//                    if(cutOffDate.isBefore(today) && trainingLevel.equals(trainingList[i+4]))
//                    {
//                        System.out.println(cutOffDate + trainingList[i+4] 
//                                + " - employee: " + employee.toString()); 
//                        employeeTraining.put(id, trainingList[i]);
//                    }   
//                }
                                
                //Junior sales assistant cut off date
                LocalDate cutOffDate = employee.getStartOfEmployment().plusMonths(3);
                if(cutOffDate.isBefore(today) && trainingLevel.equals("na"))
                {
                    System.out.println(cutOffDate + " js - employee: " + employee.toString()); 
                    employeeTraining.put(id, "Junior Sales Assistant");
                }
                
                //Sales assistant cut off date
                cutOffDate = employee.getStartOfEmployment().plusMonths(6);
                //cutOffDate = LocalDate.of(cutOffDate.getYear(), cutOffDate.getMonth(), employeeStartDateDay);                
                if(cutOffDate.isBefore(today) && trainingLevel.equals("js"))
                {
                    System.out.println(cutOffDate + " sa - employee: " + employee.toString());
                    employeeTraining.put(id, "Sales Assistant");
                }
                
                //Qualified sales assistant cut off date
                cutOffDate = employee.getStartOfEmployment().plusMonths(12);                
                if((cutOffDate.isBefore(today) && trainingLevel.equals("sa")) || qaDate.plusMonths(12).isBefore(today))
                {
                    System.out.println(cutOffDate + " qa - employee: " + employee.toString());
                    employeeTraining.put(id, "Qualified Sales Assistant");
                }
                
                //Senior sales assistant cut off date
                cutOffDate = employee.getStartOfEmployment().plusMonths(18);               
                if(cutOffDate.isBefore(today) && trainingLevel.equals("qa"))
                {
                    System.out.println(cutOffDate + " ss - employee: " + employee.toString());
                    employeeTraining.put(id, "Senior Sales Assistant");
                }

            }
        }
        catch(JsonSyntaxException e)
        {
            System.out.println("Error: " + e.getMessage());
        }            
        return employeeTraining;
    }
    
    /**
     * Removes employee from the database and the running instance of the core system
     * 
     * @param anEmployee
     * @param aStore
     * @throws IOException 
     */
    public void removeEmployee(Employee anEmployee, Store aStore) throws IOException
    {
        String USER_AGENT = "Mozilla/5.0";
        
        String employee_id = anEmployee.getId().getID().toLowerCase();        
        
        String url = "http://localhost/tms_rest_api/api/employees/delete.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        //set to JSON
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.addRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        
        con.setDoOutput(true);
        
        //Information sent to the API
        String urlParameters = "{\"employee_id\":\""+ employee_id + "\"}";        
        
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
        
        System.out.println(response.toString());
        
        //remove employee from store than store from employee 
        aStore.removeEmployee(anEmployee);
        employees.remove(anEmployee.getId());
        
    }
    
    /**
     * Gets all the employees for the store
     * 
     * @return employees
     */
    public Map<ID, Employee> getEmployees()
    {
        return employees;
    }

    /**
     * Returns a string representing the linked employees
     * 
     * @return employees
     */
    @Override
    public String toString()
    {
        return "EmployeeFactory{" + "employees=" + employees + '}';
    }
    
    
    
}
