<?php

// include function files for this application
require_once('book_sc_fns.php');
session_start();

if(isset($_POST['username'])&& isset($_POST['passwd'])){
  // they have just tried logging in

    $username = $_POST['username'];
    $passwd = $_POST['passwd'];

    if (login($username, $passwd)) {
      // if they are in the database register the user id
      $_SESSION['admin_user'] = $username;

    } else {
      // unsuccessful login
      do_html_header("Problem:");
      echo "<p>You could not be logged in.<br/>
            You must be logged in to view this page.</p>";
      do_html_url('login.php', 'Login');
      do_html_footer();
      exit;
    }
}


do_html_header("Administration");
if (check_admin_user()) {
  display_admin_menu();
} else {
  echo "<p>You are not authorized to enter the administration area.</p>";
}
do_html_footer();

?>
