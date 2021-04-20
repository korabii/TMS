<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: POST');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

  include_once '../../config/Database.php';
  include_once '../../models/Employees.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog employee object
  $employee = new Employees($db);

  // Get raw employee data
  $data = json_decode(file_get_contents("php://input"));

  $employee->employee_id = $data->employee_id;

  // Create employee
  if($employee->read_single()) {
    // Create array
    $employee_arr = array(
      'employee_id' => $employee->employee_id,
      'firstname' => $employee->firstname,
      'store_id' => $employee->store_id,
    );

    // Make JSON
    print_r(json_encode($employee_arr));

  } else {
    echo json_encode(
      array('message' => 'employee Not Created')
    );
  }

