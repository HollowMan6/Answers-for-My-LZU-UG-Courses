<?php
include "func_output.php";
do_html_header("Members Only");
session_start();
do_log_info("members");
do_html_url("usercheck.php", "Back to Home Page");
do_html_footer();
