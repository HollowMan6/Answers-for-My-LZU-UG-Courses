<?php

if(!empty($_GET)){
    $id=$_GET['id'];
    require "conn.php";
    $sql = "select * from message where id = $id";
    $result=$conn->query($sql);
    $row=$result->fetch_assoc();
}

?>
<form method="post" action="edit.php">
    标题：<input type="text" name="title" value="<?php echo $row['title'];?>"><br>
    <input type="hidden" name="id" value="<?php echo $row['id'];?>">
    昵称：<input type="text" name="author" value="<?php echo $row['author'];?>"><br>
    内容：<textarea name="content"><?php echo $row['content'];?></textarea><br>
    <input type="submit" name="sub">
</form>

<?php

if(!empty($_POST['sub'])){
	$title=$_POST['title'];
	$author=$_POST['author'];
    $content=$_POST['content'];
    $id=$_POST['id'];
    $ip=$_SERVER['REMOTE_ADDR'];
    require "conn.php";
    $sql="UPDATE `message` SET `title`='$title',`author`='$author',`content`='$content',`ip`='$ip',`ptime`=now() WHERE id=$id";
    $is=$conn->query($sql);
    if($is){
        header("location:show.php");
    }
    else{
        echo "修改失败";
    }
}

?>