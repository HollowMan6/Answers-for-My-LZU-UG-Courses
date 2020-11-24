<?php
include "func_output.php";
do_html_header("Members Only");
session_start();
if (isset($_SESSION['valid_user'])) {
    do_log_in($_SESSION['valid_user']);
    echo "Members Only Page........<br/>";
    include "welcome.php";
} else {
    echo "<p>You are not logged in, couldn't visit this page.</p>";
}
do_html_url("usercheck.php", "Back to Home Page");
do_html_footer();
