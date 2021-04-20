<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/Employees.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate employee object
  $employee = new Employees($db);

  // Make sure the store id has been set from the URL or exit.
  $employee->store_id = isset($_GET['store_id']) ? $_GET['store_id'] : die();
  

  // Invoke read method from employee to get all employees
  $result = $employee->trainingProgress();
  // Get row count
  $num = $result->rowCount();

  // Check if any results are returned
  if($num > 0) {
    // create an array to store employee data
    $employees_arr = array();
    // $employees_arr['data'] = array();

    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);

      $employee_item = array(
        'employee_id' => $employee_id,
        'training_level' => $training_level,
        'last_qa' => $last_qa
      );

      // Push to "data"
      array_push($employees_arr, $employee_item);
      // array_push($employees_arr['data'], $employee_item);
    }

    // Turn to JSON & output
    echo json_encode($employees_arr);

  } else {
    // No employees
    echo json_encode(
      array('message' => 'No Employees found')
    );
  }
