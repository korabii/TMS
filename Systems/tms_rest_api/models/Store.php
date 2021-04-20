<?php 
  class Store {
    // DB stuff
    private $conn;
    private $table = 'store';

    public $store_id;
    public $number;
    public $streetname;
    public $city;
    public $postcode;


    // Constructor with DB
    public function __construct($db) {
      $this->conn = $db;
    }

    // find training by type
    public function findStore() {

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
    
  }
  ?>