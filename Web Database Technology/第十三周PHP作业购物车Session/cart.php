<?php
include "func_output.php";
do_html_header("Shopping Cart");
session_start();
do_log_info("cart");
do_html_url("usercheck.php", "Back to Shopping Page");
do_html_footer();
