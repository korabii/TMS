<?php 
  class Training {
    // DB stuff
    private $conn;
    private $table = 'training';

    // Post Properties
    public $employee_id;
    public $training_event_id;
    public $remaining_spaces;


    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
    }

    // Make booking
    public function makeBooking() {

      // Create query
      $query = 'INSERT IGNORE INTO ' . $this->table . ' SET employee_id = :employee_id, training_event_id = :training_event_id';
      
      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Clean data
      $this->employee_id = htmlspecialchars(strip_tags($this->employee_id));
      $this->training_event_id = htmlspecialchars(strip_tags($this->training_event_id));
      
      // Bind data
      $stmt->bindParam(':employee_id', $this->employee_id);
      $stmt->bindParam(':training_event_id', $this->training_event_id);


      // Execute query if there are free places at the event

      if($this->checkBooking() && $stmt->execute() ) {
        return true;
      }

      // Print error if something goes wrong
      printf("Error: %s.\n", $stmt->error);

      return false;
    }

    // Cancel booking
    public function cancelBooking() {

      // Create query
      $query = 'DELETE FROM ' . $this->table . ' WHERE employee_id = :employee_id AND training_event_id = :training_event_id';
      
      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Clean data
      $this->employee_id = htmlspecialchars(strip_tags($this->employee_id));
      $this->training_event_id = htmlspecialchars(strip_tags($this->training_event_id));
      
      // Bind data
      $stmt->bindParam(':employee_id', $this->employee_id);
      $stmt->bindParam(':training_event_id', $this->training_event_id); 

      // Execute query
      if($stmt->execute()) {
        return true;
      }

      // Print error if something goes wrong
      printf("Error: %s.\n", $stmt->error);

      return false;
    }

    private function checkBooking(){
      // Create query
      $query1 = 'SELECT COUNT(training_event_id) FROM training WHERE training_event_id = :training_event_id';
      $query2 = 'SELECT capacity FROM trainingdepartment INNER JOIN trainingevent ON trainingevent.td_id=trainingdepartment.td_id WHERE te_id = :training_event_id';

      // Prepare statement
      $stmt1 = $this->conn->prepare($query1);
      $stmt2 = $this->conn->prepare($query2);

      // Bind data
      $stmt1->bindParam(':training_event_id', $this->training_event_id);
      $stmt2->bindParam(':training_event_id', $this->training_event_id);

      $stmt1->execute();
      $stmt2->execute();
      
      $bookedPlaces = $stmt1->fetch()[0];
      $capacity = $stmt2->fetch()[0];

      $this->remaining_spaces = $capacity - $bookedPlaces;

      return ($this->remaining_spaces) > 0 ? True : False;

    }
    
  }
  ?>