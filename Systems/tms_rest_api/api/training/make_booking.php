<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: POST');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

  include_once '../../config/Database.php';
  include_once '../../models/Training.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate employee object
  $training = new Training($db);

  // Get raw employee data
  $data = json_decode(file_get_contents("php://input"));

  $training->employee_id = $data->employee_id;
  $training->training_event_id = $data->training_event_id;


  // Create employee
  if($training->makeBooking()) {
    echo json_encode(
      array('message' => 'training booked')
    );
  } else {
    echo json_encode(
      array('message' => 'training could not be booked')
    );
  }

?>