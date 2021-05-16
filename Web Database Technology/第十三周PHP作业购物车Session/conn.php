<?php
function connect_database()
{
	$conn = new mysqli('localhost', 'root', '', 'my_db');
	if ($conn->connect_errno) {
		die("数据库连接失败" . $conn->connect_error);
	} else {
		return $conn;
	}
}
function check_log_in()
{
	if (isset($_POST['userid']) and isset($_POST['password'])) {
		$userid = $_POST['userid'];
		$password = $_POST['password'];
		$conn = connect_database();
		$sql = "select * from user where username='$userid' and password='$password'";
		$result = $conn->query($sql);
		if ($result->num_rows) {
			$_SESSION['valid_user'] = $userid;
		}
		$conn->close();
	}
}

