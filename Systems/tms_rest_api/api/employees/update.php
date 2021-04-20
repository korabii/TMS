<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: PUT');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

  include_once '../../config/Database.php';
  include_once '../../models/Employees.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog employee object
  $employee = new Employees($db);

  // Get raw employeeed data
  $data = json_decode(file_get_contents("php://input"));

  // Set ID to update
  $employee->employee_id = $data->employee_id;
  $employee->title = $data->title;
  $employee->firstname = $data->firstname;
  $employee->lastname = $data->lastname;
  $employee->date_of_birth = $data->date_of_birth;
  $employee->start_of_employment = $data->start_of_employment;
  $employee->email_address = $data->email_address;
  $employee->mobile_number = $data->mobile_number;

  // Update employee
  if($employee->update()) {
    echo json_encode(
      array('message' => 'employee Updated')
    );
  } else {
    echo json_encode(
      array('message' => 'employee Not Updated')
    );
  }

