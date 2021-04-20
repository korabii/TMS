package trainingmanagementsystem;

import coreclasses.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.*;


public class UserInterface extends Application {
    
    final ID storeID = new ID("st00000001");
    // employee menu
    MenuItem menuViewEmployee;
    MenuItem menuAddEmployee;
    MenuItem menuEditEmployee;
    MenuItem menuRemoveEmployee;
    // training menu
    MenuItem menuBookTraining;
    MenuItem menuCancelTraining;
    MenuItem menuTrainingProgress;
    
    VBox mainLayout;
    HBox hbox;
    GridPane gridPane;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);

//        Coordinator coord = Coordinator.getInstance();
//        ID storeID = new ID("st00000001");
//        
//        //get all training departments
//        Set<TrainingDepartment> trainingDepartments = 
//                new TrainingDepartmentFactory().getTrainingDepartments();
//        
//        //Inititalise the store that the machine is located in using the store id
//        Store aStore = new StoreFactory(storeID).getStore();
//        
//        /*get all the employees for the store and link 
//        employee -> store, employee <-> trainingevent, trainingevent->trainingdepartment*/
//        EmployeeFactory eFactory = new EmployeeFactory(aStore, trainingDepartments);
//        Map<ID, Employee> employees = eFactory.getEmployees();
//        
//        //add the employees to the store, employee <- store.
//        aStore.setEmployees(employees);               
        
    }

    @Override
    public void start(Stage stage) throws Exception
    {       
    /* ------------------------------ Initialise the system ------------------------------ */
                
        //get all training departments
        Set<TrainingDepartment> trainingDepartments = 
                new TrainingDepartmentFactory().getTrainingDepartments();
        
        //Inititalise the store that the machine is located in using the store id
        Store aStore = new StoreFactory(storeID).getStore();
        
        /*get all the employees for the store and create the following links
        store <- employee <-> trainingevent -> trainingdepartment*/
        EmployeeFactory eFactory = new EmployeeFactory(aStore, trainingDepartments);
        Map<ID, Employee> employees = eFactory.getEmployees();
        
        /*add the employees to the store, links = store -> employee.  
        Links = store <-> employee <-> trainingevent -> trainingdepartment */
        aStore.setEmployees(employees); 
                        
        // used in the use cases to book and cancel trainin events
        TrainingEventFactory trainingEventFactory = new TrainingEventFactory();
        
        
        
    /* ---------------------------------- user interface ---------------------------------- */
        stage.setTitle("Employee Training Management System");        
           
        // Secondary layouts
        hbox = new HBox();
        gridPane = new GridPane();
        
        // Set the main layout
        mainLayout = new VBox();
        mainLayout.getChildren().add(menuBar());

        // Set menu item action behaviour
        menuViewEmployee.setOnAction((t) -> viewEmployees(employees));   
        menuAddEmployee.setOnAction((t) -> addEmployee(aStore, eFactory));
        menuEditEmployee.setOnAction((t) -> editEmployee(aStore, eFactory, employees));
        menuRemoveEmployee.setOnAction((t) -> removeEmployee(employees, eFactory, aStore));
        menuBookTraining.setOnAction((t) -> 
                bookTrainingEvent(trainingEventFactory, trainingDepartments, employees));
        menuCancelTraining.setOnAction( (t) -> 
                cancelTrainingEvent(employees, trainingEventFactory));
        menuTrainingProgress.setOnAction((t) -> viewProgress(eFactory, aStore, employees));
                               
        //Set initial screen to view progress
        viewProgress(eFactory, aStore, employees);
        
        // Create a scene and show it to the user
        Scene scene1 = new Scene(mainLayout, 600, 400);      
        stage.setScene(scene1);
        stage.show();
    }
    
    private void viewEmployees(Map<ID, Employee> employees)
    {
        //clear screen
        clearScreen();
        if(!mainLayout.getChildren().contains(hbox)) mainLayout.getChildren().add(hbox);

        //create lists
        ListView<Employee> employeeList = new ListView<>();
        ListView<String> employeeInfo = new ListView<>();
        employeeList.getItems().addAll(employees.values());
        
        // poppulate the employee info list with the first employee
        employeeInfo.getItems().addAll(employees.entrySet().iterator().next()
                .getValue().employeeInformation());
        
        //on click get employee info
        employeeList.setOnMouseClicked((t) ->
        {
            employeeInfo.getItems().clear();
            employeeInfo.getItems().addAll(employeeList.getSelectionModel()
                    .getSelectedItem().employeeInformation());
        });    

        
        //add elements to layout
        hbox.getChildren().addAll(employeeList, employeeInfo);
    }
    
    private void addEmployee(Store aStore, EmployeeFactory aFactory)
    {
        //clear screen
        clearScreen();
        // add layout if it has not allready been added to the main layout
        if(!mainLayout.getChildren().contains(gridPane)) mainLayout.getChildren().add(gridPane);
                       
        //Set the labels
        Label title = new Label("Title");
        Label firstNameLabel = new Label("First name");
        Label secondNameLabel = new Label("Second name");
        Label idLabel = new Label("Employee ID");
        Label dateOfBirthLabel = new Label("Date of birth");
        Label startOfEmploymentLabel = new Label("Start of employment");
        Label eMailLabel = new Label("eMail address");
        Label mobileNumberLabel = new Label("Mobile phone number");
        
        // set the labels as the first item in every row [col][row]
        GridPane.setConstraints(title, 0, 0);
        GridPane.setConstraints(firstNameLabel, 0, 1);
        GridPane.setConstraints(secondNameLabel, 0, 2);
        GridPane.setConstraints(idLabel, 0, 3);
        GridPane.setConstraints(dateOfBirthLabel, 0, 4);
        GridPane.setConstraints(startOfEmploymentLabel, 0, 5);
        GridPane.setConstraints(eMailLabel, 0, 6);
        GridPane.setConstraints(mobileNumberLabel, 0, 7);
        
        // set the input fields
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Mr", "Mrs", "Ms", "Miss", "Mx");
        TextField firstName = new TextField();
        TextField secondName = new TextField();
        TextField id = new TextField();       
        DatePicker dateOfBirth = new DatePicker();
        DatePicker startOfEmployment = new DatePicker();
        TextField eMail = new TextField(); 
        TextField mobileNumber = new TextField();
                
        // set the input fields as the second item in every row
        GridPane.setConstraints(choiceBox, 1, 0);
        GridPane.setConstraints(firstName, 1, 1);
        GridPane.setConstraints(secondName, 1, 2);
        GridPane.setConstraints(id, 1, 3);
        GridPane.setConstraints(dateOfBirth, 1, 4);
        GridPane.setConstraints(startOfEmployment, 1, 5);
        GridPane.setConstraints(eMail, 1, 6);
        GridPane.setConstraints(mobileNumber, 1, 7);       
        
        // select some initial values 
        choiceBox.getSelectionModel().select("Mr");
        id.setText("pn");
        
        //Submit button
        Button submit = new Button("Submit");
        submit.setOnAction((t) ->
        {
            try
            {
            // create employee, and link [store <-> employee]
             
                ID anID = new ID(id.getText());
                Employee employee = new Employee
                (
                    new Name(choiceBox.getValue().toLowerCase(), 
                        firstName.getText(), secondName.getText()),
                    anID,
                    dateOfBirth.getValue(),
                    startOfEmployment.getValue(),
                    new EMail(eMail.getText()),
                    new PhoneNumber(mobileNumber.getText()),
                    aStore 
                ); 
                                
            
                // add employee to the database and system
                aFactory.addEmployee(employee, aStore);
            }
            catch (Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        });
        
        GridPane.setConstraints(submit, 1, 8);  
        
        //add all elements to the layout
        gridPane.getChildren().addAll
        (       
            title, firstNameLabel, secondNameLabel, idLabel, 
            dateOfBirthLabel, startOfEmploymentLabel, eMailLabel, 
            mobileNumberLabel, choiceBox, firstName, secondName,
            id, dateOfBirth, startOfEmployment, eMail, mobileNumber, submit
        );
    }
    
    private void editEmployee(Store aStore, EmployeeFactory aFactory, Map<ID, Employee> employees)
    {
        //clear screen
        clearScreen();
        // add layout if it has not allready been added to the main layout
        if(!mainLayout.getChildren().contains(hbox)) mainLayout.getChildren().add(hbox);        
        if(!mainLayout.getChildren().contains(gridPane)) mainLayout.getChildren().add(gridPane);
                       
        //create list of employees
        ListView<Employee> employeeList = new ListView<>();
        employeeList.getItems().addAll(employees.values());             
        
        //Set the labels
        Label title = new Label("Title");
        Label firstNameLabel = new Label("First name");
        Label secondNameLabel = new Label("Second name");
        Label idLabel = new Label("Employee ID");
        Label dateOfBirthLabel = new Label("Date of birth");
        Label startOfEmploymentLabel = new Label("Start of employment");
        Label eMailLabel = new Label("eMail address");
        Label mobileNumberLabel = new Label("Mobile phone number");
        
        // set the labels as the first item in every row [col][row]
        GridPane.setConstraints(title, 0, 0);
        GridPane.setConstraints(firstNameLabel, 0, 1);
        GridPane.setConstraints(secondNameLabel, 0, 2);
        GridPane.setConstraints(idLabel, 0, 3);
        GridPane.setConstraints(dateOfBirthLabel, 0, 4);
        GridPane.setConstraints(startOfEmploymentLabel, 0, 5);
        GridPane.setConstraints(eMailLabel, 0, 6);
        GridPane.setConstraints(mobileNumberLabel, 0, 7);
        
        // set the input fields
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Mr", "Mrs", "Ms", "Miss", "Mx");
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField id = new TextField();        
        DatePicker dateOfBirth = new DatePicker();
        DatePicker startOfEmployment = new DatePicker();
        TextField eMail = new TextField(); 
        TextField mobileNumber = new TextField();
                
        // set the input fields as the second item in every row
        GridPane.setConstraints(choiceBox, 1, 0);
        GridPane.setConstraints(firstName, 1, 1);
        GridPane.setConstraints(lastName, 1, 2);
        GridPane.setConstraints(id, 1, 3);
        GridPane.setConstraints(dateOfBirth, 1, 4);
        GridPane.setConstraints(startOfEmployment, 1, 5);
        GridPane.setConstraints(eMail, 1, 6);
        GridPane.setConstraints(mobileNumber, 1, 7);       
        
        //on click get employee info
        employeeList.setOnMouseClicked((t) ->
        {    
            Employee employee = employeeList.getSelectionModel().getSelectedItem();
            choiceBox.getSelectionModel().select(employee.getName().getTitle().toString());
            firstName.setText(employee.getName().getFirstName());
            lastName.setText(employee.getName().getLastName());
            id.setText(employee.getId().getID());
            dateOfBirth.setValue(employee.getDateOfBirth());
            startOfEmployment.setValue(employee.getStartOfEmployment());
            eMail.setText(employee.geteMailAddress());
            mobileNumber.setText(employee.getMobileNumber().getMobileNumber());           
        }); 
        
        //Submit button
        Button submit = new Button("Submit");
        submit.setOnAction((t) ->
        {
            String message = "";
            Employee employee = employeeList.getSelectionModel().getSelectedItem();
            
            // create employee, and link [store <-> employee]
            try
            {
                
                employee.setName(new Name(choiceBox.getValue().toLowerCase(), 
                        firstName.getText(), lastName.getText()));
                employee.setDateOfBirth(dateOfBirth.getValue());
                employee.setStartOfEmployment(startOfEmployment.getValue());
                employee.seteMailAddress(new EMail(eMail.getText()));
                employee.setMobileNumber(new PhoneNumber(mobileNumber.getText()));
                
                
                System.out.println(employee);
                
                // update db with employee data
                aFactory.updateEmployee(employee);
                
                message = "Successfully updated employee: " + employee;
                
                employeeList.refresh();
            }
            catch(IOException e)
            {
                message = e.getMessage();           
            }
            
            // Inform the user whether the operation was successful or not.
            System.out.println(message);
            
        });
        
        GridPane.setConstraints(submit, 1, 8);  
        
        //add all elements to the layouts        
        gridPane.getChildren().addAll
        (       
            title, firstNameLabel, secondNameLabel, idLabel, 
            dateOfBirthLabel, startOfEmploymentLabel, eMailLabel, 
            mobileNumberLabel, choiceBox, firstName, lastName,
            id, dateOfBirth, startOfEmployment, eMail, mobileNumber, submit
        ); 
        
        hbox.getChildren().addAll(employeeList, gridPane);
    }
    
    private void removeEmployee(Map<ID, Employee> employees, EmployeeFactory eFactory, Store aStore)
    {
        //clear screen and add secondary layout used.
        clearScreen();
        if(!mainLayout.getChildren().contains(hbox)) mainLayout.getChildren().add(hbox); 
        
        // create a list of employees
        ListView<Employee> employeeList = new ListView<>();
        employeeList.getItems().addAll(employees.values());   
        
        Button button = new Button("Remove");
        button.setOnAction((t) ->
        {
            String message = "";
            try
            {
                // get the selected employee
                Employee employee = employeeList.getSelectionModel().getSelectedItem();

                // remove from the database and instance
                eFactory.removeEmployee(employee, aStore);

                // refresh list after deletion
                employeeList.getItems().clear();
                employeeList.getItems().addAll(employees.values());
                
                message = "employee deleted successfuly " + employee;
            }
            catch(Exception e)
            {
                message = e.getMessage();
            }
            
            System.out.println(message);
        });
        
        hbox.getChildren().addAll(employeeList, button);
        
    }
    
    private void bookTrainingEvent(TrainingEventFactory tef, 
            Set<TrainingDepartment> td, Map<ID, Employee>allEmployees)
    {
        //clear screen
        clearScreen();
        // add layout if it has not allready been added to the main layout
        if(!mainLayout.getChildren().contains(hbox)) mainLayout.getChildren().add(hbox);
        
        // create the choice box
        ListView<Type> trainingChoice = new ListView<>();
        trainingChoice.getItems().addAll(Type.JuniorSalesAssistant, Type.SalesAssistant, 
                Type.QualifiedSalesAssistant, Type.SeniorSalesAssistant);
        
        //poppulate employees list
        ListView<Employee> employees = new ListView<>();
        employees.getItems().addAll(allEmployees.values());
        
        //poppulate events list
        ListView<TrainingEvent> events = new ListView<>();
        trainingChoice.setOnMouseClicked((t) ->
        {
            events.getItems().clear();
            events.getItems().addAll(tef.findTrainingEvents
                (trainingChoice.getSelectionModel().getSelectedItem(), td));
        });
        
        //book selected event
        Button submit = new Button("Book");
        submit.setOnAction((t) ->
        {
            TrainingEvent event = events.getSelectionModel().getSelectedItem();
            Employee employee = employees.getSelectionModel().getSelectedItem();
            try
            {
                //2 way link created; employee <-> event.
                
                // if there is space add employee to booking in currently 
                // running instance and database.
                if(tef.makeBooking(employee, event)){                    
                    //event.addEmployee(employee);
                    System.out.println("added");
                }
                else
                {
                    System.out.println("not added");
                }
 
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            
        });
        
        // add all elements created in this method to the secondary layout
        hbox.getChildren().addAll(employees, trainingChoice, events, submit);  
    }
    
    private void cancelTrainingEvent(Map<ID, Employee> allEmployees, TrainingEventFactory tef)
    {
        //clear screen and add the layout/s used for the use case
        clearScreen();
        if(!mainLayout.getChildren().contains(hbox)) mainLayout.getChildren().add(hbox);
        
        // populate list with employees
        ListView<Employee> employees = new ListView<>();
        employees.getItems().addAll(allEmployees.values());
        
        // create list for employees training events
        ListView<TrainingEvent> trainingEvents = new ListView<>();
        
        // populate employees training events list with training events from selected employee
        employees.setOnMouseClicked((t) ->
        {
            trainingEvents.getItems().clear();
            Set<TrainingEvent> te = 
                    employees.getSelectionModel().getSelectedItem().getTrainingEvents();
            trainingEvents.getItems().addAll(te);
        });
        
        // remove employee from event
        Button button = new Button("Cancel Event");
        button.setOnAction((t) ->
        {
            TrainingEvent event = trainingEvents.getSelectionModel().getSelectedItem();
            Employee employee = employees.getSelectionModel().getSelectedItem();
            
            // if successfuly removed from database then remove from instance
            if(tef.cancelBooking(employee, event)) {               
                // refresh training events section
                trainingEvents.getItems().clear();
                trainingEvents.getItems().addAll(employee.getTrainingEvents());
            } 
        });
        
        hbox.getChildren().addAll(employees, trainingEvents, button);
        
    }
    
    private void viewProgress(EmployeeFactory eFactory, Store aStore, Map<ID, Employee> employees)
    {
        //clear screen and add the layout/s used for the use case
        clearScreen();
        if(!mainLayout.getChildren().contains(hbox)) mainLayout.getChildren().add(hbox);
        
        Map<ID, String> overdueEmployees = eFactory.trainingProgress(aStore);
        ListView<String> overdueList = new ListView<>();
                
        overdueEmployees.forEach((t, u) ->
        {
            String listItem = employees.get(t).toString() + " is overdue for " + u;
            overdueList.getItems().add(listItem);
        });
        overdueList.setPrefWidth(400);
        
        hbox.getChildren().add(overdueList);
    }

    private MenuBar menuBar()
    {       
        Menu employeeMenu = new Menu("Employee");
        menuViewEmployee = new MenuItem("View");
        menuAddEmployee = new MenuItem("Add");
        menuEditEmployee = new MenuItem("Edit");
        menuRemoveEmployee = new MenuItem("Remove");
        employeeMenu.getItems().addAll(menuViewEmployee, menuAddEmployee, 
                menuEditEmployee, menuRemoveEmployee);
        
        Menu trainingMenu = new Menu("Training");
        menuBookTraining = new MenuItem("Book Training");
        menuCancelTraining = new MenuItem("Cancel Training");
        menuTrainingProgress = new MenuItem("Training Progress");
        trainingMenu.getItems().addAll(menuBookTraining, menuCancelTraining, menuTrainingProgress);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(employeeMenu, trainingMenu);
        
        return menuBar;
    }
    
    private void clearScreen()
    {
        hbox.getChildren().clear();
        gridPane.getChildren().clear();
    }
      
}
