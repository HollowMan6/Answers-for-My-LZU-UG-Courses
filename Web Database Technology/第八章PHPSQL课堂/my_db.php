<meta charset="utf-8" />

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
// $sql = "INSERT INTO `user`(`id`, `username`, `password`, `age`, `email`) VALUES (null,'黄蓉','123456',18,'123@123.com')";
// $sql = "UPDATE `user` SET `age`=20 WHERE `username`='黄蓉'";
// $sql = "DELETE FROM `user` WHERE `username`='黄蓉'";
// $is = $conn->query($sql);
// if ($is){
//     echo "数据修改成功<br/>";
// }
// else {
//     echo "数据修改失败<br/>";
// }

$sql = "select * from `user`";

$result = $conn->query($sql);
while ($row = $result->fetch_row()) 
{
    echo "<br/>ID: " . $row[0] . " Name: " . $row[1] . " Age: " . $row[3] . "<br/>";
}

$result = $conn->query($sql);
while ($row = $result->fetch_array()) 
{
    echo "<br/>ID: " . $row['id'] . " Name: " . $row['username'] . " Age: " . $row['age'] . "<br/>";
}

$result->free();
$conn->close();

?>