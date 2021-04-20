<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');

  include_once '../../config/Database.php';
  include_once '../../models/Store.php';

  // Instantiate DB & connect
  $database = new Database();
  $db = $database->connect();

  // Instantiate store object
  $store = new Store($db);

  // Make sure the store id has been set from the URL or exit.
  $store->store_id = isset($_GET['store_id']) ? $_GET['store_id'] : die();

  // Invoke read method from store to get all stores
  $result = $store->findStore();
  // Get row count
  $num = $result->rowCount();

  // Check if any results are returned
  if($num > 0) {
    // create an array to store store data
    $stores_arr = array();
    // $stores_arr['data'] = array();

    while($row = $result->fetch(PDO::FETCH_ASSOC)) {
      extract($row);

      $store_item = array(
        'store_id' => $store_id,
        'number' => $number,
        'streetname' => $streetname,
        'city' => $city,
        'postcode' => $postcode
      );

      // Push to "data"
      array_push($stores_arr, $store_item);
      // array_push($stores_arr['data'], $store_item);
    }

    // Turn to JSON & output
    echo json_encode($stores_arr);

  } else {
    // No stores
    echo json_encode(
      array('message' => 'No stores found')
    );
  }

?>