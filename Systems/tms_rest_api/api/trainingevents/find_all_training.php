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

  // Invoke read method from trainingEvent to get all trainingEvents
  $result = $trainingEvent->findTraining();
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
      array('message' => 'No trainingEvents found')
    );
  }
