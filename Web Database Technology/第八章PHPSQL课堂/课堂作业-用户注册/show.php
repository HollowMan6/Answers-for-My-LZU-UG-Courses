<html>

<head>
    <title>用户注册</title>
    <meta charset="utf-8">
    <style>
    div{
        width: 400px;
        height: 100px;
        position: absolute;
        left: 42%;
        top: 15%;
        margin: -20px 0 0 -20px;
    }
    </style>
</head>

<body>
<?php

header("Content-type: text/html; charset='utf-8'");
$host = "localhost";
$user = "root";
$password = "";
$database = "my_db";

$conn = @new mysqli($host, $user, $password, $database);
if ($conn->connect_errno) {
    die("数据库连接失败，原因：" . $conn->connect_error);
} else {
    echo "数据库连接成功<br/>";
}
echo "继续执行操作<br/>";

$conn->query("set names utf8");
//设置数据库接受发送数据编码

//增改删

$sql = "INSERT INTO `user`(`id`, `username`, `password`, `age`, `email`) VALUES (null, '{$_POST['username']}', '{$_POST['password']}', '{$_POST['age']}', '{$_POST['email']}')";

// $sql = "UPDATE `user` SET `age`=20 WHERE `username`='黄蓉'";
// $sql = "DELETE FROM `user` WHERE `username`='黄蓉'";
$is = $conn->query($sql);
if ($is){
    echo "数据增加成功<br/>";
}
else {
    echo "数据增加失败<br/>";
}

$conn->close();

?>
</body>

</html>