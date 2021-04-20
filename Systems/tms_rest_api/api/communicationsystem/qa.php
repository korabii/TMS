<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/CommunicationSystem.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate commuincationsystem object
  $communicationsystem = new CommunicationSystem($db);
  

  // Invoke method from commuincationsystem
  $result = $communicationsystem->qa();
  // Get row count
  $num = $result->rowCount();

  // Check if any results are returned
  if($num > 0) {
    // create an array to store commuincationsystem data
    $communicationsystems_arr = array();
    // $commuincationsystems_arr['data'] = array();

    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);

      $communicationsystem_item = array(
        'title' => $title,
        'firstname' => $firstname,
        'lastname' => $lastname,
        'email_address' => $email_address,
        'mobile_number' => $mobile_number,
      );

      // Push to "data"
      array_push($communicationsystems_arr, $communicationsystem_item);
    }

    // Turn to JSON & output
    echo json_encode($communicationsystems_arr);

  } else {
    // No results
    echo json_encode(
      array('message' => 'No results found')
    );
  }

  ?>
