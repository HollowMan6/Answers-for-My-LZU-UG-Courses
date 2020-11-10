<?php
require "conn.php";
if (!empty($_GET['key'])) {
    $key = $_GET['key'];
    $k = "title = '$key'";
} else {
    $k = "1";
    $key = "";
}
$sql = "select * from message where $k";
$result = $conn->query($sql);
$row = $result->fetch_assoc()
?>
<h3>标题: <?php echo $row['title']; ?></h3>
<p>作者： <?php echo $row['author']; ?> |来自于： <?php echo $row['ip']; ?> |发表时间： <?php echo $row['ptime']; ?></p>
<p>留言内容： <?php echo $row['content']; ?></p>
<p>
    <a href="edit.php?id=<?php echo $row['id']; ?>">编辑</a> |
    <a href="javascript:do_del(<?php echo $row['id']; ?>)">删除</a>
</p>
<hr />