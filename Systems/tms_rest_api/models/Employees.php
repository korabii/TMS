<?php 
  class Employees {
    // DB stuff
    private $conn;
    private $table = 'employee';

    // Post Properties
    public $employee_id;
    public $title;
    public $firstname;
    public $lastname;
    public $date_of_birth;
    public $start_of_employment;
    public $email_address;
    public $mobile_number;
    public $training_level;
    public $last_qa;
    public $store_id;


    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
    }

    // Get employees
    public function read() {
      // Create query
      $query = 'SELECT * FROM ' . $this->table . ' WHERE store_id = ? ';
      
      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->store_id);

      // Execute query
      $stmt->execute();

      return $stmt;
    }

    // Create employee
    public function create() {
          // Create query
          $query = 'INSERT INTO ' . $this->table . ' SET employee_id = :employee_id, title = :title, firstname = :firstname, lastname = :lastname,' 
          . ' date_of_birth = :date_of_birth, start_of_employment = :start_of_employment, email_address = :email_address, mobile_number = :mobile_number, ' 
          . 'training_level = :training_level, last_qa = :last_qa, store_id = :store_id';

          // Prepare statement
          $stmt = $this->conn->prepare($query);

          // Clean data
          $this->employee_id = htmlspecialchars(strip_tags($this->employee_id));
          $this->title = htmlspecialchars(strip_tags($this->title));
          $this->firstname = htmlspecialchars(strip_tags($this->firstname));
          $this->lastname = htmlspecialchars(strip_tags($this->lastname));
          $this->date_of_birth = htmlspecialchars(strip_tags($this->date_of_birth));
          $this->start_of_employment = htmlspecialchars(strip_tags($this->start_of_employment));
          $this->email_address = htmlspecialchars(strip_tags($this->email_address));
          $this->mobile_number = htmlspecialchars(strip_tags($this->mobile_number));
          $this->training_level = htmlspecialchars(strip_tags($this->training_level));
          $this->last_qa = htmlspecialchars(strip_tags($this->last_qa));
          $this->store_id = htmlspecialchars(strip_tags($this->store_id));

          // Bind data
          $stmt->bindParam(':employee_id', $this->employee_id);
          $stmt->bindParam(':title', $this->title);
          $stmt->bindParam(':firstname', $this->firstname);
          $stmt->bindParam(':lastname', $this->lastname);
          $stmt->bindParam(':date_of_birth', $this->date_of_birth);
          $stmt->bindParam(':start_of_employment', $this->start_of_employment);
          $stmt->bindParam(':email_address', $this->email_address);
          $stmt->bindParam(':mobile_number', $this->mobile_number);
          $stmt->bindParam(':training_level', $this->training_level);
          $stmt->bindParam(':last_qa', $this->last_qa);
          $stmt->bindParam(':store_id', $this->store_id);

          // Execute query
          if($stmt->execute()) {
            return true;
      }

      // Print error if something goes wrong
      printf("Error: %s.\n", $stmt->error);

      return false;
    }

    // Update Employee
    public function update() {
          // Create query
          $query = 'UPDATE ' . $this->table . ' SET title = :title, firstname = :firstname, lastname = :lastname, ' 
          . 'date_of_birth = :date_of_birth, start_of_employment = :start_of_employment, email_address = :email_address, ' 
          . 'mobile_number = :mobile_number WHERE employee_id = :employee_id';

          // Prepare statement
          $stmt = $this->conn->prepare($query);

          // Clean data
          $this->employee_id = htmlspecialchars(strip_tags($this->employee_id));
          $this->title = htmlspecialchars(strip_tags($this->title));
          $this->firstname = htmlspecialchars(strip_tags($this->firstname));
          $this->lastname = htmlspecialchars(strip_tags($this->lastname));
          $this->date_of_birth = htmlspecialchars(strip_tags($this->date_of_birth));
          $this->start_of_employment = htmlspecialchars(strip_tags($this->start_of_employment));
          $this->email_address = htmlspecialchars(strip_tags($this->email_address));
          $this->mobile_number = htmlspecialchars(strip_tags($this->mobile_number));

          // Bind data
          $stmt->bindParam(':employee_id', $this->employee_id);
          $stmt->bindParam(':title', $this->title);
          $stmt->bindParam(':firstname', $this->firstname);
          $stmt->bindParam(':lastname', $this->lastname);
          $stmt->bindParam(':date_of_birth', $this->date_of_birth);
          $stmt->bindParam(':start_of_employment', $this->start_of_employment);
          $stmt->bindParam(':email_address', $this->email_address);
          $stmt->bindParam(':mobile_number', $this->mobile_number);

          // Execute query
          if($stmt->execute()) {
            return true;
          }

          // Print error if something goes wrong
          printf("Error: %s.\n", $stmt->error);

          return false;
    }

    // Get employees trainig progress
    public function trainingProgress() {
      // Create query
      $query = 'SELECT employee_id, training_level, last_qa FROM ' . $this->table . ' WHERE store_id = ? ';
      
      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->store_id);

      // Execute query
      $stmt->execute();

      return $stmt;
    }

    // Delete employee
    public function delete() {
          // Create query
          $query = 'DELETE FROM ' . $this->table . ' WHERE employee_id = :employee_id';

          // Prepare statement
          $stmt = $this->conn->prepare($query);

          // Clean data
          $this->employee_id = htmlspecialchars(strip_tags($this->employee_id));

          // Bind data
          $stmt->bindParam(':employee_id', $this->employee_id);

          // Execute query
          if($stmt->execute()) {
            return true;
          }

          // Print error if something goes wrong
          printf("Error: %s.\n", $stmt->error);

          return false;
    }
    
  }
  ?>