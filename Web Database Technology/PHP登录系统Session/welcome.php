<?php
if (!isset($_SESSION)) {
    session_start();
}
if (!empty($_SESSION['last_visit'])) {
    echo "Last visit time：";
    echo date("Y-m-d H:i:s", $_SESSION['last_visit']);
    echo "<br>";
    echo "Visit time(s)：" . $_SESSION['count_visit'];
} else {
    $_SESSION['count_visit'] = 0;
    echo "This is the first time you visit this page.";
}
$_SESSION['last_visit'] = time();
$_SESSION['count_visit']++;
