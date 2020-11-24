<?php
$conn=new mysqli('localhost','root','','my_db');
if($conn->connect_errno){
	die("数据库连接失败".$conn->connect_error);
}else{
	echo "<br/>";
}
$sql = "select * from user where username='$userid' and password='$password'";
$result = $conn->query($sql);
if ($result->num_rows) {
	$_SESSION['valid_user'] = $userid;
}
$conn->close();
