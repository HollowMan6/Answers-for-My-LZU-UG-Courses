<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>BBS留言系统</title>
</head>

<body>
    <form method="post" action="insert.php">
        标题：<input type="text" name="title" required="required"><br/><br/>
        昵称：<input type="text" name="author" required="required"><br/><br/>
        内容：<textarea name="content" required="required"></textarea><br/><br/>
        <input type="submit" name="sub">
    </form>
    <?php
    require "conn.php";
    $sql = "select * from message order by ptime desc;";
    $result = $conn->query($sql);

    while ($row = $result->fetch_assoc()) {
    ?>
        <h3>标题: <?php echo $row['title']; ?></h3>
        <p>作者： <?php echo $row['author']; ?> |来自于： <?php echo $row['ip']; ?> |发表时间： <?php echo $row['ptime']; ?></p>
        <p>留言内容： <?php echo $row['content']; ?></p>
        <p><a href="edit.php?id=<?php echo $row['id']; ?>">编辑</a> | <a href="delete.php?id=<?php echo $row['id']; ?>">删除</a></p>
        <hr>
    <?php
    }
    ?>
</body>

</html>