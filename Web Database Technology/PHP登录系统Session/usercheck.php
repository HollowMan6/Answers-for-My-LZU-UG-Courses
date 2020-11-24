<?php
session_start();
if (isset($_POST['userid']) and isset($_POST['password'])) {
    $userid = $_POST['userid'];
    $password = $_POST['password'];
    require "conn.php";
}
include "func_output.php";
do_html_header("Log in");
if (isset($_SESSION['valid_user'])) {
    do_log_in($_SESSION['valid_user']);
} else {
    if (isset($_POST['userid'])) {
        echo "<p>Could not log you in.</p>";
    } else {
        echo "<p>You are not logged in.</p>";
    }
    display_login_form();
}
do_html_url("members_only.php", "Members Only");
do_html_footer();
