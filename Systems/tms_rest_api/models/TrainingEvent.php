<?php 
  class TrainingEvent {
    // DB stuff
    private $conn;
    private $table = 'trainingevent';

    public $te_id;
    public $date_time;
    public $type;
    public $td_id;

    public $employee_id;


    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
    }

    // find training by type
    public function findTraining() {

      // Create query
      $query = 'SELECT * FROM ' . $this->table . ' WHERE type = ? ';
      
      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->type);

      // Execute query
      $stmt->execute();

      return $stmt;
    }

    // find all training
    public function findAllTraining() {

      // Create query
      $query = 'SELECT * FROM ' . $this->table . ' WHERE 1 ';
      
      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Execute query
      $stmt->execute();

      return $stmt;
    }

    // find training by type
    public function findEmployeeTraining() {

      // Create query
      $query = 'SELECT * FROM ' . $this->table . ' WHERE te_id in (SELECT training_event_id FROM training WHERE employee_id = ?)';
      
      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      $stmt->bindParam(1, $this->employee_id);

      // Execute query
      $stmt->execute();

      return $stmt;
    }
    
  }
  ?>