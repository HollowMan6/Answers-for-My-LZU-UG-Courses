<?php
setcookie("testcookie", "My first cookie", time() + 60 * 60 * 24 * 7);
setcookie("username", "admin", time() + 60 * 60 * 24 * 30);
print_r($_COOKIE);
if (isset($_COOKIE["username"])) {
    echo "<br>欢迎" . $_COOKIE["username"] . "访问本站！";
} else {
    echo "<br>欢迎游客访问本站！";
}
