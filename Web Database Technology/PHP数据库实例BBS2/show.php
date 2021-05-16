<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>BBS留言系统</title>
    <script type="text/javascript">
        function do_del(id) {
            //提示用户确认删除
            if (window.confirm('你确定要删除吗？')) {
                window.location.href = "delete.php?id=" + id;
            }
        }
    </script>
</head>

<body>
    <h1>留言</h1>
    <form method="post" action="insert.php">
        标题：<input type="text" name="title" required="required"><br /><br />
        昵称：<input type="text" name="author" required="required"><br /><br />
        内容：<textarea name="content" required="required"></textarea><br /><br />
        <input type="submit" name="sub">
    </form>
    <hr />
    <h1>搜索</h1>
    <form method="get" action="show.php">
        <input type="text" name="key">
        <input type="submit">
    </form>
    <?php
    require "conn.php";
    if (!empty($_GET['key'])) {
        $key = $_GET['key'];
        $k = "title like '%$key%'";
    } else {
        $k = "1";
        $key = "";
    }
    $pageSize = 3;
    $sql = "select * from message where $k";
    $result = $conn->query($sql);
    $num = $result->num_rows;
    $totalPage = ceil($num / $pageSize);
    if (!empty($_GET['page'])) {
        $page = $_GET['page'];
    } else {
        $page = 1;
    }
    if ($page >= $totalPage) {
        $page = $totalPage;
    }
    $start = ($page - 1) * $pageSize;
    $sql = "select * from message where $k order by ptime desc LIMIT $start,$pageSize";
    $result = $conn->query($sql);

    while ($row = $result->fetch_assoc()) {
    ?>
        <h3>标题: <a href="detail.php?key=<?php echo $row['title']; ?>"><?php echo $row['title']; ?></a></h3>
        <p>作者： <?php echo $row['author']; ?> |来自于： <?php echo $row['ip']; ?> |发表时间： <?php echo $row['ptime']; ?></p>
        <hr />
    <?php
    }
    ?>
    <p>
        <a href="show.php?page=1&key=<?php echo $key ?>">首页</a>
        <?php
        if ($page <= 1) {
            echo "<span> 上一页 </span>";
        } else {
            $temppage = $page - 1;
            echo "<a href='show.php?page=$temppage&key=$key'>上一页</a>";
        }
        for ($i = 1; $i <= $totalPage; $i++) {
            if ($i == $page)
                echo "<span> $i </span>";
            else
                echo "<a href='show.php?page=$i&key=$key'> $i </a>";
        }
        ?>
        <?php
        if ($page >= $totalPage) {
            echo "<span> 下一页 </span>";
        } else {
            $temppage = $page + 1;
            echo "<a href='show.php?page=$temppage&key=$key'>下一页</a>";
        }
        ?>
        <a href="show.php?page=<?php echo $totalPage; ?>&key=<?php echo $key; ?>">尾页</a>
    </p>
</body>

</html>