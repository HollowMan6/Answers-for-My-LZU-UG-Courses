<?php
$username = $_POST["username"];
$password = $_POST["password"];
if (!empty($_GET)) {
    if (isset($_GET["action"])) {
        index_action($_GET["action"]);
    }
} else if ($username == "admin" and $password == "123456") {
    echo "登陆成功,欢迎光临 | [<a href='index.php?action=del'>清除个人信息</a>]";
    if (isset($_POST["saveinfo"])) {
        setcookie("username", $username, time() + 60 * 60 * 24 * 7);
        setcookie("password", $password, time() + 60 * 60 * 24 * 7);
    }
} else
    echo "登录失败";

function index_action($param)
{
    if ($param == "del") {
        setcookie("username", "", time() - 3600);
        setcookie("password", "", time() - 3600);
        header("location:login.php");
    }
}
