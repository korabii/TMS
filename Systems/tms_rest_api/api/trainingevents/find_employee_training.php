<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/TrainingEvent.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate trainingEvent object
  $trainingEvent = new TrainingEvent($db);

  // Make sure the store id has been set from the URL or exit.
  $trainingEvent->employee_id = isset($_GET['employee_id']) ? $_GET['employee_id'] : die();

  // Invoke read method from trainingEvent to get all trainingEvents
  $result = $trainingEvent->findEmployeeTraining();
  // Get row count
  $num = $result->rowCount();

  // Check if any results are returned
  if($num > 0) {
    // create an array to store trainingEvent data
    $trainingEvents_arr = array();
    // $trainingEvents_arr['data'] = array();

    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);

      $trainingEvent_item = array(
        'te_id' => $te_id,
        'date_time' => $date_time,
        'type' => $type,
        'td_id' => $td_id
      );

      // Push to "data"
      array_push($trainingEvents_arr, $trainingEvent_item);
      // array_push($trainingEvents_arr['data'], $trainingEvent_item);
    }

    // Turn to JSON & output
    echo json_encode($trainingEvents_arr);

  } else {
    // No trainingEvents
    echo json_encode(
      array('te_id' => 'No trainingEvents found',
            'message' => 'No trainingEvents found')
    );
  }
