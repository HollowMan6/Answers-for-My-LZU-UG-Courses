<?php
function process_card($card_details) {
  // connect to payment gateway or
  // use gpg to encrypt and mail or
  // store in DB if you really want to

  return true;
}

function insert_order($order_details) {
  // extract order_details out as variables
  extract($order_details);

  // set shipping address same as address
  if((!$ship_name) && (!$ship_address) && (!$ship_city) && (!$ship_state) && (!$ship_zip) && (!$ship_country)) {
    $ship_name = $name;
    $ship_address = $address;
    $ship_city = $city;
    $ship_state = $state;
    $ship_zip = $zip;
    $ship_country = $country;
  }

  $conn = db_connect();

  // we want to insert the order as a transaction
  // start one by turning off autocommit
  $conn->autocommit(FALSE);

  // insert customer address
  $query = "select customerid from customers where
            name = '".$conn->real_escape_string($name) ."' and address = '". $conn->real_escape_string($address)."'
            and city = '".$conn->real_escape_string($city)."' and state = '".$conn->real_escape_string($state)."'
            and zip = '".$conn->real_escape_string($zip)."' and country = '".$conn->real_escape_string($country)."'";

  $result = $conn->query($query);

  if($result->num_rows>0) {
    $customer = $result->fetch_object();
    $customerid = $customer->customerid;
  } else {
    $query = "insert into customers values
            ('', '" . $conn->real_escape_string($name) ."','" . $conn->real_escape_string($address) . 
            "','". $conn->real_escape_string($city) ."','". $conn->real_escape_string($state) . 
            "','". $conn->real_escape_string($zip) ."','". $conn->real_escape_string($country)."')";
    $result = $conn->query($query);

    if (!$result) {
       return false;
    }
  }

  $customerid = $conn->insert_id;

  $date = date("Y-m-d");

  $query = "insert into orders values
            ('', '". $conn->real_escape_string($customerid) . "', '". $conn->real_escape_string($_SESSION['total_price']) . 
             "', '". $conn->real_escape_string($date) ."', 'PARTIAL',
             '" . $conn->real_escape_string($ship_name) . "', '" . $conn->real_escape_string($ship_address) . 
             "', '". $conn->real_escape_string($ship_city)."', '" . $conn->real_escape_string($ship_state) ."',
             '". $conn->real_escape_string($ship_zip) . "', '". $conn->real_escape_string($ship_country)."')";

  $result = $conn->query($query);
  if (!$result) {
    return false;
  }

  $query = "select orderid from orders where
               customerid = '". $conn->real_escape_string($customerid)."' and
               amount > (".(float)$_SESSION['total_price'] ."-.001) and
               amount < (". (float)$_SESSION['total_price']."+.001) and
               date = '".$conn->real_escape_string($date)."' and
               order_status = 'PARTIAL' and
               ship_name = '".$conn->real_escape_string($ship_name)."' and
               ship_address = '".$conn->real_escape_string($ship_address)."' and
               ship_city = '".$conn->real_escape_string($ship_city)."' and
               ship_state = '".$conn->real_escape_string($ship_state)."' and
               ship_zip = '".$conn->real_escape_string($ship_zip)."' and
               ship_country = '".$conn->real_escape_string($ship_country)."'";

  $result = $conn->query($query);

  if($result->num_rows>0) {
    $order = $result->fetch_object();
    $orderid = $order->orderid;
  } else {
    return false;
  }

  // insert each book
  foreach($_SESSION['cart'] as $isbn => $quantity) {
    $detail = get_book_details($isbn);
    $query = "delete from order_items where
              orderid = '". $conn->real_escape_string($orderid)."' and isbn = '". $conn->real_escape_string($isbn)."'";
    $result = $conn->query($query);
    $query = "insert into order_items values
              ('". $conn->real_escape_string($orderid) ."', '". $conn->real_escape_string($isbn) . 
              "', ". $conn->real_escape_string($detail['price']) .", " . $conn->real_escape_string($quantity). ")";
    $result = $conn->query($query);
    if(!$result) {
      return false;
    }
  }

  // end transaction
  $conn->commit();
  $conn->autocommit(TRUE);

  return $orderid;
}

?>
