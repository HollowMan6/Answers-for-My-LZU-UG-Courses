<?php
session_start();
require "conn.php";
include "func_output.php";
do_html_header("Log in");
check_log_in();
do_log_info("login");
do_html_url("members_only.php", "Members Only");
do_html_footer();
