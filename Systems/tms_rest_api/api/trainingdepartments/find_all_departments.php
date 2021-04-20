<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/TrainingDepartment.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate trainingDepartment object
  $trainingDepartment = new TrainingDepartment($db);

  // Make sure the store id has been set from the URL or exit.
  //$trainingDepartment->type = isset($_GET['type']) ? $_GET['type'] : die();

  // Invoke read method from trainingDepartment to get all trainingDepartments
  $result = $trainingDepartment->findAllDepartments();
  // Get row count
  $num = $result->rowCount();

  // Check if any results are returned
  if($num > 0) {
    // create an array to store trainingDepartment data
    $trainingDepartments_arr = array();
    // $trainingDepartments_arr['data'] = array();

    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);

      $trainingDepartment_item = array(
        'td_id' => $td_id,
        'number' => $number,
        'streetname' => $streetname,
        'city' => $city,
        'postcode' => $postcode,
        'capacity' => $capacity
      );

      // Push to "data"
      array_push($trainingDepartments_arr, $trainingDepartment_item);
      // array_push($trainingDepartments_arr['data'], $trainingDepartment_item);
    }

    // Turn to JSON & output
    echo json_encode($trainingDepartments_arr);

  } else {
    // No trainingDepartments
    echo json_encode(
      array('message' => 'No trainingDepartments found')
    );
  }

?>