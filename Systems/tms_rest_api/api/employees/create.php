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

  // Instantiate employee object
  $employee = new Employees($db);

  // Get raw employee data
  $data = json_decode(file_get_contents("php://input"));

  $employee->employee_id = $data->employee_id;
  $employee->title = $data->title;
  $employee->firstname = $data->firstname;
  $employee->lastname = $data->lastname;
  $employee->date_of_birth = $data->date_of_birth;
  $employee->start_of_employment = $data->start_of_employment;
  $employee->email_address = $data->email_address;
  $employee->mobile_number = $data->mobile_number;
  $employee->training_level = $data->training_level;
  $employee->last_qa = $data->last_qa;
  $employee->store_id = $data->store_id;

  // Create employee
  if($employee->create()) {
    echo json_encode(
      array('message' => 'employee Created')
    );
  } else {
    echo json_encode(
      array('message' => 'employee Not Created')
    );
  }

