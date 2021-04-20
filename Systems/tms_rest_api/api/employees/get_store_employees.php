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

  $employee->store_id = $data->store_id;

  // Create employee
  if($employee->get_store_employees()) {
    // Create array
    $employee_arr = array(
      'employee_id' => $employee->employee_id,
      'name' => $employee->name,
      'date_of_birth' => $employee->date_of_birth,
      'start_of_employment' => $employee->start_of_employment,
      'email_address' => $employee->email_address,
      'mobile_number' => $employee->mobile_number,
      'store_id' => $employee->store_id,
    );

    // Make JSON
    print_r(json_encode($employee_arr));

  } else {
    echo json_encode(
      array('message' => 'employee Not Created')
    );
  }

