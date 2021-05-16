<?php
session_start();
unset($_SESSION['valid_user']);
session_destroy();
include "func_output.php";
do_html_header("You have Logged out");
do_html_url("usercheck.php", "Back to Home Page");
do_html_footer();
