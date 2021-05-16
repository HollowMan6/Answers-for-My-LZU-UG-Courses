<?php
session_start();
require "conn.php";
include "func_output.php";
do_html_header("Shopping");
check_log_in();
do_log_info("shopping");
do_html_url("cart.php", "Shopping Cart");
do_html_footer();
