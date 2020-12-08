<?php

// include function files for this application
require_once('book_sc_fns.php');
session_start();

do_html_header("Edit book details");
if (check_admin_user()) {
  if ($book = get_book_details($_GET['isbn'])) {
    display_book_form($book);
  } else {
    echo "<p>Could not retrieve book details.</p>";
  }
  do_html_url("admin.php", "Back to administration menu");
} else {
  echo "<p>You are not authorized to enter the administration area.</p>";
}
do_html_footer();

?>
