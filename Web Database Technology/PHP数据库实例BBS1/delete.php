<?php

if(!empty($_GET)){
    $id=$_GET['id'];
    require "conn.php";
    $sql = "DELETE FROM `message` WHERE id = $id";
    $result=$conn->query($sql);
    if($result){
        header("location:show.php");
    }
    else{
        echo "删除失败";
    }
}

?>