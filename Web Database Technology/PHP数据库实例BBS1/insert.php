<?php
if(!empty($_POST['sub'])){
	$title=$_POST['title'];
	$author=$_POST['author'];
	$content=$_POST['content'];
	$ip=$_SERVER['REMOTE_ADDR'];
	//$ptime=time();
}
require "conn.php";
$sql="INSERT INTO `message`(`id`, `title`, `author`, `content`, `ip`, `ptime`) VALUES (NULL,'$title','$author','$content','$ip',now())";
$conn->query($sql);
header('location:show.php');
