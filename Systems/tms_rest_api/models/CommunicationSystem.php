<?php 
  class CommunicationSystem {
    // DB stuff
    private $conn;
    private $table = 'employee';

    public $title;
    public $firstname;
    public $lastname;
    public $email_address;
    public $mobile_number;


    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
    }

    // Get employees who have not finished the qualified assistant test on time
    public function qa() {
      
      //get date for 11 months in the past.
      $date = date('Y-m-d', strtotime('-11 month'));
      //echo("date: " . $date);

      // Create query : select all employees that have not completed or retaken the Qualified Sales Assistant test within 11 months     
      $query = 'SELECT title, firstname, lastname, email_address, mobile_number FROM ' . $this->table . ' WHERE (last_qa < "'. $date . '") OR (training_level NOT IN ("ss", "qa") AND start_of_employment < "'. $date . '")';

      //(training_level NOT IN ("ss", "qa") AND start_of_employment < "2018-10-10") OR (`last_qa` < "2018-10-10")

      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Execute query
      $stmt->execute();

      return $stmt;
    }   
    
    public function js() {
      
      //get todays date
      $date = date('Y-m-d', strtotime('-2 month'));
      //echo("date: " . $date);

      // Create query : select employees that have not completed any of the training and 
      // thier start of employment date was before 2 months from today.
      $query = 'SELECT title, firstname, lastname, email_address, mobile_number FROM ' . $this->table 
      . ' WHERE training_level NOT IN ("ss", "qa", "sa", "js") AND start_of_employment < "'. $date . '"';

      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Execute query
      $stmt->execute();

      return $stmt;
    }   

    public function sa() {
      
      //get todays date
      $date = date('Y-m-d', strtotime('-5 month'));
      //echo("date: " . $date);

      // Create query : select employees that have not completed Sales Assistant, Qualified Sales Assistant or 
      // Senior Sales Assistant test and thier start of employment date was before 5 months from today.
      $query = 'SELECT title, firstname, lastname, email_address, mobile_number FROM ' . $this->table 
      . ' WHERE training_level NOT IN ("ss", "qa", "sa") AND start_of_employment < "'. $date . '"';

      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Execute query
      $stmt->execute();

      return $stmt;
    } 

    public function ss() {
      
      //get todays date
      $date = date('Y-m-d', strtotime('-17 month'));
      //echo("date: " . $date);

      // Create query : select employees that have not completed
      // Senior Sales Assistant test and thier start of employment date was before 17 months from today.
      $query = 'SELECT title, firstname, lastname, email_address, mobile_number FROM ' . $this->table 
      . ' WHERE training_level NOT IN ("ss") AND start_of_employment < "'. $date . '"';

      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Execute query
      $stmt->execute();

      return $stmt;
    } 
    
  }

  ?>