<?php 
  class TrainingDepartment {
    // DB stuff
    private $conn;
    private $table = 'trainingdepartment';

    public $td_id;
    public $number;
    public $streetname;
    public $city;
    public $postcode;
    public $capacity;


    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
    }

    // find training by type
    public function findAllDepartments() {

      // Create query
      $query = 'SELECT * FROM ' . $this->table . ' WHERE capacity > 0 ';
      
      // Prepare statement
      $stmt = $this->conn->prepare($query);

      // Bind ID
      //$stmt->bindParam(1, $this->type);

      // Execute query
      $stmt->execute();

      return $stmt;
    }
    
  }
  ?>