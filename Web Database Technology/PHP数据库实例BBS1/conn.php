<?php
$conn=new mysqli('localhost','root','','my_db');
if($conn->connect_errno){
	die("数据库连接失败".$conn->connect_error);
}else{
	echo "<br/>";
}
