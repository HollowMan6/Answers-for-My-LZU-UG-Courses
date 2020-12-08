<?php
 require_once('book_sc_fns.php');
 session_start();
 do_html_header("Change administrator password");
 check_admin_user();

 display_password_form();

 do_html_url("admin.php", "Back to administration menu");
 do_html_footer();
?>
