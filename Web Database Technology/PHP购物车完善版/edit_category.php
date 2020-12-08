<?php

// include function files for this application
require_once('book_sc_fns.php');
session_start();

do_html_header("Updating category");
if (check_admin_user()) {
  if (filled_out($_POST)) {
    if(update_category($_POST['catid'], $_POST['catname'])) {
      echo "<p>Category was updated.</p>";
    } else {
      echo "<p>Category could not be updated.</p>";
    }
  } else {
    echo "<p>You have not filled out the form.  Please try again.</p>";
  }
  do_html_url("admin.php", "Back to administration menu");
} else {
  echo "<p>You are not authorised to view this page.</p>";
}
do_html_footer();

?>
