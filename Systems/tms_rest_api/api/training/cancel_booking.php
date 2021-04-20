<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: DELETE');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');

  include_once '../../config/Database.php';
  include_once '../../models/Training.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate blog training object
  $training = new Training($db);

  // Get raw traininged data
  $data = json_decode(file_get_contents("php://input"));

  // Set ID to delete
  $training->employee_id = $data->employee_id;
  $training->training_event_id = $data->training_event_id;

  // Delete training
  if($training->cancelBooking()) {
    echo json_encode(
      array('message' => 'training event cancelled')
    );
  } else {
    echo json_encode(
      array('message' => 'training event could not be cancelled')
    );
  }

?>

