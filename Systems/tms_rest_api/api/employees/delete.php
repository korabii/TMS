<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: DELETE');
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

  // Set ID to delete
  $employee->employee_id = $data->employee_id;

  // Delete employee
  if($employee->delete()) {
    echo json_encode(
      array('message' => 'employee Deleted')
    );
  } else {
    echo json_encode(
      array('message' => 'employee Not Deleted')
    );
  }

?>

